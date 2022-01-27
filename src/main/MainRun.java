package main;

import entity.Subject;
import entity.Teacher;
import entity.teaching.Teaching;
import service.SubjectService;
import service.TeacherService;
import service.TeachingService;
import util.DataUtil;
import util.file.FileUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainRun {

    public static Subject[] subjects;
    public static Teacher[] teachers;
    public static Teaching[] teachings;


    public static TeacherService teacherService = new TeacherService();
    public static SubjectService subjectService = new SubjectService();
    public static TeachingService teachingService = new TeachingService();

    public static FileUtil fileUtil = new FileUtil();


    public static void main(String[] args) {
        initializeData();
        menu();
    }

    private static void initializeData() {
        Object teacherFromFile = fileUtil.readDataFromFile(TeacherService.TEACHER_DATA_FILE);
        MainRun.teachers = DataUtil.isNullOrEmpty(teacherFromFile) ? new Teacher[100] : (Teacher[]) teacherFromFile;

        Object subjectFromFile = fileUtil.readDataFromFile(SubjectService.SUBJECT_DATA_FILE);
        MainRun.subjects = DataUtil.isNullOrEmpty(subjectFromFile) ? new Subject[100] : (Subject[]) subjectFromFile;

        Object teachingFromFile = fileUtil.readDataFromFile(TeachingService.TEACHING_DATA_FILE);
        MainRun.teachings = DataUtil.isNullOrEmpty(subjectFromFile) ? new Teaching[100] : (Teaching[]) teachingFromFile;
    }

    public static void menu() {
        do {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    subjectService.addNewSubject();
                    subjectService.showSubjects();
                    break;
                case 2:
                    teacherService.addNewTeacher();
                    teacherService.showTeachers();
                    break;
                case 3:
                    teachingService.createTeaching();
                    teachingService.showTeachings();
                    break;
                case 4:
                    teachingService.sortTeaching();
                    break;
                case 5:
                    teachingService.salaryCalculation();
                    break;
                case 6:
                    System.exit(0);
            }
        } while (true);
    }


    public static int functionChoice() {
        System.out.println("QUẢN LÝ TRẢ LƯƠNG CHO GIẢNG VIÊN THỈNH GIẢNG");
        System.out.println("1. Nhập danh sách môn học mới và in ra danh sách môn học trong trường");
        System.out.println("2. Nhập danh sách giảng viên mới và in ra danh sách giảng viên trong trường");
        System.out.println("3. Phân công giảng dạy cho giảng viên và in ra danh sách giảng dạy");
        System.out.println("4. Sắp xếp danh sách giảng dạy");
        System.out.println("5. Tính toán và lập bảng lương");
        System.out.println("6. Thoát");
        System.out.println("----------------------------------------------");
        System.out.print("Chọn chức năng: ");
        do {
            try {
                int choice = new Scanner(System.in).nextInt();
                if (choice >= 1 && choice <= 6) {
                    return choice;
                }
                System.out.print("Chức năng khả dụng là một số nguyên từ 1 tới 6, vui lòng chọn lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Chức năng chọn phải là một số nguyên, vui lòng nhập lại: ");
            }
        } while (true);
    }

}
