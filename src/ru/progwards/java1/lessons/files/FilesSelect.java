package ru.progwards.java1.lessons.files;
/*Реализовать метод с сигнатурой
public void selectFiles(String inFolder, String outFolder, List<String> keys),
который сортирует файлы по их содержимому.
Нужно просмотреть содержимое всех файлов,
с расширением txt, содержащихся в каталоге inFolder с подкаталогами,
и если файл содержит ключевое слово из коллекции keys,
то скопировать его в подпапку с соответствующим именем,
этого элемента keys, все подпапки должны находиться в outFolder.

Например, вызвана функция с параметрами (“aaa”, “bbb”, {“check”, “files”} )
нужно проверить каталог aaa с подкаталогами, найти там все файлы txt,
и если файл содержит “check”, скопировать его в папку bbb/check,
если файл содержит “files”, скопировать его в папку bbb/files.
Важно! Если, например, слово “files” ни разу не встретилось, пустую папку создавать не нужно*/

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesSelect {
    public static void main(String[] args) throws IOException {
        String inFolder = "C:\\Minkin";
        String outFolder = "C:\\Minkin\\Task2";
        List<String> keys = new ArrayList<>(Arrays.asList("Реализовать", "метод", "сигнатурой"));
        FilesSelect filesSelect = new FilesSelect();
        filesSelect.selectFiles(inFolder, outFolder, keys);
    }

    //переменные класса
    private Path inFolderPath;
    private Path outFolderPath;
    private List<String> keys = new ArrayList<>();

    List<String> pathsList = new ArrayList<>();


    //Сортировка файлов по их содержимому
    public void selectFiles(String inFolder, String outFolder, List<String> keys) throws IOException {
        inFolderPath = Path.of(inFolder);
        outFolderPath = Path.of(outFolder);
        this.keys = keys;
//        newDir(keys.get(0));
        findFiles();
    }

    //Метод для поиска файлов
    private void findFiles() throws IOException {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.txt");
        Files.walkFileTree(inFolderPath, new SimpleFileVisitor<>(){
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if(pathMatcher.matches(path)){
//                    System.out.println(path);
                    checkKeysInFile(path);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc){
                return FileVisitResult.CONTINUE;
            }
        });
    }

    //Метод поиска слов в файлах
    private void checkKeysInFile(Path filePath) throws IOException {
        String fileContent = Files.readString(filePath);
        for(String key:keys){
            if(fileContent.contains(key)){
                System.out.println(filePath);
                if(!findDirNamedKey(key)){
                    newDir(key);
                }
                doCopyFile(filePath, key);
            }
        }
    }

    //Метод для копирования файла
    private void doCopyFile(Path filePath, String key) throws IOException {
        Path pathTargetDir = outFolderPath.resolve(key);
        Path relPath = filePath.relativize(pathTargetDir);
        Path p = filePath.getFileName();
        Path p2 = pathTargetDir.resolve(p);
        Files.copy(filePath, p2, StandardCopyOption.REPLACE_EXISTING);

    }

    //Поиск каталогов keys
    private boolean findDirNamedKey(String key){
        File targetDir = new File(outFolderPath.toString());
        File[] listDirs = targetDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        if(listDirs != null) {
            for (File dir : listDirs) {
                if (dir.getName().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Метод для создания каталога
    private void newDir(String nameDir) throws IOException {
        Path pathNewDir = outFolderPath.resolve(nameDir);
        Files.createDirectory(pathNewDir);
    }
}
