package br.com.feltex.whatsapbot.ServiceImpl;

import br.com.feltex.whatsapbot.Model.Contact;
import br.com.feltex.whatsapbot.Repository.ContactRepository;
import br.com.feltex.whatsapbot.Service.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService{
    ContactRepository contactRepository;
    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }
}
