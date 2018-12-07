package cn.org.zax.manager;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Package: cn.org.zax.config
 * @ClassName: ConnectionManager
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/11/2 下午7:52
 * @Version: 1.0
 */
public class ConnectionManager  {

    HikariDataSource dataSource;

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static Connection getConnection(HikariDataSource dataSource) {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
        }
        return null;
    }

    public Connection getConnection()  {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
        }
        return null;
    }

}
