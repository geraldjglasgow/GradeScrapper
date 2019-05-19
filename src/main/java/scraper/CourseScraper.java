package scraper;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import objects.Course;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CourseScraper implements Scraper {
    private static final int FIRST_SEMESTER_GRADE_INDEX = 15;
    private static final int SECOND_SEMESTER_GRADE_INDEX = 19;
    List<WebElement> webElementCourses;

    public void scrape() {
        WebDriver driver = ScraperFactory.getDriver();
        List<Course> courses = Lists.newArrayList();
        webElementCourses = ScraperUtils.getClassRowWebElements(driver);
        for (int i = 3; i < webElementCourses.size() - 3; i++) {
            List<WebElement> tds = webElementCourses.get(i).findElements(By.tagName("td"));
            Course course = new Course();
            if (Strings.isNullOrEmpty(tds.get(FIRST_SEMESTER_GRADE_INDEX).getText())) {
                course.setFinalGrade(getFinalGrade(tds.get(FIRST_SEMESTER_GRADE_INDEX).getText()));
                course.setFinalLetterGrade(getFinalLetterGrade(tds.get(FIRST_SEMESTER_GRADE_INDEX).getText()));
                course.setSemester(1);
            } else if (Strings.isNullOrEmpty(tds.get(SECOND_SEMESTER_GRADE_INDEX).getText())) {
                course.setFinalGrade(getFinalGrade(tds.get(SECOND_SEMESTER_GRADE_INDEX).getText()));
                course.setFinalLetterGrade(getFinalLetterGrade(tds.get(SECOND_SEMESTER_GRADE_INDEX).getText()));
                course.setSemester(2);
            }

            course.setName(getClassName(tds.get(11).getText()));
            /*getSemester();
            getYear(); */
        }
    }

    private Double getFinalGrade(String stringWithGrade) {
        String[] parts = stringWithGrade.split("\n");
        return Double.parseDouble(parts[1]);
    }

    private String getFinalLetterGrade(String stringWithLetterGrade) {
        String[] parts = stringWithLetterGrade.split("\n");
        return parts[0];
    }

    public String getClassName(String stringWithClassName) {
        String[] parts = stringWithClassName.split("\n");
        String classname;
        if (parts[parts.length - 2].length() > 1 && parts[parts.length - 3].length() > 1) {
            classname = parts[parts.length - 3] + " " + parts[parts.length - 2] + " " + parts[parts.length - 1];
        } else if (parts[parts.length - 2].length() > 1) {
            classname = parts[parts.length - 2] + " " + parts[parts.length - 1];
        } else {
            classname = parts[parts.length - 1];
        }
        return classname;
    }

    public Integer parseTardies() {
        return 0;
    }

    public Integer parseAbscences() {
        return 0;
    }
}
