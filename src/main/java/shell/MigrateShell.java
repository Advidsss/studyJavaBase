package shell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
* Created by  WangDi  on 2017/9/29 0029
*/
public class MigrateShell {

    public static void main(String[] args) {

        // 1.清华MBA 更新 2.清华FMBA更新
        int type = 1;
        List<String> list = readText("E:\\remote", "更新数据.txt");
        createShell(list, type);
    }

    //创建shell
    private static void createShell(List<String> list,int type){

        //分别定义存放js相关和java相关list
        List<String> javaList = new ArrayList<String>();
        List<String> jsList = new ArrayList<String>();

        list.forEach(resultStr->{

            if(!resultStr.trim().equals("") && !resultStr.startsWith("#")){

                if(resultStr.startsWith("/statics")){

                    jsList.add(resultStr);
                }else {

                    javaList.add(resultStr);
                }
            }
        });

        //打包脚本
        StringBuilder handlePackedFile = new StringBuilder();

        //迁移脚本
        StringBuilder migrateFile = new StringBuilder();

        handlePackedFile.append("copyPath=/root/copyQHMBA");
        handlePackedFile.append("\n");
        handlePackedFile.append("cd $copyPath");
        handlePackedFile.append("\n");
        handlePackedFile.append("rm -Rf *.*");
        handlePackedFile.append("\n");

        switch (type){

            case 1:
                handlePackedFile.append("classPath=/usr/local/tomcat/sit_webapps/ROOT/WEB-INF/classes");
                handlePackedFile.append("\n");
                handlePackedFile.append("jsPath=/usr/local/tomcat/sit_webapps/ROOT");
                break;

            case 2:
                handlePackedFile.append("classPath=/export/tranzvision/tomcat/uat_webapps/ROOT/WEB-INF/classes");
                handlePackedFile.append("\n");
                handlePackedFile.append("jsPath=/export/tranzvision/tomcat/uat_webapps/ROOT");
                break;

            default:
                break;
        }
        handlePackedFile.append("\n");

        migrateFile.append("cd  /home/psoft");
        migrateFile.append("\n");
        migrateFile.append("rm -Rf copyQHMBA");
        migrateFile.append("\n");
        migrateFile.append("tar -xvf copyQHMBA.tar copyQHMBA");
        migrateFile.append("\n");
        migrateFile.append("cd copyQHMBA");
        migrateFile.append("\n");

        switch (type){

            case 1:
                migrateFile.append("classPath=/home/tomcat/mba_webapps/ROOT/WEB-INF/classes");
                migrateFile.append("\n");
                migrateFile.append("jsPath=/opt/CRM/MBAZS/tzgd_java");
                break;

            case 2:
                migrateFile.append("classPath=/TranzvisionApps/apps/tomcat/FMBA/tomcatapply/apply_webapps/ROOT/WEB-INF/classes");
                migrateFile.append("\n");
                migrateFile.append("jsPath=/opt/CRM/tzgd_java");
                break;

            default:
                break;
        }
        migrateFile.append("\n");

        //java打包文件
        List<String> javaPackedList = new ArrayList<String>();

        javaList.forEach(resultFile->{

            if (resultFile.endsWith(".class") || resultFile.endsWith(".html") || resultFile.endsWith(".sql")
                    || resultFile.endsWith(".xml")){

                handlePackedFile.append("cp $classPath");
                handlePackedFile.append(resultFile);
                handlePackedFile.append(" $copyPath");
                handlePackedFile.append("\n");

                // 备份 ，检查文件是否存在，如果不存在，不需要备份
                migrateFile.append("filePath=$classPath");
                migrateFile.append(resultFile);
                migrateFile.append("\n");
                migrateFile.append("if [ -f $filePath ]; then");
                migrateFile.append("\n");
                migrateFile.append("cp $classPath");
                migrateFile.append(resultFile);
                migrateFile.append(" $classPath");
                migrateFile.append(resultFile);
                migrateFile.append(".bak");
                migrateFile.append("\n");
                migrateFile.append("fi");
                migrateFile.append("\n");

                //创建目录
                switch (type){

                    case 1:
                        migrateFile.append(makeCatalog("/home/tomcat/mba_webapps/ROOT/WEB-INF/classes"
                                + resultFile.substring(0, resultFile.lastIndexOf("/"))));
                        break;

                    case 2:
                        migrateFile.append(makeCatalog("/TranzvisionApps/apps/tomcat/FMBA/tomcatapply/apply_webapps/ROOT/WEB-INF/classes"
                                + resultFile.substring(0, resultFile.lastIndexOf("/"))));
                        break;

                }
                migrateFile.append("cd /home/psoft/copyQHMBA");
                migrateFile.append("\n");
                migrateFile.append("cp");
                migrateFile.append(resultFile.substring(resultFile.lastIndexOf("/") + 1, resultFile.length()));
                migrateFile.append(" $classPath");
                migrateFile.append(resultFile.substring(0, resultFile.lastIndexOf("/")));
                migrateFile.append("\n");

            }else{

                javaPackedList.add(resultFile);
            }

            System.out.println("java打包文件的个数=========" + javaPackedList.size());

            String tempStr = "";
            if(javaPackedList.size() > 0){

                for (String resultPacked:javaPackedList){

                    handlePackedFile.append("cd $classPath");
                    handlePackedFile.append(resultPacked.substring(0, resultPacked.lastIndexOf("/")));
                    handlePackedFile.append("\n");

                    tempStr = resultPacked.substring(resultPacked.lastIndexOf("/") + 1, resultPacked.length());
                    handlePackedFile.append("tar cvf ");
                    handlePackedFile.append(tempStr);

                    if(tempStr.startsWith("/html")){

                        handlePackedFile.append("_html.tar ");
                    }else if(tempStr.startsWith("/sql")){

                        handlePackedFile.append("_sql.tar ");
                    }else {

                        handlePackedFile.append("_java.tar ");
                    }

                    handlePackedFile.append(tempStr);
                    handlePackedFile.append("\n");

                    if (resultPacked.startsWith("/html")) {
                        handlePackedFile.append("cp " + tempStr + "_html.tar $copyPath");
                    } else if (resultPacked.startsWith("/sql")) {
                        handlePackedFile.append("cp " + tempStr + "_sql.tar $copyPath");
                    } else {
                        handlePackedFile.append("cp " + tempStr + "_java.tar $copyPath");
                    }

                    handlePackedFile.append("\n");

                    // 备份,整个文件夹打包 检查文件夹是否存在，如果不存在，不需要备份
                    migrateFile.append("myPath=$classPath" + resultPacked);
                    migrateFile.append("\n");
                    migrateFile.append("if [ -d $myPath ] ; then");
                    migrateFile.append("\n");
                    migrateFile.append("cd $classPath" + resultPacked.substring(0, resultPacked.lastIndexOf("/")));
                    migrateFile.append("\n");
                    migrateFile.append("tar cvf  " + tempStr + ".tar " + tempStr);
                    migrateFile.append("\n");
                    migrateFile.append("mv " + tempStr + ".tar " + tempStr + "_bak.tar");
                    migrateFile.append("\n");
                    migrateFile.append("fi");
                    migrateFile.append("\n");

                    // 创建目录
                    switch (type) {
                        case 1:

                            migrateFile.append(makeCatalog("/home/tomcat/mba_webapps/ROOT/WEB-INF/classes"
                                    + migrateFile.substring(0, resultPacked.lastIndexOf("/"))));
                            break;
                        case 2:

                            migrateFile.append(makeCatalog("/TranzvisionApps/apps/tomcat/FMBA/tomcatapply/apply_webapps/ROOT/WEB-INF/classes"
                                    + resultPacked.substring(0, resultPacked.lastIndexOf("/"))));
                            break;
                    }

                    migrateFile.append("cd /home/psoft/copyQHMBA");
                    migrateFile.append("\n");

                    if (resultPacked.startsWith("/html")) {
                        migrateFile.append(
                                "cp " + tempStr + "_html.tar $classPath" + resultPacked.substring(0, resultPacked.lastIndexOf("/")));
                    } else if (resultPacked.startsWith("/sql")) {
                        migrateFile.append(
                                "cp " + tempStr + "_sql.tar $classPath" + resultPacked.substring(0, resultPacked.lastIndexOf("/")));
                    } else {
                        migrateFile.append(
                                "cp " + tempStr + "_java.tar $classPath" + resultPacked.substring(0, resultPacked.lastIndexOf("/")));
                    }
                    migrateFile.append("\n");
                    migrateFile.append("cd $classPath" + resultPacked.substring(0, resultPacked.lastIndexOf("/")));
                    migrateFile.append("\n");

                    if (resultPacked.startsWith("/html")) {
                        migrateFile.append("tar -xvf " + tempStr + "_html.tar " + tempStr);
                    } else if (resultPacked.startsWith("/sql")) {
                        migrateFile.append("tar -xvf " + tempStr + "_sql.tar " + tempStr);
                    } else {
                        migrateFile.append("tar -xvf " + tempStr + "_java.tar " + tempStr);
                    }

                    migrateFile.append("\n");

                    if (resultPacked.startsWith("/html")) {
                        migrateFile.append("rm -Rf " + tempStr + "_html.tar");
                    } else if (resultPacked.startsWith("/sql")) {
                        migrateFile.append("rm -Rf " + tempStr + "_sql.tar");
                    } else {
                        migrateFile.append("rm -Rf " + tempStr + "_java.tar");
                    }
                    migrateFile.append("rm -Rf " + tempStr + ".tar");
                    migrateFile.append("\n");
                    migrateFile.append("cd /home/psoft/copyQHMBA");
                    migrateFile.append("\n");
                }
            }

            //js处理
            List<String> jsPackedList = new ArrayList<String>();
            jsList.forEach(resultJsFile->{

                if (resultJsFile.endsWith(".css") || resultJsFile.endsWith(".js") || resultJsFile.endsWith(".png")
                        || resultJsFile.endsWith(".jpg") || resultJsFile.endsWith(".html")){

                    handlePackedFile.append("cp $jsPath");
                    handlePackedFile.append(resultJsFile);
                    handlePackedFile.append(" $copyPath");
                    handlePackedFile.append("\n");

                    // 备份 ，检查文件是否存在，如果不存在，不需要备份
                    migrateFile.append("filePath=$classPath" + resultJsFile);
                    migrateFile.append("\n");
                    migrateFile.append("if [ -f $filePath ]; then");
                    migrateFile.append("\n");
                    migrateFile.append("cp $jsPath");
                    migrateFile.append(resultJsFile);
                    migrateFile.append(" $jsPath");
                    migrateFile.append(resultJsFile);
                    migrateFile.append(".bak");
                    migrateFile.append("\n");
                    migrateFile.append("\n");
                    migrateFile.append("fi");
                    migrateFile.append("\n");

                    switch (type){

                        case 1:

                            migrateFile.append(makeCatalog("/opt/CRM/MBAZS/tzgd_java" + resultJsFile.substring(0, resultJsFile.lastIndexOf("/"))));
                            break;
                        case 2:

                            migrateFile.append(makeCatalog("/opt/CRM/tzgd_java" + resultJsFile.substring(0, resultJsFile.lastIndexOf("/"))));
                            break;
                        default:

                            break;
                    }

                    migrateFile.append("cd /home/psoft/copyQHMBA");
                    migrateFile.append("\n");
                    migrateFile.append("cp ");
                    migrateFile.append(resultJsFile.substring(resultJsFile.lastIndexOf("/") + 1, resultJsFile.length()));
                    migrateFile.append(" $jsPath");
                    migrateFile.append(resultJsFile.substring(0, resultJsFile.lastIndexOf("/")));
                    migrateFile.append("\n");
                }else {

                    jsPackedList.add(resultFile);
                }

                System.out.println("js打包文件的个数=========" + javaPackedList.size());

                String tempJsStr = "";
                if(jsPackedList.size() > 0){

                    for (String resultJsPacked:jsPackedList){

                        handlePackedFile.append("cd $jsPath");
                        handlePackedFile.append(resultJsPacked.substring(0, resultJsPacked.lastIndexOf("/")));
                        handlePackedFile.append("\n");

                        tempJsStr = resultJsPacked.substring(resultJsPacked.lastIndexOf("/") + 1, resultJsPacked.length());
                        handlePackedFile.append("tar cvf ");
                        handlePackedFile.append(tempJsStr);
                        handlePackedFile.append("_js.tar ");
                        handlePackedFile.append(tempJsStr);
                        handlePackedFile.append("\n");
                        handlePackedFile.append("cp " + tempJsStr + "_js.tar $copyPath");
                        handlePackedFile.append("\n");

                        // 备份。整个文件夹打包 检查文件夹是否存在，如果不存在，不需要备份
                        migrateFile.append("myPath=$jsPath" + resultFile);
                        migrateFile.append("\n");
                        migrateFile.append("if [ -d $myPath ] ; then");
                        migrateFile.append("\n");
                        migrateFile.append("cd $jsPath" + resultFile.substring(0, resultFile.lastIndexOf("/")));
                        migrateFile.append("\n");
                        migrateFile.append("tar cvf  " + tempJsStr + ".tar " + tempJsStr);
                        migrateFile.append("\n");
                        migrateFile.append("mv " + tempJsStr + ".tar " + tempJsStr + "_bak.tar");
                        migrateFile.append("\n");
                        migrateFile.append("fi");
                        migrateFile.append("\n");

                        //JDBsb.append(this.mkdir("/opt/CRM/MBAZS/tzgd_java" + jsstr.substring(0, jsstr.lastIndexOf("/"))));

                        switch (type) {
                            case 1:
                                migrateFile.append(makeCatalog("/opt/CRM/MBAZS/tzgd_java" + resultJsFile.substring(0, resultJsFile.lastIndexOf("/"))));

                                break;
                            case 2:
                                migrateFile.append(makeCatalog("/opt/CRM/tzgd_java" + resultJsFile.substring(0, resultJsFile.lastIndexOf("/"))));
                                break;
                        }

                        migrateFile.append("cd /home/psoft/copyQHMBA");
                        migrateFile.append("\n");
                        migrateFile.append("cp " + tempJsStr + "_js.tar $jsPath" + resultJsFile.substring(0, resultJsFile.lastIndexOf("/")));
                        migrateFile.append("\n");
                        migrateFile.append("cd $jsPath" + tempJsStr.substring(0, resultJsFile.lastIndexOf("/")));
                        migrateFile.append("\n");
                        migrateFile.append("tar -xvf " + tempJsStr + "_js.tar " + tempJsStr);
                        migrateFile.append("\n");
                        migrateFile.append("rm -Rf " + tempJsStr + "_js.tar");
                        migrateFile.append("\n");
                        migrateFile.append("cd /home/psoft/copyQHMBA");
                        migrateFile.append("\n");
                    }
                }

                handlePackedFile.append("cd /root");
                handlePackedFile.append("\n");
                handlePackedFile.append("tar cvf copyQHMBA.tar copyQHMBA");
                handlePackedFile.append("\n");

                writeText("E:\\remote", "packed.sh", handlePackedFile.toString());
                writeText("E:\\remote", "migrate.sh", migrateFile.toString());
            });
        });

    }

