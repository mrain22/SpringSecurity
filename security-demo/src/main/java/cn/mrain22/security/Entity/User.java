package cn.mrain22.security.Entity;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class User {

    private Integer id;
    @NotBlank(message = "用户名不能为空！")
    private String name;
    private String pass;
    private Date idate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getIdate() {
        return idate;
    }

    public void setIdate(Date idate) {
        this.idate = idate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", date=" + idate +
                '}';
    }

//    JsonView 创建视图
}
