package com.iflytek.rocketmqorder;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class RocketmqOrderApplicationTests {

    @Test
    public void testProducer() throws Exception{


        //声明并初始化一个producer
        //需要一个producer group名字作为构造方法的参数，这里为producer1
        DefaultMQProducer producer = new DefaultMQProducer("producer1");

        //设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
        //NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
        producer.setNamesrvAddr("47.98.161.251:9876");
        //producer.setVipChannelEnabled(false);
        //调用start()方法启动一个producer实例
        producer.start();

        //发送10条消息到Topic为TopicTest，tag为TagA，消息内容为“Hello RocketMQ”拼接上i的值
        for (int i = 0; i < 50; i++) {
            try {
                Message msg = new Message("TopicTest",// topic
                        "TagA",// tag
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)// body
                );

                //调用producer的send()方法发送消息
                //这里调用的是同步的方式，所以会有返回结果
                SendResult sendResult = producer.send(msg);

                //打印返回结果，可以看到消息发送的状态以及一些相关信息
                System.out.println(sendResult);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }

        //发送完消息之后，调用shutdown()方法关闭producer
        producer.shutdown();

//        /**
//         * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
//         * 注意：ProducerGroupName需要由应用来保证唯一<br>
//         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
//         * 因为服务器会回查这个Group下的任意一个Producer
//         */
//        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
//        producer.setNamesrvAddr("47.98.161.251:9876");
//        producer.setInstanceName("Producer");
//        producer.setVipChannelEnabled(false);
//
//        /**
//         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
//         * 注意：切记不可以在每次发送消息时，都调用start方法
//         */
//        producer.start();
//
//        /**
//         * 下面这段代码表明一个Producer对象可以发送多个topic，多个tag的消息。
//         * 注意：send方法是同步调用，只要不抛异常就标识成功。但是发送成功也可会有多种状态，<br>
//         * 例如消息写入Master成功，但是Slave不成功，这种情况消息属于成功，但是对于个别应用如果对消息可靠性要求极高，<br>
//         * 需要对这种情况做处理。另外，消息可能会存在发送失败的情况，失败重试由应用来处理。
//         */
//        for (int i = 0; i < 100; i++) {
//            try {
//                {
//                    Message msg = new Message("TopicTest1",// topic
//                            "TagA",// tag
//                            "OrderID001",// key
//                            ("Hello MetaQ").getBytes());// body
//                    SendResult sendResult = producer.send(msg);
//                    System.out.println(sendResult);
//                }
//
//                {
//                    Message msg = new Message("TopicTest2",// topic
//                            "TagB",// tag
//                            "OrderID0034",// key
//                            ("Hello MetaQ").getBytes());// body
//                    SendResult sendResult = producer.send(msg);
//                    System.out.println(sendResult);
//                }
//
//                {
//                    Message msg = new Message("TopicTest3",// topic
//                            "TagC",// tag
//                            "OrderID061",// key
//                            ("Hello MetaQ").getBytes());// body
//                    SendResult sendResult = producer.send(msg);
//                    System.out.println(sendResult);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            TimeUnit.MILLISECONDS.sleep(500);
//        }
//
//        /**
//         * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己
//         * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
//         */
//        producer.shutdown();
    }


    @Test
    public void testConsumer() throws Exception{


        //声明并初始化一个consumer
        //需要一个consumer group名字作为构造方法的参数，这里为consumer1
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer1");

        //同样也要设置NameServer地址
        consumer.setNamesrvAddr("47.98.161.251:9876");

        //这里设置的是一个consumer的消费策略
        //CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
        //CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
        //CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        //设置consumer所订阅的Topic和Tag，*代表全部的Tag
        consumer.subscribe("TopicTest", "*");

        //设置一个Listener，主要进行消息的逻辑处理
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {

                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);

                //返回消费状态
                //CONSUME_SUCCESS 消费成功
                //RECONSUME_LATER 消费失败，需要稍后重新消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //调用start()方法启动consumer
        consumer.start();

        System.out.println("Consumer Started.");
        Thread.sleep(10000);

//        /**
//         * 当前例子是PushConsumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。<br>
//         * 但是实际PushConsumer内部是使用长轮询Pull方式从MetaQ服务器拉消息，然后再回调用户Listener方法<br>
//         */
//            /**
//             * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
//             * 注意：ConsumerGroupName需要由应用来保证唯一
//             */
//            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
//                    "ConsumerGroupName");
//            consumer.setNamesrvAddr("47.98.161.251:9876");
//            consumer.setInstanceName("Consumber");
//
//            /**
//             * 订阅指定topic下tags分别等于TagA或TagC或TagD
//             */
//            consumer.subscribe("TopicTest1", "TagA || TagB || TagC");
//            /**
//             * 订阅指定topic下所有消息<br>
//             * 注意：一个consumer对象可以订阅多个topic
//             */
//            //consumer.subscribe("TopicTest2", "*");
//
//            consumer.registerMessageListener(new MessageListenerConcurrently() {
//
//                /**
//                 * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
//                 */
//                @Override
//                public ConsumeConcurrentlyStatus consumeMessage(
//                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                    System.out.println(Thread.currentThread().getName()
//                            + " Receive New Messages: " + msgs.size());
//
//                    MessageExt msg = msgs.get(0);
//                    if (msg.getTopic().equals("TopicTest1")) {
//                        // 执行TopicTest1的消费逻辑
//                        if (msg.getTags() != null && msg.getTags().equals("TagA")) {
//                            // 执行TagA的消费
//                            System.out.println(new String(msg.getBody()));
//                        } else if (msg.getTags() != null
//                                && msg.getTags().equals("TagB")) {
//                            // 执行TagC的消费
//                        } else if (msg.getTags() != null
//                                && msg.getTags().equals("TagC")) {
//                            // 执行TagD的消费
//                        }
//                    } else if (msg.getTopic().equals("TopicTest2")) {
//                        System.out.println(new String(msg.getBody()));
//                    }
//
//                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                }
//            });
//
//            /**
//             * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
//             */
//            consumer.start();
//
//            System.out.println("Consumer Started.");
//            Thread.sleep(5000);
    }
}
