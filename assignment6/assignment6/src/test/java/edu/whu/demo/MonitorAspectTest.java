package edu.whu.demo;

import edu.whu.demo.aspect.MonitorAspect;
import edu.whu.demo.controller.ProductController;
import edu.whu.demo.domain.Product;
import edu.whu.demo.exception.ProductAdminException;
import edu.whu.demo.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Ryann
 * @create 2023/10/31 - 15:48
 */
@SpringBootTest
public class MonitorAspectTest {
    @Autowired
    MonitorAspect monitorAspect;

    @Autowired
    ProductController productController;

    @Test
    public void testAspect() throws ProductAdminException {
        // 准备测试数据
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("IPhone 13");
        product1.setPrice(8000F);
        product1.setProductType("SND-dff");
        product1.setCategory("手机");
        product1.setStockQuantity(20F);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("IPhone 14");
        product2.setPrice(10000F);
        product2.setProductType("SND-ABC");
        product2.setCategory("手机");
        product2.setStockQuantity(120F);

        productController.addProduct(product1);
        productController.addProduct(product2);
        productController.getProduct(2L);
        product2.setName("test product");
        productController.updateProduct(product2.getId(), product2);
        productController.getProduct(2L);
        productController.updateProduct(-1L, product1);  // 制造异常

        monitorAspect.getInvokeNumMetric().forEach((k,v)->{
            System.out.println("API " + k + " 调用次数 = " + v);
        });
        monitorAspect.getMaxTimeMetric().forEach((k,v)->{
            System.out.println("API " + k + " 最长响应时间 = " + v);
        });
        monitorAspect.getMinTimeMetric().forEach((k,v)->{
            System.out.println("API " + k + " 最短响应时间 = " + v);
        });
        monitorAspect.getAvgTimeMetric().forEach((k,v)->{
            System.out.println("API " + k + " 平均响应时间 = " + v);
        });
        monitorAspect.getExceptionNumMetric().forEach((k,v)->{
            System.out.println("API " + k + " 发生异常的次数 = " + v);
        });
    }
}
