package edu.whu.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ryann
 * @create 2023/11/2 - 10:07
 */
@Data
@NoArgsConstructor
public class User {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    String id;
    private String password; //密码在数据库中需要加密保存
}
