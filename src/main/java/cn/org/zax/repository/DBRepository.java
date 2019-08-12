package cn.org.zax.repository;

import cn.org.zax.mapper.BindBeanMapper;
import cn.org.zax.mapper.BindMapMapper;

import java.util.List;
import java.util.Map;

/**
 * @Package: cn.org.zax.datasource.config
 * @ClassName: DboOp
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/11/1 下午7:50
 * @Version: 1.0
 */
public interface DBRepository {
    <T> List<T> selectAll(String sql, String dbName, BindBeanMapper bindBeanMapper);

    Integer selectInteger(String sql, String dbName);

    <K, V> Map<K, V> selectMap(String sql, String dbName, BindMapMapper bindMapMapper);

    <T> T select(String sql, String dbName, BindBeanMapper bindMapper, Object... obj);

    int insert(String sql, String dbName, Class clazz, Object... obj);

    int update(String sql, String dbName, Object... obj);

}
