package com.leyou.user.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


import java.util.Date;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.user.pojo
 * @ClassName: User
 * @Author:
 * @Description:
 * @Date: 2019-05-17 7:59
 * @Version: 1.0
 */

@Data
@Table(name = "tb_user")
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    @NotEmpty(message = "用户名不能为空")
    @Length(min = 4, max = 32, message = "用户名长度必须在4~32位之间")
    private String username;    // 用户名

    @JsonIgnore
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 32, message = "密码长度必须在4~32位之间")
    private String password;    // 密码

    @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$", message = "手机号不正确")
    private String phone;      //电话

    private Date created;       //创建时间

    @JsonIgnore
    private String salt;        //盐值

}
