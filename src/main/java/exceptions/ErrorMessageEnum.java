package exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessageEnum {
    NO_STATUS_MESSAGE("No status information is presented"),
    NO_STATE_MESSAGE("No state information is presented"),
    INCORRECT_STATUS_MESSAGE("There is no such status"),
    INCORRECT_STATE_MESSAGE("There is no such state");

    private final String message;

    ErrorMessageEnum(String message){
        this.message = message;
    }
}