package edu.whu.assignment5.service.impl;

import edu.whu.assignment5.domain.Supplier;
import edu.whu.assignment5.dao.SupplierDao;
import edu.whu.assignment5.service.ISupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yuran Wang
 * @since 2023-10-21
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierDao, Supplier> implements ISupplierService {
    @Autowired
    SupplierDao supplierDao;

    // 增加一个供应商
    public Supplier insertSupplier(Supplier supplier) {
        supplierDao.insert(supplier);
        return supplierDao.selectById(supplier.getId());
    }

    // 查询所有供应商
    public List<Supplier> queryAllSuppliers() {
        return supplierDao.selectList(null);
    }
}
