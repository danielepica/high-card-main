package it.sara.demo.web.response;

import it.sara.demo.dto.StatusDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse {
    private StatusDTO status;

    public static GenericResponse success(String message) {
        GenericResponse returnValue = new GenericResponse();
        returnValue.setStatus(new StatusDTO());
        returnValue.getStatus().setCode(200);
        returnValue.getStatus().setMessage(message != null ? message : "Success");
        returnValue.getStatus().setTraceId(java.util.UUID.randomUUID().toString());
        return returnValue;
    }
}
