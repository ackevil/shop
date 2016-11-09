<%@ WebHandler Language="C#" Class="imageUp" %>

using System;
using System.Web;
using System.IO;
public class imageUp : IHttpHandler{
    public void ProcessRequest(HttpContext context){
        context.Response.ContentType = "text/plain";
        context.Response.Charset = "utf-8";

        //上传配置
        String pathbase = "/UpLoad/";                                      //保存路径
        string[] filetype = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };          //文件允许格式
        int size = 1024;                                                          //文件大小限制，单位KB

        //文件上传状态,初始默认成功，可选参数{"SUCCESS","ERROR","SIZE","TYPE"}
        String state = "SUCCESS";

        String title = String.Empty;
        String filename = String.Empty;
        String url = String.Empty;
        String currentType = String.Empty;
        String uploadpath = String.Empty;

        uploadpath = context.Server.MapPath(pathbase);

        try{
            HttpPostedFile uploadFile = context.Request.Files[0];
            title = uploadFile.FileName;

            //目录验证
            if (!Directory.Exists(uploadpath)){
                Directory.CreateDirectory(uploadpath);
            }

            //格式验证
            string[] temp=uploadFile.FileName.Split('.');
            currentType = "."+ temp[temp.Length - 1];
            if (Array.IndexOf(filetype, currentType)==-1){
                state = "TYPE";
            }

            //大小验证
            if( uploadFile.ContentLength/1024>size){
                state="SIZE";
            }

            //保存图片
            if (state=="SUCCESS"){
                filename = DateTime.Now.ToString("yyyyMMddhhmmssfff") + currentType;
                uploadFile.SaveAs(uploadpath + filename);
                url = pathbase + filename;
            }
        }catch (Exception){
            state = "ERROR";
        }

        //获取图片描述
        if (context.Request.Form["pictitle"] != null){
            if (!String.IsNullOrEmpty(context.Request.Form["pictitle"])){
                title = context.Request.Form["pictitle"];
            }
        }

        url = url.Replace("../", "");
        //向浏览器返回数据json数据
        HttpContext.Current.Response.Write("{'url':'" + url + "','title':'" + title + "','state':'" + state + "'}");
    }

    public bool IsReusable{
        get{
            return false;
        }
    }

}