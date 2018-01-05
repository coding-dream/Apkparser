package com.less.apkparser.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.less.apkparser.util.ApkParserUtil;
import com.less.apkparser.util.FileChooserUtil;
import com.less.apkparser.util.LayoutInflater;
import com.less.apkparser.util.PlantUMLHelper;
import com.less.downloadmanager.lib.DownloadException;
import com.less.downloadmanager.lib.request.FileCallBack;
import com.less.downloadmanager.lib.request.GetBuilder;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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

	@FXML
	private ProgressIndicator progressbar;

	@FXML
	private Button btn_uml;

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
		String url = tv_net.getText();
		if(url.isEmpty()){
			lb_message.setText("下载地址不能为空！");
			return;
		}
		String tag = url;
		new GetBuilder()
		.name("temp.apk")
		.folder(new File("F:/"))
		.uri(url)
		.tag(tag)
		.build()
		.execute(new FileCallBack() {

			@Override
			public void onDownloadFailed(DownloadException e) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						lb_message.setText("文件下载失败！");
					}
				});
			}

			@Override
			public void onDownloadCompleted(File file) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						progressbar.setProgress(1f);
						tv_local.setText("F:/temp.apk");
					}
				});
			}

			@Override
			public void onDownloadProgress(long finished, long totalLength, int percent) {
				System.out.println(percent);
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						progressbar.setProgress(percent / 100f);
					}
				});
			}

			@Override
			public void onStart() {
				System.out.println("start");
			}
		});
	}

	@FXML public void begin_parse(ActionEvent event) throws IOException {
		String filePath = tv_local.getText();
		if(!filePath.isEmpty()){
			ApkFile apkFile = new ApkFile(filePath);
			ApkMeta apkMeta = ApkParserUtil.getApkMetaInfo(apkFile);
			String result = ApkParserUtil.getDexPackageNames(apkFile);
			textarea_info.setText(result);
		} else {
			lb_message.setText("输入框不能为空");
		}
	}

	@FXML public void generateUML() throws Exception {
		Parent root = LayoutInflater.inflate("activity_uml");
		ImageView imageView = (ImageView) root.lookup("#imageView");
		String destPath = "F:/uml.png";
		PlantUMLHelper.createUML(textarea_info.getText(), destPath);

		File file = new File("F:/uml.png");
		imageView.setImage(new Image(file.toURL().toExternalForm()));

		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
