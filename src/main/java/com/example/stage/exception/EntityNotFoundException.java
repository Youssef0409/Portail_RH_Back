package com.example.stage.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityNotFoundException  extends RuntimeException{
    private ErrorCodes errorCodes;
    public EntityNotFoundException(String message){
        super(message);
    }
    public EntityNotFoundException(String message,Throwable cause){
        super(message, cause);
    }
    public EntityNotFoundException(String message,Throwable cause,ErrorCodes errorCodes){
        super(message,cause);
        this.errorCodes=errorCodes;
    }
    public EntityNotFoundException(String message,ErrorCodes errorCodes){
        super(message);
        this.errorCodes=errorCodes;
    }
}
