package com.demo.controller;

import com.demo.entity.Inform;
import com.demo.service.TableService.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class InfromController {
    @Autowired
    InformService informService;

    @RequestMapping(value = "/InformList", method = RequestMethod.GET)
    public ArrayList<Inform> getnformList() {
        return informService.getInformList();
    }

    @RequestMapping(value = "/Inform/{id}", method = RequestMethod.GET)
    public Inform getInform(@PathVariable("id") Long id) {
        return informService.findInfromById(id);
    }

    @RequestMapping(value = "/Infrom/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteInform(@PathVariable("id") Long id) {
        informService.deleteInformById(id);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/Infrom/Add", method = RequestMethod.POST)
    public ResponseEntity addInfrom(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "title") String content
    ) {
        Inform inform = new Inform();
        inform.setTitle(title);
        inform.setContent(content);
        inform.setPubTime(new Date());
        informService.saveInform(inform);
        return ResponseEntity.ok(true);
    }
}
