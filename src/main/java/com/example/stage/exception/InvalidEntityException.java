package com.example.stage.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidEntityException  extends RuntimeException{
    ErrorCodes errorCodes;
    List<String> errors;
    public InvalidEntityException(String message){
        super(message);
    }
    public InvalidEntityException(String message,Throwable cause){
        super(message, cause);
    }
    public InvalidEntityException(String message,Throwable cause,ErrorCodes errorCodes){
        super(message,cause);
        this.errorCodes=errorCodes;
    }
    public InvalidEntityException(String message,ErrorCodes errorCodes){
        super(message);
        this.errorCodes=errorCodes;
    }
    public InvalidEntityException(String message,ErrorCodes errorCodes,List<String>errors){
        super(message);
        this.errorCodes=errorCodes;
        this.errors=errors;
    }
}
