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

import entity.Dianying;
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

// 电影控制器类，实现 Initializable 接口
public class DianyingController implements Initializable {

	// FXML 注解用于注入 UI 组件
	@FXML
	private TableView<Dianying> dianyingTable;  // 电影信息表格
	@FXML
	private TableColumn<Dianying, String> dianyingColumn;  // 电影列
	@FXML
	private TableColumn<Dianying, String> yingpianleixingColumn;  // 影片类型列
	@FXML
	private TableColumn<Dianying, String> shizhangColumn;  // 时长列
	@FXML
	private TableColumn<Dianying, String> daoyanColumn;  // 导演列
	@FXML
	private TableColumn<Dianying, String> haibaoColumn;  // 海报列
	@FXML
	private TextField dianyingQueryTxt;  // 电影查询文本框
	@FXML
	private TextField dianyingTxt;  // 电影文本框
	@FXML
	private ComboBox<String> yingpianleixingQueryCombox;  // 影片类型查询下拉框
	@FXML
	private ComboBox<String> yingpianleixingCombox;  // 影片类型下拉框
	@FXML
	private TextField shizhangTxt;  // 实掌文本框
	@FXML
	private TextField daoyanTxt;  // 导演文本框
	@FXML
	private ImageView haibaoImg;  // 海报图片视图
	@FXML
	private TextField haibaoTxt;  // 海报文本框

	private Dianying dianying = new Dianying();// 创建 Dianying 对象用于存储电影信息
	private ObservableList<Dianying> list = FXCollections.observableArrayList();// 创建 ObservableList 用于在 TableView 中展示电影信息列表

