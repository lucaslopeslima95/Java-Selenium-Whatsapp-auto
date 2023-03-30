package br.com.feltex.whatsapbot.Repository;

import br.com.feltex.whatsapbot.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
}
