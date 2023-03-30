package br.com.feltex.whatsapbot.Repository;

import br.com.feltex.whatsapbot.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
}