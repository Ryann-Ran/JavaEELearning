package edu.whu.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.demo.dao.SupplierDao;
import edu.whu.demo.domain.Supplier;
import edu.whu.demo.exception.ProductAdminException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class SupplierServiceTest {

    @Autowired
    ISupplierService supplierService;

    @Autowired
    SupplierDao supplierDao;

    @BeforeEach
    public void init(){
        Supplier supplier=new Supplier();
        supplier.setName("A公司");
        supplier.setPhone("1982229992");
        supplierDao.insert(supplier);

        Supplier supplier2=new Supplier();
        supplier2.setName("B公司");
        supplier2.setPhone("1982223332");
        supplierDao.insert(supplier2);
    }
//
    @Test
    public void testAdd() throws ProductAdminException {
        Supplier supplier=new Supplier();
        supplier.setName("C公司");
        supplier.setPhone("1982223332");
        supplierService.save(supplier);
        assertNotNull(supplier.getId());
        assertNotNull(supplierDao.selectById(supplier.getId()));
    }
//
    @Test
    public void testFind() throws ProductAdminException {
        IPage<Supplier> result = supplierService.findSuppliers("公司", 0, 3);
        assertNotNull(result);
        assertEquals(2,result.getTotal());
        assertEquals(2,result.getRecords().size());
    }







}
