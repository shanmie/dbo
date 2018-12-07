package cn.org.zax.support;

import cn.org.zax.mapper.BindMapper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @Package: cn.org.zax.support
 * @ClassName: DbSupport
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/12/6 下午1:47
 * @Version: 1.0
 */
public class DBSupport extends ResultSetSupport{
    static public BindMapper mapper;
    public List<Object> paramsList = new ArrayList<>();

    public DBSupport(String sql, String dbName) {
        super(sql, dbName);
    }

    public DBSupport addParams(Object... args){
        if (Objects.nonNull(args)){
            for (Object arg : args) {
                paramsList.add(arg);
            }
        }
        return this;
    }

    public DBSupport bindMapper(BindMapper mapper){
        this.mapper = mapper;
        return this;
    }

    public static <T> T parseMapper(ResultSet rs) throws SQLException {
        return (T) mapper.mapper(rs);
    }

    //组装参数
    public void buildSqlParams(PreparedStatement ps) throws SQLException {
        if (paramsList.size() > 0){
            for (int i = 0; i < paramsList.size(); i++) {
                ps.setObject(i+1 , paramsList.get(i));
            }
        }
    }


}
