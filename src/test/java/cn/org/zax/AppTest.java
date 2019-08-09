package cn.org.zax;


import cn.org.zax.config.Config;

import cn.org.zax.mapper.BindMapper;
import cn.org.zax.repository.DBRepository;
import cn.org.zax.support.DBSupport;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Unit test for simple App.
 */
public class AppTest{

    class UserMapper implements BindMapper {
        @Override
        public User mapper(ResultSet rs) throws SQLException {
            User u = new User();
            u.setUsername(rs.getString("username"));
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setAge(rs.getInt("age"));
            u.setBalance(rs.getInt("balance"));
            u.setPassword(rs.getString("password"));
            u.setUuid(rs.getString("uuid"));
            return u;
        }
    }

    UserMapper userMapper = new UserMapper();

    String dbName = "testboot";
    Config config = new Config().setUrl("jdbc:mysql://127.0.0.1:3306/").setUsername("root").setPassword("root");
    DBRepository db = DB.create(config);

    @Test
    public void test() throws SQLException {
        /*String sql = "insert into :Users(username,name,age,balance,password,uuid) values (?,?,?,?,?,?)";

        List list = new ArrayList();
        list.add("呀嘿嘿嘿");
        list.add("hello");
        list.add(12);
        list.add(21);
        list.add("12144231");
        list.add("derf34dd");

        int insert2 = db.insert(sql,dbName,Integer.class,list);

        //int insert = db.insert(sql,dbName,Integer.class, "呀嘿嘿嘿", "hello", 10, 20, "12144231", "disd890923d");

        System.out.println("插入 | "+insert2);*/


        String sql2 = "select count(*) from :Users";
        Integer select = db.select(sql2, dbName);
        System.out.println("查总"+select);

        /*for (int i = 0; i < 10000; i++) {
            String sql3 = "select * from :Users where id =?";
            User one =  db.select(sql3,dbName,userMapper, Arrays.asList(4));
            System.out.println("查一个"+one+"-----i--------"+i);
        }*/





        /*String sql4 = "update :Users set password=? where id =?";
        int update = db.update(sql4,dbName,"111",6);
        System.out.println("更新"+update);*/


    }


}
