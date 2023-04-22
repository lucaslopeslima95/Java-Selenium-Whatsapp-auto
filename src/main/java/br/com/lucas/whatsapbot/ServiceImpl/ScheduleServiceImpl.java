package br.com.lucas.whatsapbot.ServiceImpl;

import br.com.lucas.whatsapbot.DTO.ScheduleDTO;
import br.com.lucas.whatsapbot.Model.Schedule;
import br.com.lucas.whatsapbot.Repository.ScheduleRepository;
import br.com.lucas.whatsapbot.Service.ScheduleService;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule save(ScheduleDTO scheduleDTO) {
        System.out.println("agenda "+scheduleDTO);
        Schedule schedule = new Schedule()
                .builder()
                .date(scheduleDTO.date())
                .hour(scheduleDTO.hour())
                .message(scheduleDTO.message())
                .build();

        return scheduleRepository.save(schedule);
    }

}
