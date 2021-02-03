package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class FileInfo {


    //Переменные класса
    String file_name;           //имя файла
//    LocalDateTime file_ldt;     //дата последнего изменения
    FileTime file_lmt;
    Date file_lmt_date;
    Integer file_size;          //размер файла
    Path file_path;             //абсолютный путь к файлу
    String fileLine;            //Содержимое файла

    //Конструктор класса
    public FileInfo(Path file_path) {
        File fileInfo = new File(file_path.toString());
        this.file_name = fileInfo.getName();
        this.file_path = file_path;
//        this.file_size = Integer.valueOf(Files.getAttribute(file_path, "size").toString());
        this.file_size = (int)fileInfo.length();
//        this.file_lmt = (FileTime)Files.getAttribute(file_path, "lastModifiedTime");
        file_lmt_date = new Date(fileInfo.lastModified());

        try {
            fileLine = Files.readString(this.file_path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getFileList(){
        List<String> fileInfoList = new ArrayList<>();
//        fileInfoList.add(file_name);
        fileInfoList.add(file_path.toString());
        return fileInfoList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;         //Подходит ли к проверке по содержимому?
        if (!(o instanceof FileInfo)) return false;
        FileInfo fileInfo = (FileInfo) o;
        if(!fileLine.equals(fileInfo.fileLine)) return false;
        return Objects.equals(file_name, fileInfo.file_name) &&
                Objects.equals(file_lmt_date, fileInfo.file_lmt_date) &&
                Objects.equals(file_size, fileInfo.file_size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file_name, file_lmt, file_size);
    }

    @Override
    public String toString() {
        return "File{" +
                "Имя ='" + file_name + '\'' +
                ", Посл.Изм =" + file_lmt_date +
                ", Размер =" + file_size +
                ", Путь =" + file_path +
                '}';
    }
}
