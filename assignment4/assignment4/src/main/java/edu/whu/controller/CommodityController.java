package edu.whu.controller;

import edu.whu.entity.Commodity;
import edu.whu.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ryann
 * @create 2023/10/12 - 8:14
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("commodities")
public class CommodityController {
    @Autowired
    CommodityService commodityService;

    @ApiOperation("添加商品")
    @PostMapping("")
    public ResponseEntity<Commodity> addCommodity(@ApiParam("商品实体")@RequestBody Commodity commodity) {
        Commodity result = commodityService.addCommodity(commodity);
        return ResponseEntity.ok(result);
    }

    @ApiOperation("删除商品")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommodity(@ApiParam("商品id") @PathVariable long id) {
        commodityService.deleteCommodity(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("修改商品")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCommodity(@ApiParam("商品id") @PathVariable long id, @ApiParam("商品名称") @RequestBody Commodity commodity) {
        commodityService.updateCommodity(id, commodity);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("根据id查询商品")
    @GetMapping("/{id}")
    public ResponseEntity<Commodity> getCommodity(@ApiParam("商品id") @PathVariable long id) {
        Commodity result = commodityService.getCommodity(id);
        if (result == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @ApiOperation("根据条件查询商品")
    @GetMapping("")
    public ResponseEntity<List<Commodity>> getCommodities(@ApiParam("商品名称") String name, @ApiParam("是否被删除") Boolean deleted) {
        List<Commodity> result = commodityService.getCommodities(name, deleted);
        return ResponseEntity.ok(result);
    }
}
