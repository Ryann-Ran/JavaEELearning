package edu.whu.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.demo.domain.Supplier;
import edu.whu.demo.exception.ProductAdminException;
import edu.whu.demo.service.ISupplierService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  供应商管理的API
 * </p>
 *  使用全局异常检测简化代码
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    ISupplierService supplierService;

    @ApiOperation("根据Id查询供应商")
    @GetMapping("/{id}")
    public Supplier getSupplier(@ApiParam("供应商Id")@PathVariable long id){
        Supplier result = supplierService.getById(id);
        return result;
    }

    @ApiOperation("根据条件查询供应商")
    @GetMapping("")
    public IPage<Supplier> findSupplier(@ApiParam("商品名称")String name,
                                                                @ApiParam("页码")@RequestParam(defaultValue = "0")Integer pageNum,
                                                                @ApiParam("每页记录数") @RequestParam(defaultValue = "10")Integer pageSize){
        IPage<Supplier> result = supplierService.findSuppliers(name, pageNum, pageSize);
        return result;
    }

    @ApiOperation("添加供应商")
    @PostMapping("")
    public Supplier addSupplier (@RequestBody Supplier supplier) throws ProductAdminException {
        supplierService.saveOrUpdate(supplier);
        return supplier;
    }

    @ApiOperation("修改供应商")
    @PutMapping("/{id}")
    public void updateSupplier (@PathVariable long id,@RequestBody Supplier supplier) throws ProductAdminException {
        supplierService.saveOrUpdate(supplier);
    }

    @ApiOperation("删除供应商")
    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable long id) {
        supplierService.removeById(id);
    }
}

