package com.test.testspringjmsmq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class tibcoPublisher {

	




static String serverUrl = "localhost";
static String userName = "";
static String password = "";

public static void sendTopicMessage(String topicName, String messageStr) {

    Connection connection = null;
    Session session = null;
    MessageProducer msgProducer = null;
    Destination destination = null;
	
    try {
        TextMessage msg;

        System.out.println("Publishing to destination '" + topicName
                + "'\n");

        ConnectionFactory factory = new com.tibco.tibjms.TibjmsConnectionFactory(
                serverUrl);

        connection = factory.createConnection();

        /* create the session */
        session = connection
                .createSession(javax.jms.Session.AUTO_ACKNOWLEDGE);

        /* create the destination */
        destination = session.createTopic(topicName);

        /* create the producer */
        msgProducer = session.createProducer(null);

        /* publish messages */
        /* create text message */
        msg = session.createTextMessage();

        /* set message text */
        msg.setText(messageStr);

        /* publish message */
        msgProducer.send(destination, msg);

        System.out.println("Published message to Tibco: " + messageStr);

        /* close the connection */
        connection.close();

    } catch (JMSException e) {
        e.printStackTrace();
    }
}

public static String recievetopicMessage(String topicName) {

    Connection connection = null;
    Session session = null;
    Message msg1 = null;
    Destination destination = null;
    MessageConsumer msgConsumer = null;

    try {
        TextMessage msg;

        System.out.println("Reciving Msg From Tibco '" + topicName
                + "'\n");

        ConnectionFactory factory = new com.tibco.tibjms.TibjmsConnectionFactory(
                serverUrl);

        connection = factory.createConnection();

        /* create the session */
        session = connection
                .createSession(javax.jms.Session.AUTO_ACKNOWLEDGE);

        /* create the destination */
        destination = session.createTopic(topicName.toString());

        /* create the producer */
        msgConsumer = session.createConsumer(destination);

        msg1 =  msgConsumer.receive();

        System.out.println("Recieved  message from Tibco: " +  msg1.toString());
        
        /* close the connection */
        connection.close();
        
    } catch (JMSException e) {
        e.printStackTrace();
    }
    return(msg1.toString());
}

}