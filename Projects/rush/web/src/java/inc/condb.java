package inc;

import java.sql.*;
import javax.sql.*;

public class condb {
  private String dburl =
      "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=cics"; //数据库标识名
  private Connection conn = null;
  private ResultSet rs = null;
  private String user = "sa";
  private String password = "";

  public condb() {
    try {
      Class.forName("net.sourceforge.jtds.jdbc.Driver"); //装载数据库驱动

    }
    catch (Exception ex) {
      ex.printStackTrace();

    }
  }

  public Connection getcondb() throws SQLException {
    conn = DriverManager.getConnection(dburl, user, password); //获取连接
    //Statement st=conn.createStatement();
    return conn;

  }

  public ResultSet executeQuery(String sql) { //执行查询语句
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement stmt = null;
    try {
      conn = getcondb();
      stmt = conn.prepareStatement(sql);
      rs = stmt.executeQuery();

    }
    catch (SQLException e) {

    }
    return rs;

  }

  public void executeUpdate(String sql) { //执行更新，插入，删除
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      conn = getcondb();
      stmt = conn.prepareStatement(sql);
      stmt.executeUpdate();

    }
    catch (SQLException e) {

    }

  }

}
