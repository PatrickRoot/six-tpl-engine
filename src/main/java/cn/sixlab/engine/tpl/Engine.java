package cn.sixlab.engine.tpl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 *
 * @author 六楼的雨/loki
 */
public class Engine extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("tool.fxml"));
        } catch (IOException e1) {
            System.out.println(e1.getMessage());
        }
        Scene scene = new Scene(parent, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("文本生成器");
        primaryStage.show();
    }
}
