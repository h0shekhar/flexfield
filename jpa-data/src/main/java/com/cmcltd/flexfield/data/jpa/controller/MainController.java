package com.cmcltd.flexfield.data.jpa.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

  @RequestMapping("/")
  @ResponseBody
  public String index() {
    return "<a href='http://www.cmcltd.com/en'>by CMC Limited</a>";
  }

}
