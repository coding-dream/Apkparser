package com.less.apkparser.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class AppController implements Initializable {

	@FXML
	private Button btn_open;

	@FXML
	private Button btn_down;

	@FXML
	private Button btn_start;

	@FXML
	private TextField tv_local;

	@FXML
	private TextField tv_net;

	@FXML
	private TextArea textarea_info;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML public void open_file(ActionEvent event) {

	}

	@FXML public void down_file(ActionEvent event) {

	}

	@FXML public void begin_parse(ActionEvent event) {

	}
}
