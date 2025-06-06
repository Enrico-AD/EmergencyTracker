package com.emergency.EmergencyTracker.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ValidationError extends StandardError implements Serializable {

    private List<FieldErrorVO> errors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public void addError(String field, String message) {
        errors.add(new FieldErrorVO(field, message));
    }

    @Data
    private static class FieldErrorVO implements Serializable {
        private String field;
        private String message;

        public FieldErrorVO(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}