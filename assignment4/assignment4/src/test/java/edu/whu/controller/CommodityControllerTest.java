package edu.whu.controller;

import edu.whu.entity.Commodity;
import edu.whu.service.CommodityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Ryann
 * @create 2023/10/12 - 17:34
 */
@SpringBootTest
public class CommodityControllerTest {
    @Mock  // 模拟一个CommodityService类的实例，commodityService为模拟对象
    CommodityService commodityService;
    @InjectMocks  // 将模拟的CommodityService注入CommodityController中
    CommodityController commodityController;

    @BeforeEach  // 每个测试方法执行前执行
    public void setup() {  // 初始化Mockito
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCommodity_PositiveCase() {
        Commodity commodity = new Commodity();
        commodity.setId(1);
        commodity.setName("薯条");
        commodity.setDeleted(false);

        // 模拟方法调用。Mockito会模拟该方法的行为，而不实际执行方法的原始逻辑。
        // .thenReturn(commodity) 意味着当这个方法被调用时，应该返回 commodity 对象作为结果
        when(commodityService.addCommodity(commodity)).thenReturn(commodity);

        ResponseEntity<Commodity> response = commodityController.addCommodity(commodity);
        // 检查响应的HTTP状态码和响应体
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(commodity, response.getBody());
    }
    @Test
    public void testDeleteCommodity_PositiveCase() {
        ResponseEntity<Void> response = commodityController.deleteCommodity(1);

        // 验证服务方法的调用次数
        verify(commodityService, times(1)).deleteCommodity(1);
        assertEquals(200, response.getStatusCodeValue());
    }
    @Test
    public void testUpdateCommodity_PositiveCase() {
        Commodity commodity = new Commodity();
        commodity.setId(1);
        commodity.setName("汉堡");
        commodity.setDeleted(true);

        ResponseEntity<Void> response = commodityController.updateCommodity(1, commodity);

        verify(commodityService, times(1)).updateCommodity(1, commodity);
        assertEquals(200, response.getStatusCodeValue());
    }
    @Test
    public void testGetCommodity_PositiveCase() {
        Commodity commodity = new Commodity();
        commodity.setId(1);
        commodity.setName("薯条");

        when(commodityService.getCommodity(1)).thenReturn(commodity);

        ResponseEntity<Commodity> response = commodityController.getCommodity(1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(commodity, response.getBody());
    }
    @Test
    public void testGetCommodity_NegativeCase() {
        when(commodityService.getCommodity(1)).thenReturn(null);

        ResponseEntity<Commodity> response = commodityController.getCommodity(1);

        assertEquals(204, response.getStatusCodeValue());
        assertEquals(null, response.getBody());
    }
    @Test
    public void testGetCommodities_PositiveCase() {
        Commodity commodity1 = new Commodity();
        commodity1.setId(1);
        commodity1.setName("小薯条");
        commodity1.setDeleted(true);

        Commodity commodity2 = new Commodity();
        commodity2.setId(2);
        commodity2.setName("大薯条");
        commodity2.setDeleted(false);

        List<Commodity> commodityList = new ArrayList<>();
        commodityList.add(commodity1);
        commodityList.add(commodity2);

        when(commodityService.getCommodities("薯条", true)).thenReturn(commodityList);

        ResponseEntity<List<Commodity>> response = commodityController.getCommodities("薯条", true);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(commodityList, response.getBody());
    }
}
