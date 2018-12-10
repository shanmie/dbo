package cn.org.zax;

import cn.org.zax.config.Config;
import cn.org.zax.manager.ConnectionManager;
import cn.org.zax.manager.DataSourceManager;
import cn.org.zax.mapper.BindMapper;
import cn.org.zax.pool.DataSourcePool;
import cn.org.zax.repository.DBRepository;
import cn.org.zax.support.DBSupport;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.List;

/**
 * @Package: cn.org.zax.datasource.config
 * @ClassName: Dbo
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/11/1 下午7:44
 * @Version: 1.0
 */
public class DB<T,ID> implements DBRepository {

    Connection connection;

    public DB(Config config){
        //创建数据源
        HikariDataSource dataSource = DataSourceManager.createDataSource(config);
        //获取连接
        this.connection = ConnectionManager.getConnection(dataSource);
        //池化数据源
        new DataSourcePool(dataSource);

    }

    static public DBRepository create(Config config){
        return register(config);
    }

    static public DBRepository create(String url, String username, String password ){
        return register(new Config().setUrl(url).setUsername(username).setPassword(password));
    }

    static private DBRepository register(Config config){
        DB db = new DB(config);
        return db;
    }


    @Override
    public List selectAll(DBSupport support, BindMapper bindMapper) {
        try {
            PreparedStatement statement = connection.prepareStatement(support.sql);
            support.buildSqlParams(statement);
            support.bindMapper(bindMapper);
            ResultSet resultSet = statement.executeQuery();
            List<T> list = support.buildResultSetList(resultSet);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T select(DBSupport support ,BindMapper bindMapper)  {
        try {
            PreparedStatement statement  = connection.prepareStatement(support.sql);
            support.buildSqlParams(statement);
            support.bindMapper(bindMapper);
            ResultSet resultSet = statement.executeQuery();
            T t = (T) support.buildResultSetBean(resultSet);
            return t;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public int insert(DBSupport support,Class clazz) {
        try {
            PreparedStatement statement = connection.prepareStatement(support.sql,PreparedStatement.RETURN_GENERATED_KEYS);
            support.buildSqlParams(statement);
            int result =  statement.executeUpdate();
            if (result > 0){
                ResultSet rs = statement.getGeneratedKeys();
                //设置id返回
                return support.returnPrimaryKey(rs,clazz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(DBSupport support) {
        try {
            PreparedStatement statement = connection.prepareStatement(support.sql);
            support.buildSqlParams(statement);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
