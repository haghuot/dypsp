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

public class YonghuController implements Initializable {

	@FXML
	private TableView<Yonghu> yonghuTable;
	@FXML
	private TableColumn<Yonghu, String> zhanghaoColumn;
	@FXML
	private TableColumn<Yonghu, String> mimaColumn;
	@FXML
	private TableColumn<Yonghu, String> shoujihaoColumn;
	@FXML
	private TableColumn<Yonghu, String> shengriColumn;
	@FXML
	private TableColumn<Yonghu, String> shenfenzhenghousiweiColumn;
	@FXML
	private TextField zhanghaoQueryTxt;
	@FXML
	private TextField zhanghaoTxt;
	@FXML
	private TextField mimaTxt;
	@FXML
	private TextField shoujihaoQueryTxt;
	@FXML
	private TextField shoujihaoTxt;
	@FXML
	private DatePicker shengriDate;
	@FXML
	private TextField shenfenzhenghousiweiTxt;

	private Yonghu yonghu = new Yonghu();
	private ObservableList<Yonghu> list = FXCollections.observableArrayList();

	// 初始化方法，当FXML文件被加载时调用
	public void initialize(URL location, ResourceBundle resources) {
		// 将ObservableList设置为表格的数据源
		yonghuTable.setItems(list);
		// 设置每列的值工厂
		zhanghaoColumn.setCellValueFactory(new PropertyValueFactory<>("zhanghao"));
		mimaColumn.setCellValueFactory(new PropertyValueFactory<>("mima"));
		shoujihaoColumn.setCellValueFactory(new PropertyValueFactory<>("shoujihao"));
		shengriColumn.setCellValueFactory(new PropertyValueFactory<>("shengri"));
		shenfenzhenghousiweiColumn.setCellValueFactory(new PropertyValueFactory<>("shenfenzhenghousiwei"));
		// 当表格行被点击时触发事件
		yonghuTable.setOnMouseClicked(e -> {
			if (yonghuTable.getSelectionModel().getSelectedItem() != null) {
				// 将选中行的数据显示在相应的文本框中
				zhanghaoTxt.setText(yonghuTable.getSelectionModel().getSelectedItem().getZhanghao());
				mimaTxt.setText(yonghuTable.getSelectionModel().getSelectedItem().getMima());
				shoujihaoTxt.setText(yonghuTable.getSelectionModel().getSelectedItem().getShoujihao());
				shengriDate.setValue(LocalDate.parse(yonghuTable.getSelectionModel().getSelectedItem().getShengri()));
				shenfenzhenghousiweiTxt.setText(yonghuTable.getSelectionModel().getSelectedItem().getShenfenzhenghousiwei());
			}
		});

		// 刷新界面数据
		refresh();
	}

	@FXML
	// 刷新方法，用于更新表格数据
	public void refresh() {
		// 清空数据列表
		list.clear();
		// 查询数据并添加到列表
		list.addAll(yonghu.query());
		// 重置输入框
		reset();
	}

	@FXML
	// 删除方法，删除选中行的用户记录
	public void delete() {
		if (yonghuTable.getSelectionModel().getSelectedItem() != null) {
			// 删除选中的用户记录
			yonghuTable.getSelectionModel().getSelectedItem().delete();
			// 显示删除成功的提示信息
			showMsg("删除成功");
			// 刷新表格数据
			refresh();
		}
	}

	@FXML
	// 添加方法，向数据库中添加用户记录
	public void add() {
		// 创建新的用户记录对象，并设置其属性
		Yonghu yonghu = new Yonghu();
		yonghu.setZhanghao(zhanghaoTxt.getText());
		yonghu.setMima(mimaTxt.getText());
		yonghu.setShoujihao(shoujihaoTxt.getText());
		if (shengriDate.getValue() != null) yonghu.setShengri(shengriDate.getValue().toString());
		yonghu.setShenfenzhenghousiwei(shenfenzhenghousiweiTxt.getText());
		yonghu.add();
		// 显示添加成功的提示信息
		showMsg("添加成功");
		// 刷新表格数据
		refresh();
	}

	@FXML
	// 编辑方法，修改选中行的用户记录
	public void edit() {
		if (yonghuTable.getSelectionModel().getSelectedItem() != null) {
			// 获取选中的用户记录
			Yonghu yonghu = yonghuTable.getSelectionModel().getSelectedItem();
			// 更新用户记录的属性
			yonghu.setZhanghao(zhanghaoTxt.getText());
			yonghu.setMima(mimaTxt.getText());
			yonghu.setShoujihao(shoujihaoTxt.getText());
			if (shengriDate.getValue() != null) yonghu.setShengri(shengriDate.getValue().toString());
			yonghu.setShenfenzhenghousiwei(shenfenzhenghousiweiTxt.getText());
			yonghu.update();
			// 显示修改成功的提示信息
			showMsg("修改成功");
			// 刷新表格数据
			refresh();
		}
	}

	@FXML
	// 重置方法，清空所有输入框内容
	public void reset() {
		zhanghaoTxt.clear();
		mimaTxt.clear();
		shoujihaoTxt.clear();
		shengriDate.setValue(null);
		shenfenzhenghousiweiTxt.clear();
		zhanghaoQueryTxt.clear();
		shoujihaoQueryTxt.clear();
	}

	@FXML
	// 查询方法，根据输入框中的条件查询用户记录
	public void query() {
		// 清空数据列表
		list.clear();
		// 创建Yonghu对象，设置查询条件
		Yonghu yonghu = new Yonghu();
		yonghu.setZhanghao(zhanghaoQueryTxt.getText());
		yonghu.setShoujihao(shoujihaoQueryTxt.getText());
		// 查询数据并添加到列表
		list.addAll(yonghu.query());
	}

	// 文件复制方法，将一个文件复制到另一个文件
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