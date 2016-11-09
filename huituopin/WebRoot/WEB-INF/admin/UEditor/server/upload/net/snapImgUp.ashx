<%@ WebHandler Language="C#" Class="snapImgUp" %>

using System;
using System.Web;
using System.IO;

public class snapImgUp : IHttpHandler
{

    public void ProcessRequest(HttpContext context)
    {
        context.Response.ContentType = "text/plain";
        context.Response.Charset = "utf-8";

        //上传配置
        String pathbase = "/UpLoad/";                                      //保存路径
        string[] filetype = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };          //文件允许格式
        int size = 1000;                                                          //文件大小限制，单位KB
        String pathlog = "log.txt";                                               //日志路径

        //文件上传状态,当成功时返回SUCCESS，其余值将直接返回对应字符窜并显示在图片预览框，同时可以在前端页面通过回调函数获取对应字符窜
        String state = "SUCCESS";

        String filename = String.Empty;
        String url = String.Empty;
        String currentType = String.Empty;
        String uploadpath = String.Empty;

        uploadpath = context.Server.MapPath(pathbase);

        try
        {
            HttpPostedFile uploadFile = context.Request.Files["upfile"];

            //目录验证
            if (!Directory.Exists(uploadpath))
            {
                Directory.CreateDirectory(uploadpath);
            }

            //格式验证
            string[] temp = uploadFile.FileName.Split('.');
            currentType = "." + temp[temp.Length - 1];
            if (Array.IndexOf(filetype, currentType) == -1)
            {
                state = "不支持的图片类型!";
            }

            //大小验证
            if (uploadFile.ContentLength / 1024 > size)
            {
                state = "图片大小超出限制!";
            }

            //保存图片
            if (state == "SUCCESS")
            {
                filename = DateTime.Now.ToString("yyyyMMddhhmmssfff") + currentType;
                uploadFile.SaveAs(uploadpath + filename);

                string a = context.Server.MapPath(pathlog);
                //写日志
                using (StreamWriter sw = File.AppendText(a))
                {
                    sw.WriteLine(DateTime.Now.ToString("yyyy-MM-dd hh:mm:ss") + "UPLOAD - " + context.Request.UserHostAddress + uploadFile.FileName + " " + uploadFile.ContentType);
                }

                url = pathbase + filename;
            }
        }
        catch (Exception)
        {
            state = "图片保存失败";
        }

        url = url.Replace("../", "");
        //向浏览器返回数据json数据
        HttpContext.Current.Response.Write("{'url':'" + url + "','state':'" + state + "'}");
    }

    public bool IsReusable
    {
        get
        {
            return false;
        }
    }

}