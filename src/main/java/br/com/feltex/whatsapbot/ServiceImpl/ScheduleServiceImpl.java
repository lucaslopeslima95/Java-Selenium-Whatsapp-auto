package br.com.feltex.whatsapbot.ServiceImpl;

import br.com.feltex.whatsapbot.Model.Schedule;
import br.com.feltex.whatsapbot.Repository.ScheduleRepository;
import br.com.feltex.whatsapbot.Service.ScheduleService;

public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
}
