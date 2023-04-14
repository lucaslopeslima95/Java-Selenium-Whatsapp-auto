package br.com.lucas.whatsapbot.Controller;

import br.com.lucas.whatsapbot.Model.Contact;
import br.com.lucas.whatsapbot.ServiceImpl.ContactServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {
    private ContactServiceImpl contactService;

    public ContactController(ContactServiceImpl contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contact")
    public Contact save(@RequestBody Contact contact){
        return contactService.save(contact);
    }
}
