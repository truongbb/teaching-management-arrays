package entity.teaching;

import entity.Subject;
import entity.Teacher;
import util.DataUtil;

public class Teaching {

    private Teacher teacher;
    private SubjectTeaching[] subjectTeachings;

    public Teaching() {
    }

    public Teaching(Teacher teacher, SubjectTeaching[] subjectTeachings) {
        this.teacher = teacher;
        this.subjectTeachings = subjectTeachings;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public SubjectTeaching[] getSubjectTeachings() {
        return subjectTeachings;
    }

    public void setSubjectTeachings(SubjectTeaching[] subjectTeachings) {
        this.subjectTeachings = subjectTeachings;
    }

    public int getCurrentTotalLesson() {
        if (DataUtil.isNullOrEmptyArray(subjectTeachings)) {
            return 0;
        }
        int currentTotalLesson = 0;
        for (int i = 0; i < subjectTeachings.length; i++) {
            Subject subject = subjectTeachings[i].getSubject();
            int classQuantity = subjectTeachings[i].getClassQuantity();
            currentTotalLesson += subject.getTotalLesson() * classQuantity;
        }
        return currentTotalLesson;
    }


}
