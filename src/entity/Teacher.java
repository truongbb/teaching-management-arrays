package entity;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Teacher extends Person {

    public static int AUTO_ID = 100;

    public static final String GS_TS = "Giáo sư - Tiến sĩ";
    public static final String PGS_TS = "Phó giáo sư - Tiến sĩ";
    public static final String ThS = "Thạc sĩ";
    public static final String GVC = "Giảng viên chính";

    private int id;
    private String teacherLevel;

    public int getId() {
        return id;
    }

    public String getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(String teacherLevel) {
        this.teacherLevel = teacherLevel;
    }

    @Override
    public void inputInfo() {
        this.id = Teacher.AUTO_ID;
        Teacher.AUTO_ID++;
        super.inputInfo();
        System.out.println("Chọn trình độ của giảng viên: ");
        System.out.println(" 1. Giáo sư - Tiến sĩ");
        System.out.println(" 2. Phó giáo sư - Tiến sĩ");
        System.out.println(" 3. Thạc sĩ");
        System.out.println(" 4. Giảng viên chính");
        System.out.println("----------------------");
        System.out.print("Vui lòng chọn: ");
        do {
            try {
                int choice = new Scanner(System.in).nextInt();
                if (choice >= 1 && choice <= 4) {
                    switch (choice) {
                        case 1:
                            this.teacherLevel = Teacher.GS_TS;
                            break;
                        case 2:
                            this.teacherLevel = Teacher.PGS_TS;
                            break;
                        case 3:
                            this.teacherLevel = Teacher.ThS;
                            break;
                        case 4:
                            this.teacherLevel = Teacher.GVC;
                            break;
                    }
                }
                System.out.print("Trình độ là một số nguyên từ 1 tới 4, vui lòng chọn lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Trình độ chọn phải là một số nguyên, vui lòng nhập lại: ");
            }
        } while (true);
    }
}
