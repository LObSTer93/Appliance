package enums;

import lombok.Getter;

@Getter
public enum DoorStateEnum {

    OPEN("Open"),
    CLOSED("Closed");

    private String state;

    DoorStateEnum(String state) {
        this.state = state;
    }
}