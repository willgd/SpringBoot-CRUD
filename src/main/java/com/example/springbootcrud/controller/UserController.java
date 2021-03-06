package com.example.springbootcrud.controller;

import com.example.springbootcrud.pojo.*;
import com.example.springbootcrud.serviceImpl.UserServiceImpl;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserLogin userLogin;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    MsgInfo msgInfo;
    @Autowired
    UserInfo userInfo;

    /**
     * 登录处理
     * @param request
     */
    @RequestMapping(value = {"/login"},method = {RequestMethod.POST,RequestMethod.GET})
    public String login(HttpServletRequest request){
        Map<String, String[]> loginUser = request.getParameterMap();
        HttpSession session = request.getSession();
        try {
            BeanUtils.populate(userLogin, loginUser);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        UserLogin user = userService.login(userLogin);
        if(user!=null){
            User activeUser = userService.getUserByUserId(user.getUserId());
            userInfo.setUser(activeUser);
            userInfo.setUserLogin(user);
            userInfo.setCode(1);
            msgInfo.setSuccessMsg("欢迎");
            msgInfo.setExist(1);
            session.setAttribute("msg",msgInfo);
            session.setAttribute("userInfo",userInfo);
            return "redirect:/user";
        }else {
            msgInfo.setErrorMsg("用户名不存在或密码错误！");
            msgInfo.setExist(0);
            session.setAttribute("msg",msgInfo);
            return "login";
        }

    }

    /**
     * 退出登录功能
     * @param session
     * @return
     */
    @RequestMapping("/exitLogin")
    public String exitLogin(HttpSession session) {
        session.removeAttribute("userInfo");
        msgInfo.setExist(0);
        msgInfo.setErrorMsg("请先登录!");
        session.setAttribute("msg",msgInfo);
        System.out.println("退出成功");
        log.info("exit:  msgInfo={}",msgInfo);
        return "login";
    }

    /**
     * 展示管理员数据
     * @param request
     * @return
     */
    @RequestMapping("/admin")
    public String toTest(HttpServletRequest request){
        List<Admin> adminList = userService.getAllAdmin();
        request.setAttribute("adminList",adminList);
        return "admin";
    }

    /**
     * 展示用户数据
     * @param
     * @return
     */
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String toUser(HttpServletRequest request,Model model, @RequestParam(value = "pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "5")int pageSize){
        if(request.getSession().getAttribute("userInfo") !=null) {
            PageInfo<User> pageInfo = userService.getAllUser(pageNum, pageSize);
            model.addAttribute("pageInfo", pageInfo);
            List<User> userList = pageInfo.getList();
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            String s = operations.get("/user");
            log.info("访问次数:={}", s);
            model.addAttribute("userCount", s);
            return "User";
        }else {
            msgInfo.setExist(0);
            msgInfo.setErrorMsg("请先登录！");
            request.getSession().setAttribute("msg",msgInfo);
            return "login";
        }
    }


    /**
     * 查看用户详细信息，跳转到更新界面
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/user/{userId}",method = RequestMethod.GET)
    public String findUser(@PathVariable("userId")int userId, Model model){
        User user = userService.getUserByUserId(userId);
        log.info("user={}",user);
        model.addAttribute("user",user);
        return "update";
    }

    /**
     * 修改用户信息
     * @param user
     * @param handImg
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String update(User user,@RequestPart("handImg") MultipartFile handImg) throws IOException {
        if (!handImg.isEmpty()){
            String Path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/img/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String substring = Path.substring(Path.lastIndexOf("/")+1);
            log.info("avatar:avatar={}",substring);
            user.setAvatar("img/"+substring);
            log.info("avatar：Path={}",Path);
            handImg.transferTo(new File(Path));
            userService.updateUserByUserId(user);
        }else {
            log.info("user.getAvatar()={}",user.getAvatar());
            userService.updateUserByUserId(user);
        }
        return "redirect:/user";
    }

    /**
     * 添加新用户
     * @param user
     * @param handImg
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String add(User user,@RequestPart("handImg") MultipartFile handImg) throws IOException {
        if (!handImg.isEmpty()){
            String filename = handImg.getOriginalFilename();
            String Path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/img/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + filename;
            String substring = Path.substring(Path.lastIndexOf("/")+1);
            user.setAvatar("img/"+substring);
            handImg.transferTo(new File(Path));
            userService.insertUser(user);
        } else {

        }

        return "redirect:/user";
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/{userId}",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("userId") int userId){
        userService.deleteUserByUserId(userId);
        System.out.println("删除用户："+userId);
        return "redirect:/user";
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(){
        return "add";
    }
}
