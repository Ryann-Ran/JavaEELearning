package edu.whu.assignment5.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.assignment5.MybatisPlusApplication;
import edu.whu.assignment5.dao.ProductDao;
import edu.whu.assignment5.domain.Product;
import edu.whu.assignment5.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ryann
 * @create 2023/10/21 - 19:21
 */
@Transactional
@SpringBootTest(classes = MybatisPlusApplication.class)
public class ProductServiceTest {
    @Autowired
    private ProductServiceImpl productService;

    /**
     * 增加一个商品
     */
    @Test
    void testInsertProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("小薯条");
        product.setPrice(8f);
        product.setStockQuantity(600f);
        product.setCategory("食品");
        product.setProductType("快餐");
        product.setDescription("小份薯条");
        assertNotNull(productService.insertProduct(product));
    }

    /**
     * 查询所有商品
     */
    @Test
    void testQueryAllProducts() {
        assertEquals(5,productService.queryAllProducts().size());
    }

    /**
     * 查询库存大于指定数量的商品
     */
    @Test
    void testQueryByCondition() {
        assertEquals(2, productService.queryByCondition(400).size());
    }

    /**
     * 使用lambda语法，基于LambdaQueryWrapper查询名字中包含指定关键字的商品
     */
    @Test
    void testQueryByLambda() {
        assertEquals(2, productService.queryByLambda("薯条").size());
    }

    /**
     * 条件组合：查询价格在[minPrice, maxPrice]范围内的商品
     */
    @Test
    void testQueryByAnd() {
        assertEquals(2, productService.queryByAnd(3000f, 6000f).size());
    }

    /**
     * 条件组合：查询价格小于minPrice或者大于maxPrice的商品
     */
    @Test
    void testQueryByOr() {
        assertEquals(3, productService.queryByOr(3000f, 6000f).size());
    }

    /**
     * 条件组合：多条件随机组合
     */
    @Test
    void testQueryByNull() {
        assertEquals(3, productService.queryByNull(400f, 500f).size());
    }

    /**
     * 投影：查询所有商品的名称和价格
     */
    @Test
    void testQueryByProjection() {
        List<Product> productList = productService.queryByProjection();
        assertEquals(5, productList.size());
        assertNull(productList.get(0).getProductType());
    }

    /**
     * 聚合：查询指定种类的商品的数量
     */
    @Test
    void testQueryCountByAggregation() {
        List<Map<String, Object>> result = productService.queryCountByAggregation("食品");
        assertEquals(1,result.size());
        assertEquals(3L,result.get(0).get("count"));
    }

    /**
     * 聚合：查询库存大于等于指定数量的商品的最大价格
     */
    @Test
    void testQueryMaxByAggregation() {
        List<Map<String, Object>> result = productService.queryMaxByAggregation(400);
        assertEquals(1,result.size());
        assertEquals(3500f,result.get(0).get("maxPrice"));
    }

    /**
     * 分类汇总：查询每个种类库存大于指定数量的商品的数量
     */
    @Test
    void testQueryByGrouping() {
        List<Map<String, Object>> result = productService.queryByGrouping(200);
        assertEquals(2,result.size());
    }

    /**
     * 分页排序：查询名字含指定关键字的商品，按价格升序
     */
    @Test
    void testQueryByPage() {
        IPage<Product> result = productService.queryByPage(1, 2, "");
        assertEquals(2,result.getRecords().size()); //获得查询结果数目
        assertEquals(1,result.getCurrent());//获得当前页码
        assertEquals(2,result.getSize()); //获得每页记录数
        assertEquals(3,result.getPages());//获得页数
        assertEquals(5,result.getTotal());//获得记录总数
    }
}
