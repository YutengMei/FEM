package com.ym.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ym.mall.common.api.CommonResult;
import com.ym.mall.modules.pms.mapper.PmsProductMapper;
import com.ym.mall.modules.pms.model.PmsMemberPrice;
import com.ym.mall.modules.pms.model.PmsProduct;
import com.ym.mall.modules.pms.model.dto.ProductConditionDTO;
import com.ym.mall.modules.pms.service.*;
import com.ym.mall.modules.ums.dto.ProductSaveParamsDTO;
import com.ym.mall.modules.ums.dto.ProductUpdateInitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author Yuteng Mei
 * @since 2024-07-05
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {

    @Autowired
    private PmsMemberPriceService memberPriceService;

    @Autowired
    private PmsProductLadderService pmsProductLadderService;

    @Autowired
    private PmsProductFullReductionService pmsProductFullReductionService;

    @Autowired
    private PmsSkuStockService pmsSkuStockService;

    @Autowired
    private PmsProductAttributeValueService pmsProductAttributeValueService;

    @Autowired
    private PmsProductMapper PmsProductMapper;
    @Autowired
    private PmsProductMapper pmsProductMapper;

    @Override
    public Page list(ProductConditionDTO condition) {
        Page page = new Page(condition.getPageNum(), condition.getPageSize());
        QueryWrapper<PmsProduct> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PmsProduct> lqw = wrapper.lambda();
        if (!StringUtils.isEmpty(condition.getKeyword())) {
            lqw.like(PmsProduct::getName, condition.getKeyword());
        }
        if (!StringUtils.isEmpty(condition.getProductSn())) {
            lqw.eq(PmsProduct::getProductSn, condition.getProductSn());
        }
        if (!StringUtils.isEmpty(condition.getProductCategoryId())) {
            lqw.eq(PmsProduct::getProductCategoryId, condition.getProductCategoryId());
        }
        if (condition.getBrandId() != null && condition.getBrandId() > 0) {
            lqw.eq(PmsProduct::getBrandId, condition.getBrandId());
        }
        if (condition.getPublishStatus() != null) {
            lqw.eq(PmsProduct::getPublishStatus, condition.getPublishStatus());
        }

        if (condition.getVerifyStatus() != null) {
            lqw.eq(PmsProduct::getVerifyStatus, condition.getVerifyStatus());
        }
        lqw.orderByAsc(PmsProduct::getSort);
        return this.page(page, lqw);
    }

    @Override
    public boolean updateStatus(Integer newStatus, List<Long> ids, SFunction<PmsProduct, ?> getNewStatus) {
        UpdateWrapper<PmsProduct> pmsProductUpdateWrapper = new UpdateWrapper<>();
        pmsProductUpdateWrapper.lambda().set(getNewStatus, newStatus).in(PmsProduct::getId, ids);
        return this.update(pmsProductUpdateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(ProductSaveParamsDTO productSaveParamsDTO) {
        PmsProduct product = productSaveParamsDTO;
        product.setId(null);
        boolean result = this.save(product);
        if (result) {
            switch(product.getPromotionType()) {
                case 2:
                    saveEmbeddedAttributeList(productSaveParamsDTO.getMemberPriceList(), product.getId(), memberPriceService);
                    break;
                case 3:
                    saveEmbeddedAttributeList(productSaveParamsDTO.getProductLadderList(), product.getId(), pmsProductLadderService);
                    break;
                case 4:
                    saveEmbeddedAttributeList(productSaveParamsDTO.getProductFullReductionList(), product.getId(), pmsProductFullReductionService);
                break;
            }
            saveEmbeddedAttributeList(productSaveParamsDTO.getSkuStockList(), product.getId(), pmsSkuStockService);
            saveEmbeddedAttributeList(productSaveParamsDTO.getProductAttributeValueList(), product.getId(), pmsProductAttributeValueService);
        }
        return result;
    }

    @Override
    public ProductUpdateInitDTO getUpdateInfo(Long id) {
        return pmsProductMapper.getUpdateInfo(id);
    }

    @Override
    public boolean update(ProductSaveParamsDTO productSaveParamsDTO) {
        PmsProduct product = productSaveParamsDTO;
        boolean result = this.updateById(product);
        if (result) {
            switch(product.getPromotionType()) {
                case 2:
                    deleteEmbeddedAttributeListByProductId(product.getId(), memberPriceService);
                    saveEmbeddedAttributeList(productSaveParamsDTO.getMemberPriceList(), product.getId(), memberPriceService);
                    break;
                case 3:
                    deleteEmbeddedAttributeListByProductId(product.getId(), pmsProductLadderService);
                    saveEmbeddedAttributeList(productSaveParamsDTO.getProductLadderList(), product.getId(), pmsProductLadderService);
                    break;
                case 4:
                    deleteEmbeddedAttributeListByProductId(product.getId(), pmsProductFullReductionService);
                    saveEmbeddedAttributeList(productSaveParamsDTO.getProductFullReductionList(), product.getId(), pmsProductFullReductionService);
                    break;
            }
            deleteEmbeddedAttributeListByProductId(product.getId(), pmsSkuStockService);
            saveEmbeddedAttributeList(productSaveParamsDTO.getSkuStockList(), product.getId(), pmsSkuStockService);
            deleteEmbeddedAttributeListByProductId(product.getId(), pmsProductAttributeValueService);
            saveEmbeddedAttributeList(productSaveParamsDTO.getProductAttributeValueList(), product.getId(), pmsProductAttributeValueService);
        }
        return result;
    }

    public void deleteEmbeddedAttributeListByProductId(Long productId, IService service) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        service.remove(queryWrapper);
    }

    public void saveEmbeddedAttributeList(List<?> list, Long productId, IService service) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        try {
            for (Object obj : list) {
                Method setProductIdMethod = obj.getClass().getMethod("setProductId", Long.class);

                //remove primary id during edit
                Method setId = obj.getClass().getMethod("setId", Long.class);
                setId.invoke(obj, (Long)null);

                setProductIdMethod.invoke(obj, productId);
            }
            service.saveBatch(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
