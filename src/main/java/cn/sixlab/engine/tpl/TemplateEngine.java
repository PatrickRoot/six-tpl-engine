/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.engine.tpl;

import com.sun.istack.internal.Nullable;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 通过Juicer模板和Json数据生成文本
 *
 * @author 六楼的雨/loki
 * @date 2015/7/27 13:51
 */
public class TemplateEngine {

    /**
     * 从指定的tpl模板文件和json数据的文件生成文本文件，保存到指定路径，并返回生成的文本文件的字符串。
     *
     * @param tplFileName 模板文件路径
     * @param dataFileName json数据文件路径
     * @param resultFilePath 保存结果的文本文件的路径
     * @return 从模板和数据生成的文本字符串
     */
    public static String generateFromFiles(String tplFileName, String dataFileName, String resultFilePath)
            throws IOException, ScriptException {
        String tpl = readTextFile(new FileInputStream(tplFileName));
        String json = readTextFile(new FileInputStream(dataFileName));
        String result = generateFromString(tpl, json);

        File file = new File(resultFilePath);
        if(!file.exists() || !file.isFile()){
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        writer.write(result);
        writer.flush();
        writer.close();
        return result;
    }

    /**
     * 传入字符串数组和json格式字符串，生成文本字符串并返回。
     *
     * @param tpl 模板字符串。
     * @param json json格式的字符串。
     * @return 生成的文本文件。
     */
    public static String generateFromString(@Nullable String tpl,@Nullable String json)
            throws IOException, ScriptException {
        InputStream is = TemplateEngine.class.getResourceAsStream("/juicer-min.js");
        String juicer = readTextFile(is);

        tpl = tpl.replaceAll("'","\'");
        tpl = tpl.replaceAll("\n","','");

        StringBuffer sb = new StringBuffer();

        sb.append(" var tplArray = ['");
        sb.append(tpl);
        sb.append("']; \n ");

        sb.append(" var tpl = tplArray.join('\\n'); \n ");
        sb.append(" var data= " + json + " ; \n ");
        sb.append(juicer+" \n ");
        sb.append(" juicer(tpl,data); ");

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        return (String) engine.eval(sb.toString());
    }

    /**
     *  从文本文件读取字符串
     *
     * @param is 文件的输入流
     * @return 返回的字符串
     * @throws IOException 文件读取错误
     */
    private static String readTextFile(InputStream is) throws IOException {
        Reader isReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isReader);

        StringBuffer sb = new StringBuffer();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line+"\n");
        }
        if(sb.length()>0){
            return sb.substring(0, sb.length()-1);
        }
        return "";
    }

}
