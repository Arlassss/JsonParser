package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Regex {
    public static boolean isExist(String pattern, String testString){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    public static ArrayList<String> getAllMatches(String patternString, String testString){
        ArrayList<String> list = new ArrayList<String>();
        Pattern pattern  = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(testString);
        while(matcher.find()) {
            String s = (String) testString.subSequence(matcher.start(), matcher.end());
            list.add(s);
        }
        return list;
    }


    public static HashMap<String, String> setStringToMap(String str, HashMap<String, String> hm){
        String[] parts = str.split(":");
        String key = parts[0].trim().replaceAll("\"", "");
        String value = parts[1].trim().replaceAll("\"", "");
        hm.put(key, value);
        return hm;
    }

}
