package edu.whu.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.whu.demo.domain.Supplier;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiaxy
 * @since 2022-10-31
 */
public interface ISupplierService extends IService<Supplier> {

    IPage<Supplier> findSuppliers(String name, Integer pageNum, Integer pageSize);
}
