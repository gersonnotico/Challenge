package com.calculator.math.controller;


import com.calculator.math.constants.RabbitMQConstant;
import com.calculator.math.dto.CalculationsDto;
import com.calculator.math.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="sum")
public class sumController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @GetMapping
    private ResponseEntity sumNumber(@RequestBody CalculationsDto calculationsDto){
        this.rabbitmqService.sendMessage(RabbitMQConstant.SUM_NUMBER, calculationsDto);
        return new ResponseEntity("Result: "+(calculationsDto.a+calculationsDto.b),HttpStatus.OK);
    }
}
