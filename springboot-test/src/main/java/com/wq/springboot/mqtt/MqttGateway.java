package com.wq.springboot.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @Author: wq
 * @Desc:
 * @Date: 2020/2/6 9:50
 * @Version 1.0
 */

@Component
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {
    void sendToMqtt(String data,@Header(MqttHeaders.TOPIC) String topic);
}
