package enums;

import lombok.Getter;

@Getter
public enum ProgramStateEnum {

    TOP_BOTTOM("Top/bottom"),
    AIR("Circulation air"),
    PRE_HEAT("Pre heat"),
    PIZZA("Pizza");

    private String state;

    ProgramStateEnum(String state) {
        this.state = state;
    }
}