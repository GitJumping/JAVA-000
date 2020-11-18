package demo.h3;

public class JDBCConnection {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testssm","root","123456");
            if (connection!=null) {
                System.out.println("connection successful!");
            } else {
                System.out.println("connection failed!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("no driver");
            e.printStackTrace();
            return;
        } catch (SQLException e) {
            System.out.println("connection failed!");
            e.printStackTrace();
        }
    }

    private void close() throws SQLException {
        preparedStatement.close();
        connection.close();
        System.out.println("连接关闭");
    }

    public static void main(String[] args) throws SQLException {
        Map<String,Object> values = new HashMap<>();
        values.put("id",0);
        values.put("name","lisa");
        values.put("age",23);
        values.put("sex","女");
        values.put("address","天津");
        //System.out.println(values.keySet().toString());
        //System.out.println(values.keySet().toString().substring(1,values.keySet().toString().length()-1));
        JDBCConnection example = new JDBCConnection();
        example.createConnection();
//        String table = "user";
//        String columns = "(name,age,sex,address)";
//        List<Object> param = Arrays.asList("mary",18,"nv","beijing");
//        //example.insert(table,columns,param);
//
//        List<Map<String,Object>> result = example.query(table,values,null);
//        for (Map r:result) {
//            System.out.println(r.toString());
//        }
        example.close();
    }
}
