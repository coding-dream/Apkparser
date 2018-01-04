package com.less.apkparser.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.less.apkparser.util.ApkParserUtil;
import com.less.apkparser.util.FileChooserUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;

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

	@FXML
	private Label lb_message;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML public void open_file(ActionEvent event) {
		File file = FileChooserUtil.chooseFile();
		if(null != file){
			tv_local.setText(file.getAbsolutePath());
		}
	}

	@FXML public void down_file(ActionEvent event) {

	}

	@FXML public void begin_parse(ActionEvent event) throws IOException {
		String filePath = tv_local.getText();
		if(!filePath.equals("")){
			ApkFile apkFile = new ApkFile(filePath);
			ApkMeta apkMeta = ApkParserUtil.getApkMetaInfo(apkFile);
			Set<String> packageSets = ApkParserUtil.getDexPackageNames(apkFile);
			textarea_info.setText(packageSets.toString());
		} else {
			lb_message.setText("输入框不能为空");
		}
	}
}
