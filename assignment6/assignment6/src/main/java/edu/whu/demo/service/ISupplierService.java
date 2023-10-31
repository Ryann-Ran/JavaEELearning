package edu.whu.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.whu.demo.domain.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface ISupplierService extends IService<Supplier> {

    IPage<Supplier> findSuppliers(String name, Integer pageNum, Integer pageSize);
}
