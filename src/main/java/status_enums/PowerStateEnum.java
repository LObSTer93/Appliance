package status_enums;

import lombok.Getter;

@Getter
public enum PowerStateEnum implements BasicEnum{

    ON("On"),
    OFF("Off");

    private String value;

    PowerStateEnum(String value) {
        this.value = value;
    }
}