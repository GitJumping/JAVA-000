package avaTest;

import java.sql.*;
import java.util.Calendar;

public class StatementAbilityTest {
    public static void main(String[] args) {
        //generateMillionRecordWithJDBC();
        generateMillionRecordWithJDBCBatch();
    }

    //耗时 1338480 毫秒
    public static void generateMillionRecordWithJDBC(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            long start = System.currentTimeMillis();
            for (int i = 1; i <= 1000000; i++) {
                String insert = "INSERT INTO `p_order`(`serial_no`, `predict_sum`, `discounts_sum`, `logistics_sum`, `actual_sum`, `pay_method_id`, `logistics_id`," +
                        " `order_state`, `logistics_state`, `operator_id`, `remark`, `operation_id`, `create_time`, `change_time`, `logistics_time`, `success_time`, `sign`)" +
                        " VALUES ("+i+", '11', '0.1', '0.1', '10.8', 1, 1, 0, 0, 1, '测试订单', 1, now(), now(), now(), now(), 1);";
                Statement insertStatement = connection.createStatement();
                insertStatement.execute(insert);
            }
            connection.close();
            System.out.println("执行用时"+ (System.currentTimeMillis() - start)+"ms");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //耗时 1331480 毫秒
    public static void generateMillionRecordWithJDBCBatch(){
        //String sql = "insert into p_order(id,order_code,order_time,address,total_amount,user_id)values(?,?,now(),'~',?,?)";
        String sql = "INSERT INTO `p_order`(`serial_no`, `predict_sum`, `discounts_sum`, `logistics_sum`, `actual_sum`, `pay_method_id`, `logistics_id`, `order_state`, `logistics_state`, `operator_id`, `remark`, `operation_id`, `create_time`, `change_time`, `logistics_time`, `success_time`, `sign`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now(), now(), now(), 1);";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int loopTimes = 1000000;
        try {
            long startTime = Calendar.getInstance().getTimeInMillis();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            preparedStatement = conn.prepareStatement(sql);
            int i = loopTimes - 1000000;
            for(i=0;i< loopTimes;i++){
                preparedStatement.setLong(1,i);
                preparedStatement.setString(2, "11");
                preparedStatement.setString(3,"0.1");
                preparedStatement.setString(4,"0.1");
                preparedStatement.setString(5,"0.1");
                preparedStatement.setInt(6,1);
                preparedStatement.setInt(7,1);
                preparedStatement.setInt(8,1);
                preparedStatement.setInt(9,1);
                preparedStatement.setInt(10,1);
                preparedStatement.setString(11,"测试订单");
                preparedStatement.setInt(12,1);
                //preparedStatement.setLong(13, now());
                //preparedStatement.setInt(17,1);
                preparedStatement.execute();
            }
            long endTime = Calendar.getInstance().getTimeInMillis();
            System.out.println("耗时："+(endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
            if(conn!= null) {
                try {
                    conn.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }
}
