package com.wistron.springboot.springbootlearn.service.user;

import com.wistron.springboot.springbootlearn.dao.bonusEventLog.BonusEventLogMapper;
import com.wistron.springboot.springbootlearn.dao.user.UserMapper;
import com.wistron.springboot.springbootlearn.domain.dto.message.UserAddBonusMsgDTO;
import com.wistron.springboot.springbootlearn.domain.entity.bonusEventLog.BonusEventLog;
import com.wistron.springboot.springbootlearn.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
    private final BonusEventLogMapper bonusEventLogMapper;

    public User findById(Integer id) {
        log.info("我被请求了！！！——————————————————————————————");
        return this.userMapper.selectByPrimaryKey(id);
    }


    @Transactional(rollbackFor = Exception.class)
    public void addBonus(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        // 当收到消息时 执行的业务
        // 1. 为用户加积分
        Integer userId = userAddBonusMsgDTO.getUserId();
        User user = this.userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus() + userAddBonusMsgDTO.getBonus());
        this.userMapper.updateByPrimaryKey(user);

        // 2. 记录日志到bonus_event_log表里面
        this.bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(user.getBonus())
                        .event("CONTRIBUTE")
                        .createTime(new Date())
                        .description("投稿加积分..")
                        .build()
        );
        log.info("积分添加完毕");
    }
}
