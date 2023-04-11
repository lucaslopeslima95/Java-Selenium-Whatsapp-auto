package br.com.feltex.whatsapbot.ServiceImpl;

import br.com.feltex.whatsapbot.Model.Schedule;
import br.com.feltex.whatsapbot.Repository.ScheduleRepository;
import br.com.feltex.whatsapbot.Service.ScheduleService;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
}
