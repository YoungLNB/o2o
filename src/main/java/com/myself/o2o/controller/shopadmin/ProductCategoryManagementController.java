package com.myself.o2o.controller.shopadmin;

import com.myself.o2o.dto.Result;
import com.myself.o2o.entity.ProductCategory;
import com.myself.o2o.entity.Shop;
import com.myself.o2o.enums.ProductCategoryStateEnum;
import com.myself.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "getProductCategoryList", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
        //To be removed
        Shop shop = new Shop();
        shop.setShopId(1L);
        request.getSession().setAttribute("currentShop", shop);

        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> productCategoryList = null;
        if (currentShop != null && currentShop.getShopId() > 0) {
            productCategoryList = productCategoryService.getProductCategoryList(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true, productCategoryList);
        } else {
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false, ps.getState(), ps.getStateInfo());
        }
    }
}
