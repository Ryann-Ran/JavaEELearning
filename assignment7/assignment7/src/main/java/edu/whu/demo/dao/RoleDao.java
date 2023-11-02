package edu.whu.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.demo.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleDao extends BaseMapper<Role> {
    @Select("select role.* from role, user_role " +
            "where role.id = user_role.role_id " +
            "and user_role.user_id = #{userId}")
    public List<Role> findRolesByUser(@Param("userId")String userId);
}
