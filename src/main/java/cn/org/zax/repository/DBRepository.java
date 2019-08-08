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
    List<T> selectAll(String sql ,String dbName , BindMapper bindMapper);

    T select(String sql ,String dbName , BindMapper bindMapper);

    T select(String sql ,String dbName , BindMapper bindMapper,Object... obj);

    T select(String sql ,String dbName , BindMapper bindMapper,List<Object> obj);

    int insert(String sql ,String dbName ,Class clazz ,Object... obj);

    int insert(String sql ,String dbName ,Class clazz ,List<Object> obj);

    int update(String sql ,String dbName);

    int update(String sql ,String dbName , Object ... obj);

    int update(String sql ,String dbName , List<Object> obj);

}
