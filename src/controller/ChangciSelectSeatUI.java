package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import entity.Changci;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
	选座controller，选择表格中的一行会显示出所有可供选择的座位，
	如果座位已被选择，则显示为灰色
	每次点击座位的时候会更想selectedsetbtn集合，
	然后判断座位是否被选中

*/
public  class  ChangciSelectSeatUI  implements  Initializable{  //  创建ChangciSelectSeatUI类实现Initializable接口

	@FXML
	private  TableView<Changci>  changciTable;  //  注入Changci类型的TableView
	@FXML
	private  TableColumn<Changci,  String>  changciColumn;  //  注入Changci类型和String类型的TableColumn
	@FXML
	private  TableColumn<Changci,  String>  dianyingColumn;  //  注入Changci类型和String类型的TableColumn
	@FXML
	private  TableColumn<Changci,  String>  riqiColumn;  //  注入Changci类型和String类型的TableColumn
	@FXML
	private  TableColumn<Changci,  String>  shijianColumn;  //  注入Changci类型和String类型的TableColumn
	@FXML
	private  TableColumn<Changci,  String>  jiageColumn;  //  注入Changci类型和String类型的TableColumn
	@FXML
	private  TableColumn<Changci,  String>  haibaoColumn;  //  注入Changci类型和String类型的TableColumn
	@FXML
	private  TextField  changciQueryTxt;  //  注入TextField
	@FXML
	private  TextField  changciTxt;  //  注入TextField
	@FXML
	private  ComboBox<String>  dianyingCombox;  //  注入String类型的ComboBox
	@FXML
	private  ComboBox<String>  dianyingQueryCombox;  //  注入String类型的ComboBox
	@FXML
	private  DatePicker  riqiQueryDate;  //  注入DatePicker
	@FXML
	private  DatePicker  riqiDate;  //  注入DatePicker
	@FXML
	private  TextField  shijianTxt;  //  注入TextField
	@FXML
	private  TextField  jiageTxt;  //  注入TextField
	@FXML
	private  ImageView  haibaoImg;  //  注入ImageView
	@FXML
	private  TextField  haibaoTxt;  //  注入TextField
	List<Button>  btnlist=new  ArrayList<Button>();  //  创建Button类型的列表
	HBox  hbox  =  new  HBox();  //  创建HBox
	int  row=6;  //  定义整型变量row并赋值为6
	int  column=6;  //  定义整型变量column并赋值为6
	int  area=1;  //  定义整型变量area并赋值为1
	int  seatwidth=80;  //  定义整型变量seatwidth并赋值为80
	int  seatheight=70;  //  定义整型变量seatheight并赋值为70
	Set<String>  selectedsetbtn=new  TreeSet<String>();  //  创建String类型的集合selectedsetbtn
	@FXML
	private  VBox  seatBox;  //  注入VBox类型的seatBox变量


	private  Changci  changci=new  Changci();  //  创建Changci对象
	private  ObservableList<Changci>  list=FXCollections.observableArrayList();  //  创建可观察列表

