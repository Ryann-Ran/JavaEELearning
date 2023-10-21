package edu.whu.assignment5.dao;

import edu.whu.assignment5.domain.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yuran Wang
 * @since 2023-10-21
 */
@Mapper
public interface SupplierDao extends BaseMapper<Supplier> {

}
