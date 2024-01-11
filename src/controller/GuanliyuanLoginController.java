package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entity.Guanliyuan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GuanliyuanLoginController implements Initializable {

	// 静态变量用于存储登录用户的用户名
	public static String username;

	// FXML注解，用于关联FXML文件中的TextField、PasswordField等控件
	@FXML
	private TextField usernameTxt;
	@FXML
	private PasswordField passwordTxt;

	// 初始化方法，实现Initializable接口
	public void initialize(URL location, ResourceBundle resources) {

	}

	// 登录按钮的事件处理方法
	@FXML
	public void login(ActionEvent e) {
		// 创建Guanliyuan对象
		Guanliyuan guanliyuan = new Guanliyuan();
		guanliyuan.setYonghuming(usernameTxt.getText());
		// 查询数据库，获取匹配的管理员信息
		List<Guanliyuan> list = guanliyuan.query();
		// 判断是否有匹配的管理员信息
		if (list.size() > 0) {
			// 验证密码是否匹配
			if (list.get(0).getMima().equals(passwordTxt.getText())) {
				// 登录成功，记录用户名，弹出提示框
				username = list.get(0).getYonghuming();
				showMsg("登录成功");

				// 打开管理员主界面
				Stage stage = new Stage();
				stage.setTitle("系统功能");
				try {
					stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/GuanliyuanMainUI.fxml"))));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				stage.show();

				// 关闭当前登录窗口
				((Stage) ((Button) e.getSource()).getScene().getWindow()).close();
				return;
			}
		}
		// 登录失败，弹出提示框
		showMsg("用户名或密码错误，登录失败");
	}

	// 注册按钮的事件处理方法
	@FXML
	public void regist() {
		try {
			// 打开注册界面
			Stage stage = new Stage();
			stage.setTitle("注册");
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/GuanliyuanRegistUI.fxml"))));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 显示信息提示框
	public void showMsg(String msg) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.titleProperty().set("提示");
		alert.headerTextProperty().set(msg);
		alert.showAndWait();
	}
}