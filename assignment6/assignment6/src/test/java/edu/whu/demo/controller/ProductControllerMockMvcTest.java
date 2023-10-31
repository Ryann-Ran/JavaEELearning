package edu.whu.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.whu.demo.domain.Product;
import edu.whu.demo.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.verify;

/**
 * @author Ryann
 * @create 2023/10/31 - 16:17
 */
@WebMvcTest(ProductController.class)
public class ProductControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;

    @Test
    void addProduct_ValidProduct_ReturnsProduct() throws Exception {
        // 准备测试数据
        Product inputProduct = new Product();
        inputProduct.setName("Test Product");
        inputProduct.setPrice(10.0f);

        // 执行请求并验证结果
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                        .contentType(MediaType.APPLICATION_JSON)  // 设置请求的内容类型为 JSON
                        .content(new ObjectMapper().writeValueAsString(inputProduct)))  // 将inputProduct对象转换为JSON格式的字符串，并将其作为请求的内容发送
                .andExpect(MockMvcResultMatchers.status().isOk());

        // 验证productService的调用参数是否正确
        verify(productService).addProduct(inputProduct);  // 使用Mockito的verify方法验证addProduct方法是否被调用，并且验证传递给该方法的参数是否与inputProduct相匹配
    }
}
