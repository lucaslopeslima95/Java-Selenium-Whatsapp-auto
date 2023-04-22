package br.com.lucas.whatsapbot.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    private String message;
    private LocalDate date;
    private LocalTime hour;
    @ManyToOne
    @JoinColumn(name = "id_contact")
    private Contact contact;
}
