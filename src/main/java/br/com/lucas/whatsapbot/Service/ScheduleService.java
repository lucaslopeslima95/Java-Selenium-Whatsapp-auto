package br.com.lucas.whatsapbot.Service;

import br.com.lucas.whatsapbot.DTO.ScheduleDTO;
import br.com.lucas.whatsapbot.Model.Schedule;

public interface ScheduleService {
    Schedule save(ScheduleDTO schedule);
}
