package com.qingofun.controller;

import com.qingofun.pojo.Items;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinkang on 16/6/21.
 */
public class ItemsController1 implements Controller {
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 商品列表
        List itemsList = new ArrayList();
        Items items_2 = new Items();
        items_2.setName("苹果手机");
        items_2.setPrice(6088f);
        items_2.setDetail("iphone6s苹果手机！");
        itemsList.add(items_2);
        // 创建modelAndView：填充数据、设置视图
        ModelAndView modelAndView = new ModelAndView();
        // 填充数据
        modelAndView.addObject("itemsList", itemsList);// 类似request.setAttribute("","")

        // 视图:逻辑名称
        modelAndView.setViewName("itemsList");// request.getRequestDispatcher("url").forward(request, response);
        return modelAndView;
    }
}