	// Initialize 方法用于初始化界面组件和事件处理
	public void initialize(URL location, ResourceBundle resources) {
		// 将 ObservableList 与 TableView 绑定，实现动态更新电影信息列表
		dianyingTable.setItems(list);
		// 配置 TableView 列，并与 Dianying 类中的属性绑定
		dianyingColumn.setCellValueFactory(new PropertyValueFactory("dianying"));
		yingpianleixingColumn.setCellValueFactory(new PropertyValueFactory("yingpianleixing"));
		shizhangColumn.setCellValueFactory(new PropertyValueFactory("shizhang"));
		daoyanColumn.setCellValueFactory(new PropertyValueFactory("daoyan"));
		haibaoColumn.setCellValueFactory(new PropertyValueFactory("haibao"));
		// 设置 TableView 的点击事件，用于在选中电影时更新详细信息
		dianyingTable.setOnMouseClicked(e -> {
			if (dianyingTable.getSelectionModel().getSelectedItem() != null) {
				// 将选中电影的信息显示在对应的文本框、下拉框和图片视图中
				dianyingTxt.setText(dianyingTable.getSelectionModel().getSelectedItem().getDianying());
				yingpianleixingCombox.getSelectionModel().select(dianyingTable.getSelectionModel().getSelectedItem().getYingpianleixing());
				shizhangTxt.setText(dianyingTable.getSelectionModel().getSelectedItem().getShizhang());
				daoyanTxt.setText(dianyingTable.getSelectionModel().getSelectedItem().getDaoyan());
				haibaoTxt.setText(dianyingTable.getSelectionModel().getSelectedItem().getHaibao());
				// 尝试加载电影海报图片并显示在图片视图中
				try {
					haibaoImg.setImage(new Image("file:" + new File("").getCanonicalPath() + "/image/" + haibaoTxt.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		// 设置 HaibaoColumn 的自定义单元格工厂，用于在 TableView 中显示电影海报
		haibaoColumn.setCellFactory(new Callback<TableColumn<Dianying, String>, TableCell<Dianying, String>>() {
			public TableCell<Dianying, String> call(TableColumn<Dianying, String> param) {
				return new TableCell<Dianying, String>() {
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty || item == null) {
							super.setText(null);
							super.setGraphic(null);
						} else {
							try {
								// 从文件加载并显示电影海报图片
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

// 向 yingpianleixingCombox 和 yingpianleixingQueryCombox 添加选项
		yingpianleixingCombox.getItems().addAll("喜剧", "悬疑", "科幻", "战争", "惊悚", "恐怖", "动漫");
		yingpianleixingQueryCombox.getItems().addAll("喜剧", "悬疑", "科幻", "战争", "惊悚", "恐怖", "动漫");

// 调用 refresh 方法刷新电影信息列表
		refresh();

	}

	// 刷新电影信息列表
	@FXML
	public void refresh() {
		// 清空 ObservableList 中的数据，并重新加载电影信息
		list.clear();
		list.addAll(dianying.query());
		reset();  // 调用 reset 方法重置界面组件
	}

	// 删除选中的电影信息
	@FXML
	public void delete() {
		if (dianyingTable.getSelectionModel().getSelectedItem() != null) {
			dianyingTable.getSelectionModel().getSelectedItem().delete();
			showMsg("删除成功");
			refresh();  // 删除后刷新电影信息列表
		}
	}

	// 添加新的电影信息
	@FXML
	public void add() {
		Dianying dianying = new Dianying();
		// 从界面组件获取电影信息并设置到 Dianying 对象中
		dianying.setDianying(dianyingTxt.getText());
		dianying.setYingpianleixing(yingpianleixingCombox.getSelectionModel().getSelectedItem());
		dianying.setShizhang(shizhangTxt.getText());
		dianying.setDaoyan(daoyanTxt.getText());
		dianying.setHaibao(haibaoTxt.getText());
		dianying.add();  // 调用 add 方法添加电影信息
		showMsg("添加成功");
		refresh();  // 添加后刷新电影信息列表
	}

	// 修改选中的电影信息
	@FXML
	public void edit() {
		if (dianyingTable.getSelectionModel().getSelectedItem() != null) {
			Dianying dianying = dianyingTable.getSelectionModel().getSelectedItem();
			// 从界面组件获取电影信息并设置到选中的 Dianying 对象中
			dianying.setDianying(dianyingTxt.getText());
			dianying.setYingpianleixing(yingpianleixingCombox.getSelectionModel().getSelectedItem());
			dianying.setShizhang(shizhangTxt.getText());
			dianying.setDaoyan(daoyanTxt.getText());
			dianying.setHaibao(haibaoTxt.getText());
			dianying.update();  // 调用 update 方法修改电影信息
			showMsg("修改成功");
			refresh();  // 修改后刷新电影信息列表
		}
	}

	// 重置界面组件，清空输入框和下拉框的值
	@FXML
	public void reset() {
		dianyingTxt.clear();
		yingpianleixingCombox.getSelectionModel().select(null);
		shizhangTxt.clear();
		daoyanTxt.clear();
		haibaoTxt.clear();
		haibaoImg.setImage(null);
		dianyingQueryTxt.clear();
		yingpianleixingQueryCombox.getSelectionModel().select(null);
	}

	// 查询电影信息
	@FXML
	public void query() {
		// 清空 ObservableList 中的数据，并重新加载查询结果的电影信息
		list.clear();
		Dianying dianying = new Dianying();
		// 从界面组件获取查询条件并设置到 Dianying 对象中
		dianying.setDianying(dianyingQueryTxt.getText());
		dianying.setYingpianleixing(yingpianleixingQueryCombox.getSelectionModel().getSelectedItem());
		list.addAll(dianying.query());  // 调用 query 方法查询电影信息
	}

	// 上传电影海报图片
	@FXML
	public void uploadHaibao(ActionEvent e) {
		// 使用文件选择器获取用户选择的文件
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(((Stage) ((Button) e.getSource()).getScene().getWindow()));
		if (selectedFile != null) {
			try {
				// 检查目标目录是否存在，不存在则创建
				if (!new File("image").exists()) new File("image").mkdir();
				// 调用 copyFileUsingStream 方法将选中的文件复制到目标目录
				copyFileUsingStream(selectedFile, new File("image/" + selectedFile.getName()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			haibaoTxt.setText(selectedFile.getName());
			try {
				// 尝试加载上传的电影海报图片并显示在图片视图中
				haibaoImg.setImage(new Image("file:" + new File("").getCanonicalPath() + "/image/" + haibaoTxt.getText()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			showMsg("上传成功");
		}
	}

	// 使用输入输出流复制文件
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

	// 显示信息提示框
	public void showMsg(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.titleProperty().set("提示");
		alert.headerTextProperty().set(msg);
		alert.showAndWait();
	}
}
