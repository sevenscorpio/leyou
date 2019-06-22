package com.leyou.auth.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.auth.pojo
 * @ClassName: UserInfo
 * @Author:
 * @Description:
 * @Date: 2019-05-19 7:32
 * @Version: 1.0
 */

@Data
public class UserInfo {

    private Long id;
    private Long role;
    private String username;

    public UserInfo(Long id, Long role, String username) {
        this.id = id;
        this.role = role;
        this.username = username;
    }

    public UserInfo(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserInfo() {
    }
}
