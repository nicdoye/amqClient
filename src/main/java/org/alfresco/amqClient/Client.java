package org.alfresco.amqClient;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

// Run via
// mvn spring-boot:run -Dspring-boot.run.arguments="ssl://localhost:61617,username,password"
// or
// mvn clean install spring-boot:repackage
// java -jar target/amq-client-0.0.1-SNAPSHOT.jar ssl://localhost:61617 user pass

// Based heavily on https://simplesassim.wordpress.com/2014/02/19/how-to-send-a-message-to-an-apache-activemq-topic-with-jms/

public class Client {

    public static void main(String[] args) {
        String brokerURL = ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL;
        String userName = ActiveMQConnectionFactory.DEFAULT_USER;
        String password = ActiveMQConnectionFactory.DEFAULT_PASSWORD;

        if (args.length > 0) {
            brokerURL = args[0];

            if (args.length == 3) {
                userName = args[1];
                password = args[2];
            }
        }

        final ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);

        try {
            final Connection connection = connectionFactory.createConnection(userName,password);
            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            final Destination destination = session.createTopic("ASampleTopic");
            final MessageProducer messageProducer = session.createProducer(destination);
            final Message message = session.createTextMessage("Test Message");

            messageProducer.send(message);
            System.out.println("Success!");

            connection.close();
        } catch (javax.jms.JMSException e) {
            System.out.println("Oh dear");
            System.out.println (e);
        }
    }
}
