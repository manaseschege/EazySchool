package com.manasses.school.repository;

import com.manasses.school.model.Contact;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */
@Repository
public interface ContactRepository extends CrudRepository<Contact,Integer> {
 List<Contact>findByStatus(@Param("status") String status);
List<Contact> findByContactId(@Param("contact_id") int id);


}