package cn.org.zax.manager;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Getter
@Setter
public class ConnectionManager  {

    HikariDataSource dataSource;

    public static Connection getConnection(HikariDataSource dataSource) {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    public Connection getConnection()  {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

}
