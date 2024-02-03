package com.batty.framework.message;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import org.springframework.stereotype.Component;

@Component(MessageBroker.BEAN_NAME)
@ConditionalOnProperty(name={"message.broker.provider"},havingValue="kafka",matchIfMissing = false )
public class  MessageBroker {
    public static final String BEAN_NAME = "MessageBroker";

    @PostConstruct
    public void init()
    {
        System.out.println("This is init");
    }

}
