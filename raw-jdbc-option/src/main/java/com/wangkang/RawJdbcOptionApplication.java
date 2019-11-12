package com.wangkang;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class RawJdbcOptionApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(RawJdbcOptionApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Connection con = null; //定义一个MYSQL链接对象
			Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=GMT%2B8", "root", "root"); //链接本地MYSQL

			Statement stmt; //创建声明
			stmt = con.createStatement();
			con.setAutoCommit(false);

			//新增一条数据
			//ResultSet res = stmt.executeQuery("select LAST_INSERT_ID()");
//			int ret_id;
//			if (res.next()) {
//				ret_id = res.getInt(1);
//				System.out.print(ret_id);
//			}

			//删除一条数据
//			String sql = "DELETE FROM user WHERE id = 1";
//			long deleteRes = stmt.executeUpdate(sql); //如果为0则没有进行删除操作，如果大于0，则记录删除的条数
//			System.out.print("DELETE:" + deleteRes);

			//更新一条数据
//			String updateSql = "UPDATE user SET username = 'xxxx' WHERE id = 2";
//			long updateRes = stmt.executeUpdate(updateSql);
//			System.out.print("UPDATE:" + updateRes);
			//查询数据并输出
			stmt = con.createStatement();
			int count = stmt.executeUpdate("INSERT INTO tt (a) VALUES (111)");
			stmt.execute("set profiling=1;");

			String selectSql = "show profile;";

			ResultSet selectRes = stmt.executeQuery(selectSql);
			System.out.println("--------------" + selectRes);

			while (selectRes.next()) { //循环输出结果集
				System.out.println(selectRes.toString());
//				String username = selectRes.getString("username");
//				String password = selectRes.getString("password");
				System.out.print("12313213213213131321321321\r\n\r\n");
//				System.out.print("username:" + username + "password:" + password);
				String status = selectRes.getString("Status");
				double duration = selectRes.getDouble("Duration");
				System.out.println(duration);

			}

		} catch (Exception e) {
			System.out.print("MYSQL ERROR:" + e.getMessage());
		}
	}
}
