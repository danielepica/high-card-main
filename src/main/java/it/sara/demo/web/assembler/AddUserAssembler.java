package it.sara.demo.web.assembler;

import it.sara.demo.service.user.criteria.CriteriaAddUser;
import it.sara.demo.web.user.request.AddUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AddUserAssembler {

    public CriteriaAddUser toCriteria(AddUserRequest addUserRequest) {
        CriteriaAddUser returnValue = new CriteriaAddUser();
        returnValue.setEmail(addUserRequest.getEmail());
        returnValue.setFirstName(addUserRequest.getFirstName());
        returnValue.setLastName(addUserRequest.getLastName());
        returnValue.setPhoneNumber(addUserRequest.getPhoneNumber());
        return returnValue;
    }
}
