package com.devmf.base.user.application.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class UserRes {
    private String id;
    private String documentNumber;
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private String email;
    private Integer phone;
    private String address;
    private Date birthday;
}
