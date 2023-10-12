package edu.whu.service;

import edu.whu.entity.Commodity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Ryann
 * @create 2023/10/12 - 8:21
 */
@Service
public class CommodityService {
    // 创建线程安全的Map，模拟Commodity的存储
    private Map<Long, Commodity> commodities = Collections.synchronizedMap(new HashMap<Long, Commodity>());

    public Commodity addCommodity(Commodity commodity) {
        commodities.put(commodity.getId(), commodity);
        return commodity;
    }

    public void deleteCommodity(long id) {
        commodities.remove(id);
    }

    public void updateCommodity(long id, Commodity commodity) {
        Commodity updatedCommodity = commodities.get(id);
        updatedCommodity.setName(commodity.getName());
        updatedCommodity.setDeleted(commodity.isDeleted());
        commodities.put(id, updatedCommodity);
    }

    public Commodity getCommodity(long id) {
        return commodities.get(id);
    }

    public List<Commodity> getCommodities(String name, Boolean deleted) {  // 此处isDeleted的类型是Boolean而不是boolean
        List<Commodity> result = new ArrayList<>();
        for (Commodity commodity: commodities.values()) {
            if (name != null && !commodity.getName().contains(name))  {
                continue;
            }
            if (deleted != null && !deleted.equals(commodity.isDeleted()))  {
                continue;
            }
            result.add(commodity);
        }
        return result;
    }
}
