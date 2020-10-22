package com.cenmo.mogu.controller;


import com.cenmo.mogu.common.vo.EUDataGridResult;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.ItemParam;
import com.cenmo.mogu.pojo.ItemParamModel;
import com.cenmo.mogu.service.ItemParamModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品规则参数 前端控制器
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-20
 */
@RestController
@RequestMapping("/item-param-model")
public class ItemParamModelController {

    @Autowired
    private ItemParamModelService itemParamModelService;

    @RequestMapping("/save/{cid}")
    public MoguResult insertItemParam(@PathVariable long cid, String paramData) {
        // 保存商品规格参数
        ItemParamModel itemParamModel = new ItemParamModel();
        // 参数模板id自增长,故无需添加
        itemParamModel.setItemCatId(cid);
        itemParamModel.setParamData(paramData);
        return itemParamModelService.insertItemParamModel(itemParamModel);
    }

    @GetMapping("/list")
    public EUDataGridResult getItemParamList(Integer page, Integer rows) {
        EUDataGridResult result = itemParamModelService.getItemParamModelList(page, rows);
        return result;
    }

    @RequestMapping("/delete")
    public MoguResult deleteItemParam(long[] ids) {
        for(long id:ids) {
            if(itemParamModelService.deleteItemParamModel(id).getStatus()!=200) {
                return MoguResult.error();
            }
        }
        return MoguResult.ok();
    }

}

