package com.ronghao.chapter15.controller;

import com.ronghao.chapter15.entity.DemoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**********************************
 * ============================== 
 * @author Ronghao Zhou          
 * @description
 * @date 1:05 AM 3/19/2018
 * ==============================
 **********************************/
@RestController
public class IndexController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/validator")
    public String validator
            (
                    @Valid DemoEntity entity, BindingResult result
            ) {
        if (result.hasErrors()) {
            StringBuffer msg = new StringBuffer();

            List<FieldError> fieldErrors = result.getFieldErrors();

            Locale currentLocale = LocaleContextHolder.getLocale();

            for (FieldError fieldError : fieldErrors) {
                String errorMessage = messageSource.getMessage(fieldError, currentLocale);

                msg.append(fieldError.getField() + ":" + errorMessage + ",");
            }
            return msg.toString();
        }
        return "验证通过，" + "名称：" + entity.getName() + "年龄：" + entity.getAge() + "邮箱地址" + entity.getMail();


    }
}
