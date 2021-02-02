package ru.progwards.java1.lessons.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test2 {
    public static void main(String[] args)  {
        String pathFile = "C:\\Java\\Progwards\\HW 28.01.2021 задания до 17\\src\\ru\\progwards\\java1\\lessons\\test\\File.txt";

        Test2 test2 = new Test2();
        test2.replaceF(pathFile);

    }

    boolean replaceF(String name)  {
        boolean result = false;
        Path path = Paths.get(name);
        Character charF = "F".charAt(0);
        try{
            String fileString = Files.readString(path);
            StringBuilder outString = new StringBuilder();
            for (Character character:fileString.toCharArray()){
                if(character.equals(charF)){
                    outString.append("f");
                }else {
                    outString.append(character);
                }
            }
             Files.writeString(path, outString.toString());
//            Files.ren
            result = true;
        }catch (IOException exception){
            return false;
        }


        return result;
    }
}
