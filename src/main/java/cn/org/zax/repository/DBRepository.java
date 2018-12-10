package cn.org.zax.repository;

import cn.org.zax.mapper.BindMapper;
import cn.org.zax.support.DBSupport;

import java.sql.SQLException;
import java.util.List;

/**
 * @Package: cn.org.zax.datasource.config
 * @ClassName: DboOp
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/11/1 下午7:50
 * @Version: 1.0
 */
public interface DBRepository<T,ID> {
    List<T> selectAll(DBSupport support, BindMapper bindMapper);

    T select(DBSupport support, BindMapper bindMapper) throws SQLException;

    int insert(DBSupport support);

    int update(DBSupport support);

}
