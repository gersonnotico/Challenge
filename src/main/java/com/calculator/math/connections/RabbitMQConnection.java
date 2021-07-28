package com.calculator.math.connections;


import com.calculator.math.constants.RabbitMQConstant;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {
    private static final String NAME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue row(String nameRow){
        return new Queue(nameRow, true, false, false);
    }

    private DirectExchange directChange(){
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding relationship(Queue row, DirectExchange exchange){
        return new Binding(row.getName(), Binding.DestinationType.QUEUE, exchange.getName(), row.getName(), null);
    }

    @PostConstruct
    private void add(){
        Queue sumNumber = this.row(RabbitMQConstant.SUM_NUMBER);
        Queue subNumber = this.row(RabbitMQConstant.SUB_NUMBER);
        Queue mulNumber = this.row(RabbitMQConstant.MUL_NUMBER);
        Queue divNumber = this.row(RabbitMQConstant.DIV_NUMBER);

        DirectExchange exchange = this.directChange();

        Binding joiningSum = this.relationship(sumNumber, exchange);
        Binding joiningSub = this.relationship(subNumber, exchange);
        Binding joiningMul = this.relationship(mulNumber, exchange);
        Binding joiningDiv = this.relationship(divNumber, exchange);

        //criando filas no RQQ
        this.amqpAdmin.declareQueue(sumNumber);
        this.amqpAdmin.declareQueue(subNumber);
        this.amqpAdmin.declareQueue(mulNumber);
        this.amqpAdmin.declareQueue(divNumber);

        this.amqpAdmin.declareExchange(exchange);

        this.amqpAdmin.declareBinding(joiningSum);
        this.amqpAdmin.declareBinding(joiningSub);
        this.amqpAdmin.declareBinding(joiningMul);
        this.amqpAdmin.declareBinding(joiningDiv);
    }
}
