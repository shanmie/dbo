package cn.org.zax.support;

import cn.org.zax.mapper.BindBeanMapper;
import cn.org.zax.mapper.BindMapMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * @Package: cn.org.zax.support
 * @ClassName: DbSupport
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/12/6 下午1:47
 * @Version: 1.0
 */
public class DBSupport extends ResultSetSupport {
    private static BindBeanMapper beanMapper;
    private static BindMapMapper mapMapper;
    private List<Object> paramsList = new ArrayList<>();

    public DBSupport(String sql, String dbName) {
        super(sql, dbName);
    }

    public DBSupport addParams(Object... args) {
        this.paramsList = new ArrayList<>();
        if (args.length > 0) {
            if (args[0] instanceof Collection<?>) {
                paramsList.addAll((Collection<?>) args[0]);
                return this;
            }
            paramsList.addAll(Arrays.asList(args));
        }
        return this;
    }

    public DBSupport bindBeanMapper(BindBeanMapper beanMapper) {
        DBSupport.beanMapper = beanMapper;
        return this;
    }

    public DBSupport bindMapMapper(BindMapMapper mapMapper) {
        DBSupport.mapMapper = mapMapper;
        return this;
    }

    public static <T> T parseBeanMapper(ResultSet rs) throws SQLException {
        return (T) beanMapper.mapper(rs);
    }

    public static <T> T parseMapMapper(ResultSet rs) throws SQLException {
        return (T) mapMapper.parse(rs);
    }

    public <T> T returnPrimaryKey(ResultSet rs, Class clazz) throws SQLException {
        if (Objects.nonNull(rs)) {
            rs.next();
            if (clazz.isAssignableFrom(Long.class)) {
                return (T) new Long(rs.getLong(1));
            }
            if (clazz.isAssignableFrom(String.class)) {
                return (T) rs.getString(1);
            }
            if (clazz.isAssignableFrom(Integer.class)) {
                return (T) new Integer(rs.getInt(1));
            }
        }
        return null;
    }

    //组装参数
    public void buildSqlParams(PreparedStatement ps) throws SQLException {
        if (paramsList.size() > 0) {
            for (int i = 0; i < paramsList.size(); i++) {
                ps.setObject(i + 1, paramsList.get(i));
            }
        }
    }


}
