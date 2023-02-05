package com.itheima.reggie.controller;

import com.itheima.reggie.common.R;
import com.itheima.reggie.domain.User;
import com.itheima.reggie.utils.SMSUtils;
import com.itheima.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.io.SessionOutputBufferImpl;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @program:reggie_take_out
 * @description:
 * @author:左毅
 * @createData:2022/10/3
 **/
@RestController
@RequestMapping("/user")
@Slf4j

public class UserController {
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        String phone = user.getPhone();

        if(StringUtils.isEmpty(phone)){

            String code = ValidateCodeUtils.generateValidateCode(4).toString();

            SMSUtils.sendMessage("阿里云短信测试","SMS_154950909","13972120482",code);
            log.info("code:{}",code);

            session.setAttribute(phone,code);

            R.success("短信发送成功");
        }

        return R.error("短信发送失败");

    }
}
