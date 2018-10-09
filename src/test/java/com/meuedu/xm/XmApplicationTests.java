package com.meuedu.xm;

import com.meuedu.xm.utils.JDBCUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XmApplicationTests {

	@Test
	public void contextLoads() {
		try {
			Connection connection=JDBCUtils.getConnection();
			System.out.println(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
