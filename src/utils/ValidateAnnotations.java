package utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import annotations.CustomDateFormat;
import annotations.JsonValue;

public class ValidateAnnotations extends ToJson {

    public static void processAnnotations(Object obj) {
        try {
            Class cl = obj.getClass();
            for(Field f : cl.getDeclaredFields()) {
                try {
                    // Processing all the annotations on a single field
                    for(Annotation a : f.getAnnotations()) {
                        // Checking annotations
                        if(a.annotationType() == JsonValue.class) {
                            JsonValue jsonValue = (JsonValue) a;
                            //System.out.println("Processing the field : " + jsonValue.name());
                            f.setAccessible(true);
                            if (f.get(obj) == null) {
                                throw new NullPointerException("The value of the field " + f.toString() + "can't be NULL.");
                            } else {
                                // System.out.println("Value of the Object : " + f.get(obj));
                                ToJson.mapJsonValueAnnotation.put(jsonValue.name(),f.get(obj).toString());
                            }
                        }
                        if(a.annotationType() == CustomDateFormat.class) {
                            CustomDateFormat customDateFormatValue = (CustomDateFormat) a;
                            //System.out.println("Processing the field : " + customDateFormatValue.format());
                            f.setAccessible(true);
                            if(f.get(obj) == null) {
                                throw new NullPointerException("The value of the field " + f.toString() + "can't be NULL.");
                            }

                            else {
                                //System.out.println("Value of the Object : " + f.get(obj));
                                ToJson.mapJsonValueAnnotation.put(customDateFormatValue.format(), f.get(obj).toString());
                            }
                        }
                    }
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}