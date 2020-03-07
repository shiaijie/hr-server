package com.shiaj.hr.controller;

import com.shiaj.hr.bean.Response;
import com.shiaj.hr.entity.User;
import com.shiaj.hr.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2019-12-01
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody User user) throws Exception {
        return new Response().success(userService.login(user.getJobCode(), user.getPassword()));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(@RequestBody Map<String, Object> params) throws Exception {
        return new Response().success(userService.register(params));
    }

    @RequestMapping(value = "/check-jobCode", method = RequestMethod.POST)
    public Response checkJobCode(@RequestBody Map<String, String> params) throws Exception {
        return new Response().success(userService.checkJobCode(params));
    }

    @RequestMapping(value = "/check-phone", method = RequestMethod.POST)
    public Response checkPhone(@RequestBody Map<String, String> params) throws Exception {
        return new Response().success(userService.checkPhone(params));
    }

    @RequestMapping(value = "/check-IDnumber", method = RequestMethod.POST)
    public Response checkIDnumber(@RequestBody Map<String, String> params) throws Exception {
        return new Response().success(userService.checkIDnumber(params));
    }

    @RequestMapping(value = "/update-userInfo", method = RequestMethod.POST)
    public Response updateUserInfo(@RequestBody Map<String, Object> params) {
        userService.updateUserInfo(params);
        return new Response().success();
    }

    @RequestMapping(value = "/get-all-userInfo", method = RequestMethod.GET)
    public Response getAllUserInfo() {
        return new Response().success(userService.getAllUserInfo());
    }

    @RequestMapping(value = "/get-all-userInfo-page", method = RequestMethod.POST)
    public Response getAllUserInfoPage(@RequestBody Map<String, String> params) {
        return new Response().success(userService.getAllUserInfoPage(params));
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public Response addUser(@RequestBody Map<String, Object> params) {
        userService.addUser(params);
        return new Response().success();
    }

    @RequestMapping(value = "/get-user-name-jobCode", method = RequestMethod.GET)
    public Response getUserNameJobCode() {
        return new Response().success(userService.getUserNameJobCode());
    }

    @RequestMapping(value = "/delete-user", method = RequestMethod.POST)
    public Response deleteUser(@RequestBody String staffId) {
        userService.deleteUser(staffId);
        return new Response().success();
    }

    @RequestMapping(value = "/get-user-by-jobCode", method = RequestMethod.POST)
    public Response getUserByJobCode(@RequestBody String jobCode) {
        return new Response().success(userService.getUserByJobCode(jobCode));
    }

    @RequestMapping(value = "/search-user-by-jobCodeOrName", method = RequestMethod.POST)
    public Response searchUserByJobCodeOrName(@RequestBody Map<String, Object> params) {
        return new Response().success(userService.searchUserByJobCodeOrName(params));
    }

    @RequestMapping(value = "/update-password", method = RequestMethod.POST)
    public Response updatePassword(@RequestBody Map<String, String> params) {
        userService.updatePassword(params);
        return new Response().success();
    }
}
