package com.pc.myjingdong.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by pc on 2017/11/17.
 */

@Entity
public class Person {
    @Id
    private long id;
    private String username;
    private String pwd;
    @Generated(hash = 1605346544)
    public Person(long id, String username, String pwd) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
    }
    @Generated(hash = 1024547259)
    public Person() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPwd() {
        return this.pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
}
