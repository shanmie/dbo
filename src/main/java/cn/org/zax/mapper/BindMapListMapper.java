package cn.org.zax.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author deacon
 * @since 2019/8/21
 */
public interface BindMapListMapper<K, V> {
    default List<Map<K, V>> parse(ResultSet rs) throws SQLException {
        List<Map<K, V>> list = new LinkedList<>();
        while (rs.next()) {
            putList(list, rs);
        }
        return list;
    }


    void putList(List<Map<K, V>> map, ResultSet rs) throws SQLException;
}
