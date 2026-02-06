package it.sara.demo.web.user;

import it.sara.demo.dto.StatusDTO;
import it.sara.demo.dto.UserDTO;
import it.sara.demo.exception.GenericException;
import it.sara.demo.service.user.UserService;
import it.sara.demo.service.user.criteria.CriteriaAddUser;
import it.sara.demo.service.user.criteria.CriteriaGetUsers;
import it.sara.demo.service.user.result.AddUserResult;
import it.sara.demo.service.user.result.GetUsersResult;
import it.sara.demo.web.assembler.AddUserAssembler;
import it.sara.demo.web.assembler.GetUserAssembler;
import it.sara.demo.web.response.GenericResponse;
import it.sara.demo.web.user.request.AddUserRequest;
import it.sara.demo.web.user.request.GetUsersRequest;
import it.sara.demo.web.user.response.AddUserResponse;
import it.sara.demo.web.user.response.GetUsersResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddUserAssembler addUserAssembler;

    @Autowired
    private GetUserAssembler getUserAssembler;


    @RequestMapping(value = {"/v1/user"}, method = RequestMethod.PUT)
    public ResponseEntity<GenericResponse<AddUserResult>> addUser(@RequestBody @Valid AddUserRequest request) throws GenericException {
        log.info("Richiesta arrivata -> {}", request);
        CriteriaAddUser criteria = addUserAssembler.toCriteria(request);
        AddUserResult result = userService.addUser(criteria);
        return ResponseEntity.ok(GenericResponse.success("User added.", result));
    }

    @RequestMapping(value = {"/v1/user"}, method = RequestMethod.POST)
    public ResponseEntity<GenericResponse<GetUsersResult>> getUsers(@RequestBody @Valid GetUsersRequest request) throws GenericException {
        log.info("Richiesta arrivata -> {}", request);
        CriteriaGetUsers criteria = getUserAssembler.toCriteria(request);
        GetUsersResult result = userService.getUsers(criteria);
        log.info("Result list -> {}", result.getUsers());
        return ResponseEntity.ok(GenericResponse.success("Lista utenti", result));
    }
}
