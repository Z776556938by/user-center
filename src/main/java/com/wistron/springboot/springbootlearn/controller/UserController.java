package com.wistron.springboot.springbootlearn.controller;

import com.wistron.springboot.springbootlearn.domain.entity.user.User;
import com.wistron.springboot.springbootlearn.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        return this.userService.findById(id);
    }

    @GetMapping("/q")
    public User query(User user) {
        return user;
    }

    //    @GetMapping("/get")
//    public List<User> getAllUserTest() {
//        User user = User.builder()
//                .avatarUrl("xxx")
//                .bonus(100)
//                .createTime(new Date())
//                .updateTime(new Date())
//                .wxId("23")
//                .wxNickname("23")
//                .build();
//         this.userMapper.insert(user);
//         return this.userMapper.selectAll();
//    }
}