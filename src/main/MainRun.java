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
        System.out.println("QU???N L?? TR??? L????NG CHO GI???NG VI??N TH???NH GI???NG");
        System.out.println("1. Nh???p danh s??ch m??n h???c m???i v?? in ra danh s??ch m??n h???c trong tr?????ng");
        System.out.println("2. Nh???p danh s??ch gi???ng vi??n m???i v?? in ra danh s??ch gi???ng vi??n trong tr?????ng");
        System.out.println("3. Ph??n c??ng gi???ng d???y cho gi???ng vi??n v?? in ra danh s??ch gi???ng d???y");
        System.out.println("4. S???p x???p danh s??ch gi???ng d???y");
        System.out.println("5. T??nh to??n v?? l???p b???ng l????ng");
        System.out.println("6. Tho??t");
        System.out.println("----------------------------------------------");
        System.out.print("Ch???n ch???c n??ng: ");
        do {
            try {
                int choice = new Scanner(System.in).nextInt();
                if (choice >= 1 && choice <= 6) {
                    return choice;
                }
                System.out.print("Ch???c n??ng kh??? d???ng l?? m???t s??? nguy??n t??? 1 t???i 6, vui l??ng ch???n l???i: ");
            } catch (InputMismatchException ex) {
                System.out.print("Ch???c n??ng ch???n ph???i l?? m???t s??? nguy??n, vui l??ng nh???p l???i: ");
            }
        } while (true);
    }

}
