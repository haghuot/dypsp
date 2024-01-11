package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entity.Yonghu;
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

public class YonghuLoginController implements Initializable {

	// 静态变量，用于在登录成功后保存用户的用户名
	public static String username;

	@FXML
	private TextField usernameTxt;

	@FXML
	private PasswordField passwordTxt;

	// 初始化方法，当FXML文件被加载时调用
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	// 登录按钮的事件处理方法
	public void login(ActionEvent e) {
		// 创建Yonghu对象，设置查询条件为输入的用户名
		Yonghu yonghu = new Yonghu();
		yonghu.setZhanghao(usernameTxt.getText());
		// 查询符合条件的用户列表
		List<Yonghu> list = yonghu.query();
		if (list.size() > 0) {
			// 如果存在用户且密码匹配，则登录成功
			if (list.get(0).getMima().equals(passwordTxt.getText())) {
				// 保存用户名到静态变量中
				username = list.get(0).getZhanghao();
				// 弹出登录成功的提示框
				showMsg("登录成功");
				// 打开主界面
				Stage stage = new Stage();
				stage.setTitle("系统功能");
				try {
					stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/YonghuMainUI.fxml"))));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				stage.show();
				// 关闭当前登录窗口
				((Stage) ((Button) e.getSource()).getScene().getWindow()).close();
				return;
			}
		}
		// 用户名或密码错误，登录失败，弹出提示框
		showMsg("用户名或密码错误，登录失败");
	}

	@FXML
	// 注册按钮的事件处理方法
	public void regist() {
		// 打开注册界面
		try {
			Stage stage = new Stage();
			stage.setTitle("注册");
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/YonghuRegistUI.fxml"))));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
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