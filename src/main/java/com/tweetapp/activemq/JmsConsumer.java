package com.tweetapp.activemq;

import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JmsConsumer implements MessageListener {

	@Override
	@JmsListener(destination = "${active-mq.topic}")
	public void onMessage(Message message) {
		log.info("Attempting to consume message now . . .");
		try {
			if (message instanceof ActiveMQTextMessage) {
				ActiveMQTextMessage amqMessage = (ActiveMQTextMessage) message;
				log.info("Received Message from Topic: " + amqMessage.getText());
			} else {
				log.info("second loop line" + message);
				BytesMessage bm = (BytesMessage) message;
				byte data[] = new byte[(int) bm.getBodyLength()];
				bm.readBytes(data);
				log.info("Received Message from Topic: " + new String(data));
			}
		} catch (Exception e) {
			log.error("Received Exception while processing message: " + e);
		}
	}
}