package com.test.security.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/")
    fun init(): ResponseEntity<String> {
        return ResponseEntity<String>("Spring Test", HttpStatus.OK)
    }
}