package com.iflytek.message.sample.producer.client;

import com.iflytek.message.api.MessageClient;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author llchen12
 * @date 2018/5/9
 */
@FeignClient(value = "relible-message")
public interface MsgClient extends MessageClient {
}
