package cn.org.zax.config;

import lombok.Getter;

/**
 * @Package: cn.org.zax.datasource
 * @ClassName: Config
 * @Description:
 * @Author: mac-pro
 * @CreateDate: 2018/10/30 下午11:32
 * @Version: 1.0
 */
@Getter
public class Config  {

    private String username;
    private String password;
    private String url;
    private String address;
    private String driver;

    private int port;

    public Config setUsername(String username){
        this.username = username;
        return this;
    }

    public Config setPassword(String password){
        this.password = password;
        return this;
    }

    public Config setUrl(String url){
        this.url = url;
        return this;
    }

    public Config setAddress(String address){
        this.address = address;
        return this;
    }

    public Config setDriver(String driver){
        this.driver = driver;
        return this;
    }

    public Config setPort(int port){
        this.port = port;
        return this;
    }

}
