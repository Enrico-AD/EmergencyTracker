package com.emergency.EmergencyTracker.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FieldMessage implements Serializable {

    private String fieldName;
    private String message;
}