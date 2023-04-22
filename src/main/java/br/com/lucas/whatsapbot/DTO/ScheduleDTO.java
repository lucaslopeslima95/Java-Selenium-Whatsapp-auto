package br.com.lucas.whatsapbot.DTO;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record ScheduleDTO(LocalDate date, LocalTime hour, String message) {
}
