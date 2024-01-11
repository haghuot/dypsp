package controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.time.LocalDate;
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
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MyInfo implements Initializable {

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
	@FXML
	private TextField idTxt;

	// 初始化方法，实现Initializable接口
	public void initialize(URL location, ResourceBundle resources) {
		// 查询用户信息并显示在界面上
		Yonghu yonghu = new Yonghu();
		yonghu.setZhanghao(YonghuLoginController.username);
		yonghu = yonghu.query().get(0);
		zhanghaoTxt.setText(yonghu.getZhanghao());
		mimaTxt.setText(yonghu.getMima());
		shoujihaoTxt.setText(yonghu.getShoujihao());
		shengriDate.setValue(LocalDate.parse(yonghu.getShengri()));
		shenfenzhenghousiweiTxt.setText(yonghu.getShenfenzhenghousiwei());
		idTxt.setText(yonghu.getId().toString());
	}

	// 提交按钮点击事件
	@FXML
	public void submit(ActionEvent e) {
		// 根据ID查询用户信息
		Yonghu yonghu = new Yonghu(Integer.valueOf(idTxt.getText())).queryById();
		yonghu.setZhanghao(zhanghaoTxt.getText());
		yonghu.setMima(mimaTxt.getText());
		yonghu.setShoujihao(shoujihaoTxt.getText());
		// 判断生日是否为空
		if (shengriDate.getValue() != null) {
			yonghu.setShengri(shengriDate.getValue().toString());
		}
		yonghu.setShenfenzhenghousiwei(shenfenzhenghousiweiTxt.getText());
		yonghu.update();
		showMsg("修改成功");
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
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.titleProperty().set("提示");
		alert.headerTextProperty().set(msg);
		alert.showAndWait();
	}
}