	public  void  initialize(URL  location,  ResourceBundle  resources)  {  //  初始化方法
		changciTable.setItems(list);  //  设置表格的数据源为list
		changciColumn.setCellValueFactory(new  PropertyValueFactory("changci"));  //  设置表格列与Changci对象属性的关联
		dianyingColumn.setCellValueFactory(new  PropertyValueFactory("dianying"));  //  设置表格列与Changci对象属性的关联
		riqiColumn.setCellValueFactory(new  PropertyValueFactory("riqi"));  //  设置表格列与Changci对象属性的关联
		shijianColumn.setCellValueFactory(new  PropertyValueFactory("shijian"));  //  设置表格列与Changci对象属性的关联
		jiageColumn.setCellValueFactory(new  PropertyValueFactory("jiage"));  //  设置表格列与Changci对象属性的关联
		haibaoColumn.setCellValueFactory(new  PropertyValueFactory("haibao"));  //  设置表格列与Changci对象属性的关联

		changciTable.setOnMouseClicked(e->{  //  点击表格行时触发事件
			if(changciTable.getSelectionModel().getSelectedItem()!=null){  //  如果选中了表格行
				changciTxt.setText(changciTable.getSelectionModel().getSelectedItem().getChangci());  //  设置场次文本框的值
				dianyingCombox.getSelectionModel().select(changciTable.getSelectionModel().getSelectedItem().getDianying());  //  设置电影选择框的值
				riqiDate.setValue(LocalDate.parse(changciTable.getSelectionModel().getSelectedItem().getRiqi()));  //  设置日期选择框的值
				shijianTxt.setText(changciTable.getSelectionModel().getSelectedItem().getShijian());  //  设置时间文本框的值
				jiageTxt.setText(changciTable.getSelectionModel().getSelectedItem().getJiage());  //  设置价格文本框的值
				haibaoTxt.setText(changciTable.getSelectionModel().getSelectedItem().getHaibao());  //  设置海报文本框的值
				try  {
					haibaoImg.setImage(new  Image("file:"+new  File("").getCanonicalPath()+"/image/"+haibaoTxt.getText()));  //  设置海报图片
				}  catch  (Exception  e1)  {
					e1.printStackTrace();
				}
				updateSeat();  //  调用更新座位方法
			}
		});

//  设置haibaoColumn的单元格工厂
		haibaoColumn.setCellFactory(new  Callback<TableColumn<Changci,String>,  TableCell<Changci,String>>()  {
			//  实现call方法
			public  TableCell<Changci,  String>  call(TableColumn<Changci,  String>  param)  {
				//  创建一个新的TableCell
				return  new  TableCell<Changci,String>()  {
					//  重写updateItem方法
					protected  void  updateItem(String  item,  boolean  empty)  {
						super.updateItem(item,  empty);
						if  (empty  ||  item  ==  null)  {
							super.setText(null);
							super.setGraphic(null);
						}    else  {
							try  {
								//  创建图像视图
								ImageView  iv  =  new  ImageView("file:"+new  File("").getCanonicalPath()+"/image/"+item);
								//  设置图像视图的宽度和高度
								iv.setFitHeight(70);
								iv.setFitWidth(70);
								//  设置单元格的图形
								super.setGraphic(iv);
							}  catch  (IOException  e)  {
								e.printStackTrace();
							}
						}
					};
				};
			}
		});
		List<entity.Dianying> dianyinglist=new entity.Dianying().query(); // 获取电影列表
		for (int i = 0; i < dianyinglist.size(); i++) { // 循环遍历电影列表
			dianyingCombox.getItems().add(dianyinglist.get(i).getDianying()); // 向下拉菜单添加电影选项
			dianyingQueryCombox.getItems().add(dianyinglist.get(i).getDianying()); // 向查询下拉菜单添加电影选项
		}
		dianyingCombox.setOnAction(e->{ // 下拉菜单选择事件监听
			entity.Dianying dianying=new entity.Dianying(); // 创建电影对象
			dianying.setDianying(dianyingCombox.getSelectionModel().getSelectedItem()); // 设置选中的电影
			haibaoTxt.setText(dianying.query().get(0).getHaibao()); // 显示选中电影的海报
		});
		refresh(); // 刷新界面
	}
	@FXML
	public  void  refresh(){  //  刷新方法         list.clear();  //  清空数据列表
		list.addAll(changci.query());  //  将查询到的场次数据重新加入列表
		reset();  //  重置数据
		updateSeat();        //  更新座位
	}

