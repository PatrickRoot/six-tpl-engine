package cn.sixlab.engine.tpl;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.lang.reflect.Method;

public class TemplateEngineTest {

    String tplFileName = "tplTestFile.txt";
    String dataFileName = "dataTestFile.json";
    String resultFileName = "resultTestFile.txt";
    String testFileName = "justTest.txt";

    @BeforeMethod
    public void setUp() throws Exception {

        String tpl = "<ul>\n" +
                "\t{@each list as it,k}\n" +
                "\t\t<li>${it.name} (index: ${k})</li>\n" +
                "\t{@/each}\n" +
                "\t{@each blah as it}\n" +
                "\t\t<li>\n" +
                "\t\t\tnum: ${it.num} <br />\n" +
                "\t\t\t{@if it.num==3}\n" +
                "\t\t\t\t{@each it.inner as it2}\n" +
                "\t\t\t\t\t${it2.time} <br />\n" +
                "\t\t\t\t{@/each}\n" +
                "\t\t\t{@/if}\n" +
                "\t\t</li>\n" +
                "\t{@/each}\n" +
                "</ul>";

        String data = "{" +
                "\tlist:[" +
                "\t\t{name:'guokai',show:true}," +
                "\t\t{name:'benben',show:false}," +
                "\t\t{name:'dierbaby',show:true}" +
                "\t]," +
                "\tblah:[" +
                "\t\t{num:1}," +
                "\t\t{num:2}," +
                "\t\t{num:3,inner:[" +
                "\t\t\t{'time':'15:00'}," +
                "\t\t\t{'time':'16:00'}," +
                "\t\t\t{'time':'17:00'}," +
                "\t\t\t{'time':'18:00'}" +
                "\t\t]}," +
                "\t\t{num:4}" +
                "\t]" +
                "}";

        File tplFile = new File(tplFileName);
        if (!tplFile.exists()) {
            tplFile.createNewFile();
        }
        FileWriter writer = new FileWriter(tplFile);
        writer.write(tpl);
        writer.flush();
        writer.close();

        File dataFile = new File(dataFileName);
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
        writer = new FileWriter(dataFile);
        writer.write(data);
        writer.flush();
        writer.close();

        File result = new File(resultFileName);
        if (result.exists()) {
            result.delete();
        }
    }

    @AfterMethod
    public void tearDown() throws Exception {
        File tplFile = new File(tplFileName);
        if (tplFile.exists()) {
            tplFile.delete();
        }

        File dataFile = new File(dataFileName);
        if (dataFile.exists()) {
            dataFile.delete();
        }

        File result = new File(resultFileName);
        if (result.exists()) {
            result.delete();
        }
    }

    @Test
    public void testGenerateFromFiles() throws Exception {

        TemplateEngine.generateFromFiles(tplFileName, dataFileName, resultFileName);
        TemplateEngine.generateFromFiles(tplFileName, dataFileName, resultFileName);
    }

    @Test
    public void testGenerateFromString() throws Exception {
        String tpl = "<ul>\n" +
                "\t{@each list as it,k}\n" +
                "\t\t<li>${it.name} (index: ${k})</li>\n" +
                "\t{@/each}\n" +
                "\t{@each blah as it}\n" +
                "\t\t<li>\n" +
                "\t\t\tnum: ${it.num} <br />\n" +
                "\t\t\t{@if it.num==3}\n" +
                "\t\t\t\t{@each it.inner as it2}\n" +
                "\t\t\t\t\t${it2.time} <br />\n" +
                "\t\t\t\t{@/each}\n" +
                "\t\t\t{@/if}\n" +
                "\t\t</li>\n" +
                "\t{@/each}\n" +
                "</ul>";

        String data = "{" +
                "\tlist:[" +
                "\t\t{name:'guokai',show:true}," +
                "\t\t{name:'benben',show:false}," +
                "\t\t{name:'dierbaby',show:true}" +
                "\t]," +
                "\tblah:[" +
                "\t\t{num:1}," +
                "\t\t{num:2}," +
                "\t\t{num:3,inner:[" +
                "\t\t\t{'time':'15:00'}," +
                "\t\t\t{'time':'16:00'}," +
                "\t\t\t{'time':'17:00'}," +
                "\t\t\t{'time':'18:00'}" +
                "\t\t]}," +
                "\t\t{num:4}" +
                "\t]" +
                "}";

        String result = TemplateEngine.generateFromString(tpl, data);
        System.out.println(result);
    }

    @Test
    public void testReadTextFile() throws Exception {
        Method method = TemplateEngine.class.getDeclaredMethod("readTextFile", InputStream.class);
        method.setAccessible(true);

        InputStream is = TemplateEngine.class.getResourceAsStream("/juicer-min.js");
        System.out.println(method.invoke(new TemplateEngine(), is));
        System.out.println("-----------------");

        File file = new File(testFileName);
        if(!file.exists()){
            file.createNewFile();
        }

        String text = "";
        FileWriter writer = new FileWriter(file);
        writer.write(text);
        writer.flush();
        writer.close();
        is = new FileInputStream(file);
        System.out.println(method.invoke(new TemplateEngine(), is));
        System.out.println("-----------------");

        text = " has \n sth \n end ";
        writer = new FileWriter(file);
        writer.write(text);
        writer.flush();
        writer.close();
        is = new FileInputStream(file);
        System.out.println(method.invoke(new TemplateEngine(), is));
        System.out.println("-----------------");

        file.delete();
    }
}