package com.wq.springboot.mqtt;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * @Author: wq
 * @Desc:
 * @Date: 2020/2/5 13:38
 * @Version 1.0
 */
@Slf4j
@Configuration
@Data
@ConfigurationProperties(prefix = "mqtt")
public class MQTTConfiguration {


    private String topics;

    private String hostUrl;

    private String username;

    private String password;

    private String clientId;

    private int completionTimeout;

    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setKeepAliveInterval(2);
        mqttConnectOptions.setMaxInflight(100000000);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{hostUrl});
        mqttConnectOptions.setConnectionTimeout(completionTimeout);
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }


    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        //clientId使用随机数产生
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(RandomUtil.randomString(6), mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(topics);
        messageHandler.setDefaultRetained(false);
        return messageHandler;
    }


    @Bean
    public MessageProducer inbound() {
        String[] inboundTopics = topics.split(",");
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(RandomUtil.randomString(6), mqttClientFactory(), inboundTopics);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }


    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            //消息消费
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println(message.getPayload().toString());
                String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
                String type = topic.substring(topic.lastIndexOf("/") + 1);
                if ("livepage/wait".equalsIgnoreCase(topic)) {
                    System.out.println("wait,fuckXX," + message.getPayload().toString());
                } else if ("demo_01".equalsIgnoreCase(topic)) {
                    System.out.println("demo_01,fuckXX," + message.getPayload().toString());
                }
            }
        };

        //return message -> log.info((String) message.getPayload());
    }
}

