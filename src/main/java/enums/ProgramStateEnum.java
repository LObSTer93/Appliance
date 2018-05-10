package enums;

import lombok.Getter;

@Getter
public enum ProgramStateEnum implements BasicEnum{

    TOP_BOTTOM("Top/bottom"),
    AIR("Circulation air"),
    PRE_HEAT("Pre heat"),
    PIZZA("Pizza");

    private String value;

    ProgramStateEnum(String value) {
        this.value = value;
    }
}