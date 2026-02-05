package it.sara.demo.web.assembler;

import it.sara.demo.service.user.criteria.CriteriaAddUser;
import it.sara.demo.service.user.criteria.CriteriaGetUsers;
import it.sara.demo.web.user.request.AddUserRequest;
import it.sara.demo.web.user.request.GetUsersRequest;
import org.springframework.stereotype.Component;

@Component
public class GetUserAssembler {

    public CriteriaGetUsers toCriteria(GetUsersRequest getUsersRequest) {
        CriteriaGetUsers returnValue = new CriteriaGetUsers();
        returnValue.setLimit(getUsersRequest.getLimit());
        returnValue.setOrder(getUsersRequest.getOrder());
        returnValue.setOffset(getUsersRequest.getOffset());
        returnValue.setQuery(getUsersRequest.getQuery());
        return returnValue;
    }
}
