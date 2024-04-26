package com.sam.LoginSysSam.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    String user_name;
    String password;
    String first_name;
    String last_name;
    String email;

}