package com.nefee.prawn.web.controller;

import com.nefee.prawn.data.dao.WowSpecRepository;
import com.nefee.prawn.data.entity.WowSpec;
import com.nefee.prawn.logic.service.DatabaseService;
import com.nefee.prawn.logic.service.FindMyBestInSlotsService;
import com.nefee.prawn.logic.service.TransformerService;
import com.nefee.prawn.web.dto.request.BiSRequest;
import com.nefee.prawn.web.dto.request.WishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class PrawnController {

    @Autowired
    private FindMyBestInSlotsService bestGearService;
    @Autowired
    private TransformerService transformerService;
    @Autowired
    private WowSpecRepository wowSpecRepository;
    @Autowired
    private DatabaseService databaseService;

//    @RequestMapping(value = "/") //mappé à un html
//    public String init(Model model) {
//        databaseService.load();
//        model.addAttribute("pawnstring", "");
//        model.addAttribute("wowSpec", new WowSpec());
//        model.addAttribute("wowSpecs", wowSpecRepository.findAll());
//        return "index";
//    }


    @RequestMapping ("/")
    public String login(BiSRequest loginForm, Model model) {
        WowSpec test = wowSpecRepository.findByWowId(105);
        if (test == null) {
            databaseService.load();
        }
        model.addAttribute("loginForm", new BiSRequest());
        model.addAttribute("wowSpecs", wowSpecRepository.findAll());
        model.addAttribute("t20", Arrays.asList(0, 2, 4));
        return "index";
    }

    @RequestMapping (value = "/submit", method = RequestMethod.POST)
    public String loginPage(@Valid BiSRequest loginForm, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            notifyService.addErrorMessage("Please fill the form correctly!");
//            return "users/login";
//        }

        System.err.println(loginForm.toString());

        WishRequest request = transformerService.transformRequest(loginForm);
        System.err.println(request.toString());
        String reportId = bestGearService.findBestInSlots(request);
        System.err.println(reportId);
        return "redirect:/report/" + reportId;
    }

    @RequestMapping ("/faq")
    public String faq() {
        return "faq";
    }


//    @RequestMapping(value = "/submit", method = RequestMethod.POST, params = {"save"})
////    public String submit(@ModelAttribute(value = "pawnstring") String pawnstring, @ModelAttribute(value = "wowSpec") WowSpecEnum myWowSpec) {
//    public String submit(@ModelAttribute(value = "pawnstring") String pawnstring, @ModelAttribute(value = "wowSpec") WowSpec wowSpec) {
////        if (bindingResult.hasErrors()) {
////            return "index";
////        }
//        WishRequest request = transformerService.transformRequest(BiSRequest.builder().pawnstring(pawnstring).wowSpec(wowSpec).build());
//        System.err.println(request.toString());
//        String reportId = bestGearService.findBestGear(request);
//        System.err.println(reportId);
//        return "redirect:/report/" + reportId;
//    }

    @ModelAttribute ("wowSpecs")
    public List<WowSpec> getAllWowSpecs() {
        return (List<WowSpec>) wowSpecRepository.findAll();
    }


}
