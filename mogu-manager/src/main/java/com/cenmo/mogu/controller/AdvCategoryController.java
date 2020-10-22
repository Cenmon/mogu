package com.cenmo.mogu.controller;


import com.cenmo.mogu.common.vo.EUTreeNode;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.service.AdvCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 广告分类表 前端控制器
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-20
 */
@RestController
@RequestMapping("/adv-category")
public class AdvCategoryController {
    @Autowired
    private AdvCategoryService advCategoryService;

    @GetMapping("/create")
    public MoguResult insertAdvCategory(Long parentId, String name) {
        return advCategoryService.insertAdvCat(parentId, name);
    }

    @GetMapping("/list")
    public List<EUTreeNode> getAdvCategoryList(@RequestParam(value = "id",defaultValue = "0")long parentId) {
        return advCategoryService.getAdvCatList(parentId);
    }

    @GetMapping("/delete")
    public MoguResult deleteAdvCategory(Long id) {
        return advCategoryService.deleteAdvCat(id);
    }

    @GetMapping("/update")
    @ApiOperation("重命名广告分类名称")
    public MoguResult resetAdvCategory(Long id,String name) {
        return advCategoryService.updateAdvCat(id, name);
    }
}

