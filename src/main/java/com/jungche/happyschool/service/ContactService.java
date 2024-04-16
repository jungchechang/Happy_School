package com.jungche.happyschool.service;

import com.jungche.happyschool.constants.SchoolConstants;
import com.jungche.happyschool.model.Contact;
import com.jungche.happyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
@Slf4j, is a Lombok-provided annotation that will automatically generate an SLF4J
Logger static property in the class at compilation time.
* */
@Slf4j
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = true;
        contact.setStatus(SchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if(null != savedContact && savedContact.getContactId()>0) {
            isSaved = true;
        }
        log.info(contact.toString());
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus(String status) {
        List<Contact> contacts = contactRepository.findByStatus(status);
        return contacts;
    }


    public boolean updateMsgStatus(int id) {
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(id);
        contact.ifPresent(c -> {
            c.setStatus(SchoolConstants.CLOSE);
        });
        Contact savedContact = contactRepository.save(contact.get());
        if(null != savedContact && savedContact.getContactId()>0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
