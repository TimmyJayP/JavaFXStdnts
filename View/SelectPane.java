package View;
// Name: T.J.
//  Description: SelectPane displays a list of available courses
//  from which a user can select and compute total number of students in multiple courses.

import java.util.ArrayList;

import Model.Course;
import javafx.event.ActionEvent; //**Need to import
import javafx.event.EventHandler; //**Need to import
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;

/**
* SelectPane displays a list of available courses from which a user
* can select and compute total number of students for selected courses.
*/
public class SelectPane extends BorderPane {

    ScrollPane scroll;
    Label top, bottom;
    VBox infoBox, checkBox;
    int totalMembers = 0;

    public SelectPane(ArrayList<Course> list) {
        infoBox = new VBox();
        checkBox = new VBox();
        
        //Wrap checkboxContainer in ScrollPane so formatting is
        //correct when many courses are added
        scroll = new ScrollPane();
        scroll.setContent(checkBox);
        scroll.setMinSize(880, 322);
        top = new Label("Select course(s)");
        top.setMinSize(400, 15);
        bottom = new Label("The total number of students:0");
        bottom.setMinSize(400, 15);
       
        // Setup layout
        
        //create an empty pane where you can add check boxes later
        infoBox.setPadding(new Insets(0 , 10 , 0, 1));
        infoBox.setMinSize(900,900);
        infoBox.setSpacing(5);
        
        
        //SelectPane is a BorderPane - add the components here
        this.getChildren().add(infoBox);
        infoBox.getChildren().add(top);
        infoBox.getChildren().add(bottom);
        infoBox.getChildren().add(scroll);


    } // end of SelectPane constructor

    // This method uses the newly added parameter Course object
    // to create a CheckBox and add it to a pane created in the constructor
    // Such check box needs to be linked to its handler class
    public void updateCourseList(Course newcourse) {
    	//Create checkbox for new course with appropriate text
    	CheckBox cBox = new CheckBox(newcourse.toString());
    	
    	//Bind checkbox toggle action to event handler
    	// Passes the number of students in each course to the handler. When the checkbox is
        // toggled, this number will be added/subtracted from the total number of selected students
    	cBox.setOnAction(new SelectionHandler(newcourse.getNumStudents(), cBox));
        
        // Add new checkbox to checkbox container
    	checkBox.getChildren().add(cBox);
    	
    } // end of updateCourseList method

    /*
     * SelectionHandler This class handles a checkbox toggle event. When the
     * checkbox is toggled, this number will be added/subtracted from the total
     * number of selected students.
     */
    private class SelectionHandler implements EventHandler<ActionEvent> {
        // Instance variable for number of students associated with a given course/checkbox
        private int numStudents;
        CheckBox cBox;
        
        
        public SelectionHandler(int nums, CheckBox c) {
        	numStudents = nums;
        	cBox = c;
        } // end of SelectionHandler constructor

        //over-ride the abstract method handle
        public void handle(ActionEvent event) {
        	
        	//increases total number of students based on how many selected
        	if (cBox.isSelected() == true) {
        		totalMembers += numStudents;
        	
        	//reduces number of students when checkbox is deselected
        	}else if(cBox.isSelected() == false){
        		totalMembers -= numStudents;
        	}
        	
            // Update the label with the new number of selected students
        	bottom.setText("The total number of students:" + totalMembers);

        } // end handle method
    } // end of SelectHandler class
} // end of SelectPane class
