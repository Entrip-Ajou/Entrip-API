package com.hwanld.EntripAPI.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping(value = "/")
    public String mainPage() {
        return "index";
    }

    @RequestMapping(value = "index.html")
    public String indexPage() { return "index"; }

    @RequestMapping(value = "planner.html")
    public String plannerPage() { return "planner"; }

    @RequestMapping(value = "community_free.html")
    public String community_freePage() {return "community_free";}

    @RequestMapping(value = "popup.html")
    public String popupPage() { return "popup";}

    @RequestMapping(value = "community_information.html")
    public String community_informationPage() { return "community_information"; }

    @RequestMapping(value = "traveltest.html")
    public String traveltestPage() { return "traveltest";}

    @RequestMapping(value = "index_free.html")
    public String index_freePage() { return "index_free"; }

    @RequestMapping(value = "index_information.html")
    public String index_informationPage() { return "index_information"; }
}
