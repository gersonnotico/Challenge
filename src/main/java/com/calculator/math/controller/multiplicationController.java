package com.calculator.math.controller;

import com.calculator.math.constants.RabbitMQConstant;
import com.calculator.math.dto.CalculationsDto;
import com.calculator.math.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="multiplication")
public class multiplicationController {
    @Autowired
    private RabbitmqService rabbitmqService;

    @GetMapping
    private ResponseEntity multiplicationNumber(@RequestBody CalculationsDto calculationsDto){
        this.rabbitmqService.sendMessage(RabbitMQConstant.MUL_NUMBER, calculationsDto);
        return new ResponseEntity("Result: "+(calculationsDto.a*calculationsDto.b), HttpStatus.OK);
    }
}
