package edu.whu.service;

import edu.whu.entity.Commodity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ryann
 * @create 2023/10/12 - 18:39
 */
public class CommodityServiceTest {
    private CommodityService commodityService;

    @BeforeEach
    public void setUp() {
        commodityService = new CommodityService();
    }

    @Test
    public void testAddCommodity_PositiveCase() {
        Commodity commodity = new Commodity(1, "薯条", false);
        Commodity result = commodityService.addCommodity(commodity);
        assertEquals(commodity, result);
    }
    
    @Test
    public void testDeleteCommodity_PositiveCase() {
        Commodity commodity = new Commodity(1, "薯条", false);
        commodityService.addCommodity(commodity);

        commodityService.deleteCommodity(1);
        Commodity result = commodityService.getCommodity(1);
        assertNull(result);
    }

    @Test
    public void testUpdateCommodity_PositiveCase() {
        Commodity commodity = new Commodity(1, "薯条", false);
        commodityService.addCommodity(commodity);

        Commodity updatedCommodity = new Commodity(1, "汉堡", true);
        commodityService.updateCommodity(1, updatedCommodity);

        Commodity result = commodityService.getCommodity(1);
        assertEquals(updatedCommodity, result);
    }

    @Test
    public void testGetCommodity_PositiveCase() {
        Commodity commodity = new Commodity(1, "薯条", false);
        commodityService.addCommodity(commodity);

        Commodity result = commodityService.getCommodity(1);
        assertEquals(commodity, result);
    }

    @Test
    public void testGetCommodity_NegativeCase() {
        Commodity result = commodityService.getCommodity(1);
        assertNull(result);
    }

    @Test
    public void testGetCommodities_PositiveCase() {
        Commodity commodity1 = new Commodity(1, "大薯条", false);
        Commodity commodity2 = new Commodity(2, "小薯条", true);
        commodityService.addCommodity(commodity1);
        commodityService.addCommodity(commodity2);

        List<Commodity> result = commodityService.getCommodities("薯条", false);
        assertEquals(1, result.size());
        assertEquals(commodity1, result.get(0));
    }

    @Test
    public void testGetCommodities_NegativeCase() {
        Commodity commodity1 = new Commodity(1, "大薯条", false);
        Commodity commodity2 = new Commodity(2, "汉堡", true);
        commodityService.addCommodity(commodity1);
        commodityService.addCommodity(commodity2);

        List<Commodity> result = commodityService.getCommodities("薯条", true);
        assertTrue(result.isEmpty());
    }
}
