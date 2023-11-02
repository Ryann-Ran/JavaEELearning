package edu.whu.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.demo.dao.ProductDao;
import edu.whu.demo.dao.SupplierDao;
import edu.whu.demo.domain.Product;
import edu.whu.demo.domain.ProductDTO;
import edu.whu.demo.domain.Supplier;
import edu.whu.demo.exception.ProductAdminException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProductServiceTest {
//
    @Autowired
    IProductService productService;
    @Autowired
    ProductDao productRepository;

    @Autowired
    SupplierDao supplierRepository;
//
//
    @BeforeEach
    private void initData() throws ProductAdminException {
        Supplier supplier=new Supplier();
        supplier.setName("A公司");
        supplier.setPhone("1982229992");
        supplierRepository.insert(supplier);

        Supplier supplier2=new Supplier();
        supplier2.setName("B公司");
        supplier2.setPhone("1982223332");
        supplierRepository.insert(supplier2);

        Product product=new Product();
        product.setName("IPhone 13");
        product.setPrice(8000F);
        product.setProductType("SND-dff");
        product.setCategory("手机");
        product.setStockQuantity(20F);

        productService.addProduct(product);
        productService.updateProductSuppliers(product.getId(), Lists.list(supplier,supplier2));


        Product product2=new Product();
        product2.setName("IPhone 14");
        product2.setPrice(10000F);
        product2.setProductType("SND-ABC");
        product2.setCategory("手机");
        product2.setStockQuantity(120F);
        productService.addProduct(product2);
        productService.updateProductSuppliers(product2.getId(), Lists.list(supplier));

        Product product3=new Product();
        product3.setName("IPhone 15");
        product3.setPrice(15000F);
        product3.setProductType("SND-CXS");
        product3.setCategory("手机");
        product3.setStockQuantity(100F);
        productService.addProduct(product3);
        productService.updateProductSuppliers(product3.getId(), Lists.list(supplier));
    }


    @Test
    public void testAddProduct() throws ProductAdminException {
        Supplier supplier=new Supplier();
        supplier.setName("C公司");
        supplier.setPhone("198222993");
        supplierRepository.insert(supplier);

        ProductDTO product=new ProductDTO();
        product.setName("IPhone 18");
        product.setPrice(12000F);
        product.setProductType("SND-dff");
        product.setCategory("手机");
        product.setStockQuantity(10F);
        product.getSupplierList().add(supplier); //添加关联属性
        Product product2 = productService.addProduct(product);

        //验证添加的结果
        assertNotNull(product2);
        Long id = product2.getId();
        Product product3 = productRepository.selectById(id);
        assertNotNull(product3);
        //删除
        productService.deleteProduct(id);
        assertNull(productRepository.selectById(id));
    }
//
    @Test
    public void testUpdateProduct() throws ProductAdminException {
        Supplier supplier=new Supplier();
        supplier.setName("C公司");
        supplier.setPhone("198222993");
        supplierRepository.insert(supplier);
        Product product=new Product();
        product.setName("IPhone 18");
        product.setPrice(12000F);
        product.setProductType("SND-dff");
        product.setCategory("手机");
        product.setStockQuantity(10F);
        Product product2 = productService.addProduct(product);

        //使用非法的id修改
        assertThrows(ProductAdminException.class, () -> {
            product2.setName("Xiaomi 3000");
            productService.updateProduct(-1,product);
        });

        //修改产品名称，去掉商品的供应商
        product2.setName("Xiaomi 3000");
        productService.updateProduct(product.getId(),product);
        //验证结果
        Product product3 = productRepository.selectById(product.getId());
        assertNotNull(product3);
        assertEquals("Xiaomi 3000",product3.getName());
    }

    @Test
    public void testFindProduct(){
        //查询条件为空
        Map<String,Object> condition=new HashMap<>();
        IPage<ProductDTO> result = productService.findProduct(condition, 0, 3);
        assertEquals(3,result.getRecords().size());

        //添加动态查询条件
        condition.put("name","IPhone");
        condition.put("price",10000F);
        result = productService.findProduct(condition, 0,3);
        assertEquals(2,result.getRecords().size());

        //使用跨表属性查询(supplierName在supplier表中)
        condition.put("supplierName","B公司");
        result = productService.findProduct(condition, 0,3);
        assertEquals(1,result.getTotal());



    }

}
