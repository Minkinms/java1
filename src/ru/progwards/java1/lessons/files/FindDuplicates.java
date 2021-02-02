package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDuplicates {
    public static void main(String[] args) throws IOException {
//        String startPath = "C:\\Java\\Progwards\\HW 28.01.2021 задания до 17\\src\\ru\\progwards\\java1\\lessons\\files\\Minkin";
        String startPath = "C:\\Minkin";
        FindDuplicates findDuplicates = new FindDuplicates();
        findDuplicates.findDuplicates(startPath);
        System.out.println(findDuplicates.findDuplicates(startPath));
    }

    //переменные класса
    public Set<FileInfo> duplicatesSet = new HashSet<>(); //Множество
    List<FileInfo> filesList = new ArrayList<>(); //Список всех файлов

    //
    public List<List<String>> findDuplicates(String startPath) throws IOException {
        List<List<String>> resultList = new ArrayList<>();
        Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs){
                FileInfo fileInfo = new FileInfo(path);
//                System.out.println(fileInfo);
                if(filesList.contains(fileInfo)){
                    duplicatesSet.add(fileInfo);
                }
                filesList.add(fileInfo);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc){
                return FileVisitResult.CONTINUE;
            }
        });

        filesList.retainAll(duplicatesSet);
        for (FileInfo fileInfo:filesList){
            resultList.add(fileInfo.getFileList());
        }

        return resultList;
    }

//    public File getNextFile(Path path){
//        return
//    }


}
