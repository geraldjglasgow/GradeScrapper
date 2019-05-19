package objects;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    @Column(name = "assignment_id")
    @GeneratedValue(generator = "incrementor")
    private int assignmentId;
    @Column(name = "due_date")
    public Date dueDate;
    @Column(name = "category")
    public String category;
    @Column(name = "name")
    public String name;
    @Column(name = "score")
    public double score;
    @Column(name = "worth")
    public double worth;
    @Column(name = "percentage")
    public double percentage;
    @Column(name = "grade")
    public String grade;
    @Column(name = "graded")
    public boolean graded;

    @ManyToOne
    @JoinColumn(name = "crs_name", referencedColumnName = "course_name")
    private Course crs_name;

    public Course getCourse() {
        return crs_name;
    }

    public void setCourse(Course crs_name) {
        this.crs_name = crs_name;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public boolean isGraded() {
        return graded;
    }

    public void setGraded(boolean graded) {
        this.graded = graded;
    }

    public Date getDate() {
        return dueDate;
    }

    public void setDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


}
