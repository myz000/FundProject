package com.demo.controller;

import com.demo.service.TableService.Impl.TrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TrendController {
    @Autowired
    TrendService trendService;

    // @RequestMapping(value="/trends/{investId}",method = RequestMethod.GET)
}
