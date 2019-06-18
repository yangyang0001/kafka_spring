package com.inspur.consumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.kafka.listener.MessageListener;

/**
 * 一个消费者线程对应一个消费者监听器!
 * @author YangJianWei
 */
public class SpringKafkaConsumerListener implements MessageListener<String, String>{

	private static final Logger LOG = Logger.getLogger(SpringKafkaConsumerListener.class);
	private static final String LogHeader = SpringKafkaConsumerListener.class.getSimpleName();
	
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		//只是简单的打印出信息
		System.out.println(LogHeader + "--------->thread.currentThread.getName ---------:" + Thread.currentThread().getName()
		                             + ",data.topic ------:" + data.topic()
		                             + ",data.partition---:" + data.partition()
		                             + ",data.offset -----:" + data.offset()
		                             + ",data.timestamp --:" + data.timestamp()
		                             + ",data.key --------:" + data.key()
		                             + ",data.value ------:" + data.value());
	}
	
}
