package br.com.lucas.whatsapbot.Controller;

import br.com.lucas.whatsapbot.Model.Schedule;
import br.com.lucas.whatsapbot.ServiceImpl.ScheduleServiceImpl;
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
