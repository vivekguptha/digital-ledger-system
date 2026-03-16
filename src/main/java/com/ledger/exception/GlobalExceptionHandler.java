
package com.ledger.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            InsufficientFundsException.class,
            ConcurrencyException.class
    })
    public ResponseEntity<String> handle(Exception ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Transfer Failed: " + ex.getClass().getSimpleName());
    }
}
