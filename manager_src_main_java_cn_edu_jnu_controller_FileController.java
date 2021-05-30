package cn.edu.jnu.controller;

import cn.edu.jnu.service.SelectService;
import cn.edu.jnu.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Controller
public class FileController {

    @Autowired
    private SelectService selectService;
//
//    @Autowired
//    private UpdateService updateService;

    @RequestMapping(value = "/downFile", method = RequestMethod.GET)
    @ResponseBody
    public String downloadFile(@RequestParam("fileId") String fileId,
                               HttpServletRequest request, HttpServletResponse response) {
        //下载文件
        //获取username

        Integer userId = selectService.selectUserIdByFileId(Integer.parseInt(fileId));
        String fileName = selectService.selectFileNameByFileId(Integer.parseInt(fileId));
        String filePath = "/Users/sunhao/Documents/Files1/" + userId + "/";
        String fileDest = filePath + fileName;//fileDest为最终需要下载的文件，在服务器的绝对路径+文件名
        File file = new File(fileDest);
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/x-download");//设置文件强制下载
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        byte[] bytes = new byte[1024];
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            System.out.println(file.getName());
            fileInputStream = new FileInputStream(file);//获取文件的输入流
            bufferedInputStream = new BufferedInputStream(fileInputStream);//获得缓冲输入流
            OutputStream os = response.getOutputStream();//输出流
            int i = bufferedInputStream.read(bytes);
            while (i != -1) {
                os.write(bytes, 0, i);//写文件
                i = bufferedInputStream.read(bytes);//i去获取字节
            }
            //管理员下载文件，文件下载量不加一
            //updateService.updateCurrentFileDownloadNumber(fileName, userId);//下载量加一，根据用户id和文件名确定哪一个文件下载量加一
            return "下载成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "读取文件错误";
        } finally {//关闭资源
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
