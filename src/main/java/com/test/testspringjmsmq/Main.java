package com.test.testspringjmsmq;

import javax.jms.JMSException;

public class Main {

	public static void main(String[] args) throws JMSException {
		// TODO Auto-generated method stub
//		tibcoPublisher tibcoPub = new tibcoPublisher();
//		tibcoPub.sendTopicMessage("topic1", "Hi Ravi Kanth ");
//		String msg = tibcoPub.recievetopicMessage("topic1");
		MessageService msgService = new MessageService();
		msgService.sendMessage("Hello");
		msgService.readMessage();
	}

}
