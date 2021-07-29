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

import java.math.BigDecimal;


@RestController
@RequestMapping(value="product")
public class productController {
    @Autowired
    private RabbitmqService rabbitmqService;

    @GetMapping
    private ResponseEntity productNumber(@RequestBody CalculationsDto calculationsDto){
        this.rabbitmqService.sendMessage(RabbitMQConstant.PRODUCT_NUMBER, calculationsDto);
        BigDecimal product = (calculationsDto.a).multiply(calculationsDto.b);
        return new ResponseEntity("Result: "+product,HttpStatus.OK);
    }
}
