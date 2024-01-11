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

import entity.Guanliyuan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GuanliyuanController implements Initializable {

	// FXML注解，用于关联FXML文件中的TableView和TableColumn等控件
	@FXML
	private TableView<Guanliyuan> guanliyuanTable;
	@FXML
	private TableColumn<Guanliyuan, String> yonghumingColumn;
	@FXML
	private TableColumn<Guanliyuan, String> mimaColumn;
	@FXML
	private TextField yonghumingQueryTxt;
	@FXML
	private TextField yonghumingTxt;
	@FXML
	private TextField mimaQueryTxt;
	@FXML
	private TextField mimaTxt;

	// Guanliyuan对象用于处理管理员信息，ObservableList用于存储TableView的数据
	private Guanliyuan guanliyuan = new Guanliyuan();
	private ObservableList<Guanliyuan> list = FXCollections.observableArrayList();

	// 初始化方法，实现Initializable接口
	public void initialize(URL location, ResourceBundle resources) {
		// 将TableView绑定到ObservableList
		guanliyuanTable.setItems(list);
		// 设置TableColumn与Guanliyuan类中的属性关联
		yonghumingColumn.setCellValueFactory(new PropertyValueFactory("yonghuming"));
		mimaColumn.setCellValueFactory(new PropertyValueFactory("mima"));

		// 设置鼠标点击事件，用于选中TableView中的数据并在文本框中显示
		guanliyuanTable.setOnMouseClicked(e -> {
			if (guanliyuanTable.getSelectionModel().getSelectedItem() != null) {
				yonghumingTxt.setText(guanliyuanTable.getSelectionModel().getSelectedItem().getYonghuming());
				mimaTxt.setText(guanliyuanTable.getSelectionModel().getSelectedItem().getMima());
			}
		});

		// 刷新TableView数据
		refresh();
	}

	// 刷新TableView数据
	@FXML
	public void refresh() {
		list.clear();
		list.addAll(guanliyuan.query());
		reset();
	}

	// 删除选中的管理员信息
	@FXML
	public void delete() {
		if (guanliyuanTable.getSelectionModel().getSelectedItem() != null) {
			guanliyuanTable.getSelectionModel().getSelectedItem().delete();
			showMsg("删除成功");
			refresh();
		}
	}

	// 添加新的管理员信息
	@FXML
	public void add() {
		Guanliyuan guanliyuan = new Guanliyuan();
		guanliyuan.setYonghuming(yonghumingTxt.getText());
		guanliyuan.setMima(mimaTxt.getText());
		guanliyuan.add();
		showMsg("添加成功");
		refresh();
	}

	// 编辑选中的管理员信息
	@FXML
	public void edit() {
		if (guanliyuanTable.getSelectionModel().getSelectedItem() != null) {
			Guanliyuan guanliyuan = guanliyuanTable.getSelectionModel().getSelectedItem();
			guanliyuan.setYonghuming(yonghumingTxt.getText());
			guanliyuan.setMima(mimaTxt.getText());
			guanliyuan.update();
			showMsg("修改成功");
			refresh();
		}
	}

	// 重置文本框内容
	@FXML
	public void reset() {
		yonghumingTxt.clear();
		mimaTxt.clear();
		yonghumingQueryTxt.clear();
		mimaQueryTxt.clear();
	}

	// 查询管理员信息
	@FXML
	public void query() {
		list.clear();
		Guanliyuan guanliyuan = new Guanliyuan();
		guanliyuan.setYonghuming(yonghumingQueryTxt.getText());
		guanliyuan.setMima(mimaQueryTxt.getText());
		list.addAll(guanliyuan.query());
	}

	// 使用输入输出流复制文件的方法，将文件从source复制到dest
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
			// 关闭输入输出流
			is.close();
			os.close();
		}
	}

	// 显示信息提示框
	public void showMsg(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.titleProperty().set("提示");
		alert.headerTextProperty().set(msg);
		alert.showAndWait();
	}
}
