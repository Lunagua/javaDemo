//package com.tz.demo.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.tz.demo.service.DemoService;
//import com.tz.demo.util.DateUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
//@RestController
//public class DemoController {
//
//    @Resource
//    private DemoService demoService;
//
//    @RequestMapping("createPayOrder")
//    public Object createPayOrder(String merchantOrderNo, String model, String amount, String memberNo, String notifyUrl) {
//        return demoService.createPayOrder(merchantOrderNo, model, amount, memberNo, notifyUrl);
//    }
//
//    @RequestMapping("queryPayOrder")
//    public JSONObject queryPayOrder(String merchantOrderNo, String submitDate) {
//        return demoService.queryPayOrder(merchantOrderNo, DateUtils.parse(submitDate, "yyyyMMddHHmmss"));
//    }
//
//    @RequestMapping("createRemitOrder")
//    public JSONObject createRemitOrder(String merchantOrderNo, String bankCode, String amount, String bankcardAccountNo, String bankcardAccountName, String notifyUrl) {
//        return demoService.createRemitOrder(merchantOrderNo, bankCode, amount, bankcardAccountNo, bankcardAccountName, notifyUrl);
//    }
//
//    @RequestMapping("queryRemitOrder")
//    public JSONObject queryRemitOrder(String merchantOrderNo, String submitDate) {
//        return demoService.queryRemitOrder(merchantOrderNo, DateUtils.parse(submitDate, "yyyyMMddHHmmss"));
//    }
//
//    @RequestMapping("queryBalance")
//    public JSONObject queryBalance() {
//        return demoService.queryBalance();
//    }
//}
