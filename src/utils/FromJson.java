package utils;

import app.Human;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FromJson {

    private static Map<String, String> mapFieldsNamesAndValuesOfOriginalObject = new HashMap<String, String>();
    private static Map<String, String> mapInputJson = new HashMap<String, String>();
    private static Field[] fields;
    private Object result;

    public Object fromJson(String json, Object clazz) throws NoSuchFieldException, IllegalAccessException, ParseException {

        getDeclaredFields(clazz);
        getNamesAndValuesFromObject(clazz);
        parseInputJsonToMap(json);
        result = createObjectFromJson(clazz);
        return  result;
    }


    private void getDeclaredFields(Object inputObject) throws IllegalAccessException, NoSuchFieldException {

        fields = ReflectionActions.getDeclaredFields(inputObject);
    }

    private void getNamesAndValuesFromObject(Object inputObject) throws NoSuchFieldException, IllegalAccessException {

        mapFieldsNamesAndValuesOfOriginalObject = ReflectionActions.getNamesAndValuesFromObject(inputObject, fields);
    }

    private void parseInputJsonToMap(String json) {
        String pattern = "(?=\")(.*)\"\\n";
        ArrayList<String> jsonList = Regex.getAllMatches(pattern, json);
        for (int i = 0; i < jsonList.size(); i++) {
            //System.out.println(jsonList.get(i));
            mapInputJson = Regex.setStringToMap(jsonList.get(i), (HashMap<String, String>) mapInputJson);
        }
    }

        private Object createObjectFromJson(Object inputObject) throws ParseException {
            Object result = null;
           // System.out.println(inputObject);
            if (inputObject instanceof Human){
                System.out.println("HUMAN = TRUE");
                Human newHuman = new Human();
                for (Map.Entry entryOriginalObject: mapFieldsNamesAndValuesOfOriginalObject.entrySet()){
                    for (Map.Entry entryInputJson: mapInputJson.entrySet()){

                        if (entryInputJson.getKey().equals(entryOriginalObject.getKey())){

                            if(entryInputJson.equals("firstName")){
                                newHuman.setFirstName((String) entryInputJson.getValue());
                            }
                            else if(entryInputJson.equals("lastName")){
                                newHuman.setLastName((String) entryInputJson.getValue());
                            }
                            else if (entryInputJson.equals("birthDate")){
                                newHuman.setBirthDate((String) entryInputJson.getValue());
                            }
                            else {
                                newHuman.setHobby((String) entryInputJson.getValue());
                            }
                        }
                    }
                    //System.out.println(entry.getKey() + " = " + entry.getValue());
                }
                result =  newHuman;
            }
            return result;
        }
}
