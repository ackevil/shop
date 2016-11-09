package com.huituopin.activity.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class CommonController {
   
	@RequestMapping("/uploadify")
    @ResponseBody
    public String uploadify(@RequestParam("Filedata")MultipartFile filedata ,HttpServletRequest request) {
        //文件保存目录路径
        //文件保存目录URL
		String ROOT =request.getSession().getServletContext().getRealPath(System.getProperty("file.separator")); 
        String saveUrl = ROOT+"resource/upload"+System.getProperty("file.separator");
        String relativeUrl="/resource/upload/";
        
//定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        //extMap.put("flash", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
       // extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
       // extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

//最大文件大小
        long maxSize = 1000000;

//检查目录
        File uploadDir = new File(saveUrl);
        if (!uploadDir.exists()) {
            //return (getError("上传目录不存在。"));
        	uploadDir.mkdirs();
        }
//检查目录写权限
        if (!uploadDir.canWrite()) {
            return (getError("上传目录没有写权限。"));
        }
        String dirName = "image";
        if (!extMap.containsKey(dirName)) {
            return (getError("目录名不正确。"));
        }
//创建文件夹
        
        saveUrl += dirName + System.getProperty("file.separator");
        relativeUrl+=dirName + "/";
        File saveDirFile = new File(saveUrl);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        saveUrl += ymd + System.getProperty("file.separator");
        relativeUrl+= ymd + "/";
        File dirFile = new File(saveUrl);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        String fileName = filedata.getOriginalFilename();
        //检查扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
            return (getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
        }

        String newFileName1 = null;//小图
        String newFileName2 = null;//中图
        String newFileName3 = null;//大图 ，也是原图
        String newFileName0 = String.valueOf(System.currentTimeMillis());
        newFileName1 = newFileName0 + "_1";
        newFileName2 = newFileName0 + "_2";
        newFileName3 = newFileName0 + "_3." + fileExt;

        File uploadedFile3 = new File(saveUrl, newFileName3);
        try {
            filedata.transferTo(uploadedFile3);
        } catch (Exception e) {
            return (getError("上传文件失败。"));
        }

        JSONObject obj = new JSONObject();
        obj.put("error", 0);
        obj.put("filePath", relativeUrl + newFileName3);
        return (obj.toString());
    }

    private String getError(String msg) {

        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("msg", "upload error !");
        return (obj.toString());
    }
}

