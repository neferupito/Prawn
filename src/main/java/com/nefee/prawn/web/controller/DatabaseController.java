package com.nefee.prawn.web.controller;

import com.nefee.prawn.logic.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DatabaseController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping("/db/generate")
    public String generate(Model model) {
        databaseService.load();
        // quel html à montrer
        return "index";
    }


}
