/*
* File: Student.java
* Author: Emily Martens
* Date: Aug 9, 2018
* Purpose: This class creates a GUI to save student records in a student database.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MakeGUI extends JFrame {

  //Swing elements for GUI
  private JLabel idLabel = new JLabel("Id: ");
  private JLabel nameLabel = new JLabel("Name: ");
  private JLabel majorLabel = new JLabel("Major: ");
  private JLabel selectionLabel = new JLabel("Choose Selection: ");
  private JTextField idInput = new JTextField("");
  private JTextField nameInput = new JTextField("");
  private JTextField majorInput = new JTextField("");
  private String[] choices = new String[]{"Insert", "Delete", "Find", "Update"};
  private JComboBox<String> selectAction = new JComboBox<String>(choices);
  private JButton process = new JButton("Process Request");
  private Student student;
  private HashMap<String, Student> studentDB = new HashMap<>();
  public static final int WIDTH = 400, HEIGHT = 300;


  //GUI constructor
  public MakeGUI(){
    //set up GUI Frame
    super("Student Database");
    setLayout(new BorderLayout());
    setSize(WIDTH, HEIGHT);
    setLocationRelativeTo(null);
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

    //create panel for inputs
    JPanel studentInfo = new JPanel();
    studentInfo.setLayout(new GridLayout(3, 2, 15, 5));
    studentInfo.add(idLabel);
    studentInfo.add(idInput);
    studentInfo.add(nameLabel);
    studentInfo.add(nameInput);
    studentInfo.add(majorLabel);
    studentInfo.add(majorInput);

    //create panel for actions that may be taken
    JPanel actionPanel = new JPanel();
    actionPanel.setLayout(new BorderLayout());
    actionPanel.add(selectionLabel);
    actionPanel.add(selectionLabel, BorderLayout.WEST);
    actionPanel.add(selectAction, BorderLayout.EAST);
    actionPanel.add(process, BorderLayout.SOUTH);

    //add event listener to Process Request button
    process.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

          boolean recordExists;
          //get Student Id;
          String id = idInput.getText();
          id = id.toLowerCase(); //change id to lowercase to avoid duplicates
          String name = nameInput.getText();
          String major = majorInput.getText();

          //check to see if the id exists in database
          recordExists = studentDB.containsKey(id);

          //Insert
          if (selectAction.getSelectedIndex() == 0){
            if (recordExists){
              final JOptionPane optionPane = new JOptionPane();
              JOptionPane.showMessageDialog(optionPane, "A student with ID " + id + " already exists.");
            }
            else {
              student = new Student(name, major);
              studentDB.put(id, student);
              final JOptionPane optionPane = new JOptionPane();
              JOptionPane.showMessageDialog(optionPane, "Success! " + name + " has been added to the database.");
            }
          }

          //Delete
          else if (selectAction.getSelectedIndex() == 1){
            if (recordExists){
              studentDB.remove(id);
              final JOptionPane optionPane = new JOptionPane();
              JOptionPane.showMessageDialog(optionPane, "Success! Student has been removed from the database.");
            }
            else {
              final JOptionPane optionPane = new JOptionPane();
              JOptionPane.showMessageDialog(optionPane, "Student record does not exist.");
            }
          }

          //Find
          else if (selectAction.getSelectedIndex() == 2){
            if (recordExists){
              Student searchStudent = (Student) studentDB.get(id);
              String message = "Student Id: " + id + "\n" + searchStudent.toString();
              final JOptionPane optionPane = new JOptionPane();
              JOptionPane.showMessageDialog(optionPane, message);
            }
            else {
              final JOptionPane optionPane = new JOptionPane();
              JOptionPane.showMessageDialog(optionPane, "Student record does not exist.");
            }
          }

          //Update
          else if (selectAction.getSelectedIndex() == 3){
            if (recordExists){
              //look up the student
              Student updateStudent = (Student) studentDB.get(id);
              //temporary variables for dialog box inputs
              double courseGrade = 0, courseCredits = 0;
              //choices for dialog box drop downs
              Object[] chooseGrade = {"A", "B", "C", "D", "F"};
              Object[] chooseCredits = {"3", "6"};

              //create new optionPane for grade selection
              JFrame optionPane = new JFrame(" ");
              String newGrade = (String) JOptionPane.showInputDialog(optionPane,
                  "Choose grade: ",
                  "Success! Grade has been added.",
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  chooseGrade,
                  chooseGrade[0]);

              //check to see which grade was chosen and assign appropriate double value
              switch(newGrade){
                case "A": courseGrade = 4.0;
                break;
                case "B": courseGrade = 3.0;
                break;
                case "C": courseGrade = 2.0;
                break;
                case "D": courseGrade = 1.0;
                break;
                case "F": courseGrade = 0.0;
                break;
                default:  courseGrade = 4.0;
                break;
              }

              //create new optionPane for credits selection
              JFrame optionPane2 = new JFrame(" ");
              String newCredits = (String) JOptionPane.showInputDialog(optionPane2,
                  "Choose credits: ",
                  "Success! Credits have been added.",
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  chooseCredits,
                  chooseCredits[0]);

                //check to see which credit level was chosen and assign appropriate double value
                switch(newCredits){
                  case "3": courseCredits = 3.0;
                  break;
                  case "6": courseCredits = 6.0;
                  break;
                }

                //update the student's credits, quality points, and GPA
                updateStudent.courseCompleted(courseGrade, courseCredits);

                final JOptionPane optionPane3 = new JOptionPane();
                JOptionPane.showMessageDialog(optionPane3, "Success! Completed course information has been added.");
            }
            else {
              final JOptionPane optionPane3 = new JOptionPane();
              JOptionPane.showMessageDialog(optionPane3, "Student record does not exist.");
            }
          }
        }
      });

    //Add panels to main Frame
    this.add(studentInfo, BorderLayout.NORTH);
    this.add(actionPanel, BorderLayout.CENTER);

    //pack and display the GUI
    pack();
    setVisible(true);
  }//end constructor

  //activate GUI
  public static void main(String[] args) {
    MakeGUI showWindow = new MakeGUI();
  }//end main
}//end class
