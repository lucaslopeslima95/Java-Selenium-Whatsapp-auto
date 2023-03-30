package br.com.feltex.whatsapbot.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
public record ContactDTO(String name,String number) {
}
