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

import entity.Yonghu;
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

public class YonghuRegistController implements Initializable {

	@FXML
	private TextField zhanghaoTxt;

	@FXML
	private TextField mimaTxt;

	@FXML
	private TextField shoujihaoTxt;

	@FXML
	private DatePicker shengriDate;

	@FXML
	private TextField shenfenzhenghousiweiTxt;

	// 初始化方法，当FXML文件被加载时调用
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	// 注册按钮的事件处理方法
	public void regist(ActionEvent e) {
		// 创建Yonghu对象，设置注册信息
		Yonghu yonghu = new Yonghu();
		yonghu.setZhanghao(zhanghaoTxt.getText());
		yonghu.setMima(mimaTxt.getText());
		yonghu.setShoujihao(shoujihaoTxt.getText());
		if (shengriDate.getValue() != null) yonghu.setShengri(shengriDate.getValue().toString());
		yonghu.setShenfenzhenghousiwei(shenfenzhenghousiweiTxt.getText());
		// 调用添加方法进行注册
		yonghu.add();
		// 弹出注册成功的提示框
		showMsg("注册成功");
		// 关闭当前注册窗口
		((Stage) ((Button) e.getSource()).getScene().getWindow()).close();
	}

	@FXML
	// 关闭按钮的事件处理方法
	public void close(ActionEvent e) {
		// 关闭当前注册窗口
		((Stage) ((Button) e.getSource()).getScene().getWindow()).close();
	}

	// 文件拷贝方法
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

	// 显示消息方法，弹出一个信息提示框
	public void showMsg(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.titleProperty().set("提示");
		alert.headerTextProperty().set(msg);
		alert.showAndWait();
	}
}