    //创建文件夹方法
    private static String makeCatalog(String path){

        StringBuilder sbMakeDir = new StringBuilder();
        sbMakeDir.append("myPath=" + path);
        sbMakeDir.append("\n");
        sbMakeDir.append("if [ -d $myPath ] ; then");
        sbMakeDir.append("\n");
        sbMakeDir.append("echo $myPath\"文件夹存在！\"");
        sbMakeDir.append("\n");
        sbMakeDir.append("else");
        sbMakeDir.append("\n");
        sbMakeDir.append("\n");
        sbMakeDir.append("mkdir " + path);
        sbMakeDir.append("\n");
        sbMakeDir.append("fi");
        sbMakeDir.append("\n");
        return sbMakeDir.toString();
    }

    private  static boolean writeText(String textPath, String textname, String date) {
        boolean flag = false;
        File filePath = new File(textPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        try {
            FileWriter fw = new FileWriter(textPath + File.separator + textname);
            fw.write(date);
            System.out.println("write " + textname + " OK");
            flag = true;
            if (fw != null)
                fw.close();
        } catch (IOException e) {
            System.out.println("write " + textname + " false");
            e.printStackTrace();
        }finally {

        }
        return flag;
    }

    private static List<String> readText(String textPath, String textname) {

        File file = new File(textPath + File.separator + textname);
        List<String> list = new ArrayList<String>();
        try {

            BufferedReader br = new BufferedReader(new java.io.FileReader(file));
            String line = br.readLine();
            while (line != null) {
                if (!line.startsWith("#")) {
                    list.add(line);
                }
                line = br.readLine();
            }
            br.close();
            return list;
        } catch (IOException e) {
            System.out.println("read  " + textname + " false");
            e.printStackTrace();
            return null;
        }
    }
}
