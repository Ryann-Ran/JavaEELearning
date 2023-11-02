package edu.whu.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Role {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    String id;

    String remark;

    /**
     * 角色的权限。每个权限是一个字符串，多个权限之间使用逗号分割
     * 权限可以根据需求定义，可能是允许访问的模块，允许显示的菜单，或者允许访问的数据。在正式项目中，可以用权限表来展示更多的权限信息
     */
    String authorities;


}
