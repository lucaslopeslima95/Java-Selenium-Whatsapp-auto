package br.com.feltex.whatsapbot.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Contact {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    private String name;
    private LocalDateTime scheduled;
    private String number;
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)
    private List<Schedule> scheduleList;

}
