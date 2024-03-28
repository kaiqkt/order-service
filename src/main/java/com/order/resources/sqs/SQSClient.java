package com.order.resources.sqs;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazon.sqs.javamessaging.SQSSession;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

@Component
public class SQSClient {
    private AmazonSQS client;
    private Connection connection;

    public SQSClient(
            @Value("${aws.access-key}")
            String accessKey,
            @Value("${aws.secret-key}")
            String secretKey,
            @Value("${aws.region}")
            String awsRegion,
            @Value("${aws.order-broker-endpoint}")
             String messageBrokerEndpoint
    ) throws JMSException {

        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        client = AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(messageBrokerEndpoint, awsRegion))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        SQSConnectionFactory sqsConnectionFactory = new SQSConnectionFactory(new ProviderConfiguration(), client);
        connection = sqsConnectionFactory.createConnection();
        connection.start();
    }

    public Session session() throws JMSException {
        return connection.createSession(false, SQSSession.UNORDERED_ACKNOWLEDGE);
    }
}
