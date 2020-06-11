package com.clanchas.clanchas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private final Log log = LogFactory.getLog(getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e) {
        ObjectMapper mapper = new ObjectMapper();
        ErrorInfo errorInfo = new ErrorInfo(e);
        String respJSONstring = "{}";
        try {
            respJSONstring = mapper.writeValueAsString(errorInfo);
        } catch (JsonProcessingException err) {
            log.error("Error al convertir mapper a JSON");
            log.error(err.getMessage());
        }
        return ResponseEntity.badRequest().body(respJSONstring);
    }

    private static class ErrorInfo {
        public final String className;
        public final String exMessage;

        public ErrorInfo(Exception exception) {
            this.className = exception.getClass().getName();
            this.exMessage = exception.getLocalizedMessage();
        }
    }
}
