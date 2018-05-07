package enums;

import lombok.Getter;

@Getter
public enum PowerStateEnum {

    ON("On"),
    OFF("Off");

    private String state;

    PowerStateEnum(String state) {
        this.state = state;
    }
}