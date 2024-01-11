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

import entity.Changcidingdan;
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

public class MyOrderUI implements Initializable {

	@FXML
	private TableView<Changcidingdan> changcidingdanTable;
	@FXML
	private TableColumn<Changcidingdan, String> dingdanhaoColumn;
	@FXML
	private TableColumn<Changcidingdan, String> changciColumn;
	@FXML
	private TableColumn<Changcidingdan, String> dianyingColumn;
	@FXML
	private TableColumn<Changcidingdan, String> riqiColumn;
	@FXML
	private TableColumn<Changcidingdan, String> shijianColumn;
	@FXML
	private TableColumn<Changcidingdan, String> jiageColumn;
	@FXML
	private TableColumn<Changcidingdan, String> haibaoColumn;
	@FXML
	private TableColumn<Changcidingdan, String> dingdanshijianColumn;
	@FXML
	private TableColumn<Changcidingdan, String> dingdanzongjiaColumn;
	@FXML
	private TableColumn<Changcidingdan, String> zuoweiColumn;
	@FXML
	private TableColumn<Changcidingdan, String> yonghuColumn;
	@FXML
	private TextField dingdanhaoQueryTxt;
	@FXML
	private TextField dingdanhaoTxt;
	@FXML
	private TextField changciQueryTxt;
	@FXML
	private TextField changciTxt;
	@FXML
	private TextField dianyingQueryTxt;
	@FXML
	private TextField dianyingTxt;
	@FXML
	private TextField riqiQueryTxt;
	@FXML
	private TextField riqiTxt;
	@FXML
	private TextField shijianTxt;
	@FXML
	private TextField jiageTxt;
	@FXML
	private ImageView haibaoImg;
	@FXML
	private TextField haibaoTxt;
	@FXML
	private TextField dingdanshijianTxt;
	@FXML
	private TextField dingdanzongjiaTxt;
	@FXML
	private TextField zuoweiTxt;
	@FXML
	private TextField yonghuTxt;

	// 创建Changcidingdan的实例和一个ObservableList来存储数据
	private Changcidingdan changcidingdan = new Changcidingdan();
	private ObservableList<Changcidingdan> list = FXCollections.observableArrayList();

	// initialize方法在关联的FXML文件被加载时调用
	public void initialize(URL location, ResourceBundle resources) {
		// 将ObservableList设置为表格的数据源
		changcidingdanTable.setItems(list);

		// 使用PropertyValueFactory为每一列设置单元格值工厂
		dingdanhaoColumn.setCellValueFactory(new PropertyValueFactory<>("dingdanhao"));
		changciColumn.setCellValueFactory(new PropertyValueFactory<>("changci"));
		dianyingColumn.setCellValueFactory(new PropertyValueFactory<>("dianying"));
		riqiColumn.setCellValueFactory(new PropertyValueFactory<>("riqi"));
		shijianColumn.setCellValueFactory(new PropertyValueFactory<>("shijian"));
		jiageColumn.setCellValueFactory(new PropertyValueFactory<>("jiage"));
		haibaoColumn.setCellValueFactory(new PropertyValueFactory<>("haibao"));
		dingdanshijianColumn.setCellValueFactory(new PropertyValueFactory<>("dingdanshijian"));
		dingdanzongjiaColumn.setCellValueFactory(new PropertyValueFactory<>("dingdanzongjia"));
		zuoweiColumn.setCellValueFactory(new PropertyValueFactory<>("zuowei"));
		yonghuColumn.setCellValueFactory(new PropertyValueFactory<>("yonghu"));

		// 当表格行被点击时触发事件
		changcidingdanTable.setOnMouseClicked(e -> {
			if (changcidingdanTable.getSelectionModel().getSelectedItem() != null) {
				// 将选中行的数据显示在相应的文本框中
				dingdanhaoTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getDingdanhao());
				changciTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getChangci());
				dianyingTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getDianying());
				riqiTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getRiqi());
				shijianTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getShijian());
				jiageTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getJiage());
				haibaoTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getHaibao());

				// 设置海报图片
				try {
					haibaoImg.setImage(new Image("file:" + new File("").getCanonicalPath() + "/image/" + haibaoTxt.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				dingdanshijianTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getDingdanshijian());
				dingdanzongjiaTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getDingdanzongjia());
				zuoweiTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getZuowei());
				yonghuTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getYonghu());
			}
		});

		// 为海报列设置自定义的CellFactory
		haibaoColumn.setCellFactory(new Callback<TableColumn<Changcidingdan, String>, TableCell<Changcidingdan, String>>() {
			public TableCell<Changcidingdan, String> call(TableColumn<Changcidingdan, String> param) {
				return new TableCell<Changcidingdan, String>() {
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty || item == null) {
							// 如果单元格为空，则清空文本和图形
							super.setText(null);
							super.setGraphic(null);
						} else {
							// 否则，显示海报图片
							try {
								ImageView iv = new ImageView("file:" + new File("").getCanonicalPath() + "/image/" + item);
								iv.setFitHeight(70);
								iv.setFitWidth(70);
								super.setGraphic(iv);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}

					;
				};
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
		// 设置用户
		changcidingdan.setYonghu(YonghuLoginController.username);
		// 查询数据并添加到列表
		list.addAll(changcidingdan.query());
		// 重置输入框
		reset();
	}

	@FXML
// 删除方法，删除选中行的订单记录
	public void delete() {
		if (changcidingdanTable.getSelectionModel().getSelectedItem() != null) {
			// 删除选中的订单记录
			changcidingdanTable.getSelectionModel().getSelectedItem().delete();
			// 显示删除成功的提示信息
			showMsg("删除成功");
			// 刷新表格数据
			refresh();
		}
	}

	@FXML
// 添加方法，向数据库中添加订单记录
	public void add() {
		// 创建新的订单记录对象，并设置其属性
		Changcidingdan changcidingdan = new Changcidingdan();
		changcidingdan.setDingdanhao(dingdanhaoTxt.getText());
		// ... 设置其他属性
		changcidingdan.add();
		// 显示添加成功的提示信息
		showMsg("添加成功");
		// 刷新表格数据
		refresh();
	}

	@FXML
// 编辑方法，修改选中行的订单记录
	public void edit() {
		if (changcidingdanTable.getSelectionModel().getSelectedItem() != null) {
			// 获取选中的订单记录
			Changcidingdan changcidingdan = changcidingdanTable.getSelectionModel().getSelectedItem();
			// 更新订单记录的属性
			changcidingdan.setDingdanhao(dingdanhaoTxt.getText());
			// ... 设置其他属性
			changcidingdan.update();
			// 显示修改成功的提示信息
			showMsg("修改成功");
			// 刷新表格数据
			refresh();
		}
	}

	@FXML
// 重置方法，清空所有输入框内容
	public void reset() {
		dingdanhaoTxt.clear();
		changciTxt.clear();
		// ... 清空其他输入框内容
	}

	@FXML
// 查询方法，根据输入框中的条件查询订单记录
	public void query() {
		// 清空数据列表
		list.clear();
		// 创建Changcidingdan对象，设置查询条件
		Changcidingdan changcidingdan = new Changcidingdan();
		changcidingdan.setDingdanhao(dingdanhaoQueryTxt.getText());
		// ... 设置其他查询条件
		changcidingdan.setYonghu(YonghuLoginController.username);
		// 查询数据并添加到列表
		list.addAll(changcidingdan.query());
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