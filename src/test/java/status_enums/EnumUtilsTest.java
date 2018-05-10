package status_enums;

import org.junit.Test;

import static org.junit.Assert.*;

public class EnumUtilsTest {

    @Test
    public void isEnumContainsValue() {
        boolean is = EnumUtils.isEnumContainsValue(StatusEnum.values(), "light");
        assertFalse(is);

        is = EnumUtils.isEnumContainsValue(StatusEnum.values(), "Power");
        assertTrue(is);
    }
}