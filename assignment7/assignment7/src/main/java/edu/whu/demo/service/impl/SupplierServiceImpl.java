package edu.whu.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.demo.dao.SupplierDao;
import edu.whu.demo.domain.Supplier;
import edu.whu.demo.service.ISupplierService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiaxy
 * @since 2022-10-31
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierDao, Supplier> implements ISupplierService {

    @Override
    public IPage<Supplier> findSuppliers(String name, Integer pageNum, Integer pageSize) {
        Page<Supplier> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Supplier> qw = new QueryWrapper<>();
        qw.like(name!=null,"name",name);
        this.baseMapper.selectPage(page,qw);
        return page;
    }
}
