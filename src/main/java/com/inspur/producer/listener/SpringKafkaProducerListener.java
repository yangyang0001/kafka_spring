package com.inspur.producer.listener;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;
import org.springframework.kafka.support.ProducerListener;

/**
 * spring-kafka整合模式中的:自定义生产者监听器
 * @author YangJianWei
 *
 */
public class SpringKafkaProducerListener implements ProducerListener<String, String>{

	private static final Logger LOG = Logger.getLogger(SpringKafkaProducerListener.class);
	private static final String LogHeader = SpringKafkaProducerListener.class.getSimpleName();
	
	/**
	 * 返回为true,onSuccess方法才能够执行!
	 */
	@Override
	public boolean isInterestedInSuccess() {
		return true;
	}

	@Override
	public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
		System.out.println(LogHeader + "-------->>onSuccess-------->topic------:" + topic 
									 + ",partition------:" + partition 
									 + ",timestamp------:" + recordMetadata.timestamp() 
									 + ",offset---------:" + recordMetadata.offset()
									 + ",key------------:" + key + ",value------:" + value);
	}

	@Override
	public void onError(String topic, Integer partition, String key, String value, Exception exception) {
		System.out.println(LogHeader + "-------->>onError-------->topic-------:" + topic 
									 + ",partition------:" + partition 
									 + ",key------------:" + key 
									 + ",value----------:" + value);
	}


}
