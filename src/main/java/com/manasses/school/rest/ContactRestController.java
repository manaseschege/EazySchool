package com.manasses.school.rest;

import com.manasses.school.constants.EazySchoolConstants;
import com.manasses.school.model.Contact;
import com.manasses.school.model.Response;
import com.manasses.school.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/contact")
public class ContactRestController {
    @Autowired
    private final ContactRepository contactRepository;

    @Autowired
    public ContactRestController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/getMessageByStatus")

    public List<Contact> getMessageByStatus(@RequestParam(name="status") String status) {
        return contactRepository.findByStatus(status);
    }
//    @GetMapping("/getAllMsgsByStatus")
//
//    public List<Contact> getAllMsgsByStatus(@RequestBody Contact contact){
//        if(null !=contact && null !=contact.getStatus()){
//            return contactRepository.findByStatus(contact.getStatus());
//        }else{
//            return List.of();
//        }
//    }
    @GetMapping("/getContactById")

    public List<Contact> getMsgById(@RequestBody Contact contact){
        if(null!=contact && contact.getContactId()>0){
            return contactRepository.findByContactId(contact.getContactId());
        }else{
            return List.of();
        }
    }
    @PostMapping("/saveMsg")
    @ResponseBody
    public ResponseEntity<Response> saveMsg(@RequestHeader(value = "invocationForm", required = false) String invocationFrom, @Valid @RequestBody Contact contact) {
        log.info(String.format("Header invocationFrom = %s", invocationFrom));
        contactRepository.save(contact);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMessage("Message Saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMessageSaved", "true")
                .body(response);
    }
    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity){
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach(((key,value) -> log.info(String.format("Header '%s'=%s", key, value.stream().collect(Collectors.joining("|"))))));
        Contact contact = requestEntity.getBody();
        assert contact != null;
        contactRepository.deleteById(contact.getContactId());
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMessage("Contact deleted successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contactReq){
        Response response=new Response();
        Optional<Contact> contact =contactRepository.findById(contactReq.getContactId());
        if(contact.isPresent()){
            contact.get().setStatus(EazySchoolConstants.CLOSE);
            contactRepository.save(contact.get());
            response.setStatusCode("200");
            response.setStatusMessage("Contacts closed successfully");
            return ResponseEntity
                 .status(HttpStatus.OK)
                 .body(response);
            }
        else {
            response.setStatusCode("400");
            response.setStatusMessage(" invalid contactId received");
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
        }
    }
}