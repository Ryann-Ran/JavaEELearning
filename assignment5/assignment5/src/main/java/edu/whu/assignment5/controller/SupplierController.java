package edu.whu.assignment5.controller;


import edu.whu.assignment5.domain.Supplier;
import edu.whu.assignment5.service.impl.SupplierServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yuran Wang
 * @since 2023-10-21
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierServiceImpl supplierService;

    @ApiOperation("添加一个供应商")
    @PostMapping("")
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier Supplier){
        Supplier result = supplierService.insertSupplier(Supplier);
        return ResponseEntity.ok(result);
    }

    @ApiOperation("根据id删除供应商")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplierById(@PathVariable int id){
        supplierService.removeById(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("修改供应商信息")
    @PutMapping("")
    public ResponseEntity<Void> updateSupplier(@RequestBody Supplier Supplier){
        supplierService.updateById(Supplier);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("根据Id查询供应商")
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplier(@ApiParam("供应商Id")@PathVariable int id){
        Supplier result = supplierService.getById(id);
        if(result==null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    @ApiOperation("查询所有供应商")
    @GetMapping("")
    public ResponseEntity<List<Supplier>> getAllSuppliers(){
        List<Supplier> result = supplierService.queryAllSuppliers();
        return ResponseEntity.ok(result);
    }
}

