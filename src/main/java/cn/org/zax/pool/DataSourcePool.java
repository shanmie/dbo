package cn.org.zax.pool;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @Package: cn.org.zax.config
 * @ClassName: DataSourceExecute
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/11/2 下午8:03
 * @Version: 1.0
 */
public class DataSourcePool {
    HikariDataSource dataSource;

    public DataSourcePool(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }
}
