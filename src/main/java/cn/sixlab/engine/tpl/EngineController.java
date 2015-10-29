package cn.sixlab.engine.tpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import javax.script.ScriptException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 *
 * @author 六楼的雨/loki
 */
public class EngineController {

    @FXML
    private TextArea resultText;
    @FXML
    private TextArea tplText;
    @FXML
    private TextArea dataText;

    private File chooseFile() {
        FileChooser fc = new FileChooser();
        fc.setTitle("选择文件");
        return fc.showOpenDialog(dataText.getScene().getWindow());
    }

    public void chooseData(ActionEvent event) {
        File file = chooseFile();
        try {
            String data = TemplateEngine.readTextFile(new FileInputStream(file));
            dataText.setText(data);
        } catch (IOException e) {
            dataText.setText("获取失败");
        }
    }

    public void chooseTpl(ActionEvent event) {
        File file = chooseFile();
        try {
            String tpl = TemplateEngine.readTextFile(new FileInputStream(file));
            tplText.setText(tpl);
        } catch (IOException e) {
            tplText.setText("获取失败");
        }
    }

    public void saveFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All types (*.*)", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(dataText.getScene().getWindow());
        if (file == null) return;
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String result = resultText.getText();
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(result);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void resetResult(ActionEvent event) {
        resultText.setText("");
    }

    public void resetData(ActionEvent event) {
        dataText.setText("");
    }

    public void resetTpl(ActionEvent event) {
        tplText.setText("");
    }

    public void genText(ActionEvent event) {
        String tpl = tplText.getText();
        String data= dataText.getText();
        try {
            String result = TemplateEngine.generateFromString(tpl, data);
            resultText.setText(result);
        } catch (IOException e) {
            resultText.setText("程序内部读取文件错误。");
        } catch (ScriptException e) {
            resultText.setText("模板 或者 数据 错误。");
        }
    }
}
