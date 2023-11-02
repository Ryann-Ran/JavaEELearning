package edu.whu.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.demo.domain.Supplier;
import edu.whu.demo.exception.ProductAdminException;
import edu.whu.demo.service.ISupplierService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    ISupplierService supplierService;

    @ApiOperation("根据Id查询供应商")
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplier(@ApiParam("供应商Id")@PathVariable long id){
        Supplier result = supplierService.getById(id);
        if(result==null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @ApiOperation("根据条件查询供应商")
    @GetMapping("")
    public IPage<Supplier> findSupplier(@ApiParam("商品名称")String name,
                                        @ApiParam("页码")@RequestParam(defaultValue = "0")Integer pageNum,
                                        @ApiParam("每页记录数") @RequestParam(defaultValue = "10")Integer pageSize){
        return supplierService.findSuppliers(name, pageNum,pageSize);
    }

    /**
     * 添加供应商
     * @param supplier
     * @return
     * @throws ProductAdminException 在Controller抛出异常，可以通过全局异常处理进行捕获
     */
    @ApiOperation("添加供应商")
    @PostMapping("")
    public Supplier addProduct(@RequestBody Supplier supplier) throws ProductAdminException {
        supplierService.saveOrUpdate(supplier);
        return supplier;
    }

    @ApiOperation("修改供应商")
    @PreAuthorize("hasAuthority('supplier/admin')")
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable long id,@RequestBody Supplier supplier) throws ProductAdminException {
        supplierService.saveOrUpdate(supplier);
    }

    @ApiOperation("删除供应商")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id){
        supplierService.removeById(id);
    }

}

