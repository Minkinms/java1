package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;

public class FileInfo {

    //Переменные класса
    String file_name;           //имя файла
    Date file_date;             //дата последнего изменения
    Integer file_size;          //размер файла
    Path file_path;             //абсолютный путь к файлу
    String fileLine;            //Содержимое файла

    //Конструктор класса
    public FileInfo(Path file_path) {
        File fileInfo = new File(file_path.toString());
        this.file_name = fileInfo.getName();
        this.file_path = file_path;
        this.file_size = (int)fileInfo.length();
        file_date = new Date(fileInfo.lastModified());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileInfo)) return false;
        FileInfo fileInfo = (FileInfo) o;
        return Objects.equals(file_name, fileInfo.file_name) &&
                Objects.equals(file_date, fileInfo.file_date) &&
                Objects.equals(file_size, fileInfo.file_size) &&
                checkContent(fileInfo);         //Проверка по содержимому.
                                                // Выполняю последней, чтобы не считывать файлы лишний раз.
    }

    //Метод для проверки по содержимому
    public boolean checkContent(FileInfo fileToCheck){
            getFileString();
            fileToCheck.getFileString();
            return fileLine.equals(fileToCheck.fileLine);
    }

    //Метод для считывания содержимого файла
    public void getFileString(){
        try {
            fileLine = Files.readString(this.file_path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(file_name, file_date, file_size);
    }

    @Override
    public String toString() {
        return "File{" +
                "Имя ='" + file_name + '\'' +
                ", Посл.Изм =" + file_date +
                ", Размер =" + file_size +
                ", Путь =" + file_path +
                '}';
    }
}
