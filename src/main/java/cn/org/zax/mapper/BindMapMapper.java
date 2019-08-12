package cn.org.zax.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author deacon
 * @since 2019/8/12
 */
public interface BindMapMapper<K,V> {
    default Map<K, V> parse(ResultSet rs) throws SQLException {
        LinkedHashMap<K, V> map = new LinkedHashMap<>();
        while (rs.next()) {
            putMap(map, rs);
        }
        return map;
    }

    void putMap(LinkedHashMap<K,V> map,ResultSet rs) throws SQLException;
}
