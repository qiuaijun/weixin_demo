package weixin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import weixin.order.Order;

/**
 * @author qiuaijun
 *
 * @date 2015年4月29日
 */
public class OrderDao {

	private static Connection conn;

	private static Connection initConnection() {

		if (conn != null) {
			return conn;
		}
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		String url = "jdbc:mysql://localhost:3306/test?"
				+ "user=root&password=&useUnicode=true&characterEncoding=UTF8";
		try {
			// 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
			// 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动

			System.out.println("成功加载MySQL驱动程序");
			// 一个Connection代表一个数据库连接
			conn = DriverManager.getConnection(url);
		} catch (Exception e) {

		}
		return conn;
	}

	public static void insertOrder(Order order) {
		initConnection();
		String sql = "insert into ORDER_TAB(id,productid,status,userid) values(?,?,?,?)";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, order.getId());
			stmt.setLong(2, order.getProductId());
			stmt.setInt(3, order.getStatus());
			stmt.setString(4, order.getUserId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void updateOrder(Order order) {
		initConnection();
		String sql = "update ORDER_TAB set productid=?,status=?,userid=? where id=?";
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);

			stmt.setLong(1, order.getProductId());
			stmt.setInt(2, order.getStatus());
			stmt.setString(3, order.getUserId());
			stmt.setInt(4, order.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		Order order = new Order();
		order.setId(1);
		order.setProductId(1);
		order.setStatus(1);
		order.setUserId("WWWWWW");
		updateOrder(order);
	}
}
