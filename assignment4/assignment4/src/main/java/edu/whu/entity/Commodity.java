package edu.whu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ryann
 * @create 2023/10/12 - 8:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="商品实体")
public class Commodity {
    @ApiModelProperty("商品编号")
    long id;

    @ApiModelProperty("商品名称")
    String name;

    @ApiModelProperty("是否被删除")
    boolean Deleted;
}
