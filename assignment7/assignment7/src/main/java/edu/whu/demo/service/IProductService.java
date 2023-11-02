package edu.whu.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.demo.domain.Product;
import edu.whu.demo.domain.ProductDTO;
import edu.whu.demo.domain.Supplier;
import edu.whu.demo.exception.ProductAdminException;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaxy
 * @since 2022-10-31
 */
public interface IProductService extends IService<Product> {

    IPage<ProductDTO> findProduct(Map<String, Object> condition, Integer pageNum, Integer pageSize);

    Product addProduct(Product product) throws ProductAdminException;

    void updateProduct(long id, Product product) throws ProductAdminException;

    void updateProductSuppliers(long id, List<Supplier> suppliers) throws ProductAdminException;

    void deleteProduct(long id) throws ProductAdminException;
}
