package service;

import entity.Teacher;
import main.MainRun;
import util.DataUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TeacherService {

    public static final String TEACHER_DATA_FILE = "teacher.dat";

    public void addNewTeacher() {
        System.out.print("Nhập số giảng viên muốn thêm mới: ");
        int teacherNumber = -1;
        do {
            try {
                teacherNumber = new Scanner(System.in).nextInt();
                if (teacherNumber > 0) {
                    break;
                }
                System.out.print("Số giảng viên muốn thêm mới phải là số nguyên, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Số giảng viên muốn thêm mới phải là một số nguyên, vui lòng nhập lại: ");
            }
        } while (true);
        for (int i = 0; i < teacherNumber; i++) {
            Teacher teacher = new Teacher();
            teacher.inputInfo();
            addTeacherToArray(teacher);
        }
        MainRun.fileUtil.writeDataToFile(MainRun.teachers, TEACHER_DATA_FILE);
    }

    public void addTeacherToArray(Teacher teacher) {
        for (int i = 0; i < MainRun.teachers.length; i++) {
            if (DataUtil.isNullOrEmpty(MainRun.teachers[i])) {
                MainRun.teachers[i] = teacher;
            }
        }
    }

    public void showTeachers() {
        for (int i = 0; i < MainRun.teachers.length; i++) {
            Teacher teacher = MainRun.teachers[i];
            if (DataUtil.isNullOrEmpty(teacher)) {
                break;
            }
            System.out.println(teacher);
        }
    }

    public Teacher findTeacherById(int teacherId) {
        for (int i = 0; i < MainRun.teachers.length; i++) {
            if (teacherId == MainRun.teachers[i].getId()) {
                return MainRun.teachers[i];
            }
        }
        return null;
    }


}
