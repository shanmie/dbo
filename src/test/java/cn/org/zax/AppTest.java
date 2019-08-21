package cn.org.zax;


import cn.org.zax.config.Config;

import cn.org.zax.help.Sql;
import cn.org.zax.mapper.BindBeanMapper;
import cn.org.zax.repository.DBRepository;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * Unit test for simple App.
 */
public class AppTest{

    class UserMapper implements BindBeanMapper {
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


        String sql2 = "select * from :Users where id=4";
        Integer select = db.selectInteger(sql2, dbName);
        System.out.println("查总"+select);

        String sql3 = "select * from :Users where id in(:id)";


        /*for (int i = 0; i < 10000; i++) {
            String sql3 = "select * from :Users where id =?";
            User one =  db.select(sql3,dbName,userMapper, Arrays.asList(4));
            System.out.println("查一个"+one+"-----i--------"+i);
        }*/

        List<Map<Object, Object>> maps = db.selectMapList(Sql.In(sql3,"id",4), dbName, (list2, rs) -> {
            Map<String,Object> map =new HashMap<>();
            map.put("userName", rs.getString("username"));
            map.put("id", rs.getString("id"));
            map.put("name", rs.getString("name"));
            list2.add(map);
        }, Arrays.asList(4, 6, 7, 8));

        System.out.println(maps);







        /*String sql4 = "update :Users set password=? where id =?";
        int update = db.update(sql4,dbName,"111",6);
        System.out.println("更新"+update);*/


    }


}
