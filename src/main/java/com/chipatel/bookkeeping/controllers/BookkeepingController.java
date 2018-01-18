package com.chipatel.bookkeeping.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookkeepingController {

  @RequestMapping("/")
  @ResponseBody
  public String index() {
    return "Welcome to Bookkeeping Spring Hibarnate application";
  }
}
