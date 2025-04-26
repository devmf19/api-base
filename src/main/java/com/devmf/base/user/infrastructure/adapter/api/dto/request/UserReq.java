package com.devmf.base.user.infrastructure.adapter.api.dto.request;

import com.devmf.base.user.infrastructure.adapter.api.dto.constant.UserRequestMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Builder
@Data
public class UserReq {

    @NotBlank(message = UserRequestMessage.EMPTY_OR_NULL_FIELD + "documentNumber")
    @Size(min = 6, max = 12, message = UserRequestMessage.OUT_OF_RANGE + "documentNumber")
    private String documentNumber;

    @NotBlank(message = UserRequestMessage.EMPTY_OR_NULL_FIELD + "firstName")
    private String firstName;

    private String secondName;

    @NotBlank(message = UserRequestMessage.EMPTY_OR_NULL_FIELD + "firstLastName")
    private String firstLastName;

    private String secondLastName;

    private String email;

    @NotBlank(message = UserRequestMessage.EMPTY_OR_NULL_FIELD + "phone")
    @Size(min = 7, max = 15, message = UserRequestMessage.OUT_OF_RANGE + "phone")
    private String phone;

    @NotBlank(message = UserRequestMessage.EMPTY_OR_NULL_FIELD + "address")
    private String address;

    private Date birthday;


}
