package jacksondemo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

//属性为NULL则不参与JSON序列化
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Integer id;

    private String username;

    //在json序列化时将java bean中的一些属性忽略掉，序列化和反序列化都受影响。
    //一般标记在属性或者方法上，返回的json数据即不包含该属性。
    @JsonIgnore
    private String password;

    private String email;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}