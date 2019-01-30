package cn.org.zax.support;
import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: cn.org.zax.support
 * @ClassName: ResultSetSupport
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/12/7 下午5:49
 * @Version: 1.0
 */
public class ResultSetSupport<T>{
    public String sql;
    public String dbName;

    public List<T> resultSetList = new ArrayList<>();

    public ResultSetSupport(String sql, String dbName) {
        this.sql = buildDBNameSql(sql,dbName);
        this.dbName = dbName;

    }

    static public String buildDBNameSql(String sql,String dbName){
        String beforeLast = StringUtils.substringBeforeLast(sql,"%");
        String afterLast = StringUtils.substringAfterLast(sql,"%");
        return beforeLast + dbName + "." + afterLast;
    }

    public List<T> buildResultSetList(ResultSet rs) throws SQLException {
        while (rs.next()){
            resultSetList.add(DBSupport.parseMapper(rs));
        }
        return resultSetList;
    }

    public T buildResultSetBean(ResultSet rs) throws SQLException {
        rs.next();
        return DBSupport.parseMapper(rs);
    }

}
