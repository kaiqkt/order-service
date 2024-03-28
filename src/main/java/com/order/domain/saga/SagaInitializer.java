package com.order.domain.saga;

import com.kaiqkt.saga.core.Orchestrator;
import com.kaiqkt.saga.jms.JmsBackEnd;
import com.order.resources.sqs.SQSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SagaInitializer {

    private static final Logger logger = LoggerFactory.getLogger(SagaInitializer.class);

    public SagaInitializer(
            @Value("${aws.order-queue-name}")
            String queueName,
            @Value("${aws.dlq-queue-name}")
            String dlqName,
            @Autowired
            SQSClient sqsClient
    ) throws Exception {
        JmsBackEnd jmsBackend = new JmsBackEnd(sqsClient.session(), queueName, dlqName);
        new Orchestrator(jmsBackend);
        logger.info("Initialized saga");
    }
}
