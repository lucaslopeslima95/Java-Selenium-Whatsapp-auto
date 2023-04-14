package br.com.lucas.whatsapbot.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    private Set<String> contacts;
    private String content;
    private String pathImage;
}
