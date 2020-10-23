package com.cenmo.mogu.manager.controller;


import com.cenmo.mogu.common.vo.EUDataGridResult;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.Adv;
import com.cenmo.mogu.manager.service.AdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 广告表 前端控制器
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-20
 */
@RestController
@RequestMapping("/adv")
public class AdvController {
    
    @Autowired
    private AdvService advService;

    @GetMapping("/list")
    public EUDataGridResult getAdvList(Long categoryId, Integer page, Integer rows) {
        return advService.getAdvList(categoryId,page, rows);
    }

    @RequestMapping("/save")
    public MoguResult insertAdv(Adv adv) {
        MoguResult result = advService.insertAdv(adv);
//        HttpClientUtil.doGet(REST_BASEURL+REST_CONTENT_SYNC_URL);
        return result;
    }

    @RequestMapping("/delete")
    public MoguResult deleteAdv(long[] ids) {
        try {
            for(long id:ids) {
                advService.deleteAdv(id);
            }
//            HttpClientUtil.doGet(REST_BASEURL+REST_CONTENT_SYNC_URL);
            return MoguResult.ok();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return MoguResult.error();
        }
    }

    @RequestMapping("/edit")
    public MoguResult editAdv(Adv adv) {
//        adv.setUpdated(new Date());
        MoguResult result = advService.editAdv(adv);
//        HttpClientUtil.doGet(REST_BASEURL+REST_CONTENT_SYNC_URL);
        return result;
    }

}

