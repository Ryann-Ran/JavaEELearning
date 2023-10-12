package edu.whu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.whu.entity.Commodity;
import edu.whu.service.CommodityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ryann
 * @create 2023/10/12 - 17:49
 */
@SpringBootTest
@AutoConfigureMockMvc  // 自动配置并初始化一个MockMvc对象，以便在单元测试中模拟HTTP请求和验证控制器的行为
public class CommodityMockMvcTest {

    @Autowired  // 注入MockMvc实例，以便在测试方法中使用。可以使用MockMvc来执行HTTP请求并验证控制器的行为，而不需要实际启动应用程序或依赖外部服务
    private MockMvc mockMvc;

    @Autowired
    private CommodityService commodityService;

    @Test
    public void testQuery() throws Exception {
        // 使用MockMvc执行Get请求
        this.mockMvc.perform(get("/commodities/{id}",1))
                .andDo(print()) //控制台打印出返回消息
                .andExpect(status().isNoContent());  // 验证HTTP响应的状态码是否为"204 No Content"。这表明在执行GET请求后，预计不会有内容返回

        commodityService.addCommodity(new Commodity(1, "薯条", false));

        this.mockMvc.perform(get("/commodities/{id}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("薯条"));  // 响应的JSON中包含一个"name"字段，且值为"薯条"

        commodityService.deleteCommodity(1);  // 恢复测试环境。保持测试的独立性、可重复性和稳定性
    }

    @Test
    public void testPost() throws Exception {
        Commodity commodity = new Commodity(1, "薯条", false);
        String content = new ObjectMapper().writeValueAsString(commodity);  // 使用ObjectMapper将该商品对象转换为JSON字符串
        // 使用MockMvc执行POST请求，请求路径是"/commodities"，请求的内容类型为JSON。请求的内容是前面创建的JSON字符串
        this.mockMvc.perform(post("/commodities")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists());  // 验证响应的JSON中是否包含一个名为"name"的字段
        commodityService.deleteCommodity(1);
    }
}
