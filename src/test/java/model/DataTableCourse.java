package model;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class DataTableCourse {
    private String instructor;
    private String course;
    private String price;

    public DataTableCourse(String instructor, String course, String price) {
        this.instructor = instructor;
        this.course = course;
        this.price = price;
    }

    public String setInstructor(String instructor) {
        return this.instructor = instructor;
    }

    public String setCourse (String course) {
        return this.course = course;
    }

    public String setPrice (String price) {
        return this.price = price;
    }

    public void printData() {
        System.out.println("--------------------");
        System.out.println("Instructor: " + instructor);
        System.out.println("Course: " + course);
        System.out.println("Price: " + price);
        System.out.println("--------------------");
        System.out.println(" ");
    }

    public void validateDataNullorEmpty() {
        if (instructor == null || instructor.isEmpty()) {
            Assert.fail("Instructor is null or empty");
        }
        if (course == null || course.isEmpty()) {
            Assert.fail("Course is null or empty");
        } 
        if (price == null || price.isEmpty()) {
            Assert.fail("Price is null or empty");
        }
    }
}
