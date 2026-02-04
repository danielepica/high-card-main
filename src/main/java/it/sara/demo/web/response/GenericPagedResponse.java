package it.sara.demo.web.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericPagedResponse extends GenericResponse {
    private int total;
}
