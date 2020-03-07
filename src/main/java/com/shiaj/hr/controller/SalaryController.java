package com.shiaj.hr.controller;

import com.shiaj.hr.bean.Response;
import com.shiaj.hr.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/salary")
public class SalaryController {
    private final ISalaryService service;

    @Autowired
    public SalaryController(ISalaryService service) {
        this.service = service;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public Response getAll() throws Exception {
        return new Response().success(service.getAll());
    }

    @RequestMapping(value = "/get-salary-by-uid", method = RequestMethod.POST)
    public Response getSalaryByUid(@RequestBody Long uid) throws Exception {
        return new Response().success(service.getSalaryByUid(uid));
    }

    @RequestMapping(value = "/get-salary-all", method = RequestMethod.POST)
    public Response getSalaryAll(@RequestBody Map<String, String> params) throws Exception {
        return new Response().success(service.getSalaryAll(params));
    }

//    @RequestMapping(value = "/search-salary", method = RequestMethod.POST)
//    public Response searchSalary(Map<String,Object> params) throws Exception {
//        return new Response().success(service.searchSalary(params));
//    }

    @RequestMapping(value = "/delete-salary", method = RequestMethod.POST)
    public Response deleteSalary(@RequestBody Long id) throws Exception {
        service.deleteSalary(id);
        return new Response().success();
    }

    @RequestMapping(value = "/update-salary", method = RequestMethod.POST)
    public Response updateSalary(@RequestBody Map<String, Object> params) throws Exception {
        service.updateSalary(params);
        return new Response().success();
    }

    @RequestMapping(value = "/add-salary", method = RequestMethod.POST)
    public Response addSalary(@RequestBody Map<String, Object> params) throws Exception {
        return new Response().success(service.addSalary(params));
    }

    @RequestMapping(value = "/check-salary", method = RequestMethod.POST)
    public Response checkSalary(@RequestBody Map<String, Object> params) throws Exception {
        return new Response().success(service.checkSalary(params));
    }

    // 根据员工姓名或工号以及时间获取薪资信息
    @RequestMapping(value = "/get-salary-by-nameOrJobCode", method = RequestMethod.POST)
    public Response getProductByName(@RequestBody Map<String, String> params) {
        return new Response().success(service.getSalaryByNameOrJobCode(params));
    }

    // 根据员工工号获取员工最新基本薪资信息
    @RequestMapping(value = "/get-salary-by-jobCode", method = RequestMethod.POST)
    public Response getSalaryByJobCode(@RequestBody String jobCode) {
        return new Response().success(service.getSalaryByJobCode(jobCode));
    }

    // 根据时间获取员工最新基本薪资信息（个人薪资单）
    @RequestMapping(value = "/get-salary-by-time", method = RequestMethod.POST)
    public Response getSalaryByTinme(@RequestBody Map<String, String> params) {
        return new Response().success(service.getSalaryByTinme(params));
    }
}
