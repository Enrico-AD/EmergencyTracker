package com.emergency.EmergencyTracker.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RestExceptionMessage {
    PROFESSOR_CRIACAO_INTEGRIDADE_DADOS_EMAIL_DUPLICADO(1000, HttpStatus.INTERNAL_SERVER_ERROR),
    PROFESSOR_CRIACAO_ESCOLA_INEXISTENTEI(1005, HttpStatus.INTERNAL_SERVER_ERROR);

    public int code;
    public int httpCode;
    private RestExceptionMessage(int id, HttpStatus httpCode) {
        this.code = id;
        this.httpCode = httpCode.value();
    }

    public ResponseEntity getResponse() {
        return ResponseEntity.status(this.httpCode).body("{\"code\": " + this.code + "}");
    }

}
