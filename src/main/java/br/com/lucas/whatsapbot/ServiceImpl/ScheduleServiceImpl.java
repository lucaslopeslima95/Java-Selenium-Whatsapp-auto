package br.com.lucas.whatsapbot.ServiceImpl;

import br.com.lucas.whatsapbot.Model.Schedule;
import br.com.lucas.whatsapbot.Repository.ScheduleRepository;
import br.com.lucas.whatsapbot.Service.ScheduleService;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
}
