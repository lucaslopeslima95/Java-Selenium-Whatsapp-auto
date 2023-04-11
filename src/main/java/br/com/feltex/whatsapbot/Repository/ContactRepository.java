package br.com.feltex.whatsapbot.Repository;

import br.com.feltex.whatsapbot.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
}
