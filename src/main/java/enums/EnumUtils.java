package enums;

public class EnumUtils {

    public static boolean isEnumContainsValue(BasicEnum[] basicEnums, String value){
        for(BasicEnum basicEnum: basicEnums){
            if(basicEnum.getValue().equals(value)){
                return true;
            }
        }
        return false;
    }
}