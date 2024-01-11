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

public class ChangcidingdanController implements Initializable{
	@FXML
	private TableView<Changcidingdan> changcidingdanTable; // 长磁订单表格
	@FXML
	private TableColumn<Changcidingdan, String> dingdanhaoColumn; // 订单号列
	@FXML
	private TableColumn<Changcidingdan, String> changciColumn; // 场次列
	@FXML
	private TableColumn<Changcidingdan, String> dianyingColumn; // 电影列
	@FXML
	private TableColumn<Changcidingdan, String> riqiColumn; // 日期列
	@FXML
	private TableColumn<Changcidingdan, String> shijianColumn; // 时间列
	@FXML
	private TableColumn<Changcidingdan, String> jiageColumn; // 价格列
	@FXML
	private TableColumn<Changcidingdan, String> haibaoColumn; // 海报列
	@FXML
	private TableColumn<Changcidingdan, String> dingdanshijianColumn; // 订单时间列
	@FXML
	private TableColumn<Changcidingdan, String> dingdanzongjiaColumn; // 订单总价列
	@FXML
	private TableColumn<Changcidingdan, String> zuoweiColumn; // 座位列
	@FXML
	private TableColumn<Changcidingdan, String> yonghuColumn; // 用户列
	@FXML
	private TextField dingdanhaoQueryTxt; // 订单号查询文本框
	@FXML
	private TextField dingdanhaoTxt; // 订单号文本框
	@FXML
	private TextField changciQueryTxt; // 场次查询文本框
	@FXML
	private TextField changciTxt; // 场次文本框
	@FXML
	private TextField dianyingQueryTxt; // 电影查询文本框
	@FXML
	private TextField dianyingTxt; // 电影文本框
	@FXML
	private TextField riqiQueryTxt; // 日期查询文本框
	@FXML
	private TextField riqiTxt; // 日期文本框
	@FXML
	private TextField shijianTxt; // 时间文本框
	@FXML
	private TextField jiageTxt; // 价格文本框
	@FXML
	private ImageView haibaoImg; // 海报图片
	@FXML
	private TextField haibaoTxt; // 海报文本框
	@FXML
	private TextField dingdanshijianTxt; // 订单时间文本框
	@FXML
	private TextField dingdanzongjiaTxt; // 订单总价文本框
	@FXML
	private TextField zuoweiTxt; // 座位文本框
	@FXML
	private TextField yonghuTxt; // 用户文本框

