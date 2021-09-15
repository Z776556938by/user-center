package com.wistron.springboot.springbootlearn.service.user;

import com.wistron.springboot.springbootlearn.dao.user.UserMapper;
import com.wistron.springboot.springbootlearn.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author K21064736
 * @Description TODO
 * @Date 10:32 2021/8/24
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserMapper userMapper;

    public User findById(Integer id) {
        log.info("我被请求了！！！——————————————————————————————");
        return this.userMapper.selectByPrimaryKey(id);
    }
}
