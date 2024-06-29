package com.devsu.banking.accounts.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.devsu.banking.accounts.application.config.AppConfig;

import io.eventuate.tram.sagas.spring.participant.SagaParticipantConfiguration;
import io.eventuate.tram.spring.commands.common.TramCommandsCommonAutoConfiguration;
import io.eventuate.tram.spring.consumer.common.TramConsumerCommonConfiguration;
import io.eventuate.tram.spring.consumer.jdbc.TramConsumerJdbcAutoConfiguration;
import io.eventuate.tram.spring.consumer.kafka.EventuateTramKafkaMessageConsumerConfiguration;
import io.eventuate.tram.spring.messaging.common.TramMessagingCommonAutoConfiguration;
import io.eventuate.tram.spring.messaging.producer.common.TramMessagingCommonProducerConfiguration;
import io.eventuate.tram.spring.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;

@ComponentScan("com.devsu.banking.accounts")
@EntityScan("com.devsu.banking.accounts.*")
@EnableJpaRepositories("com.devsu.banking.accounts")
@SpringBootApplication
//@Import({
//	AppConfig.class,
//	OptimisticLockingDecoratorConfiguration.class,
//	SagaParticipantConfiguration.class,
//	TramConsumerCommonConfiguration.class,
//	EventuateTramKafkaMessageConsumerConfiguration.class,
//	TramMessagingCommonAutoConfiguration.class,
//	TramConsumerJdbcAutoConfiguration.class, // Idempotent consumer
//	TramCommandsCommonAutoConfiguration.class,
//	TramMessagingCommonProducerConfiguration.class,
//	TramMessageProducerJdbcConfiguration.class
//})
@EnableAutoConfiguration
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
