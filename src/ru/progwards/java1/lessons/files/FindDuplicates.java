package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDuplicates {
    public static void main(String[] args){
//        String startPath = "C:\\Java\\Progwards\\HW 28.01.2021 задания до 17\\src\\ru\\progwards\\java1\\lessons\\files\\Minkin";
        String startPath = "C:\\Minkin";
        FindDuplicates findDuplicates = new FindDuplicates();
        findDuplicates.findDuplicates(startPath);
//        System.out.println(findDuplicates.findDuplicates(startPath));
    }

    //переменные класса
    public Set<FileInfo> duplicatesSet = new HashSet<>(); //Множество
    List<FileInfo> filesList = new ArrayList<>(); //Список всех файлов

    //
    public List<List<String>> findDuplicates(String startPath) {
        try {
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    FileInfo fileInfo = new FileInfo(path);
//                System.out.println(fileInfo);
                    if (filesList.contains(fileInfo)) {  //Проверка - повторяется ли файл в основном списке
                        duplicatesSet.add(fileInfo);    //Отдельное множество для повторяющихся, т.к. интересны только имена и др. повторяющихся
                    }
                    filesList.add(fileInfo);        //Общий список всех файлов
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            });

        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }

        filesList.retainAll(duplicatesSet);     //Исключаю не повторяющиеся файлы
        return getResultList();
    }

    //Метод для формирования итогового списка
    public List<List<String>> getResultList(){
        List<List<String>> resultList = new ArrayList<>();
        for(FileInfo fileInfo:duplicatesSet){
            List<String> duplicatedFilesString = new ArrayList<>();     //Создание списков путей одинаковых файлов
                                                                        //Количество списков определяется количеством элементов в SET
            for(FileInfo duplicateFromList:filesList){                  //Списки путей одинаковых файлов формируются путем перебора и копирования из общего списка
                if(duplicateFromList.equals(fileInfo)){
                    duplicatedFilesString.add(duplicateFromList.file_path.toString());
                }
            }
            resultList.add(duplicatedFilesString);
        }
        return resultList;
    }


}
