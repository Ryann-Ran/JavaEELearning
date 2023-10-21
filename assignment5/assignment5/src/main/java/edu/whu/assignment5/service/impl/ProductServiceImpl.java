package edu.whu.assignment5.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.assignment5.domain.Product;
import edu.whu.assignment5.dao.ProductDao;
import edu.whu.assignment5.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yuran Wang
 * @since 2023-10-21
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements IProductService {
    @Autowired
    ProductDao productDao;

    // 增加一个商品
    public Product insertProduct(Product product) {
        productDao.insert(product);
        return productDao.selectById(product.getId());
    }

    // 添加商品和供应商的关系
    public void insertProductSupplier (Integer product_id, Integer supplier_id) {
        productDao.insertProductSupplier(product_id, supplier_id);
    }

    // 根据商品id查询所有供应商
    public List<Map<String, Object>> querySuppliersById (Integer id) {
        return productDao.querySupplier(id);
    }

    // 查询所有商品
    public List<Product> queryAllProducts() {
        return productDao.selectList(null);
    }

    // 查询库存大于指定数量的商品
    public List<Product> queryByCondition(float stockQuantity) {
        QueryWrapper<Product> qw = new QueryWrapper<>();
        qw.gt("stockQuantity", stockQuantity);
        return productDao.selectList(qw);
    }

    // 使用lambda语法，基于LambdaQueryWrapper查询名字中包含指定关键字的商品
    public List<Product> queryByLambda (String keyword) {
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<Product>();
        lqw.like(Product::getName, keyword);
        return productDao.selectList(lqw);
    }

    // 条件组合：查询价格在[minPrice, maxPrice]范围内的商品
    public List<Product> queryByAnd (float minPrice, float maxPrice) {
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<Product>();
        lqw.le(Product::getPrice, maxPrice).ge(Product::getPrice, minPrice);
        return productDao.selectList(lqw);
    }

    // 条件组合：查询价格小于minPrice或者大于maxPrice的商品
    public List<Product> queryByOr (float minPrice, float maxPrice) {
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<Product>();
        lqw.lt(Product::getPrice, minPrice).or().gt(Product::getPrice, maxPrice);
        return productDao.selectList(lqw);
    }

    // 条件组合：多条件随机组合
    public List<Product> queryByNull (Float minStockQuantity, Float maxStockQuantity) {
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<Product>();
        lqw.ge(null != minStockQuantity, Product::getStockQuantity, minStockQuantity).le(null != maxStockQuantity, Product::getStockQuantity, maxStockQuantity);
        return productDao.selectList(lqw);
    }

    // 投影：查询所有商品的名称和价格
    public List<Product> queryByProjection () {
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<Product>();
        lqw.select(Product::getName, Product::getPrice);
        return productDao.selectList(lqw);
    }

    // 聚合：查询指定种类的商品的数量
    public List<Map<String, Object>> queryCountByAggregation (String category) {
        QueryWrapper<Product> qw = new QueryWrapper<Product>();
        qw.eq("category", category).select("count(*) as count");
        return productDao.selectMaps(qw);
    }

    // 聚合：查询库存大于等于指定数量的商品的最大价格
    public List<Map<String, Object>> queryMaxByAggregation (float stockQuantity) {
        QueryWrapper<Product> qw = new QueryWrapper<Product>();
        qw.ge("stockQuantity", stockQuantity).select("max(price) as maxPrice");
        return productDao.selectMaps(qw);
    }

    // 分类汇总：查询每个种类库存大于指定数量的商品的数量
    public List<Map<String, Object>> queryByGrouping (float stockQuantity) {
        QueryWrapper<Product> qw = new QueryWrapper<Product>();
        qw.lambda().gt(Product::getStockQuantity, stockQuantity);
        qw.select("category, count(*) as count");
        qw.groupBy("category");
        return productDao.selectMaps(qw);
    }

    // 分页排序：查询名字含指定关键字的商品，按价格升序
    public IPage<Product> queryByPage (int current, int size, String keyword) {
        LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<Product>();
        lqw.like(Product::getName, keyword);
        lqw.orderByAsc(Product::getPrice);
        IPage<Product> page = new Page<Product>(current, size);
        return productDao.selectPage(page, lqw);
    }
}
