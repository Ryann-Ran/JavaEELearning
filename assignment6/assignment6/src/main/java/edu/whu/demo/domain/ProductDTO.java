package edu.whu.demo.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO extends Product{
    private List<Supplier> supplierList=new ArrayList<>();
}

