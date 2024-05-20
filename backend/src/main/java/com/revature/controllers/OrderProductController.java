package com.revature.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orderproducts")
@CrossOrigin(origins = {"http://localhost:3000", "http://smilemarts3.s3-website.us-east-2.amazonaws.com/"}, allowCredentials = "true")
public class OrderProductController {

}
