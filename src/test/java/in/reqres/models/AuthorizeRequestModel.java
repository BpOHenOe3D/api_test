package in.reqres.models;

import lombok.Data;

@Data
public class AuthorizeRequestModel {
    private String email,
            password;
}
