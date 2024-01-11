
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

import entity.Changci;
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

public class ChangciController implements Initializable{

	@FXML
	private TableView<Changci> changciTable; // TableView用于显示电影场次信息
	@FXML
	private TableColumn<Changci, String> changciColumn; // TableColumn用于定义表格列
	@FXML
	private TableColumn<Changci, String> dianyingColumn;
	@FXML
	private TableColumn<Changci, String> riqiColumn;
	@FXML
	private TableColumn<Changci, String> shijianColumn;
	@FXML
	private TableColumn<Changci, String> jiageColumn;
	@FXML
	private TableColumn<Changci, String> haibaoColumn;
	@FXML
	private TextField changciQueryTxt; // TextField用于输入查询条件
	@FXML
	private TextField changciTxt;
	@FXML
	private ComboBox<String> dianyingCombox; // ComboBox用于选择电影
	@FXML
	private ComboBox<String> dianyingQueryCombox;
	@FXML
	private DatePicker riqiQueryDate; // DatePicker用于选择日期
	@FXML
	private DatePicker riqiDate;
	@FXML
	private TextField shijianTxt;
	@FXML
	private TextField jiageTxt;
	@FXML
	private ImageView haibaoImg; // ImageView用于显示电影海报
	@FXML
	private TextField haibaoTxt;

	private Changci changci=new Changci(); // 创建Changci对象用于操作电影场次信息
	private ObservableList<Changci> list=FXCollections.observableArrayList(); // 创建ObservableList用于存储电影场次信息

	public void initialize(URL location, ResourceBundle resources) {
		changciTable.setItems(list); // 设置TableView的数据源为ObservableList
		changciColumn.setCellValueFactory(new PropertyValueFactory("changci")); // 设置TableColumn的数据源为Changci对象的属性
		dianyingColumn.setCellValueFactory(new PropertyValueFactory("dianying"));
		riqiColumn.setCellValueFactory(new PropertyValueFactory("riqi"));
		shijianColumn.setCellValueFactory(new PropertyValueFactory("shijian"));
		jiageColumn.setCellValueFactory(new PropertyValueFactory("jiage"));
		haibaoColumn.setCellValueFactory(new PropertyValueFactory("haibao"));
		changciTable.setOnMouseClicked(e->{
			if(changciTable.getSelectionModel().getSelectedItem()!=null){
				changciTxt.setText(changciTable.getSelectionModel().getSelectedItem().getChangci());
				dianyingCombox.getSelectionModel().select(changciTable.getSelectionModel().getSelectedItem().getDianying());
				riqiDate.setValue(LocalDate.parse(changciTable.getSelectionModel().getSelectedItem().getRiqi()));
				shijianTxt.setText(changciTable.getSelectionModel().getSelectedItem().getShijian());
				jiageTxt.setText(changciTable.getSelectionModel().getSelectedItem().getJiage());
				haibaoTxt.setText(changciTable.getSelectionModel().getSelectedItem().getHaibao());
				try {
					haibaoImg.setImage(new Image("file:"+new File("").getCanonicalPath()+"/image/"+haibaoTxt.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		haibaoColumn.setCellFactory(new Callback<TableColumn<Changci,String>, TableCell<Changci,String>>() {
			public TableCell<Changci, String> call(TableColumn<Changci, String> param) {
				return new TableCell<Changci,String>() {
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty || item == null) {
							super.setText(null);
							super.setGraphic(null);
						}  else {
							try {
								ImageView iv = new ImageView("file:"+new File("").getCanonicalPath()+"/image/"+item);
								iv.setFitHeight(70);
								iv.setFitWidth(70);
								super.setGraphic(iv);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					};
				};
			}
		});

		List<entity.Dianying> dianyinglist=new entity.Dianying().query(); // 查询电影列表
		for (int i = 0; i < dianyinglist.size(); i++) {
			dianyingCombox.getItems().add(dianyinglist.get(i).getDianying()); // 将电影名称添加到ComboBox中
			dianyingQueryCombox.getItems().add(dianyinglist.get(i).getDianying());
		}
		dianyingCombox.setOnAction(e->{
			entity.Dianying dianying=new entity.Dianying();
			dianying.setDianying(dianyingCombox.getSelectionModel().getSelectedItem());
			haibaoTxt.setText(dianying.query().get(0).getHaibao()); // 根据选择的电影名称获取对应的海报路径
		});
		refresh();
	}

	@FXML
	public void refresh(){
		list.clear(); // 清空ObservableList
		list.addAll(changci.query()); // 查询电影场次信息并添加到ObservableList中
		reset();
	}

	@FXML
	public void delete(){
		if(changciTable.getSelectionModel().getSelectedItem()!=null){
			changciTable.getSelectionModel().getSelectedItem().delete(); // 删除选中的电影场次信息
			showMsg("删除成功");
			refresh();
		}
	}

	@FXML
	public void add(){
		Changci changci=new Changci();
		changci.setChangci(changciTxt.getText());
		changci.setDianying(dianyingCombox.getSelectionModel().getSelectedItem());
		if(riqiDate.getValue()!=null)changci.setRiqi(riqiDate.getValue().toString());
		changci.setShijian(shijianTxt.getText());
		changci.setJiage(jiageTxt.getText());
		changci.setHaibao(haibaoTxt.getText());
		changci.add(); // 添加电影场次信息
		showMsg("添加成功");
		refresh();
	}

	@FXML
	public void edit(){
		if(changciTable.getSelectionModel().getSelectedItem()!=null){
			Changci changci=changciTable.getSelectionModel().getSelectedItem();
			changci.setChangci(changciTxt.getText());
			changci.setDianying(dianyingCombox.getSelectionModel().getSelectedItem());
			if(riqiDate.getValue()!=null)changci.setRiqi(riqiDate.getValue().toString());
			changci.setShijian(shijianTxt.getText());
			changci.setJiage(jiageTxt.getText());
			changci.setHaibao(haibaoTxt.getText());
			changci.update(); // 更新选中的电影场次信息
			showMsg("修改成功");
			refresh();
		}
	}

	@FXML
	public void reset(){
		changciTxt.clear();
		dianyingCombox.getSelectionModel().select(null);
		riqiDate.setValue(null);
		shijianTxt.clear();
		jiageTxt.clear();
		haibaoTxt.clear();
		haibaoImg.setImage(null);
		changciQueryTxt.clear();
		dianyingQueryCombox.getSelectionModel().select(null);
		riqiQueryDate.setValue(null);
	}

	@FXML
	public void query(){
		list.clear();
		Changci changci=new Changci();
		changci.setChangci(changciQueryTxt.getText());
		changci.setDianying(dianyingQueryCombox.getSelectionModel().getSelectedItem());
		if(riqiQueryDate.getValue()!=null)changci.setRiqi(riqiQueryDate.getValue().toString());
		list.addAll(changci.query()); // 根据查询条件查询电影场次信息并添加到ObservableList中
	}

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

	public void showMsg(String msg){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.titleProperty().set("提示");
		alert.headerTextProperty().set(msg);
		alert.showAndWait();
	}
}

   