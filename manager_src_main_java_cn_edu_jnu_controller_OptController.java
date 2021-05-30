package cn.edu.jnu.controller;

import cn.edu.jnu.entity.Article;
import cn.edu.jnu.entity.File;
import cn.edu.jnu.entity.OptRecord;
import cn.edu.jnu.service.DeleteService;
import cn.edu.jnu.service.SelectService;
import cn.edu.jnu.service.UpdateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class OptController {

    @Autowired
    private SelectService selectService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

    @RequestMapping("record")
    public String record(Model model, HttpServletRequest request,
                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "optTime",defaultValue = "noLimit") String optTime) {

        String adminName = (String) request.getSession().getAttribute("adminName");
        Integer adminId = selectService.selectAdminIdByAdminName(adminName);
        PageHelper.startPage(pageNum, 12);// pageNum:当前页码数，第一次进来时默认为1（首页）
        List<OptRecord> optRecordList = new ArrayList<>();
        //判断用户传来的时间条件
        if(optTime.equals("noLimit")){
            optRecordList = selectService.selectRecordByAdminId(adminId);
        }else {
            //按用户时间查找,当天
            optRecordList = selectService.selectRecordByAdminIdAndTime(adminId, optTime);
        }
        PageInfo<OptRecord> pageInfo = new PageInfo<>(optRecordList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("optRecordList", optRecordList);
        model.addAttribute("adminId",adminId);
        return "record-search";
    }
    //评论
    @RequestMapping("user-info")
    public String userinfo(Model model, @RequestParam(defaultValue = "1") String pageNum,
                           @RequestParam(value = "userId", defaultValue = "1") Integer userId) {
        PageHelper.startPage(Integer.parseInt(pageNum), 20);
        //查询该id的用户信息
        //查询用户名，用户id，用户评论，用户文章，用户文件
        //用户名
        List<String> commentList = selectService.selectUserCommentsByUserId(userId);//list:页面要展示的数据的集合
        PageInfo<String> pageInfo = new PageInfo<>(commentList);//pageInfo:将分页数据和显示的数据封装到PageInfo当中

        model.addAttribute("pageInfo", pageInfo);//将封装好的数据返回到前台页面
        model.addAttribute("userId",userId);
        model.addAttribute("commentList", commentList);
        return "user-info";
    }

    @RequestMapping("de_user-info")
    @ResponseBody
    public JSONObject deUserinfo(Model model, @RequestParam(defaultValue = "1") String pageNum,
                           @RequestParam(value = "userId", defaultValue = "1") Integer userId) {
        PageHelper.startPage(Integer.parseInt(pageNum), 20);
        //查询该id的用户信息
        //查询用户名，用户id，用户评论，用户文章，用户文件
        //用户名
        List<String> commentList = selectService.selectUserCommentsByUserId(userId);//list:页面要展示的数据的集合
        PageInfo<String> pageInfo = new PageInfo<>(commentList);//pageInfo:将分页数据和显示的数据封装到PageInfo当中

        model.addAttribute("pageInfo", pageInfo);//将封装好的数据返回到前台页面
        model.addAttribute("userId",userId);
        model.addAttribute("commentList", commentList);
        JSONObject json = new JSONObject();
        json.put("pageInfo", pageInfo);
        json.put("userId",userId);
        json.put("commentList", commentList);
        return json;
    }

    //文章
    @RequestMapping("user-info2")
    public String userInfo2(Model model, @RequestParam(defaultValue = "1") String pageNum,
                            @RequestParam(value = "userId", defaultValue = "1") Integer userId){
        PageHelper.startPage(Integer.parseInt(pageNum), 5);
        //查询该id的用户信息
        //查询用户名，用户id，用户评论，用户文章，用户文件
        //用户名
        List<Article> articleList = selectService.selectUserArticlesByUserId(userId);//list:页面要展示的数据的集合
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);//pageInfo:将分页数据和显示的数据封装到PageInfo当中
        model.addAttribute("pageInfo", pageInfo);//将封装好的数据返回到前台页面
        model.addAttribute("userId",userId);
        model.addAttribute("articleList", articleList);
        return "user-info2";
    }
    @RequestMapping("de_user-info2")
    @ResponseBody
    public JSONObject deUserInfo2(Model model, @RequestParam(defaultValue = "1") String pageNum,
                            @RequestParam(value = "userId", defaultValue = "1") Integer userId){
        PageHelper.startPage(Integer.parseInt(pageNum), 5);
        //查询该id的用户信息
        //查询用户名，用户id，用户评论，用户文章，用户文件
        //用户名
        List<Article> articleList = selectService.selectUserArticlesByUserId(userId);//list:页面要展示的数据的集合
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);//pageInfo:将分页数据和显示的数据封装到PageInfo当中
//        model.addAttribute("pageInfo", pageInfo);//将封装好的数据返回到前台页面
//        model.addAttribute("userId",userId);
//        model.addAttribute("articleList", articleList);
        JSONObject json = new JSONObject();
        json.put("pageInfo", pageInfo);
        json.put("userId",userId);
        json.put("articleList", articleList);
        return json;
    }
    //文件
    @RequestMapping("user-info3")
    public String userInfo3(Model model, @RequestParam(defaultValue = "1") String pageNum,
                            @RequestParam(value = "userId", defaultValue = "1") Integer userId){
        PageHelper.startPage(Integer.parseInt(pageNum), 15);
        //查询该id的用户信息
        //查询用户名，用户id，用户评论，用户文章，用户文件
        //用户名
        List<File> fileList = selectService.selectUserFilesByUserId(userId);//list:页面要展示的数据的集合
        PageInfo<File> pageInfo = new PageInfo<>(fileList);//pageInfo:将分页数据和显示的数据封装到PageInfo当中
        model.addAttribute("pageInfo", pageInfo);//将封装好的数据返回到前台页面
        model.addAttribute("userId",userId);
        model.addAttribute("fileList", fileList);
        return "user-info3";
    }
    @RequestMapping("de_user-info3")
    @ResponseBody
    public JSONObject deUserInfo3(Model model, @RequestParam(defaultValue = "1") String pageNum,
                            @RequestParam(value = "userId", defaultValue = "1") Integer userId){
        PageHelper.startPage(Integer.parseInt(pageNum), 15);
        //查询该id的用户信息
        //查询用户名，用户id，用户评论，用户文章，用户文件
        //用户名
        List<File> fileList = selectService.selectUserFilesByUserId(userId);//list:页面要展示的数据的集合
        PageInfo<File> pageInfo = new PageInfo<>(fileList);//pageInfo:将分页数据和显示的数据封装到PageInfo当中
//        model.addAttribute("pageInfo", pageInfo);//将封装好的数据返回到前台页面
//        model.addAttribute("userId",userId);
//        model.addAttribute("fileList", fileList);
        JSONObject json = new JSONObject();
        json.put("pageInfo", pageInfo);
        json.put("userId",userId);
        json.put("fileList", fileList);
        return json;
    }

    @PostMapping("banUser")
    @ResponseBody
    public String banUser(HttpServletRequest request,
                          @RequestParam("userId") String userId){
        String adminName = (String) request.getSession().getAttribute("adminName");
        Integer adminId = selectService.selectAdminIdByAdminName(adminName);
        String username = selectService.selectUsernameById(Integer.valueOf(userId));
        Integer userStat = selectService.selectUserIsAvailableById(userId);
        if (userStat==-1){
            return "用户已处于禁用状态！";
        }else if (userStat == 0){
            return "该用户已注销！";
        }
        //禁用用户，将用户可用状态改成-1
        boolean isSuccess = updateService.banUser(Integer.valueOf(userId));
        //记录管理员操作
        String opt = "管理员"+adminName+"禁用了用户"+username;
        if (isSuccess){
            Timer timer = new Timer();
            //管理员不主动解禁，会在三十天内解禁
            long t= 30L *24*60*60*1000;
//            long t=10*1000;测试成功
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    updateService.releaseUser(Integer.valueOf(userId));
                }
            }, t);
            updateService.recordOpt(opt,adminId);
            return "成功禁用该用户！";
        }else {
            return "禁用用户失败！";
        }
    }

    @PostMapping("limitUser")
    @ResponseBody
    public String limitUser(HttpServletRequest request,
                            @RequestParam("userId") Integer userId){
        String adminName = (String) request.getSession().getAttribute("adminName");
        Integer adminId = selectService.selectAdminIdByAdminName(adminName);
        String username = selectService.selectUsernameById(userId);
        //解禁用户，将用户状态设置为1
        boolean isSuccess = updateService.limitUser(userId);
        if (isSuccess){
            Timer timer = new Timer();
            //管理员不主动解禁，会在十天后自动解禁
            long t=10*24*60*60*1000;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    updateService.releaseUser(userId);
                }
            }, t);
            String opt = "管理员"+adminName+"将用户"+username+"限制使用，该用户只能查看无法发言";
            updateService.recordOpt(opt,adminId);
            //十天后自动解禁
            return "成功限制该用户！";
        }else {
            return "限制用户失败！";
        }
    }
    @PostMapping("releaseUser")
    @ResponseBody
    public String releaseUser(HttpServletRequest request,
                              @RequestParam("userId") String userId){
        String adminName = (String) request.getSession().getAttribute("adminName");
        Integer adminId = selectService.selectAdminIdByAdminName(adminName);
        String username = selectService.selectUsernameById(Integer.valueOf(userId));
        //获取用户状态
        Integer userStat = selectService.selectUserIsAvailableById(userId);
        if (userStat==1){
            return "用户已处于解禁状态！";
        }else if (userStat == 0){
            return "该用户已注销！";
        }
        //解禁用户，将用户状态设置为1
        boolean isSuccess = updateService.releaseUser(Integer.valueOf(userId));
        String opt = "管理员"+adminName+"解禁了用户"+username;
        if (isSuccess){
            updateService.recordOpt(opt,adminId);
            return "成功解禁该用户！";
        }else {
            return "解禁用户失败！";
        }
    }

    @PostMapping("refuseRequest")
    @ResponseBody
    public String refuseRequest(HttpServletRequest request,
                                @RequestParam("userId") String userId,
                                @RequestParam("requestDetail") String requestDetail){
        String adminName = (String) request.getSession().getAttribute("adminName");
        Integer adminId = selectService.selectAdminIdByAdminName(adminName);
        String username = selectService.selectUsernameById(Integer.valueOf(userId));
        String requestType = requestDetail.substring(requestDetail.length()-4);
        System.out.println(requestType);
        boolean isSuccess;
        if (requestType.equals("请求注销")){
            //如果请求类型是注销账号，执行相关操作,消除账号
            isSuccess = updateService.updateUserInfo(Integer.valueOf(userId));
            String opt = "管理员"+adminName+"审查用户"+username+"信息后，拒绝了该用户的注销请求，";
            if (isSuccess){
                updateService.recordOpt(opt,adminId);
                return "已拒绝用户注销请求！";
            }else{
                return "拒绝用户注销请求失败！";
            }
        }else {
            //如果请求类型是宣传活动，执行相关操作，修改宣传许可号码
            //多请求怎么办,根据后缀，但用户不能提交注销账号为结尾的活动申请
            isSuccess = deleteService.deleteAction(Integer.valueOf(userId));
            String opt = "管理员"+adminName+"审查用户"+username+"的信息后，拒绝该用户的宣传活动请求";
            if (isSuccess){
                updateService.recordOpt(opt,adminId);
                return "已拒绝该活动！";
            }else{
                return "拒绝该活动失败，请刷新重试！";
            }
        }
    }
    @PostMapping("permitRequest")
    @ResponseBody
    public String permitRequest(HttpServletRequest request,
                                @RequestParam("userId") String userId,
                                @RequestParam("requestDetail") String requestDetail){
        String adminName = (String) request.getSession().getAttribute("adminName");
        Integer adminId = selectService.selectAdminIdByAdminName(adminName);
        String username = selectService.selectUsernameById(Integer.valueOf(userId));
        String requestType = requestDetail.substring(requestDetail.length()-4);
        boolean isSuccess;
        if (requestType.equals("请求注销")){
            //如果请求类型是注销账号，执行相关操作,消除账号
            isSuccess = deleteService.deleteUserByUserId(userId);
            String opt = "管理员"+adminName+"审查通过了用户"+username+"，并同意了该用户的注销请求，";
            if (isSuccess){
                updateService.recordOpt(opt,adminId);
                return "成功删除用户！";
            }else{
                return "删除用户失败！";
            }
        }else {
            //如果请求类型是宣传活动，执行相关操作，修改宣传许可号码
            //多请求怎么办,根据后缀，但用户不能提交注销账号为结尾的活动申请
            isSuccess = updateService.permitAction(Integer.valueOf(userId));
            String opt = "管理员"+adminName+"审查用户"+username+"的信息通过，并同意了该用户的宣传活动请求";
            if (isSuccess){
                updateService.recordOpt(opt,adminId);
                return "活动审核成功！";
            }else{
                return "活动审核失败！";
            }
        }
    }

    @PostMapping("deleteComment")
    @ResponseBody
    public String deleteComment(@RequestParam("comment") String comment,HttpServletRequest request){
        String adminName = (String) request.getSession().getAttribute("adminName");
        Integer adminId = selectService.selectAdminIdByAdminName(adminName);
        boolean isSuccess = deleteService.deleteComment(comment);
        String opt = "管理员"+adminName +"删除了评论内容为："+"\""+comment+"\"的评论";
        if (isSuccess){
            updateService.recordOpt(opt,adminId);
            return "删除评论成功";
        }else {
            return "评论不存在或删除失败";
        }
    }

    @GetMapping("deleteArticle")
    @ResponseBody
    public String deleteArticle(@RequestParam("articleId") Integer articleId,HttpServletRequest request){
        String adminName = (String) request.getSession().getAttribute("adminName");
        Integer adminId = selectService.selectAdminIdByAdminName(adminName);
        boolean isSuccess = deleteService.deleteArticle(articleId);
        String opt = "管理员"+adminName +"删除了代号为"+articleId+"的长文/提问！";
        if(isSuccess){
            updateService.recordOpt(opt,adminId);
            return "删除文章成功";
        }else {
            return "文章不存在或删除失败";
        }
    }

    @GetMapping("deleteFile")
    @ResponseBody
    public String deleteFile(@RequestParam("fileId") Integer fileId,HttpServletRequest request){
        String adminName = (String) request.getSession().getAttribute("adminName");
        Integer adminId = selectService.selectAdminIdByAdminName(adminName);
        boolean isSuccess = deleteService.deleteFile(fileId);
        String opt = "管理员"+adminName +"删除了代号为"+fileId+"的文件！";
        if(isSuccess){
            updateService.recordOpt(opt,adminId);
            return "删除文件成功";
        }else {
            return "文件信息不存在或删除失败";
        }
    }

}
