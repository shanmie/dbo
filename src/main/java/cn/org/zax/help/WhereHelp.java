package cn.org.zax.help;

import java.util.List;

/**
 * @Package: cn.org.zax.help
 * @ClassName: WhereHelp
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/12/18 下午4:44
 * @Version: 1.0
 */
public class WhereHelp {
    /**
     * like or
     * @param sql
     * @param collect 需要like的集合值
     * @param fieldName 需要like的字段名
     * @param joinMark like前的链接符号
     * @return
     */
    private String orLike(String sql, List<Long> collect,String fieldName,String joinMark){
        String where ="(";
        if (joinMark !=null){
            where = joinMark + where;
        }
        for (int i = 0; i < collect.size(); i++) {
            where += " "+fieldName+" like '%"+collect.get(i)+"%'";
            if (i!= collect.size()-1){
                where = where +" or";
            }
        }
        where = where + ")";
        sql += where;
        return sql;
    }

    /**
     * like and
     * @param sql
     * @param collect 需要like的集合值
     * @param fieldName 需要like的字段名
     * @param joinMark like前的链接符号
     * @return
     */
    private String andLike(String sql, List<Long> collect,String fieldName,String joinMark){
        String where ="(";
        if (joinMark !=null){
            where = joinMark + where;
        }
        for (int i = 0; i < collect.size(); i++) {
            where += " "+fieldName+" like '%"+collect.get(i)+"%'";
            if (i!= collect.size()-1){
                where = where +" and";
            }
        }
        where = where + ")";
        sql += where;
        return sql;
    }
}
