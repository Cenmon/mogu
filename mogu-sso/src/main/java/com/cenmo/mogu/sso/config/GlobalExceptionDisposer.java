package com.cenmo.mogu.sso.config;

import com.cenmo.mogu.common.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Cenmo
 * @Date 2020-10-16 2020/10/16
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionDisposer {

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public MoguResult globalError(Exception e){
//        log.error(ExceptionUtil.getMessage(e));
//        return MoguResult.error();
//    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
//    @ExceptionHandler(MoguException.class)
//    @ResponseBody
//    public MoguResult MoguError(MoguException e){
//        log.error(ExceptionUtil.getMessage(e));
//        return MoguResult.error(e.getResEnum());
//    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ModelAndView globalError(Exception e){
        ModelAndView modelAndView = new ModelAndView("error/error.html");
        modelAndView.addObject("msg","未知错误");
        log.error(ExceptionUtil.getMessage(e));
        return modelAndView;
    }

}
