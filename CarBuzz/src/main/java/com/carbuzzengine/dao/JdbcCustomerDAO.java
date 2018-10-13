package com.carbuzzengine.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.web.context.ContextLoader;

public class JdbcCustomerDAO {
	private DataSource dataSource;
	
	public JdbcCustomerDAO()
	{
		this.dataSource = (DataSource) ContextLoader.getCurrentWebApplicationContext().getBean("dataSource");
	}

	public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
	}

	public List findByCustomerId() {

		Connection conn = null;
		List <String> lst = new ArrayList<>(); 
		try {
			//dataSource = (DataSource) ContextLoader.getCurrentWebApplicationContext().getBean("dataSource");
			conn = dataSource.getConnection();
			String Sql = "select * from test";
			Statement sta = conn.createStatement();
			ResultSet rs = sta.executeQuery(Sql);

			while (rs.next()) {
				//System.out.println(rs.getString("ID") + "-" + rs.getString("Name"));
				lst.add(rs.getString("Name"));

			}
			rs.close();
			sta.close();
			return lst;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
