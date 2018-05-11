package com.iflytek.message.dao;

import com.iflytek.message.entity.TMsg;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author llchen12
 * @since 2018-05-08
 */
public interface TMsgMapper extends BaseMapper<TMsg> {

    TMsg findByMsgId(@Param("msgId") String msgId);

    int updateStatus(@Param("id") Integer id,@Param("status") Integer status);

    int updateProducerTry(@Param("id") Integer id,@Param("ptry") Integer ptry);

    int updateConsumerTry(@Param("id") Integer id,@Param("ctry") Integer ctry);
}
