package entity;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Subject {

    public static int AUTO_ID = 100;

    private int id;
    private String name;
    private int totalLesson;
    private int theoryLesson;
    private float lessonCost;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalLesson() {
        return totalLesson;
    }

    public void setTotalLesson(int totalLesson) {
        this.totalLesson = totalLesson;
    }

    public int getTheoryLesson() {
        return theoryLesson;
    }

    public void setTheoryLesson(int theoryLesson) {
        this.theoryLesson = theoryLesson;
    }

    public float getLessonCost() {
        return lessonCost;
    }

    public void setLessonCost(float lessonCost) {
        this.lessonCost = lessonCost;
    }

    public void inputInfo() {
        this.id = this.AUTO_ID;
        this.AUTO_ID++;
        System.out.print("Nhập tên môn học: ");
        this.name = new Scanner(System.in).nextLine();

        System.out.print("Nhập tổng số tiết: ");
        int tempTotalLesson = -1;
        do {
            try {
                tempTotalLesson = new Scanner(System.in).nextInt();
                if (tempTotalLesson > 0) {
                    this.totalLesson = tempTotalLesson;
                    break;
                }
                System.out.print("Tổng số tiết phải là số nguyên, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Tổng số tiết phải là một số nguyên, vui lòng nhập lại: ");
            }
        } while (true);

        System.out.print("Nhập số tiết lý thuyết: ");
        this.theoryLesson = new Scanner(System.in).nextInt();

        System.out.print("Nhập mức kinh phí: ");
        this.lessonCost = new Scanner(System.in).nextFloat();
    }

    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", name='" + name + '\'' + ", totalLesson=" + totalLesson + ", theoryLesson=" + theoryLesson + ", lessonCost=" + lessonCost + '}';
    }
}
