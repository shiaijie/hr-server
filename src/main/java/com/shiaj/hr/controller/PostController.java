package com.shiaj.hr.controller;

import com.shiaj.hr.bean.Response;
import com.shiaj.hr.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>
 * 岗位信息管理表 前端控制器
 * </p>
 */
@Controller
@ResponseBody
@RequestMapping("/post")
public class PostController {
    private final IPostService postService;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public Response getAll() throws Exception {
        return new Response().success(postService.getAll());
    }

    @RequestMapping(value = "/get-post-by-id", method = RequestMethod.POST)
    public Response postDetail(@RequestBody String postId) throws Exception {
        return new Response().success(postService.getPostById(postId));
    }

    @RequestMapping(value = "/delete-post", method = RequestMethod.POST)
    public Response deletePost(@RequestBody String postId) throws Exception {
        postService.deletePost(postId);
        return new Response().success();
    }

    @RequestMapping(value = "/update-post", method = RequestMethod.POST)
    public Response updatePost(@RequestBody Map<String, Object> postInfo) throws Exception {
        postService.updatePost(postInfo);
        return new Response().success();
    }

    @RequestMapping(value = "/add-post", method = RequestMethod.POST)
    public Response addPost(@RequestBody Map<String, Object> postInfo) throws Exception {
        postService.addPost(postInfo);
        return new Response().success();
    }

    @RequestMapping(value = "/check-post", method = RequestMethod.POST)
    public Response checkPost(@RequestBody String postName) throws Exception {
        return new Response().success(postService.checkPost(postName));
    }

    @RequestMapping(value = "/delete-by-parent", method = RequestMethod.POST)
    public Response deleteByParent(@RequestBody String departId) throws Exception {
        postService.deleteByParent(departId);
        return new Response().success();
    }

    @RequestMapping(value = "/get-post-by-parent", method = RequestMethod.POST)
    public Response getPostByParent(@RequestBody String departId) throws Exception {
        return new Response().success(postService.getPostByParent(departId));
    }

    @RequestMapping(value = "/search-post", method = RequestMethod.POST)
    public Response searchPost(@RequestBody String postName) throws Exception {
        return new Response().success(postService.searchPost(postName));
    }
}
