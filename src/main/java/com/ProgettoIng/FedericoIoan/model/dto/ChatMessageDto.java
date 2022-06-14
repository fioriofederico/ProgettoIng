package com.ProgettoIng.FedericoIoan.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ChatMessageDto {

/*    @NotNull
    Long senderId;

    @NotNull
    Long receiverId;*/

    @NotNull
    String message;
}
