package it.sara.demo.web.response;

import it.sara.demo.dto.StatusDTO;
import it.sara.demo.service.result.GenericResult;
import it.sara.demo.service.user.result.AddUserResult;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GenericResponse<T> {
    private StatusDTO status;
    private T data;

    public static <T> GenericResponse<T> success(String message, T data) {
        GenericResponse<T> returnValue = new GenericResponse<>();
        returnValue.setStatus(new StatusDTO());
        returnValue.getStatus().setCode(200);
        returnValue.getStatus().setMessage(message != null ? message : "Success");
        returnValue.getStatus().setTraceId(java.util.UUID.randomUUID().toString());
        returnValue.setData(data);
        return returnValue;
    }
    public static <T> GenericResponse<T> error(int code, String message) {
        GenericResponse<T> response = new GenericResponse<>();
        StatusDTO status = new StatusDTO();
        status.setCode(code);
        status.setMessage(message);
        status.setTraceId(UUID.randomUUID().toString());
        response.setStatus(status);
        response.setData(null);
        return response;
    }
}