	private  Changcidingdan  changcidingdan=new  Changcidingdan();  //  创建Changcidingdan对象
	private  ObservableList<Changcidingdan>  list=FXCollections.observableArrayList();  //  创建可观测列表
	public  void  initialize(URL  location,  ResourceBundle  resources)  {  //  初始化方法
		changcidingdanTable.setItems(list);  //  设置changcidingdanTable的数据源为list
		dingdanhaoColumn.setCellValueFactory(new  PropertyValueFactory("dingdanhao"));  //  设置dingdanhao列的值工厂
		changciColumn.setCellValueFactory(new  PropertyValueFactory("changci"));  //  设置changci列的值工厂
		dianyingColumn.setCellValueFactory(new  PropertyValueFactory("dianying"));  //  设置dianying列的值工厂
		riqiColumn.setCellValueFactory(new  PropertyValueFactory("riqi"));  //  设置riqi列的值工厂
		shijianColumn.setCellValueFactory(new  PropertyValueFactory("shijian"));  //  设置shijian列的值工厂
		jiageColumn.setCellValueFactory(new  PropertyValueFactory("jiage"));  //  设置jiage列的值工厂
		haibaoColumn.setCellValueFactory(new  PropertyValueFactory("haibao"));  //  设置haibao列的值工厂
		dingdanshijianColumn.setCellValueFactory(new  PropertyValueFactory("dingdanshijian"));  //  设置dingdanshijian列的值工厂
		dingdanzongjiaColumn.setCellValueFactory(new  PropertyValueFactory("dingdanzongjia"));  //  设置dingdanzongjia列的值工厂
		zuoweiColumn.setCellValueFactory(new  PropertyValueFactory("zuowei"));  //  设置zuowei列的值工厂
		yonghuColumn.setCellValueFactory(new  PropertyValueFactory("yonghu"));  //  设置yonghu列的值工厂
		changcidingdanTable.setOnMouseClicked(e->{  //  当表格被点击时
			if(changcidingdanTable.getSelectionModel().getSelectedItem()!=null){
				dingdanhaoTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getDingdanhao());  //  设置dingdanhaoTxt的文本
				changciTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getChangci());  //  设置changciTxt的文本
				dianyingTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getDianying());  //  设置dianyingTxt的文本
				riqiTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getRiqi());  //  设置riqiTxt的文本
				shijianTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getShijian());  //  设置shijianTxt的文本
				jiageTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getJiage());  //  设置jiageTxt的文本
				haibaoTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getHaibao());  //  设置haibaoTxt的文本
				try  {
					haibaoImg.setImage(new  Image("file:"+new  File("").getCanonicalPath()+"/image/"+haibaoTxt.getText()));  //  设置haibaoImg的图像
				}  catch  (Exception  e1)  {
					e1.printStackTrace();
				}
				dingdanshijianTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getDingdanshijian());  //  设置dingdanshijianTxt的文本
				dingdanzongjiaTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getDingdanzongjia());  //  设置dingdanzongjiaTxt的文本
				zuoweiTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getZuowei());  //  设置zuoweiTxt的文本
				yonghuTxt.setText(changcidingdanTable.getSelectionModel().getSelectedItem().getYonghu());  //  设置yonghuTxt的文本
			}
		});
		haibaoColumn.setCellFactory(new  Callback<TableColumn<Changcidingdan,String>,  TableCell<Changcidingdan,String>>()  {  //  设置haibaoColumn的单元格工厂
			public  TableCell<Changcidingdan,  String>  call(TableColumn<Changcidingdan,  String>  param)  {
				return  new  TableCell<Changcidingdan,String>()  {
					protected  void  updateItem(String  item,  boolean  empty)  {
						super.updateItem(item,  empty);
						if  (empty  ||  item  ==  null)  {
							super.setText(null);
							super.setGraphic(null);
						}    else  {
							try  {
								ImageView  iv  =  new  ImageView("file:"+new  File("").getCanonicalPath()+"/image/"+item);  //  创建ImageView对象
								iv.setFitHeight(70);  //  设置图像视图的高度
								iv.setFitWidth(70);  //  设置图像视图的宽度
								super.setGraphic(iv);  //  设置控件的图形
							}  catch  (IOException  e)  {
								e.printStackTrace();
							}
						}
					};
				};
			}
		});

		refresh();  //  刷新列表
	}
	@FXML
	// 刷新按钮的事件处理方法
	public void refresh(){
		// 清空列表数据
		list.clear();
		// 添加查询结果到列表中
		list.addAll(changcidingdan.query());
		// 重置页面
		reset();
	}

	@FXML public  void  delete(){  //  删除方法
		if(changcidingdanTable.getSelectionModel().getSelectedItem()!=null){  //  如果所选项不为空
			changcidingdanTable.getSelectionModel().getSelectedItem().delete();  //  删除所选项
			showMsg("删除成功");  //  显示消息“删除成功”
			refresh();  //  刷新
		}
	}

	@FXML
	public  void  add(){
		Changcidingdan  changcidingdan=new  Changcidingdan();  //  创建对象
		changcidingdan.setDingdanhao(dingdanhaoTxt.getText());  //  设置订单号
		changcidingdan.setChangci(changciTxt.getText());  //  设置场次
		changcidingdan.setDianying(dianyingTxt.getText());  //  设置电影
		changcidingdan.setRiqi(riqiTxt.getText());  //  设置日期
		changcidingdan.setShijian(shijianTxt.getText());  //  设置时间
		changcidingdan.setJiage(jiageTxt.getText());  //  设置价格
		changcidingdan.setHaibao(haibaoTxt.getText());  //  设置海报
		changcidingdan.setDingdanshijian(dingdanshijianTxt.getText());  //  设置订单时间
		changcidingdan.setDingdanzongjia(dingdanzongjiaTxt.getText());  //  设置订单总价
		changcidingdan.setZuowei(zuoweiTxt.getText());  //  设置座位
		changcidingdan.setYonghu(yonghuTxt.getText());  //  设置用户
		changcidingdan.add();  //  调用添加方法
		showMsg("添加成功");  //  显示信息
		refresh();  //  刷新
	}
	@FXML
	public  void  edit(){
		//  当编辑按钮被点击时执行以下代码
		if(changcidingdanTable.getSelectionModel().getSelectedItem()!=null){
			//  如果场次订单表中有选中的项
			Changcidingdan  changcidingdan=changcidingdanTable.getSelectionModel().getSelectedItem();
			//  获取选中的场次订单
			changcidingdan.setDingdanhao(dingdanhaoTxt.getText());
			changcidingdan.setChangci(changciTxt.getText());
			changcidingdan.setDianying(dianyingTxt.getText());
			changcidingdan.setRiqi(riqiTxt.getText());
			changcidingdan.setShijian(shijianTxt.getText());
			changcidingdan.setJiage(jiageTxt.getText());
			changcidingdan.setHaibao(haibaoTxt.getText());
			changcidingdan.setDingdanshijian(dingdanshijianTxt.getText());
			changcidingdan.setDingdanzongjia(dingdanzongjiaTxt.getText());
			changcidingdan.setZuowei(zuoweiTxt.getText());
			changcidingdan.setYonghu(yonghuTxt.getText());
			//  设置场次订单的各个属性为对应的输入框中的值
			changcidingdan.update();
			//  更新场次订单信息
			showMsg("修改成功");
			//  显示修改成功提示
			refresh();
			//  刷新页面
		}
	}
	@FXML
	public void reset(){
		dingdanhaoTxt.clear(); // 清空订单号文本框
		changciTxt.clear(); // 清空场次文本框
		dianyingTxt.clear(); // 清空电影文本框
		riqiTxt.clear(); // 清空日期文本框
		shijianTxt.clear(); // 清空时间文本框
		jiageTxt.clear(); // 清空价格文本框
		haibaoTxt.clear(); // 清空海报文本框
		haibaoImg.setImage(null); // 清空海报图片
		dingdanshijianTxt.clear(); // 清空订单时间文本框
		dingdanzongjiaTxt.clear(); // 清空订单总价文本框
		zuoweiTxt.clear(); // 清空座位文本框
		yonghuTxt.clear(); // 清空用户文本框
		dingdanhaoQueryTxt.clear(); // 清空订单号查询文本框
		changciQueryTxt.clear(); // 清空场次查询文本框
		dianyingQueryTxt.clear(); // 清空电影查询文本框
		riqiQueryTxt.clear(); // 清空日期查询文本框
	}
	@FXML
	public void query(){
		list.clear(); // 清空列表
		Changcidingdan changcidingdan=new Changcidingdan(); // 创建场次订单对象
		changcidingdan.setDingdanhao(dingdanhaoQueryTxt.getText()); // 设置订单号
		changcidingdan.setChangci(changciQueryTxt.getText()); // 设置场次
		changcidingdan.setDianying(dianyingQueryTxt.getText()); // 设置电影
		changcidingdan.setRiqi(riqiQueryTxt.getText()); // 设置日期
		list.addAll(changcidingdan.query()); // 查询订单并添加到列表
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
		Alert alert = new Alert(AlertType.INFORMATION); // 创建提示对话框
		alert.titleProperty().set("提示"); // 设置对话框标题
		alert.headerTextProperty().set(msg); // 设置对话框消息
		alert.showAndWait(); // 显示提示对话框并等待
	}
}