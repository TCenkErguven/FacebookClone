package com.cenk.exception;

import lombok.Getter;

/**
 * Kendi özelleştirilmiş exception'ınımız
 * Hatalarımızın tümü runtime'da oluşacağından burdan miras almak daha doğru
 */
@Getter
public class UserException extends RuntimeException{
    private final ErrorType errorType;
    public UserException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
    public UserException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
