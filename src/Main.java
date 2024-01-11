import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


// JavaFX应用程序的主类
public class Main extends Application {

	// start方法，在init方法返回后、系统准备好运行应用程序之后调用
	public void start(Stage primaryStage) {
		try {
			// 加载LoginUI.fxml文件并创建新场景
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("view/LoginUI.fxml")));

			// 设置场景到主舞台并显示
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}