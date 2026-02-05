package it.sara.demo.web.user.request;

import it.sara.demo.service.user.criteria.CriteriaGetUsers;
import it.sara.demo.web.request.GenericRequest;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUsersRequest extends GenericRequest {

    @Size(max = 50, message = "Query too long")
    private String query;

    @Min(value = 0, message = "Offset must be >= 0")
    private int offset = 0;

    @Min(value = 1, message = "Limit must be >= 1")
    @Max(value = 100, message = "Limit too large")
    private int limit = 10;

    @NotNull(message = "Order is required")
    private CriteriaGetUsers.OrderType order;
}

