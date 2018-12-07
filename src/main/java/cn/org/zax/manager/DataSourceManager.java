package cn.org.zax.manager;

import cn.org.zax.config.Config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @Package: cn.org.zax.config
 * @ClassName: DataSou
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/11/2 下午7:43
 * @Version: 1.0
 */
public class DataSourceManager {

    static public HikariDataSource createDataSource(Config config){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getUrl());
        hikariConfig.setUsername(config.getUsername());
        hikariConfig.setPassword(config.getPassword());
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(hikariConfig);
    }
}
