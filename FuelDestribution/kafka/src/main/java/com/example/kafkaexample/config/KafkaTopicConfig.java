package com.example.kafkaexample.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic myCodeTopic(){  //this will instantiate and get a new topic
        return TopicBuilder.name("NewOrder").build();
    }

    @Bean
    public NewTopic myCodeTopic2(){  //this will instantiate and get a new topic
        return TopicBuilder.name("AllocationComplete").build();
    }

    @Bean
    public NewTopic myCodeTopic3(){  //this will instantiate and get a new topic
        return TopicBuilder.name("DeliveryScheduled").build();
    }

    @Bean
    public NewTopic myCodeTopic4(){  //this will instantiate and get a new topic
        return TopicBuilder.name("OrderDispatched").build();
    }
}
