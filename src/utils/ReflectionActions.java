package utils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class ReflectionActions {

    public static  Field[] getDeclaredFields(Object inputObject) throws IllegalAccessException, NoSuchFieldException {
        Field[] fields = inputObject.getClass().getDeclaredFields();

        return fields;
    }

    public static Map<String, String> getNamesAndValuesFromObject(Object inputObject, Field[] fields) throws NoSuchFieldException, IllegalAccessException {
        Map<String, String> mapFieldsNamesAndValues = new HashMap<String, String>();

        for (Field field : fields) {
            // System.out.println("Field Name: " + field.getName());
            String fieldName = field.getName();
            Field thisFild = inputObject.getClass().getDeclaredField(fieldName);
            thisFild.setAccessible(true);

            if (thisFild.get(inputObject) instanceof LocalDate){
                String fieldValue = thisFild.get(inputObject).toString();
                //  System.out.println("fieldValue = " + fieldValue);
                mapFieldsNamesAndValues.put(fieldName, fieldValue);
            }
            else{
                String fieldValue = "" + thisFild.get(inputObject);
                // System.out.println("fieldValue = " + fieldValue);
                mapFieldsNamesAndValues.put(fieldName, fieldValue);
            }
        }
        return mapFieldsNamesAndValues;
    }

}
