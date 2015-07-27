/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.engine.tpl;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/7/27 13:51
 */
public class TemplateEngine {

    public static void main(String[] args) throws ScriptException, IOException {

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        StringBuilder js = new StringBuilder();

        String[] juicerArray = readJsFile("juicer-min.js");
        String[] tplArray = readJsFile("tpl.txt");
        String[] dataArray = readJsFile("data.json");

        StringBuilder invokeJuicer = new StringBuilder();
        invokeJuicer.append(" var data = ");
        for (String data : dataArray) {
            invokeJuicer.append(data + "\n");
        }
        invokeJuicer.append("; \n ");

        invokeJuicer.append("var tplArray = [");
        int length = tplArray.length;
        int temp = 0;
        for (String tpl : tplArray) {
            invokeJuicer.append("'" + tpl + "'");
            if (++temp < length) {
                invokeJuicer.append(",");
            }
        }
        invokeJuicer.append(" ]; \n ");

        invokeJuicer.append(" var tpl = tplArray.join('\\n'); ");
        invokeJuicer.append(";print(tpl);\n\n");
        System.out.println(invokeJuicer.toString());

        invokeJuicer.append(" juicer(tpl, data) \n ");

        for (String juicer : juicerArray) {
            js.append(juicer + "\n");
        }
        js.append(invokeJuicer);

        String text = (String) engine.eval(js.toString());
        FileWriter writer = new FileWriter("result.txt");
        writer.write(text);
        writer.flush();
        writer.close();
    }

    private static String[] readJsFile(String fileName) throws IOException {
        InputStream is = TemplateEngine.class.getResourceAsStream("/" + fileName);
        Reader isReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isReader);

        List<String> jsFiles = new ArrayList<>();

        String jsFile;
        while ((jsFile = reader.readLine()) != null) {
            jsFiles.add(jsFile);
        }

        return jsFiles.toArray(new String[]{});
    }

}
