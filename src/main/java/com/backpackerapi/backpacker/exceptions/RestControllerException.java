package com.backpackerapi.backpacker.exceptions;

import com.backpackerapi.backpacker.dtos.Message;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestControllerException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Message> handleException(Exception e){
        return ResponseEntity.internalServerError()
                .body(new Message(e.getMessage()));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Message> handleCustomException(CustomException e){
        return ResponseEntity.status(e.getStatus())
                .body(new Message(e.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Message> handleBadCredentialsException(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Message(e.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Message> handleAccessDeniedException(AccessDeniedException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new Message(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Message> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<String> messages = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(err -> messages.add(err.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Message(messages.stream().collect(Collectors.joining(","))));
    }

    @ExceptionHandler(value = {
            MalformedJwtException.class,
            UnsupportedJwtException.class,
            IllegalArgumentException.class,
            SignatureException.class
    })
    public ResponseEntity<Message> jwtException(JwtException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new Message(e.getMessage()));
    }
}
