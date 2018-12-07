package cn.org.zax;

import lombok.Data;

/**
 * @Package: cn.org.zax
 * @ClassName: User
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/12/7 下午1:34
 * @Version: 1.0
 */
@Data
public class User {
    int id;
    String username;
    String name;
    int age;
    int balance;
    String password;
    String uuid;
}
