package edu.whu.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.demo.domain.User;
import edu.whu.demo.domain.UserDto;
import org.apache.ibatis.annotations.*;

/**
 * @author Ryann
 * @create 2023/11/2 - 10:06
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
    /**
     * 查询用户及其角色
     * @return
     */
    @Select("select * from user where id =  #{id}")
    @Results({@Result(id = true, property = "id", column = "id")})
    public UserDto getUser(String id);
}
