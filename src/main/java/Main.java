import objects.Course;
import objects.Grades;
import scraper.CourseScraper;

public class Main {
    private static final String GERRY = "+12692164642";

    //private static final String MICHALE = "+12696895205";
    public static void main(String[] args) {

/*
        WebElement element = driver.findElement(By.id("quickLookup"));
        List<WebElement> elements = element.findElements(By.tagName("tr"));
        elements.get(2).getText();
        classes.add(retrieveClassData(elements.get(3).getText()));
        classes.add(retrieveClassData(elements.get(4).getText()));
        classes.add(retrieveClassData(elements.get(5).getText()));
        classes.add(retrieveClassData(elements.get(6).getText()));
        classes.add(retrieveClassData(elements.get(7).getText()));
        classes.add(retrieveClassData(elements.get(8).getText()));
        //classes.add(retrieveClassData(elements.get(9).getText()));
        //classes.add(retrieveClassData(elements.get(10).getText()));
        elements.get(10).getText();
        PhoneMessage phone = new PhoneMessage();
        //phone.sendMessage(getMessage(classes));
        //phone.sendMessage(getMessageGradeFirst(classes), GERRY);*/
        CourseScraper courseScraper = new CourseScraper();
        courseScraper.scrape();
        /*AssignmentScraper assignmentScraper = new AssignmentScraper();
        assignmentScraper.scrape();*/
    }

    public static Grades getGrades(String[] strings, Course study) {
        //p2, a2, s2
        Grades grades = new Grades();
        String[] parts = strings[1].split(" ");
        //set letter p2
        grades.setP2Letter(parts[parts.length - 1]);
        parts = strings[2].split(" ");
        // set num p2
        grades.setP2Number(Double.parseDouble(parts[0]));
        // set letter a2
        grades.setA2Letter(parts[1]);
        parts = strings[3].split(" ");
        // set num a2
        grades.setA2Number(Double.parseDouble(parts[0]));
        // set letter s2
        grades.setS2Letter(parts[parts.length - 1]);
        parts = strings[4].split(" ");
        // set s2 num
        grades.setS2Number(Double.parseDouble(parts[0]));
        // set abscences
        study.setAbsences(Integer.parseInt(parts[1]));
        // set tardies
        study.setTardies(Integer.parseInt(parts[2]));
        return grades;
    }

    /*private static String getMessage(List<Course> courses){
        StringBuilder message = new StringBuilder();
        for(Course clazz : courses){
            message.append(clazz.getClassname() + " " + clazz.getGrades().getS2Number() + " " + clazz.getGrades().getS2Letter()+"\n");
        }
        return message.toString();
    }*/

    /*private static String getMessageGradeFirst(List<Course> courses){
        StringBuilder message = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.00");
        for(Course clazz : courses){
            if(clazz.getGrades().getS2Letter().length() == 1){
                message.append(clazz.getGrades().getS2Letter() + "      ");
            } else {
                if(clazz.getGrades().getS2Letter().charAt(1) == '+'){
                    message.append(clazz.getGrades().getS2Letter() + "    ");
                } else {
                    message.append(clazz.getGrades().getS2Letter() + "     ");
                }
            }
            message.append(df.format(clazz.getGrades().getS2Number()) + "   ");
            message.append(clazz.getClassname() +"\n");
        }
        return message.toString();
    }*/
}
