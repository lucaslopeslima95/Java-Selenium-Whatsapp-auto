package br.com.feltex.whatsapbot.Controller;

import br.com.feltex.whatsapbot.Model.Schedule;
import br.com.feltex.whatsapbot.ServiceImpl.ScheduleServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleServiceImpl scheduleService;

    @PostMapping("/save")
    public Schedule save(Schedule schedule){
        return scheduleService.save(schedule);
    }
}
