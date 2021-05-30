package cn.edu.jnu.controller;

import cn.edu.jnu.entity.*;
import cn.edu.jnu.service.SelectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义管理员操作的控制器
 */
@Controller
public class AdminController {

    @Autowired
    private SelectService selectService;

    @RequestMapping("index")
    public String adminLoginPage() {
        return "index";
    }

//    @RequestMapping("de_index")
//    @ResponseBody
//    public String deAdminLoginPage(){
//        return "index";
//    }

    @RequestMapping("admin")
    public String admin(HttpServletRequest request, Model model) {
        String adminName = (String) request.getSession().getAttribute("adminName");
        model.addAttribute("adminName", adminName);
        return "admin";
    }

    @RequestMapping("ajaxAdminLogin")
    @ResponseBody
    public String adminLogin(@RequestParam("adminName") String adminName,
                             @RequestParam("password") String password,
                             HttpServletRequest request) {
        String passwordExact = selectService.selectAdminPasswordByName(adminName);
        if (passwordExact == null) {
            return "无此管理员";
        } else {
            if (!passwordExact.equals(password)) {
                return "密码错误";
            } else {
                //向session中添加adminName
                request.getSession().setAttribute("adminName", adminName);
                return "登录成功";
            }
        }
    }

    @RequestMapping("first-page")
    public String firstPage() {
        return "first-page";
    }

    /**
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("manage-user")
    public String manageUser(Model model, HttpServletRequest request,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        String adminName = (String) request.getSession().getAttribute("adminName");
        //查询所有用户列表
        Integer adminId = selectService.selectAdminIdByAdminName(adminName);
        PageHelper.startPage(pageNum, 12);// pageNum:当前页码数，第一次进来时默认为1（首页）
        List<User> userList = selectService.selectAllUsers();
        PageInfo<User> pageInfo = new PageInfo<>(userList);//pageInfo:将分页数据和显示的数据封装到PageInfo当中
        model.addAttribute("userList",userList);
        model.addAttribute("adminId",adminId);
        model.addAttribute("pageInfo",pageInfo);
        return "manage-user";
    }

    @RequestMapping("user-request")
    public String userRequest(Model model, HttpServletRequest request,
                              @RequestParam(defaultValue = "1") Integer pageNum) {
        String adminName = (String) request.getSession().getAttribute("adminName");
        Integer adminId = selectService.selectAdminIdByAdminName(adminName);
        PageHelper.startPage(pageNum, 12);// pageNum:当前页码数，第一次进来时默认为1（首页）
        //第一类请求，请求注销账号
        List<TempRequest> requests1 = selectService.selectNotAvailableUsers();
        //第二类请求，请求宣传活动
        List<TempRequest> requests2 = selectService.selectNotPermitActions();

        //将需要处理的请求放入requestlist,
        //list:页面要展示的数据的集合，request1为注销请求
        List<TempRequest> requestList = new ArrayList<>(requests1);
        //request2为宣传请求,拼接完为最终请求列表
        requestList.addAll(requests2);
        PageInfo<TempRequest> pageInfo = new PageInfo<>(requestList);//pageInfo:将分页数据和显示的数据封装到PageInfo当中
        model.addAttribute("pageInfo", pageInfo);//将封装好的数据返回到前台页面
        model.addAttribute("requestList", requestList);
        model.addAttribute("adminId",adminId);
        return "user-request";
    }
}
