package com.example.javafxstdnts;
// Name: T.J.
// Description: The SchoolCourseSelector class creates a Tabbed Pane with
//              two tabs, one for course Creation and one for
//              course Selection.

import Model.Course;
import View.GeneratePane;
import View.SelectPane;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;

public class SchoolCourseSelector extends Application {
    private TabPane tabPane;
    private GeneratePane createPane;
    private SelectPane selectPane;
    private ArrayList<Course> courseList;

    public void start(Stage stage) {
        StackPane root = new StackPane();

        // courseList to be used in both generatePane & selectPane
        courseList = new ArrayList<Course>();

        selectPane = new SelectPane(courseList);
        createPane = new GeneratePane(courseList, selectPane);

        tabPane = new TabPane();

        Tab tab1 = new Tab();
        tab1.setText("Add Course");
        tab1.setContent(createPane);

        Tab tab2 = new Tab();
        tab2.setText("Select Course");
        tab2.setContent(selectPane);

        tabPane.getSelectionModel().select(0);
        tabPane.getTabs().addAll(tab1, tab2);

        root.getChildren().add(tabPane);

        Scene scene = new Scene(root, 900, 400);
        stage.setTitle("Course Selection App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}