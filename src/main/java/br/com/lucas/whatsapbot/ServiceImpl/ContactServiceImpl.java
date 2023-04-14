package br.com.lucas.whatsapbot.ServiceImpl;

import br.com.lucas.whatsapbot.Model.Contact;
import br.com.lucas.whatsapbot.Repository.ContactRepository;
import br.com.lucas.whatsapbot.Service.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    ContactRepository contactRepository;
    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }
}
