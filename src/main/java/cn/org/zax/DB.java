package cn.org.zax;

import cn.org.zax.config.Config;
import cn.org.zax.manager.ConnectionManager;
import cn.org.zax.manager.DataSourceManager;
import cn.org.zax.mapper.BindBeanMapper;
import cn.org.zax.mapper.BindMapListMapper;
import cn.org.zax.mapper.BindMapMapper;
import cn.org.zax.pool.DataSourcePool;
import cn.org.zax.repository.DBRepository;
import cn.org.zax.support.DBSupport;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * @Package: cn.org.zax.datasource.config
 * @ClassName: Dbo
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/11/1 下午7:44
 * @Version: 1.0
 */
@Slf4j
public class DB<T, ID> implements DBRepository {

    private Connection connection;

    private DB(Config config) {
        //创建数据源
        HikariDataSource dataSource = DataSourceManager.createDataSource(config);
        //获取连接
        this.connection = ConnectionManager.getConnection(dataSource);
        //池化数据源
        new DataSourcePool(dataSource);

    }

    public static DBRepository create(Config config) {
        return register(config);
    }

    static public DBRepository create(String url, String username, String password) {
        return register(new Config().setUrl(url).setUsername(username).setPassword(password));
    }

    static private DBRepository register(Config config) {
        DB db = new DB(config);
        return db;
    }


    @Override
    public List<T> selectAll(String sql, String dbName, BindBeanMapper bindMapper, Object... obj) {
        try {
            DBSupport support = new DBSupport(sql, dbName);
            support.addParams(obj);
            PreparedStatement statement = connection.prepareStatement(support.sql);
            support.buildSqlParams(statement);
            support.bindBeanMapper(bindMapper);
            ResultSet resultSet = statement.executeQuery();
            return support.buildResultSetList(resultSet);
        } catch (SQLException e) {
            log.error("select all fail to message {}", e);
        }
        return null;
    }

    @Override
    public Integer selectInteger(String sql, String dbName, Object... obj) {
        try {
            DBSupport support = new DBSupport(sql, dbName);
            support.addParams(obj);
            PreparedStatement statement = connection.prepareStatement(support.sql);
            support.buildSqlParams(statement);
            ResultSet resultSet = statement.executeQuery();
            return support.buildResultSetInteger(resultSet);
        } catch (SQLException e) {
            log.error("select count fail to message {}", e);
        }
        return null;
    }

    @Override
    public <K, V> Map<K, V> selectMap(String sql, String dbName, BindMapMapper bindMapMapper, Object... obj) {
        try {
            DBSupport support = new DBSupport(sql, dbName);
            support.addParams(obj);
            PreparedStatement statement = connection.prepareStatement(support.sql);
            support.buildSqlParams(statement);
            support.bindMapMapper(bindMapMapper);
            ResultSet resultSet = statement.executeQuery();
            return support.buildResultSetMap(resultSet);
        } catch (Exception e) {
            log.error("select map fail to message {}", e);
        }
        return null;
    }

    @Override
    public <K, V> List<Map<K, V>> selectAllMap(String sql, String dbName, BindMapListMapper bindMapListMapper, Object... obj) {
        try {
            DBSupport support = new DBSupport(sql, dbName);
            support.addParams(obj);
            PreparedStatement statement = connection.prepareStatement(support.sql);
            support.buildSqlParams(statement);
            support.bindMapListMapper(bindMapListMapper);
            ResultSet resultSet = statement.executeQuery();
            return support.buildResultSetMapList(resultSet);
        } catch (Exception e) {
            log.error("select map list fail to message {}", e);
        }
        return null;


    }

    @Override
    public T select(String sql, String dbName, BindBeanMapper bindMapper, Object... obj) {
        try {
            DBSupport support = new DBSupport(sql, dbName);
            support.addParams(obj);
            PreparedStatement statement = connection.prepareStatement(support.sql);
            support.buildSqlParams(statement);
            support.bindBeanMapper(bindMapper);
            ResultSet resultSet = statement.executeQuery();
            return support.buildResultSetBean(resultSet);
        } catch (Exception e) {
            log.error("select one fail to message {}", e);
        }
        return null;
    }

    @Override
    public int insert(String sql, String dbName, Class clazz, Object... obj) {
        try {
            DBSupport support = new DBSupport(sql, dbName);
            support.addParams(obj);
            PreparedStatement statement = connection.prepareStatement(support.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            support.buildSqlParams(statement);
            int result = statement.executeUpdate();
            if (result > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                //设置id返回
                return support.returnPrimaryKey(rs, clazz);
            }
        } catch (SQLException e) {
            log.error("insert record fail to message {}", e);
        }
        return 0;
    }

    @Override
    public int update(String sql, String dbName, Object... obj) {
        try {
            DBSupport support = new DBSupport(sql, dbName);
            support.addParams(obj);
            PreparedStatement statement = connection.prepareStatement(support.sql);
            support.buildSqlParams(statement);
            return statement.executeUpdate();
        } catch (SQLException e) {
            log.error("update record fail to message {}", e);
        }
        return 0;
    }

}
