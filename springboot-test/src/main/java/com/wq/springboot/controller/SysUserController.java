package com.wq.springboot.controller;

import com.wq.springboot.domain.User;
import com.wq.springboot.mqtt.MqttGateway;
import com.wq.springboot.service.impl.SysuserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import org.json.*;
/**
 * @Author: wq
 * @Desc:
 * @Date: 2020/1/5 16:25
 * @Version 1.0
 */
@Slf4j
@Controller
public class SysUserController {

    public static List paramList = new ArrayList<String>();

    @Autowired
    private MqttGateway mqttGateway;


    @Autowired
    private SysuserServiceImpl sysuserService;

    @ResponseBody
    @GetMapping("/user/{id}")
    public User getUserById(String id) {
        log.info("2324");

        User user = sysuserService.queryUser(id);
        return user;
    }

    /*public static void main(String[] args) {
        paramList.add("12");

        new SysuserServiceImpl().deleteMoth(paramList);
        System.out.println(paramList.size());
    }*/

    @ResponseBody
    @RequestMapping("/sendMqtt.do")
    public String sendMqtt(String sendData) {
        mqttGateway.sendToMqtt(sendData, "hello");
        return "OK";
    }

    public static void main(String[] args) {


        String xmlStr="<?xml       version=\\\"1.0\\\"encoding=\\\"UTF-8\\\"?>\n" +
                "<PACKAGE>\n" +
                "  <PACKAGEHEAD>\n" +
                "     <FFQQBH>4660c10a-6aef-4a75-8b19-90b3b1b34e2f</FFQQBH>\n" +
                "    <FSSJ>20190805092712</FSSJ>\n" +
                "    <FHDM>10</FHDM>\n" +
                "    <FHMS>接口调用成功</FHMS>\n" +
                "    </PACKAGEHEAD>\n" +
                "    <DATA><RECORD no=\"1\" code=\"\" msg=\"\">\n" +
                "    <GMSFHM>220502198807260231</GMSFHM>\n" +
                "    <GMSFHM_PPDDM>1</GMSFHM_PPDDM>\n" +
                "   <SWBS>0</SWBS>\n" +
                "   <XM>刘宇</XM>\n" +
                "   <XM_PPDDM>1</XM_PPDDM>\n" +
                "   </RECORD></DATA>\n" +
                "</PACKAGE>";
        JSONObject parseJSON;
        try {
            parseJSON = XML.toJSONObject(xmlStr).getJSONObject("PACKAGE"); //xmlStr传入的xml格式字符串，BaseLine xml中的顶层节点
            System.out.println(parseJSON);
        } catch (JSONException e) {
            log.error("XML格式错误******" + e.getMessage() + "******");

        }

    }
}
