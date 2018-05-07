package enums;

import lombok.Getter;

@Getter
public enum StatusEnum {

    POWER("Power"),
    DOOR("Door"),
    PROGRAM("Program");

    private String name;

    StatusEnum(String name) {
        this.name = name;
    }
}