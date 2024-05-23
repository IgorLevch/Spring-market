package ru.geekbrains.spring.spring.market.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice   // означает, что этот класс будет обрабатывать исключения во всех контроллерах
@Slf4j
public class GlobalExceptionHandler {   // это класс, который будет перехватывать исключения  и ЭТО БИН
    @ExceptionHandler   //эта аннотация для того, чтобы заставить этот метод перехватывать исключения из других методов
    public ResponseEntity<AppError> exceptionHandler(ResourceNotFoundException e){

    log.error(e.getMessage(), e);

        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
        // HttpStatus.NOT_FOUND.value()  - это 404 код , потому что ResourceNotFoundException - однозначно дает,
        // что данные не найдены.

        // КАК ЭТО РАБОТАЕТ: Если в любом методе Контроллера вылетит исключение: ResourceNotFoundException e,
        // мы его перехватим,обернем в AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
        // и вернем клиенту


    }


}
