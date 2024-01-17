package com.manasses.school.service;

import com.manasses.school.constants.EazySchoolConstants;
import com.manasses.school.model.Person;
import com.manasses.school.model.Roles;
import com.manasses.school.repository.PersonRepository;
//import com.manasses.school.repository.RolesRepository;
import com.manasses.school.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;
@Autowired
private PasswordEncoder passwordEncoder;
    public boolean createNewPerson(Person person){
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE);
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }
}
