package com.natixis.transfers.controllers;

import com.natixis.transfers.services.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;


}
