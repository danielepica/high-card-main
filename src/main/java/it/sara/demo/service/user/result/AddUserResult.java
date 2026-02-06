package it.sara.demo.service.user.result;

import it.sara.demo.service.result.GenericResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class  AddUserResult extends GenericResult {
    private String userId;

}
