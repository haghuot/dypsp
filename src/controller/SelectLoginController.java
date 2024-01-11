package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SelectLoginController {

	@FXML
	// 管理员登录按钮点击事件
	public void guanliyuanLogin() {
		try {
			// 创建新的舞台对象
			Stage stage = new Stage();
			// 设置舞台标题
			stage.setTitle("管理员登录");
			// 加载管理员登录界面的FXML文件，并设置为场景的根节点
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/GuanliyuanLoginUI.fxml"))));
			// 显示舞台
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	// 用户登录按钮点击事件
	public void yonghuLogin() {
		try {
			// 创建新的舞台对象
			Stage stage = new Stage();
			// 设置舞台标题
			stage.setTitle("用户登录");
			// 加载用户登录界面的FXML文件，并设置为场景的根节点
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/YonghuLoginUI.fxml"))));
			// 显示舞台
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
