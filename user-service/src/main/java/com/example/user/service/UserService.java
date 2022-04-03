package com.example.user.service;

import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import com.example.user.vo.Department;
import com.example.user.vo.UserWithDeptVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.services.department.url}")
    private String deptServiceUrl;

    public User saveUser(User user) {
        log.info("Inside UserService:saveUser");
        return userRepository.save(user);
    }

    public User findUserById(Long userId) {
        log.info("Inside UserService:findUserById");
        return userRepository.findUserByUserId(userId);
    }

    public UserWithDeptVO getUserWithDepartment(Long userId) {
        log.info("Inside UserService:getUserWithDepartment");
        UserWithDeptVO userWithDept = new UserWithDeptVO();
        User user = userRepository.findUserByUserId(userId);

        Department department = restTemplate.getForObject(deptServiceUrl+user.getDepartmentId(), Department.class);
        userWithDept.setUser(user);
        userWithDept.setDepartment(department);

        return userWithDept;
    }
}
