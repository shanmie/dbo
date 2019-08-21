package cn.org.zax.support;

import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Package: cn.org.zax.support
 * @ClassName: ResultSetSupport
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/12/7 下午5:49
 * @Version: 1.0
 */
public class ResultSetSupport {
    public String sql;
    public String dbName;


    ResultSetSupport(String sql, String dbName) {
        this.sql = buildDBNameSql(sql, dbName);
        this.dbName = dbName;
    }

    private static String buildDBNameSql(String sql, String dbName) {
        String beforeLast = StringUtils.substringBeforeLast(sql, ":");
        String afterLast = StringUtils.substringAfterLast(sql, ":");
        return beforeLast + dbName + "." + afterLast;
    }

    public <T> List<T> buildResultSetList(ResultSet rs) throws SQLException {
        List<T> resultSetList = new ArrayList<>();
        while (rs.next()) {
            resultSetList.add(DBSupport.parseBeanMapper(rs));
        }
        return resultSetList;
    }

    public <T> T buildResultSetBean(ResultSet rs) throws SQLException {
        rs.next();
        return DBSupport.parseBeanMapper(rs);
    }

    public <K, V> Map<K, V> buildResultSetMap(ResultSet rs) throws SQLException {
        return DBSupport.parseMapMapper(rs);
    }

    public <K, V> List<Map<K, V>> buildResultSetMapList(ResultSet rs) throws SQLException {
        return DBSupport.parseMapListMapper(rs);
    }

    public Integer buildResultSetInteger(ResultSet rs) throws SQLException {
        rs.next();
        return rs.getInt(1);
    }

}
