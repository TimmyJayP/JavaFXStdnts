package View;// Assignment 6: ASU - CSE 205
// Name: T.J.
//  Description: GeneratePane creates a pane where a user can enter
//  course information and create a list of available courses.





/* --------------- */
/* Import Packages */
/* --------------- */

import java.util.ArrayList;

import Model.Course;
import Model.Instructor;
import javafx.event.ActionEvent; //**Need to import
import javafx.event.EventHandler; //**Need to import

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * GeneratePane builds a pane where a user can enter a course
 * information and create a list of available courses.
 */
public class GeneratePane extends HBox {
    /* ------------------ */
    /* Instance variables */
    /* ------------------ */

    ArrayList<Course> courseList;
    private SelectPane selectPane; // The relationship between GeneratePane and SelectPane is Aggregation
    
    //declare and init
    TextArea textArea;
    Label error;
    TextField universityF, numberF, courseF, instructorF;
    
    /**
     * CreatePane constructor
     *
     * @param list   the list of courses
     * @param sePane the SelectPane instance
     */
    public GeneratePane(ArrayList<Course> list, SelectPane sePane) {
        /* ------------------------------ */
        /* Instantiate instance variables */
        /* ------------------------------ */
    	
    	courseList = list;
    	selectPane = sePane;
    	
    	 //initialize each instance variable (textfields, labels, textarea, button, etc.)
    	
    	//Textfields
    	courseF = new TextField("HerbologyC102");
    	instructorF = new TextField("Mirabel Garlick");
    	universityF = new TextField("Hogwarts");
    	numberF = new TextField("35");
    	
    	//Labels 
    	Label courseN = new Label("Course Name");
    	Label instructorN = new Label("Name of instructor");
    	Label universityN = new Label("Name of University");
    	Label studentN = new Label("Number of students");
    	error = new Label("");
    	error.setTextFill(Color.RED);
    	

    	//Right half of GeneratePane is TextArea
    	textArea = new TextArea("No Courses");
    	textArea.setEditable(false);
    	textArea.setMaxSize(445, 350);
    	
    	Button addCourse = new Button("Add a course");
    	addCourse.setOnAction(new ButtonHandler());
    	
		//create a GridPane to hold labels & text fields.	
    	//Set up the layout for the left half of the GeneratePane.
    	GridPane LeftPane = new GridPane();
    	
    	VBox vbox = new VBox();

    	vbox.getChildren().add(error);
    	vbox.setPadding(new Insets(10, 10, 10, 10));
    	vbox.setPrefWidth(430);
    	vbox.setAlignment(Pos.TOP_LEFT);
    	
    	LeftPane.setPadding(new Insets(20, 50, 0, 0));
    	LeftPane.setVgap(1);
    	LeftPane.setHgap(5);
    	LeftPane.setAlignment(Pos.TOP_CENTER);
    	
    	//Text Boxes
    	LeftPane.add(courseN , 0 , 5);
    	LeftPane.add(instructorN,  0, 6);
    	LeftPane.add(universityN, 0, 7);
    	LeftPane.add(studentN, 0, 8);
    
    	//Text Fields
    	LeftPane.add(courseF ,2 , 5);
    	LeftPane.add(instructorF, 2, 6);
    	LeftPane.add(universityF,  2, 7);
    	LeftPane.add(numberF, 2 , 8);
    	
    	//Button
    	LeftPane.add(addCourse, 2 ,10);
    	
    	//Add the left half and right half to the GeneratePane
    	this.getChildren().add(vbox);
    	this.getChildren().add(textArea);
    	this.setSpacing(10);
    	vbox.getChildren().add(LeftPane);


    } // end of constructor

    /*** ButtonHandler ButtonHandler listens to see if the button "Add a course" is pushed 					
     ** or not, When the event occurs, it asks for course and instructor name, number of students enrolled,  
     ** and its university information from the relevant text fields, then create a   
     ** new course and adds it to the courseList. Meanwhile it will display the course's 
     ** information inside the text area. using the toString method of the Course     
     ** class. It also does error checking in case any of the text fields are empty,   
     ** or a non-numeric value was entered for number of student     */
    // This class will deal with everyone thats supposed to happen after add course is clicked
    private class ButtonHandler implements EventHandler<ActionEvent>
    {
        // handle Override the abstract method handle()
    	Boolean isEmptyFields;

        public void handle(ActionEvent event) 
        {
        	// Declare local variables
            Course newCourse;
            int numStudents = 0;
            
            // Checks to see if fields is empty, if so will display error message. 
            if(numberF.getText().equals("")||instructorF.getText().equals("")||universityF.getText().equals("")||courseF.getText().equals("")) 
            {
            	isEmptyFields = true;
            	error.setText("Please fill all fields");
            }else {
            	isEmptyFields = false;
            }
            //if fields are not empty then tries to add course
            try {
                    	
                    //Cast students Field to an integer, throws NumberFormatException if unsuccessful
            		numStudents = Integer.parseInt(numberF.getText());
            		//If the fields were sufficient we add the course
            		if(isEmptyFields == false) {
            		
            			// Data is valid, so create new Department object and populate data
            			Instructor teacher = new Instructor("?" , instructorF.getText(),"?" , 0);
            			newCourse = new Course(courseF.getText(), teacher, universityF.getText(), numStudents);
            		
            			// Loop through existing departments to check for duplicates      
            			// and if exist do not add it to the list and display a message
            			Boolean registered = false;
            			for(int i = 0;i < courseList.size(); i++) 
            			{ 
            				if(courseList.get(i).getName().equals(newCourse.getName())) {
            					registered= true;
            					throw new Exception();
            				}
            			}
            		
            			//clears "No Courses" on first loop
            			if(textArea.getText().equalsIgnoreCase("No Courses")) { 
            				textArea.clear();
            			}
            			//adds the course and prints to textArea
            			if(registered == false ){ 
            				textArea.appendText(newCourse.toString());
            				courseList.add(newCourse);
            				selectPane.updateCourseList(newCourse);
            				
            				// Clears the text fields after course is added and then gives confirmation that course was added
            				courseF.clear();
            				instructorF.clear();
            				universityF.clear();
            				numberF.clear();
            				error.setText("Course added");
            			}
            		}
                } //end of try
            
                catch (NumberFormatException e) {
                    // If student number entered was not an Int -- displays error message
                	error.setText("Please enter an integer for the number of student(s)");
                } 
            
                catch (Exception e) {
                		// Catches generic exception and displays message
                		// Used to display 'Course is not added - already exist' message if course already exist
                	error.setText("Course is not added - already exist");
                	
               }
        } // end of handle() method
    } // end of ButtonHandler class
} // end of GeneratePane class


