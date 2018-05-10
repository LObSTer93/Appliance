package status_enums;

import lombok.Getter;

@Getter
public enum StatusEnum implements BasicEnum{

    POWER("Power"),
    DOOR("Door"),
    PROGRAM("Program");

    private String value;

    StatusEnum(String value) {
        this.value = value;
    }
}