package br.com.lucas.whatsapbot.Repository;

import br.com.lucas.whatsapbot.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
}
