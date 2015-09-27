package app;

import utils.FromJson;
import utils.ToJson;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class App {

    public static void main(String[] args ) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ParseException {
        String resultOfToJsonAction;

        Human human = new Human();
        human.setFirstName("Name");
        human.setLastName("Surname");
        human.setHobby("Dance");
        human.setBirthDate("31-05-1991");


        System.out.println("---To_JSON---");
        ToJson toJsonObj = new ToJson();
        resultOfToJsonAction = toJsonObj.toJson(human);
        System.out.println(resultOfToJsonAction);


        System.out.println("---From_JSON---");
        FromJson fromJsonObj = new FromJson();
        Human newHuman = (Human) fromJsonObj.fromJson(resultOfToJsonAction, human.getClass());
        System.out.println(newHuman);
    }
}
