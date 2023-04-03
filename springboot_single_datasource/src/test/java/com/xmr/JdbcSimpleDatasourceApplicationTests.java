package com.xmr;

import com.xmr.jdbc.domain.User;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by xmr on 2020/1/13.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcSimpleDatasourceApplicationTests {

    @Autowired
    private DataSource dataSource;
    @Test
    public void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
    public void springDataSourceTest(){
        //输出为true
        System.out.println(dataSource instanceof HikariDataSource);
        System.out.println(dataSource instanceof DataSource);
        try{
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            User user = null;
            if(resultSet.next()){
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setSex(resultSet.getString("sex"));
            }
            System.out.println(user);
            statement.close();
            connection.close();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

}
