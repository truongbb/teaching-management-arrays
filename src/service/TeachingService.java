package service;

import entity.Subject;
import entity.Teacher;
import entity.teaching.SubjectTeaching;
import entity.teaching.Teaching;
import main.MainRun;
import util.DataUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TeachingService {

    public static final String TEACHING_DATA_FILE = "teaching.dat";

    private static boolean isNullOfEmptyTeacherOrSubject() {
        return MainRun.teachers.length == 0 || MainRun.subjects.length == 0;
    }


    public void createTeaching() {
        if (isNullOfEmptyTeacherOrSubject()) {
            System.out.println("Bạn cần nhập thông tin giảng viên và môn học trước khi phân công giảng dạy.");
            return;
        }
        int teacherNumber = inputNumberOfTeacher();

        for (int i = 0; i < teacherNumber; i++) {
            Teacher teacher = inputTeacherId(i);
            System.out.println("Giảng viên vừa chọn là: " + teacher);

            int totalLesson = getTotalLesson(teacher);
            if (totalLesson >= 200) {
                System.out.println("Giảng viên bạn chọn đã vượt quá số tiết cho phép dạy, vui lòng phân công cho giảng viên khác");
                continue;
            }
            int subjectNumber = inputSubjectNumber();

            SubjectTeaching[] subjectTeachings = new SubjectTeaching[subjectNumber];
            createSubjectTeaching(subjectTeachings, subjectNumber, teacher);

            Teaching teaching = new Teaching(teacher, subjectTeachings);
            addTeachingToArray(teaching);
            MainRun.fileUtil.writeDataToFile(MainRun.teachings, TEACHING_DATA_FILE);
        }
    }

    private void addTeachingToArray(Teaching teaching) {
        for (int k = 0; k < MainRun.teachings.length; k++) {
            if (DataUtil.isNullOrEmpty(MainRun.teachings[k])) {
                MainRun.teachings[k] = teaching;
                break;
            }
        }
    }

    private void createSubjectTeaching(SubjectTeaching[] subjectTeachings, int subjectNumber, Teacher teacher) {
        for (int j = 0; j < subjectNumber; j++) {
            Subject subject = inputSubjectId(j, teacher);
            int classQuantity = inputClassQuantity(subject, j, teacher);
            SubjectTeaching subjectTeaching = new SubjectTeaching(subject, classQuantity);
            addSubjectTeachingToArray(subjectTeaching, subjectTeachings);
            // check xem số lượng môn học đã dạy từ trước cộng với số lượng môn học chuẩn bị đăng ký có vượt quá 200 không
            // tìm trong mảng môn học xem có môn học nào thỏa mãn không
            // ==> có, cho dạy tiếp
            // ==> không, không cho đăng ký thêm
        }
    }

    private int inputClassQuantity(Subject subject, int j, Teacher teacher) {
        System.out.print("Nhập số lớp của môn " + subject.getName() + " mà giảng viên " + teacher.getFullName() + " muốn dạy: ");
        int classQuantity = -1;
        do {
            try {
                classQuantity = new Scanner(System.in).nextInt();
                if (classQuantity > 0 && classQuantity <= 3) {

                    int totalLessonTmp = getTotalLesson(teacher);
                    totalLessonTmp += subject.getTotalLesson() * classQuantity;
                    if (totalLessonTmp > 200) {
                        System.out.print("Nếu số lớp đăng ký dạy là " + classQuantity + " với môn học này thì tổng số tiết giảng dạy của giảng viên sẽ vượt quá 200, " + "vui lòng thu nhỏ số lớp giảng dạy cho môn học này: ");
                        continue;
                    }
                    break;
                }
                System.out.print("Số lớp giảng dạy cho 1 môn học là số nguyên dương và không được lớn hơn 3, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Số lớp muốn dạy phải là một số nguyên dương, không phải là chữ, vui lòng nhập lại: ");
            }
        } while (true);
        return classQuantity;
    }

    private Subject inputSubjectId(int j, Teacher teacher) {
        System.out.print("Nhập ID môn học thứ " + (j + 1) + "  mà giảng viên " + teacher.getFullName() + "muốn dạy: ");
        Subject subject = null;
        do {
            try {
                int subjectId = new Scanner(System.in).nextInt();
                subject = MainRun.subjectService.findSubjectById(subjectId);
                if (!DataUtil.isNullOrEmpty(subject)) {
                    break;
                }
                System.out.print("ID môn học vừa nhập không tồn tại trong hệ thống, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("ID môn học phải là số nguyên dương, không phải là chữ, vui lòng nhập lại: ");
            }
        } while (true);
        return subject;
    }

    private void addSubjectTeachingToArray(SubjectTeaching subjectTeaching, SubjectTeaching[] subjectTeachings) {
        for (int k = 0; k < subjectTeachings.length; k++) {
            if (DataUtil.isNullOrEmpty(subjectTeachings[k])) {
                subjectTeachings[k] = subjectTeaching;
                break;
            }
        }
    }

    private int inputSubjectNumber() {
        System.out.print("Nhập số lượng môn học mà giảng viên này muốn dạy: ");
        int subjectNumber = -1;
        do {
            try {
                subjectNumber = new Scanner(System.in).nextInt();
                if (subjectNumber > 0) {
                    break;
                }
                System.out.print("Số lớp phải là số nguyên, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Số lớp là một số nguyên, vui lòng nhập lại: ");
            }
        } while (true);
        return subjectNumber;
    }

    private Teacher inputTeacherId(int no) {
        System.out.print("Nhập ID của giảng viên thứ " + (no + 1) + " mà bạn muốn phân công: ");
        Teacher teacher = null;
        do {
            try {
                int teacherId = new Scanner(System.in).nextInt();
                teacher = MainRun.teacherService.findTeacherById(teacherId);
                if (!DataUtil.isNullOrEmpty(teacher)) {
                    break;
                }
                System.out.print("ID giảng viên vừa nhập không tồn tại trong hệ thống, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("ID giảng viên phải là một số nguyên dương, không phải là chữ, vui lòng nhập lại: ");
            }
        } while (true);
        return teacher;
    }

    private int inputNumberOfTeacher() {
        System.out.print("Nhập số giảng viên muốn phân công giảng dạy: ");
        int teacherNumber = -1;
        do {
            try {
                teacherNumber = new Scanner(System.in).nextInt();
                if (teacherNumber > 0) {
                    break;
                }
                System.out.print("Số giảng viên phải là số nguyên, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Số giảng viên là một số nguyên, vui lòng nhập lại: ");
            }
        } while (true);
        return teacherNumber;
    }

    private int getTotalLesson(Teacher teacher) {
//        if (DataUtil.isNullOrEmpty(teacher)) {
//            return 0;
//        }
        Teaching teaching = findTeaching(teacher.getId());
        if (DataUtil.isNullOrEmpty(teaching)) {
            return 0;
        }
        return teaching.getCurrentTotalLesson();
    }


    private Teaching findTeaching(int teacherId) {
        for (int i = 0; i < MainRun.teachings.length; i++) {
            if (MainRun.teachings[i].getTeacher().getId() == teacherId) {
                return MainRun.teachings[i];
            }
        }
        return null;
    }


    public void showTeachings() {
        for (int i = 0; i < MainRun.teachings.length; i++) {
            if (DataUtil.isNullOrEmpty(MainRun.teachings[i])) {
                break;
            }
            System.out.println(MainRun.teachings[i]);
        }
    }

    public void sortTeaching() {
        //TODO - 4.1, 4.2
    }

    public void salaryCalculation() {
        // TODO - 5
    }

}

