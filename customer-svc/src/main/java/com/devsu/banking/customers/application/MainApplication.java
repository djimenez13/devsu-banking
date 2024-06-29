package com.devsu.banking.customers.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.devsu.banking.customers.application.config.AppConfig;

import io.eventuate.tram.sagas.spring.orchestration.SagaOrchestratorConfiguration;
import io.eventuate.tram.spring.commands.common.TramCommandsCommonAutoConfiguration;
import io.eventuate.tram.spring.consumer.common.TramConsumerCommonConfiguration;
import io.eventuate.tram.spring.consumer.common.TramNoopDuplicateMessageDetectorConfiguration;
import io.eventuate.tram.spring.consumer.kafka.EventuateTramKafkaMessageConsumerConfiguration;
import io.eventuate.tram.spring.messaging.common.TramMessagingCommonAutoConfiguration;
import io.eventuate.tram.spring.messaging.producer.common.TramMessagingCommonProducerConfiguration;
import io.eventuate.tram.spring.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;

@ComponentScan("com.devsu.banking.customers")
@EntityScan("com.devsu.banking.customers.*")
@EnableJpaRepositories("com.devsu.banking.customers")
@SpringBootApplication
@Import({
	AppConfig.class,
	OptimisticLockingDecoratorConfiguration.class,
	SagaOrchestratorConfiguration.class,
	TramMessagingCommonProducerConfiguration.class,
	TramMessagingCommonAutoConfiguration.class,
	TramMessageProducerJdbcConfiguration.class,
	TramCommandsCommonAutoConfiguration.class,
	TramConsumerCommonConfiguration.class,
	EventuateTramKafkaMessageConsumerConfiguration.class,
	TramNoopDuplicateMessageDetectorConfiguration.class
})
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
