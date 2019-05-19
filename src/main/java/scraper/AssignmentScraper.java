package scraper;

import com.google.common.collect.Lists;
import dao.AssignmentDao;
import dao.CourseDao;
import objects.Assignment;
import objects.Course;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class AssignmentScraper implements Scraper {
    // get all links to assignments
    List<Course> courses = Lists.newArrayList();

    public void scrape() {
        ScraperFactory.setDriverUrl(URL);
        ScraperFactory.login();
        getLinks(ScraperFactory.getDriver());
    }

    private void getLinks(WebDriver driver) {
        String courseName;
        String selectLinkOpenInNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
        List<WebElement> element = driver.findElements(By.className("linkDescList"));
        List<WebElement> elements = element.get(0).findElements(By.tagName("tr"));
        for (int i = 3; i < elements.size() - 3; i++) {
            List<WebElement> tds = elements.get(i).findElements(By.tagName("td"));
            tds.get(19).findElements(By.className("bold")).get(0).sendKeys(selectLinkOpenInNewTab);
            courseName = getCourseNameFromString(tds.get(11).getText());

            ScraperUtils.sleep(3000);
            String currentWindow = driver.getWindowHandle();
            Set<String> windows = driver.getWindowHandles();

            for (String win : windows) {
                if (!win.equals(currentWindow)) {
                    driver.switchTo().window(win);
                    ScraperUtils.sleep(1000);
                    WebElement assignmentsTable = driver.findElement(By.id("scoreTable"));
                    List<WebElement> assignments = assignmentsTable.findElements(By.tagName("tr"));
                    for (int k = 1; k < assignments.size() - 1; k++) {
                        List<WebElement> td = assignments.get(k).findElements(By.tagName("td"));

                        Assignment assignment = new Assignment();

                        assignment.setDate(getDateFromString(td.get(0).getText()));
                        assignment.setCategory(td.get(1).getText());
                        assignment.setName(td.get(2).getText());
                        assignment.setGraded(isAssignmentGraded(td.get(10).getText()));
                        assignment.setScore(getScoreEarned(td.get(10).getText()));
                        assignment.setWorth(getTotalPoints(td.get(10).getText()));
                        Double percentage = assignment.isGraded() ? Double.parseDouble(td.get(11).getText()) : 0;
                        assignment.setPercentage(percentage);
                        assignment.setGrade(td.get(12).getText());
                        CourseDao courseDao = new CourseDao();
                        Course course = new Course();
                        course.setName(courseName);
                        Course persistedCourse = courseDao.retrieveCourse(courseName);
                        if (persistedCourse == null) {
                            courseDao.persistCourse(course);
                        }
                        if (course.getClassname() != null) {
                            assignment.setCourse(course);
                        }
                        AssignmentDao assignmentDao = new AssignmentDao();
                        assignmentDao.persistAssignment(assignment);
                    }
                    driver.close();
                    ScraperUtils.sleep(1000);
                    driver.switchTo().window(currentWindow);
                }
            }
        }

    }

    private Date getDateFromString(String dateString) {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(dateString);
        } catch (ParseException e) {

        }
        return null;
    }

    private boolean isAssignmentGraded(String score) {
        String[] parts = score.split("/");
        try {
            Double.parseDouble(parts[0]);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private double getScoreEarned(String score) {
        String[] parts = score.split("/");
        try {
            return Double.parseDouble(parts[0]);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double getTotalPoints(String score) {
        String[] parts = score.split("/");
        try {
            return Double.parseDouble(parts[1]);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String getCourseNameFromString(String stringWithName) {
        String[] parts = stringWithName.split("\n");
        return parts[0].replace(" ", "");
    }


}
