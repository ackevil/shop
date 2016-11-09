using System;
using System.Web;
using System.IO;
using System.Collections;


/// <summary>
/// UEditor编辑器通用上传类
/// </summary>
public  class Uploader
{
     string state = "SUCCESS";

     string _url;
     string _currentType;
     string _uploadpath;
     string _filename;
     string _originalName;
     HttpPostedFile _uploadFile;

    /**
  * 上传文件的主处理方法
  * @param HttpContext
  * @param string
  * @param  string[]
  *@param int
  * @return Hashtable
  */
    public  Hashtable upFile(HttpContext cxt, string pathbase, string[] filetype, int size)
    {
        pathbase = pathbase + DateTime.Now.ToString("yyyy-MM-dd") + "/";
        _uploadpath = cxt.Server.MapPath(pathbase);//获取文件上传路径

        try
        {
            _uploadFile = cxt.Request.Files[0];
            _originalName = _uploadFile.FileName;

            //目录创建
            CreateFolder();

            //格式验证
            if (checkType(filetype))
            {
                state = "不允许的文件类型";
            }
            //大小验证
            if (CheckSize(size))
            {
                state = "文件大小超出网站限制";
            }
            //保存图片
            if (state == "SUCCESS")
            {
                _filename = reName();
                _uploadFile.SaveAs(_uploadpath + _filename);
                _url = pathbase + _filename;
            }
        }
        catch (Exception)
        {
            state = "未知错误";
            _url = "";
        }
        return getUploadInfo();
    }

    /**
 * 上传涂鸦的主处理方法
  * @param HttpContext
  * @param string
  * @param  string[]
  *@param string
  * @return Hashtable
 */
    public  Hashtable upScrawl(HttpContext cxt, string pathbase, string tmppath, string base64Data)
    {
        pathbase = pathbase + DateTime.Now.ToString("yyyy-MM-dd") + "/";
        _uploadpath = cxt.Server.MapPath(pathbase);//获取文件上传路径
        FileStream fs = null;
        try
        {
            //创建目录
            CreateFolder();
            //生成图片
            _filename = Guid.NewGuid() + ".png";
            fs = File.Create(_uploadpath + _filename);
            byte[] bytes = Convert.FromBase64String(base64Data);
            fs.Write(bytes, 0, bytes.Length);

            _url = pathbase + _filename;
        }
        catch (Exception)
        {
            state = "未知错误";
            _url = "";
        }
        finally
        {
            fs.Close();
            DeleteFolder(cxt.Server.MapPath(tmppath));
        }
        return getUploadInfo();
    }

    /**
* 获取文件信息
* @param context
* @param string
* @return string
*/
    public  string getOtherInfo(HttpContext cxt, string field)
    {
        string info = null;
        if (cxt.Request.Form[field] != null && !String.IsNullOrEmpty(cxt.Request.Form[field]))
        {
            info = field == "fileName" ? cxt.Request.Form[field].Split(',')[1] : cxt.Request.Form[field];
        }
        return info;
    }

    /**
     * 获取上传信息
     * @return Hashtable
     */
    private  Hashtable getUploadInfo()
    {
        Hashtable infoList = new Hashtable();

        infoList.Add("state", state);
        infoList.Add("url", _url);

        if (_currentType != null)
            infoList.Add("currentType", _currentType);
        if (_originalName != null)
            infoList.Add("originalName", _originalName);
        return infoList;
    }

    /**
     * 重命名文件
     * @return string
     */
    private  string reName()
    {
        return System.Guid.NewGuid() + GetFileExt();
    }

    /**
     * 文件类型检测
     * @return bool
     */
    private  bool checkType(string[] filetype)
    {
        _currentType = GetFileExt();
        return Array.IndexOf(filetype, _currentType) == -1;
    }

    /**
     * 文件大小检测
     * @param int
     * @return bool
     */
    private  bool CheckSize(int size)
    {
        return _uploadFile.ContentLength >= (size * 1024*1024);
    }

    /**
     * 获取文件扩展名
     * @return string
     */
    private  string GetFileExt()
    {
        string[] temp = _uploadFile.FileName.Split('.');
        return "." + temp[temp.Length - 1].ToLower();
    }

    /**
     * 按照日期自动创建存储文件夹
     */
    private  void CreateFolder()
    {
        if (!Directory.Exists(_uploadpath))
        {
            Directory.CreateDirectory(_uploadpath);
        }
    }

    /**
     * 删除存储文件夹
     * @param string
     */
    public  void DeleteFolder(string path)
    {
        //if (Directory.Exists(path))
        //{
        //    Directory.Delete(path, true);
        //}
    }
}