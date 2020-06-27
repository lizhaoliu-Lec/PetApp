package org.fisco.bcos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorld {
    @RequestMapping(value = "/helloWorld")
    public ResponseEntity<Object> sayHelloWorld() {
        return new ResponseEntity<>("HelloWorld!", HttpStatus.OK);
    }
}