	@FXML
	public  void  delete(){  //  删除方法
		if(changciTable.getSelectionModel().getSelectedItem()!=null){  //  如果选中的场次不为空
			changciTable.getSelectionModel().getSelectedItem().delete();  //  删除选中的场次
			showMsg("删除成功");  //  显示删除成功消息
			refresh();  //  刷新页面
		}
	}
	@FXML
	public  void  add(){  //  添加方法
		Changci  changci=new  Changci();  //  创建场次实例
		changci.setChangci(changciTxt.getText());  //  设置场次名称
		changci.setDianying(dianyingCombox.getSelectionModel().getSelectedItem());    //  设置电影
		if(riqiDate.getValue()!=null)changci.setRiqi(riqiDate.getValue().toString());  //  如果选择了日期则设置日期
		changci.setShijian(shijianTxt.getText());  //  设置时间
		changci.setJiage(jiageTxt.getText());  //  设置价格
		changci.setHaibao(haibaoTxt.getText());  //  设置海报
		changci.add();  //  添加场次
		showMsg("添加成功");  //  显示添加成功消息
		refresh();  //  刷新页面
	}

	@FXML
	public  void  edit(){  //编辑方法
		if(changciTable.getSelectionModel().getSelectedItem()!=null){  //如果场次表格中有选中项
			Changci  changci=changciTable.getSelectionModel().getSelectedItem();  //获取选中的changci对象
			changci.setChangci(changciTxt.getText());  //设置changci的场次属性为文本框中的文本
			changci.setDianying(dianyingCombox.getSelectionModel().getSelectedItem());  //设置changci的电影属性为下拉框中选中的项
			if(riqiDate.getValue()!=null)changci.setRiqi(riqiDate.getValue().toString());  //如果日期选择器有值，则设置changci的日期属性为日期的字符串值
			changci.setShijian(shijianTxt.getText());  //设置changci的时间属性为时间文本框中的文本
			changci.setJiage(jiageTxt.getText());  //设置changci的价格属性为价格文本框中的文本
			changci.setHaibao(haibaoTxt.getText());  //设置changci的海报属性为海报文本框中的文本
			changci.update();  //更新changci
			showMsg("修改成功");  //显示成功消息
			refresh();  //刷新
		}
	}
	@FXML
	public  void  reset(){  //重置方法
		changciTxt.clear();  //清空场次文本框
		dianyingCombox.getSelectionModel().select(null);  //将电影下拉框的选择项设为null
		riqiDate.setValue(null);  //将日期选择器的值设为null
		shijianTxt.clear();  //清空时间文本框
		jiageTxt.clear();  //清空价格文本框
		haibaoTxt.clear();  //清空海报文本框
		haibaoImg.setImage(null);  //将海报图片设为null
		changciQueryTxt.clear();  //清空场次查询文本框
		dianyingQueryCombox.getSelectionModel().select(null);  //将电影查询下拉框的选择项设为null
		riqiQueryDate.setValue(null);  //将日期查询选择器的值设为null
	}
	@FXML
	public  void  query(){  //查询方法
		list.clear();  //清空列表
		Changci  changci=new  Changci();  //创建新的changci对象
		changci.setChangci(changciQueryTxt.getText());  //设置changci的场次属性为场次查询文本框中的文本
		changci.setDianying(dianyingQueryCombox.getSelectionModel().getSelectedItem());  //设置changci的电影属性为电影查询下拉框中选中的项
		if(riqiQueryDate.getValue()!=null)changci.setRiqi(riqiQueryDate.getValue().toString());  //如果日期查询选择器有值，则设置changci的日期属性为日期查询选择器的字符串值
		list.addAll(changci.query());  //将查询结果添加到列表中
	}
	private  void  copyFileUsingStream(File  source,  File  dest)  throws  IOException  {  //使用流复制文件的方法
		InputStream  is  =  null;  //输入流为空
		OutputStream  os  =  null;  //输出流为空
		try  {
			is  =  new  FileInputStream(source);  //创建文件输入流
			os  =  new  FileOutputStream(dest);  //创建文件输出流
			byte[]  buffer  =  new  byte[1024];  //创建缓冲区
			int  length;  //长度
			while  ((length  =  is.read(buffer))  >  0)  {  //当读取到文件末尾时停止
				os.write(buffer,  0,  length);  //将读取的内容写入输出流
			}
		}  finally  {
			is.close();  //关闭输入流
			os.close();  //关闭输出流
		}
	}
	/**
		更加座位界面
	*/
	public void updateSeat() {
		// 清除先前的座位信息和列表
		seatBox.getChildren().clear();
		selectedsetbtn.clear();
		btnlist.clear();
		hbox.getChildren().clear();
		btnlist.clear();

		// 如果 'changciTxt' 为空，返回
		if (changciTxt.getText().isEmpty()) return;

		// 初始化一个列表以存储座位信息
		List<String> slist = new ArrayList<>();

		// 创建一个 Changcidingdan 对象并设置其 changci 属性
		entity.Changcidingdan changcidingdan = new entity.Changcidingdan();
		changcidingdan.setChangci(changciTxt.getText());

		// 查询数据库以根据 changci 获取 Changcidingdan 对象的列表
		List<entity.Changcidingdan> dlist = changcidingdan.query();

		// 从列表中提取座位信息并添加到 'slist'
		// 遍历查询到的场次订单列表
		for (int i = 0; i < dlist.size(); i++) {
			// 从订单对象中获取座位信息并按空格拆分成数组
			String ss[] = dlist.get(i).getZuowei().split(" ");

			// 遍历座位数组
			for (int j = 0; j < ss.length; j++) {
				// 如果座位信息非空，将其添加到座位列表（slist）中
				if (!ss[j].equals("")) {
					slist.add(ss[j]);
				}
			}
		}

// 创建ScrollPane、设置外层布局VBox和hbox的属性
		ScrollPane sp = new ScrollPane();
		hbox.setAlignment(Pos.TOP_CENTER);
		hbox.setMinHeight(seatheight);
		VBox vbox = new VBox();

// 循环创建座位区域（GridPane）
		for (int k = 0; k < area; k++) {
			GridPane gp = new GridPane();
			gp.setPadding( new Insets(10) );
			gp.setHgap( 8 );
			gp.setVgap( 8 );
			gp.setAlignment(Pos.TOP_CENTER);
			VBox.setVgrow(gp, Priority.ALWAYS );
			int mid=column/2-1;
			for (int i = 0; i < row; i++) {
				int cindex=0;
				for (int j = 0; j < column; j++) {
					Button btn=new Button(k+""+i+""+j);
					if(ifselected(slist, btn.getText())){
						btn.setStyle("-fx-background-color:rgb(128,128,128)");
					}else{
						btn.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent event) {
								updateBtn(btn.getText());
							}
						});
					}

					// 设置座位按钮的大小和将按钮添加到列表中
					btn.setPrefWidth(seatwidth);
					btn.setPrefHeight(seatheight);
					btnlist.add(btn);
					gp.add(btn, cindex, i);

					// 在每行中间添加一个空白按钮
					if (j == mid) {
						cindex++;
						Button blankbtn = new Button("");
						blankbtn.setPrefWidth(seatwidth * 2);
						blankbtn.setPrefHeight(seatheight);
						gp.add(blankbtn, cindex, i);
					}
					cindex++;
				}
			}

			// 将座位区域添加到VBox中，并在座位区域之间添加分隔线
			vbox.getChildren().add(gp);
			vbox.getChildren().add(new Separator());
		}


		// 创建按钮条
		ButtonBar buttonBar = new ButtonBar();
		buttonBar.setPadding(new Insets(10));

