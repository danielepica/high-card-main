package it.sara.demo.service.user.impl;

import it.sara.demo.dto.UserDTO;
import it.sara.demo.exception.GenericException;
import it.sara.demo.service.assembler.UserAssembler;
import it.sara.demo.service.database.UserRepository;
import it.sara.demo.service.database.model.User;
import it.sara.demo.service.user.UserService;
import it.sara.demo.service.user.criteria.CriteriaAddUser;
import it.sara.demo.service.user.criteria.CriteriaGetUsers;
import it.sara.demo.service.user.result.AddUserResult;
import it.sara.demo.service.user.result.GetUsersResult;
import it.sara.demo.service.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StringUtil stringUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAssembler userAssembler;

    @Override
    public AddUserResult addUser(CriteriaAddUser criteria) throws GenericException {

        User user;
        user = new User();
        user.setFirstName(criteria.getFirstName());
        user.setLastName(criteria.getLastName());
        user.setEmail(criteria.getEmail());
        user.setPhoneNumber(criteria.getPhoneNumber());

        if (!userRepository.save(user)) {
            throw new GenericException(500, "Error saving user");
        }
        AddUserResult returnValue = new AddUserResult();
        returnValue.setUserId(user.getGuid());

        return returnValue;
    }

    @Override
    public GetUsersResult getUsers(CriteriaGetUsers criteriaGetUsers) throws GenericException {
        GetUsersResult result = new GetUsersResult();
        String query = criteriaGetUsers.getQuery() == null ? "" : criteriaGetUsers.getQuery().toLowerCase();
        List<User> allUsers = userRepository.getAll();

        // Filtro case-insensitive
        List<User> filtered = allUsers.stream()
                .filter(u ->
                        u.getFirstName().toLowerCase().contains(query) ||
                                u.getLastName().toLowerCase().contains(query) ||
                                u.getEmail().toLowerCase().contains(query) ||
                                u.getPhoneNumber().contains(query)
                )
                .toList();

        // total = numero totale dopo filtro
        result.setTotal(filtered.size());

        // Applico paginazione e ordinamento
        List<UserDTO> listResult = filtered.stream()
                .sorted(getComparator(criteriaGetUsers.getOrder()))
                .skip(criteriaGetUsers.getOffset())
                .limit(criteriaGetUsers.getLimit())
                .map(u -> userAssembler.toDTO(u))
                .toList();

        result.setUsers(listResult);
        return result;
    }

    private Comparator<User> getComparator(CriteriaGetUsers.OrderType order) {
        return switch (order) {
            case BY_FIRSTNAME -> Comparator.comparing(User::getFirstName, String.CASE_INSENSITIVE_ORDER);
            case BY_FIRSTNAME_DESC ->
                    Comparator.comparing(User::getFirstName, String.CASE_INSENSITIVE_ORDER).reversed();
            case BY_LASTNAME -> Comparator.comparing(User::getLastName, String.CASE_INSENSITIVE_ORDER);
            case BY_LASTNAME_DESC -> Comparator.comparing(User::getLastName, String.CASE_INSENSITIVE_ORDER).reversed();
        };
    }

}
