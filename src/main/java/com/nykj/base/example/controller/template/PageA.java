package com.nykj.base.example.controller.template;

import com.alibaba.fastjson.JSON;
import com.nykj.base.example.entity.ioframe.DataTableResponse;
import com.nykj.base.example.entity.web.datatable.DataTableFormat;
import com.nykj.base.example.entity.web.form.*;
import com.nykj.base.example.entity.web.template.TemplatePageA;
import com.nykj.base.example.util.MapBuildUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cq on 4/20/16.
 */
@Controller
@Scope("prototype")
public class PageA {

    @RequestMapping(value = "page_a", method = RequestMethod.GET)
    public ModelAndView cfgApiComInfoLoad() {
        ModelAndView view = new ModelAndView("jsp/template/page_a");
        Object[][] queryForm = {
                {
                        new DatePickerFormat("timepicker", "时间选择"),
                },
                {
                        new InputTextFormat("name", "HIS厂商"),
                        new InputTextFormat("stmethod", "标准方法名", true),
                        new SelectOneFormat("test", "测试select", false, new MapBuildUtil<String, String>()
                                .put("11", "test")
                                .put("23", "test23")
                                .build()
                        ),
                },
        };
        Object[] resultTable = {
                new DataTableFormat("his_com", "HIS厂商", false),
                new DataTableFormat("test", "测试"),
                new DataTableFormat("test2", "测试2"),
        };
        Object[][] addForm = {
                {
                        new InputTextFormat("his_com_name", "HIS厂商", true),
                        new InputTextFormat("a", "标准方法名", true),
                        new InputTextFormat("b", "参数名列表", true),
                },
                {
                        new SelectOneFormat("status", "状态", true, new MapBuildUtil<String, String>()
                                .put("1", "启用")
                                .put("0", "禁用")
                                .build()
                        ),
                        new InputTextFormat("c", "接口方法名", true),
                        new InputTextFormat("d", "参数类型列表"),
                },
                {
                        new InputTextFormat("e", "前置方法名", true),
                        new InputTextFormat("f", "后置方法名", true),
                        new SelectOneFormat("g", "出参格式", false, new MapBuildUtil<String, String>()
                                .put("1", "xml")
                                .put("0", "json")
                                .build()
                        ),
                },
                {
                        new DatePickerFormat("name", "时间选择"),
                },
                {
                        new TextareaFormat("h", "入参模板"),
                        new TextareaFormat("i", "出参模板"),
                },
                {
                        new TextareaFormat("j", "入参脚本"),
                        new TextareaFormat("k", "出参脚本"),
                },
        };
        Object[][] editForm = {
                {
                        new InputHiddenFormat("his_com"),
                },
                {
                        new InputTextFormat("test", "测试"),
                        new InputTextFormat("test2", "测试2"),
                },
        };

        TemplatePageA entity = new TemplatePageA();
        entity.setTitle("Template - PageA");
        entity.setSelfUrl("/page_a");
        entity.setQueryUrl("/page_a");
        entity.setAddUrl("/page_a_add");
        entity.setEditUrl("/page_a_edit");
        entity.setDeleteUrl("/page_a_delete");
        entity.setQueryForm(queryForm);
        entity.setResultTable(resultTable);
        entity.setAddForm(addForm);
        entity.setEditForm(editForm);

        view.addObject("web_entity", entity);

        return view;
    }

    /**
     * DataTable接口
     *
     * @param draw     DataTable控制参数,透传回response即可
     * @param start    开始的条目
     * @param length   当前页条目数量
     * @param stmethod 查询参数
     * @return
     */
    @RequestMapping(value = "page_a", method = RequestMethod.POST)
    @ResponseBody
    public String cfgApiComInfoLoad(
            @RequestParam Long draw,
            @RequestParam Long start,
            @RequestParam Long length,
            @RequestParam String stmethod,
            @RequestParam String timepicker
    ) {
        DataTableResponse response = new DataTableResponse();
        for (long i = start; i < length + start; i++) {
            Map<String, String> data = new HashMap<String, String>();
            data.put("his_com", String.valueOf(i));
            data.put("test", String.valueOf(i));
            data.put("test2", stmethod);
            response.getData().add(data);
        }
        response.setDraw(draw);
        response.setRecordsTotal(100L);
        response.setRecordsFiltered(100L);
        return JSON.toJSONString(response);
    }

}
