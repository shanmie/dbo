package cn.org.zax.help;

/**
 * @author deacon
 * @since 2019/8/21
 */
public class Sql {
    public static String In(String sql,String varName,int count){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(",").append("?");
        }
        return sql.replace(":"+varName,builder.toString().substring(1));
    }
}
