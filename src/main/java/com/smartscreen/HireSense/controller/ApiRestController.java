package com.smartscreen.HireSense.controller;

import com.smartscreen.HireSense.exception.DataValidationException;
import com.smartscreen.HireSense.exception.InvalidPassword;
import com.smartscreen.HireSense.exception.UserAlreadyExists;
import com.smartscreen.HireSense.exception.UserNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public abstract class ApiRestController implements ApplicationEventPublisherAware {

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {}

    @ResponseBody
    @ExceptionHandler(value = UserAlreadyExists.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExists userAlreadyExists) {
        return new ResponseEntity<>(userAlreadyExists.getMessage(), HttpStatus.CONFLICT);
    }

    @ResponseBody
    @ExceptionHandler(value = InvalidPassword.class)
    public ResponseEntity<String> handleInvalidPassword(InvalidPassword invalidPassword) {
        return new ResponseEntity<>(invalidPassword.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @ExceptionHandler(value = UserNotFound.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFound userNotFound) {
        return new ResponseEntity<>(userNotFound.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(value = DataValidationException.class)
    public ResponseEntity<String> handleDataValidationException(DataValidationException dataValidationException) {
        return new ResponseEntity<>(dataValidationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
