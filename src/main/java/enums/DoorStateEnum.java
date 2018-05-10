package enums;

import lombok.Getter;

@Getter
public enum DoorStateEnum implements BasicEnum{

    OPEN("Open"),
    CLOSED("Closed");

    private String value;

    DoorStateEnum(String value) {
        this.value = value;
    }
}