package edu.whu.assignment5.dao;

import edu.whu.assignment5.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yuran Wang
 * @since 2023-10-21
 */
@Mapper
public interface ProductDao extends BaseMapper<Product> {
    // 根据商品id查询所有供应商
    @Select("SELECT supplier_id FROM product_supplier where product_id = {product_id}")
    List<Map<String, Object>> querySupplier (Integer product_id);

    @Insert("INSERT INTO product_supplier (product_id, supplier_id) VALUES (#{product_id}, #{supplier_id}")
    void insertProductSupplier (Integer product_id, Integer supplier_id);
}
