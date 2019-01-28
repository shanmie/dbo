package cn.org.zax;


import cn.org.zax.config.Config;

import cn.org.zax.mapper.BindMapper;
import cn.org.zax.repository.DBRepository;
import cn.org.zax.support.DBSupport;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "insert into %Users(username,name,age,balance,password,uuid) values (?,?,?,?,?,?)";
        int insert = db.insert(new DBSupport(sql,dbName).addParams("呀嘿嘿嘿", "hello", 10, 20, "12144231", "disd890923d"),Integer.class);
        System.out.println("插入 "+insert);

        String sql2 = "select * from %Users";
        List<User> all = db.selectAll(new DBSupport(sql2,dbName).addParams(),userMapper);
        System.out.println("查全部"+all);

        String sql3 = "select * from %Users where id =?";
        User one = (User) db.select(new DBSupport(sql3, dbName).addParams(4),userMapper);
        System.out.println("查一个"+one);


        String sql4 = "update %Users set password=? where id =?";
        int update = db.update(new DBSupport(sql4, dbName).addParams("213",4));
        System.out.println("更新"+update);


    }


}
