package it.sara.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusDTO {
    private int code;
    private String message;
    private String traceId;
}
