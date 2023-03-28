package br.com.feltex.whatsapbot.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Contact {

    @Id
    private String id;
    private String name;
    private LocalDateTime scheduled;
    private String number;
    private String link ;


}
