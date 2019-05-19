package objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "course")
public class Course implements Serializable {
    @Id
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "semester")
    private int semester;
    @Column(name = "absences")
    private int absences;
    @Column(name = "tardies")
    private int tardies;
    @Column(name = "finalGrade")
    private double finalGrade;

    @Column(name = "finalLetterGrade")
    private String finalLetterGrade;

    public Course() {
        courseName = "";
        absences = 0;
        tardies = 0;
        finalGrade = 0.0;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getFinalLetterGrade() {
        return finalLetterGrade;
    }

    public void setFinalLetterGrade(String finalLetterGrade) {
        this.finalLetterGrade = finalLetterGrade;
    }

    public void setName(String name) {
        this.courseName = name;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public void setAbsences(int abscences) {
        this.absences = abscences;
    }

    public void setTardies(int tardies) {
        this.tardies = tardies;
    }

    public String getClassname() {
        return courseName;
    }

    public int getAbsences() {
        return absences;
    }

    public int getTardies() {
        return tardies;
    }
}
