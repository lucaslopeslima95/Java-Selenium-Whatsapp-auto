package br.com.feltex.whatsapbot.DTO;

import lombok.Builder;

@Builder
public record ContactDTO(String name,String number) {
}
