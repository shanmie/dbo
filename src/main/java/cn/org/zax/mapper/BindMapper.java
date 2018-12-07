package cn.org.zax.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Package: cn.org.zax.mapper
 * @ClassName: RowMapper
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/12/6 下午1:48
 * @Version: 1.0
 */
public interface BindMapper<T> {
    T mapper(ResultSet rs) throws SQLException;
}
