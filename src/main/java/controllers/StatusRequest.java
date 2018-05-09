package controllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusRequest {
    private String statusName;
    private String newState;
}