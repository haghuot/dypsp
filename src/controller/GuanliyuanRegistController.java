package controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entity.Guanliyuan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GuanliyuanRegistController implements Initializable {

	@FXML
	private TextField yonghumingTxt;
	@FXML
	private TextField mimaTxt;

	// 初始化方法，实现Initializable接口
	public void initialize(URL location, ResourceBundle resources) {

	}

	// 注册按钮点击事件
	@FXML
	public void regist(ActionEvent e) {
		Guanliyuan guanliyuan = new Guanliyuan();
		guanliyuan.setYonghuming(yonghumingTxt.getText());
		guanliyuan.setMima(mimaTxt.getText());
		guanliyuan.add();
		showMsg("注册成功");
		// 关闭当前窗口
		((Stage) ((Button) e.getSource()).getScene().getWindow()).close();
	}

	// 关闭按钮点击事件
	@FXML
	public void close(ActionEvent e) {
		// 关闭当前窗口
		((Stage) ((Button) e.getSource()).getScene().getWindow()).close();
	}

	// 复制文件的方法
	private void copyFileUsingStream(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}

	// 显示信息的方法
	public void showMsg(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.titleProperty().set("提示");
		alert.headerTextProperty().set(msg);
		alert.showAndWait();
	}
}