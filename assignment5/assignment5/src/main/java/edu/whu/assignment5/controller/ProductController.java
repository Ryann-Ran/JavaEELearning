package edu.whu.assignment5.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.assignment5.domain.Product;
import edu.whu.assignment5.service.impl.ProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yuran Wang
 * @since 2023-10-21
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @ApiOperation("添加一个商品")
    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product result = productService.insertProduct(product);
        return ResponseEntity.ok(result);
    }

    @ApiOperation("添加商品与供应商的关系")
    @PostMapping("/{product_id}/{supplier_id}")
    public ResponseEntity<Void> addProductSupplier (@PathVariable int product_id, @PathVariable int supplier_id) {
        productService.insertProductSupplier(product_id, supplier_id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("根据id删除商品")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int id){
        productService.removeById(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("修改商品信息")
    @PutMapping("")
    public ResponseEntity<Void> updateProduct(@RequestBody Product product){
        productService.updateById(product);
        return ResponseEntity.ok().build();
    }

//    @ApiOperation("根据Id查询商品的所有供应商")
//    @GetMapping("")
//    public ResponseEntity<List<Map<String, Object>>> getSuppliersById (@ApiParam("商品Id") @RequestParam int id) {
//        List<Map<String, Object>> result = productService.querySuppliersById(id);
//        return ResponseEntity.ok(result);
//    }

    @ApiOperation("根据Id查询商品")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@ApiParam("商品Id")@PathVariable int id){
        Product result = productService.getById(id);
        if(result==null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @ApiOperation("查询所有商品")
    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> result = productService.queryAllProducts();
        return ResponseEntity.ok(result);
    }

//    @ApiOperation("查询库存大于指定数量的商品")
//    @GetMapping("/{stockQuantity}")
//    public ResponseEntity<List<Product>> findProductByCondition(@ApiParam("商品库存")@PathVariable Float stockQuantity){
//        List<Product> result = productService.queryByCondition(stockQuantity);
//        return ResponseEntity.ok(result);
//    }
//
//    @ApiOperation("查询名称包含指定关键字的商品")
//    @GetMapping("/{keyword}")
//    public ResponseEntity<List<Product>> findProductByCondition(@ApiParam("商品名字关键字")@PathVariable String keyword){
//        List<Product> result = productService.queryByLambda(keyword);
//        return ResponseEntity.ok(result);
//    }
//
//    @ApiOperation("查询价格在[minPrice, maxPrice]范围内的商品")
//    @GetMapping("/{minPrice}/{maxPrice}")
//    public ResponseEntity<List<Product>> findProductByAnd(@ApiParam("商品价格最小值")@PathVariable Float minPrice, @ApiParam("商品价格最大值")@PathVariable Float maxPrice){
//        List<Product> result = productService.queryByAnd(minPrice, maxPrice);
//        return ResponseEntity.ok(result);
//    }
//
//    @ApiOperation("查询价格小于minPrice或者大于maxPrice的商品")
//    @GetMapping("/{minPrice}/{maxPrice}")
//    public ResponseEntity<List<Product>> findProductByOr(@ApiParam("商品价格最小值")@PathVariable Float minPrice, @ApiParam("商品价格最大值")@PathVariable Float maxPrice){
//        List<Product> result = productService.queryByOr(minPrice, maxPrice);
//        return ResponseEntity.ok(result);
//    }
//
//    @ApiOperation("多条件随机组合")
//    @GetMapping("/{minStockQuantity}/{maxStockQuantity}")
//    public ResponseEntity<List<Product>> findProductByNull(@ApiParam("商品库存最小值")@PathVariable Float minStockQuantity, @ApiParam("商品库存最大值")@PathVariable Float maxStockQuantity){
//        List<Product> result = productService.queryByOr(minStockQuantity, maxStockQuantity);
//        return ResponseEntity.ok(result);
//    }
//
//    @ApiOperation("查询所有商品的名称和价格")
//    @GetMapping("")
//    public ResponseEntity<List<Product>> findProductByProjection(){
//        List<Product> result = productService.queryByProjection();
//        return ResponseEntity.ok(result);
//    }
//
//    @ApiOperation("查询指定种类的商品的数量")
//    @GetMapping("/{category}")
//    public ResponseEntity<List<Map<String, Object>>> findCountByAggregation(@ApiParam("商品种类")@PathVariable String category){
//        List<Map<String, Object>> result = productService.queryCountByAggregation(category);
//        return ResponseEntity.ok(result);
//    }
//
//    @ApiOperation("查询库存大于等于指定数量的商品的最大价格")
//    @GetMapping("/{stockQuantity}")
//    public ResponseEntity<List<Map<String, Object>>> findCountByAggregation(@ApiParam("商品库存")@PathVariable Float stockQuantity){
//        List<Map<String, Object>> result = productService.queryMaxByAggregation(stockQuantity);
//        return ResponseEntity.ok(result);
//    }
//
//    @ApiOperation("查询每个种类库存大于指定数量的商品的数量")
//    @GetMapping("/{stockQuantity}")
//    public ResponseEntity<List<Map<String, Object>>> findProductByGrouping(@ApiParam("商品库存")@PathVariable Float stockQuantity){
//        List<Map<String, Object>> result = productService.queryByGrouping(stockQuantity);
//        return ResponseEntity.ok(result);
//    }

//    @ApiOperation("分页排序")
//    @GetMapping("")
//    public ResponseEntity<IPage<Product>> getPagedProducts(
//            @ApiParam("页数，默认为1") @RequestParam(defaultValue = "1") int current,
//            @ApiParam("每页显示数量，默认为10") @RequestParam(defaultValue = "10") int size,
//            @ApiParam("可选的名字关键词") @RequestParam(required = false) String keyword
//    ) {
//        IPage<Product> result = productService.queryByPage(current, size, keyword);
//        return ResponseEntity.ok(result);
//    }
}

