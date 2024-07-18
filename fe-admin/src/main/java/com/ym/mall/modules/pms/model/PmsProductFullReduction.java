package com.ym.mall.modules.pms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 产品满减表(只针对同商品)
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_product_full_reduction")
@ApiModel(value="PmsProductFullReduction对象", description="产品满减表(只针对同商品)")
public class PmsProductFullReduction implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private BigDecimal fullPrice;

    private BigDecimal reducePrice;


}
