package utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


public class ToJson {

    private static Map<String, String> mapFieldsNamesAndValues = new HashMap<String, String>();
    protected static Map<String, String> mapJsonValueAnnotation = new HashMap<String, String>();
    protected static Map<String, String> mapCustomDateFormatAnnotation = new HashMap<String, String>();
    private static  StringBuilder jsonFinalResult = new StringBuilder();
    private static Field[] fields;
    private static Class<?> secretClass;
    private Object inputObject = null;


    public String  toJson(Object someObject) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        setInputObject(someObject);
        getDeclaredFields(someObject);
        getNamesAndValuesFromObject(someObject);
        getNamesAndValuesFromAnnotationsThatDeclaredInTheObject();
        createJson();

        return jsonFinalResult.toString();
    }

    private void setInputObject(Object inputObject) {

        this.inputObject = inputObject;
    }


    private void getDeclaredFields(Object inputObject) throws IllegalAccessException, NoSuchFieldException {
        secretClass = inputObject.getClass();
        fields = ReflectionActions.getDeclaredFields(inputObject);
    }


    private void getNamesAndValuesFromObject(Object inputObject) throws NoSuchFieldException, IllegalAccessException {

        mapFieldsNamesAndValues = ReflectionActions.getNamesAndValuesFromObject(inputObject, fields);
    }


    private void getNamesAndValuesFromAnnotationsThatDeclaredInTheObject() throws IllegalAccessException {

        ValidateAnnotations.processAnnotations(inputObject);
    }


    private void createJson (){

        String nameJsonValue = null;
        String valueJsonValue = null;
        String nameCusDatForm = null;
        String valueCusDatForm = null;

        for (Map.Entry entry: mapJsonValueAnnotation.entrySet()){
            nameJsonValue = (String) entry.getKey();
            valueJsonValue = (String) entry.getValue();
        }

        for (Map.Entry entry: mapCustomDateFormatAnnotation.entrySet()){
            nameCusDatForm = (String) entry.getKey();
            valueCusDatForm = (String) entry.getValue();
        }

        jsonFinalResult.append("{\n");

        for (Map.Entry entry : mapFieldsNamesAndValues.entrySet()){

            if(entry.getValue() != null){

                if(entry.getValue().toString().equals(valueJsonValue)){
                    jsonFinalResult.append("\"" + nameJsonValue + "\": \"" + entry.getValue() + "\"\n");
                }

                else if (entry.getValue().toString().equals(valueCusDatForm)){
                    jsonFinalResult.append("\"" + nameCusDatForm + "\": \"" + entry.getValue() + "\"\n");
                }

                else {
                    jsonFinalResult.append("\"" + entry.getKey() + "\": \"" + entry.getValue() + "\"\n");
                }
            }
        }

        jsonFinalResult.append("}");

    }
}