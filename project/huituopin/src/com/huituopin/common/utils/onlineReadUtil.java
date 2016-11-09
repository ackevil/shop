package com.huituopin.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;

@Entity
public class onlineReadUtil {
	

//
//	public static class FlashPaper extends Thread {
//		
//	
//	public static int convertPDF2SWF(String swfName,String documentName,HttpServletRequest request ) throws IOException {
//		
//		String fileSuffix = "......."; //文件的前缀，即"test"
//
//		String fileType = "........."; //文件类型，即"doc"
//
//		String fileRealPath = "..........."; //文件的实际路径
//
//		String flashFilePath = "......."; //临时存放生成的swf文件路径
//
//		String pdfFilePath = "......"; //临时存放生成的pdf文件路径
//
//		//创建test.doc的swf文件，即test.swf
//
//		File flashFile = new File(flashFilePath + fileSuffix + "swf");
//
//		//如果test.swf文件不存在
//
//		if(!flashFile.exists()){
//
//		//1.如果文件为pdf格式，则只需将文件移动到/pdfTemp路径下，不需要openoffice转换了
//		//2.如果文件为其他格式，则需先将文件的格式转化为pdf格式
//
//		File sourceFile = new File(fileRealPath + fileSuffix +"." + fileType);
//
//		if(fileType.equals("pdf")){
//
//		//如果为pdf文件，则将其复制到临时存放生成的pdf文件路径
//
//		sourceFile.renameTo(new File(pdfFilePath, sourceFile.getName()));
//
//		}else{
//
//		File pdfFile = new File(pdfFilePath + fileSuffix + "." + "pdf");
//
//		//使用openoffice的API接口转化为pdf文件（大家可以网上下载jodconverter-2.2.2.zip以及例子程序，网上挺多的，这里由于空间有限，不能为大家上传，非常抱歉！）
//
//		JOD4DocToPDF.docToPdf(sourceFile, pdfFile);
//
//		}
//
//		try {
//
//		//将pdf文件转化为swf文件(第一个参数为swf文件的名字，第二个参数为pdf文件的名字，详细可参见下方的代码)
//		FlashPaper.convertPDF2SWF(fileSuffix, fileSuffix, request);
//		} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		}
//
//		}
//		
//		
//		
//	String pdfPath = request.getSession().getServletContext().getRealPath(pdfFilePath+documentName+".pdf");
//	String swfPath = request.getSession().getServletContext().getRealPath(flashFilePath+swfName+".swf");
//	////目标路径不存在则建立目标路径
//	File dest = new File(request.getSession().getServletContext().getRealPath("/swfTemp"));
//	if (!dest.exists()) dest.mkdirs();
//
//	//源文件不存在则返回
//	File source = new File(pdfPath);
//	if (!source.exists()) return 0;
//
//	//调用pdf2swf命令进行转换(安装swftools路径为 D盘根目录)
//	String command= "D:/Program Files/SWFTools/pdf2swf.exe -t \""+pdfPath+"\" -o \""+swfPath+"\" -s flashversion=9 -s languagedir=c://xpdftest//xpdf-chinese-simplified";
//	System.out.println("cmd:"+command);
//	// Process pro = Runtime.getRuntime().exec(command);
//	Process process = Runtime.getRuntime().exec(command); // 调用外部程序 
//	final InputStream is1 = process.getInputStream(); 
//	new Thread(new Runnable() { 
//	public void run() { 
//	BufferedReader br = new BufferedReader(new InputStreamReader(is1)); 
//	try {
//	while(br.readLine()!= null) ;
//	} catch (IOException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//	} 
//	} 
//	}).start(); // 启动单独的线程来清空process.getInputStream()的缓冲区 
//	InputStream is2 = process.getErrorStream(); 
//	BufferedReader br2 = new BufferedReader(new InputStreamReader(is2)); 
//	StringBuilder buf = new StringBuilder(); // 保存输出结果流 
//	String line = null; 
//	while((line = br2.readLine()) != null) buf.append(line); // 循环等待ffmpeg进程结束 
//	System.out.println("输出结果为：" + buf);
//
//	while (br2.readLine() != null); 
//
//	try {
//	process.waitFor();
//	} catch (InterruptedException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//	}
//
//	return process.exitValue();
//
//
//	}
//	}
//	
}
