package jdbc;

import java.sql.*;

/*
* Created by  WangDi  on 2017/10/10 0010
*/
public class Connect2Mysql {

    public static void main(String[] args) {

        //声明connection对象
        Connection conn;

        String driverName = "com.mysql.cj.jdbc.Driver";
        String urlName = "jdbc:mysql://localhost:3306/sem?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
        String username = "root";
        String password = "123456";

        try {

            //加载驱动
            Class.forName(driverName);

            //连接数据库
            conn = DriverManager.getConnection(urlName,username,password);

            if(!conn.isClosed()){
                System.out.println("Succeeded connecting to the Database!");
            }

            //创建执行语句类
            Statement statement = conn.createStatement();
            String executeSql = "SELECT * FROM ps_tz_jusr_rel_tbl";

            //执行sql语句
            ResultSet resultSet = statement.executeQuery(executeSql);

            while (resultSet.next()){

                System.out.printf("%-10s",resultSet.getString("TZ_JG_ID"));
                System.out.printf("%-15s",resultSet.getString("OPRID"));
                System.out.println(resultSet.getString("TZ_JUGTYP_ID"));
            }

            resultSet.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
