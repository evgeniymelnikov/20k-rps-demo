package com.evgeniymelnikov.infoservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginRequestInfo {

    private String username;
    private String password;
}
