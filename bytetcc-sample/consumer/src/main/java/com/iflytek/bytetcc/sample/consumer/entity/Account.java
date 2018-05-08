package com.iflytek.bytetcc.sample.consumer.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author llchen12
 * @date 2018/5/8
 */
@Data
@TableName("tb_account_two")
public class Account implements Serializable {

    @TableId(type = IdType.INPUT)
    private String id;
    private Double amount;
    private Double frozen;
}
