package com.less.apkparser;

import com.less.apkparser.util.LayoutInflater;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = LayoutInflater.inflate("activity_main");
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
