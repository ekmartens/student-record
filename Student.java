/*
* File: Student.java
* Author: Emily Martens
* Date: Aug 9, 2018
* Purpose: This class defintes the student record.

GPA = this.totalQualityPoints / this.totalCredits
*/

public class Student {

  //instance variables
  private String name;
  private String major;
  private double credits;
  private double qualityPoints;
  private double totalCredits;
  private double totalQualityPoints;

  //constructor
  public Student(String name, String major) {
    this.name = name;
    this.major = major;
    this.credits = 0.0;
    this.qualityPoints = 0.0;
    this.totalCredits = 0.0;
    this.totalQualityPoints = 0.0;

  }

  //method to update variables to calculate gpa
  public void courseCompleted(double grade, double creditHours) {
    this.credits = creditHours;
    this.qualityPoints = grade * creditHours;
    this.totalCredits += creditHours;
    this.totalQualityPoints += qualityPoints;
  }

  //toString method
  @Override
  public String toString(){
    double gpa;
    if (this.totalCredits == 0){
      gpa = 4.0;
    }
    else {
      gpa = this.totalQualityPoints / this.totalCredits;
    }
    return "Student Name: " + this.name + "\nMajor: " + this.major + "\nGPA: " + gpa;
  }

}//end class
