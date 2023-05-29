package com.cenk.exception;

import lombok.Getter;

/**
 * Kendi özelleştirilmiş exception'ınımız
 * Hatalarımızın tümü runtime'da oluşacağından burdan miras almak daha doğru
 */
@Getter
public class ElasticException extends RuntimeException{
    private final ErrorType errorType;
    public ElasticException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
    public ElasticException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
