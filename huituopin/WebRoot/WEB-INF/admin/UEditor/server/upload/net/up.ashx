<%@ WebHandler Language="C#" Class="up" %>

using System;
using System.Web;
using System.IO;
public class up : IHttpHandler
{
    String uploadPath = "Pictures/uploadFiles/"; //保存路径    
    String fileType = ".jpg,.jpeg,.gif,.png,.bmp"; //文件允许格式    
    Int32 fileSize = 1000; //文件大小限制，单位KB   

    public void ProcessRequest(HttpContext context)
    {
        context.Response.ContentType = "text/plain";
        HttpPostedFile oFile = context.Request.Files[0];
        //获取文件的扩展名   
        string FullPath = "";
        string state = "SUCCESS";
        string fileExtension = System.IO.Path.GetExtension(oFile.FileName).ToLower();
        if (fileType.ToLower().IndexOf(fileExtension) > -1)
        {
            if (fileSize * 1024 >= oFile.ContentLength)
            {
                string DirectoryPath;
                DirectoryPath = uploadPath + DateTime.Now.ToString("yyyy-MM");
                string sFileName = DateTime.Now.ToString("yyyyMMddHHmmssffff"); //文件名称   
                FullPath = "~/" + DirectoryPath + "/" + sFileName + fileExtension;//最终文件路   
                string AbsolutePath = context.Server.MapPath(FullPath);
                if (!Directory.Exists(context.Server.MapPath("~/" + DirectoryPath)))
                    Directory.CreateDirectory(context.Server.MapPath("~/" + DirectoryPath));
                try
                {
                    oFile.SaveAs(AbsolutePath);
                }
                catch (Exception e)
                {
                    state = "上传失败!";
                }
            }
            else
            {
                state = "上传文件大小超过限制!";
            }
        }
        else
        {
            state = "上传文件扩展名是不允许的扩展名!";
        }
        context.Response.Write("{'url':'" + FullPath.Replace("~", "") + "','state':'" + state + "'}");

    }

    public bool IsReusable
    {
        get
        {
            return false;
        }
    }

}