// 创建确定按钮，并设置点击事件
		Button saveButton = new Button("确定");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// 检查是否选择了座位
				if (selectedsetbtn.size() == 0) {
					showMsg("您还没有选择座位，请重新选择后购买");
					return;
				}

				// 创建场次订单对象，并设置订单信息
				entity.Changcidingdan dingdan = new entity.Changcidingdan();
				dingdan.setZuowei(getSelectedSeat());
				dingdan.setDingdanhao(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
				dingdan.setChangci(changciTxt.getText());
				dingdan.setDianying(dianyingCombox.getSelectionModel().getSelectedItem());
				if (riqiDate.getValue() != null) dingdan.setRiqi(riqiDate.getValue().toString());
				dingdan.setShijian(shijianTxt.getText());
				dingdan.setJiage(jiageTxt.getText());
				dingdan.setHaibao(haibaoTxt.getText());
				dingdan.setDingdanshijian(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				dingdan.setDingdanzongjia(String.valueOf(Double.valueOf(jiageTxt.getText()) * dingdan.getZuowei().split(" ").length));
				dingdan.setYonghu(YonghuLoginController.username);

				// 弹出确认购买对话框
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("确认购买");
				alert.setHeaderText("确定购买吗?");
				alert.setContentText("您当前的选择是:" + getSelectedSeat());
				Optional<ButtonType> result = alert.showAndWait();

				// 如果用户确认购买，添加订单并刷新界面
				if (result.get() == ButtonType.OK) {
					dingdan.add();
					showMsg("购买成功");
					refresh();
				}
			}
		});

