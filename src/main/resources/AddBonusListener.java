import com.wistron.springboot.springbootlearn.dao.bonusEventLog.BonusEventLogMapper;
import com.wistron.springboot.springbootlearn.dao.user.UserMapper;
import com.wistron.springboot.springbootlearn.domain.dto.message.UserAddBonusMsgDTO;
import com.wistron.springboot.springbootlearn.domain.entity.bonusEventLog.BonusEventLog;
import com.wistron.springboot.springbootlearn.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RocketMQMessageListener(consumerGroup = "consumer-group", topic = "add-bonus")  // 与生产者消息topic对应获取
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {
    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    @Override
    public void onMessage(UserAddBonusMsgDTO userAddBonusMsgDTO) {
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
