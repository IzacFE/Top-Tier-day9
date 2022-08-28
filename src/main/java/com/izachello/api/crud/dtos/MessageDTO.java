package com.izachello.api.crud.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigInteger;

@Data
@NoArgsConstructor
public class MessageDTO {

    private long messageId;

    private String message;
}