// 创建取消按钮，并设置点击事件
		Button cancelButton = new Button("取消");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// 关闭窗口
				((Stage) ((Button) e.getSource()).getScene().getWindow()).close();
			}
		});

// 设置按钮的数据类型
		buttonBar.setButtonData(saveButton, ButtonBar.ButtonData.OK_DONE);
		buttonBar.setButtonData(cancelButton, ButtonBar.ButtonData.CANCEL_CLOSE);

// 将按钮添加到按钮条
		buttonBar.getButtons().addAll(saveButton, cancelButton);

// 设置ScrollPane的内容和宽度属性
		sp.setContent(vbox);
		sp.fitToWidthProperty().set(true);

// 将按钮条、ScrollPane、和座位信息显示区域添加到容器中
		seatBox.getChildren().add(hbox);
		seatBox.getChildren().add(sp);
		seatBox.getChildren().add(buttonBar);

	}
	// 判断座位是否已被选择
	public boolean ifselected(List<String> seatlist, String seat) {
		for (int i = 0; i < seatlist.size(); i++) {
			if (seatlist.get(i).equals(seat)) {
				return true;
			}
		}
		return false;
	}

	// 获取已选择的座位字符串
	public String getSelectedSeat() {
		String seats = "";
		for (String ss : selectedsetbtn) {
			seats += ss + " ";
		}
		return seats;
	}

	// 更新座位按钮状态
	public void updateBtn(String btntxt) {
		for (int i = 0; i < btnlist.size(); i++) {
			if (btntxt.equals(btnlist.get(i).getText())) {
				if (btnlist.get(i).getStyle().equals("-fx-background-color:red")) {
					// 如果座位按钮已选中，取消选中并从已选座位列表中移除
					btnlist.get(i).setStyle("");
					selectedsetbtn.remove(btntxt);
				} else if (btnlist.get(i).getStyle().equals("")) {
					// 如果座位按钮未选中，设置为选中状态并添加到已选座位列表
					btnlist.get(i).setStyle("-fx-background-color:red");
					selectedsetbtn.add(btntxt);
				}
			}
		}

		// 清空座位信息显示区域并重新添加已选择的座位按钮
		hbox.getChildren().clear();
		for (String sd : selectedsetbtn) {
			Button btn = new Button(sd);
			btn.setPrefWidth(seatwidth);
			btn.setPrefHeight(seatheight);
			btn.setStyle("-fx-background-color:yellow");
			hbox.getChildren().add(btn);
		}
	}

	// 显示提示信息
	public void showMsg(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.titleProperty().set("提示");
		alert.headerTextProperty().set(msg);
		alert.showAndWait();
	}
}
