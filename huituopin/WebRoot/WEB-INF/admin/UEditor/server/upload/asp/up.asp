<!--#include FILE="upload.inc"-->
<%
    dim upload,file,formName,title,state,picSize,picType,uploadPath

    uploadPath = "../uploadfiles/"       '上传保存路径
    picSize = 500                        '允许的文件大小，单位KB
    picType = ".jpg,.gif,.png,.bmp"      '允许的图片格式
    
    '文件上传状态,当成功时返回SUCCESS，其余值将直接返回对应字符窜并显示在图片预览框，同时可以在前端页面通过回调函数获取对应字符窜
    state="SUCCESS"
    
    set upload=new upload_5xSoft ''''建立上传对象
    title=upload.form("pictitle")

    for each formName in upload.file
        set file=upload.file(formName)

        '大小验证
        if file.filesize > picSize*1024 then
            state="图片大小超出限制"
        end if

        '格式验证
        if instr(picType, mid(file.FileName, instrrev(file.FileName,".")))=0 then
            state = "图片类型错误"
        end If

        '保存图片
        if state="SUCCESS" then
            file.SaveAs Server.mappath( uploadPath & file.FileName) ''''保存文件
        end if
        
        '返回数据
        response.Write "{'url':'" & uploadPath & file.FileName &"','title':'"& title &"','state':'"& state &"'}"
        set file=nothing

    next
    set upload=nothing
%>