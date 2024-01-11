package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class YonghuMainController implements Initializable {

	// 初始化方法，当FXML文件被加载时调用
	public void initialize(URL location, ResourceBundle resources) {
		// 初始化操作，可留空
	}

	@FXML
	// 打开管理员管理界面
	public void openGuanliyuanManageUI() {
		try {
			Stage stage = new Stage();
			stage.setTitle("管理员管理");
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/GuanliyuanManageUI.fxml"))));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	// 打开电影管理界面
	public void openDianyingManageUI() {
		try {
			Stage stage = new Stage();
			stage.setTitle("电影管理");
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DianyingManageUI.fxml"))));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	// 打开场次管理界面
	public void openChangciManageUI() {
		try {
			Stage stage = new Stage();
			stage.setTitle("场次管理");
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ChangciManageUI.fxml"))));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	// 打开用户管理界面
	public void openYonghuManageUI() {
		try {
			Stage stage = new Stage();
			stage.setTitle("用户管理");
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/YonghuManageUI.fxml"))));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	// 打开场次选座界面
	public void openChangciSelectSeatUI() {
		try {
			Stage stage = new Stage();
			stage.setTitle("场次选座");
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ChangciSelectSeatUI.fxml"))));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	// 打开场次订单管理界面
	public void openChangcidingdanManageUI() {
		try {
			Stage stage = new Stage();
			stage.setTitle("场次订单管理");
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ChangcidingdanManageUI.fxml"))));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	// 打开我的资料界面
	public void openmyinfo() {
		try {
			Stage stage = new Stage();
			stage.setTitle("我的资料");
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/myinfo.fxml"))));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	// 打开我的订单界面
	public void openmyorder() {
		try {
			Stage stage = new Stage();
			stage.setTitle("我的订单");
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/myorder.fxml"))));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	// 退出应用程序
	public void exit() {
		System.exit(0);
	}
}