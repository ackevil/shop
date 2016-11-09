package com.huituopin.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

//import sun.misc.BASE64Encoder;

@Entity
public class dateToStrLong {
    // 将时间类型转化为字符串
    public static String dateFormat(Date date) {
        SimpleDateFormat fom = new SimpleDateFormat("yyyy-mm-dd");
        String dateString = fom.format(date);
        return dateString;
    }

    /**
     * 将时间转换成年月日时分秒
     * 
     * @param date
     * @return
     */
    public static String dateFormat1(Date date) {
        SimpleDateFormat fom = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String dateString = fom.format(date);
        return dateString;
    }

    /**
     * 获取星期几
     * 
     * @param date
     * @return
     */
    public static String dateWeek(Date date) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 动态转换日期格式
     * 
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static String dateFormat(String date, String format) throws ParseException {
        SimpleDateFormat fom = new SimpleDateFormat(format);
        return fom.format(fom.parse(date));
    }

    /**
     * 进行MD5加密
     * 
     * @param passWord
     * @return
     */
    // public static String md5Encoder(String passWord){
    // String encrypt ="";
    // try {
    // // MessageDigest md5 = MessageDigest.getInstance("MD5");
    // // BASE64Encoder baseEncoder = new BASE64Encoder();
    // // encrypt = baseEncoder.encode(md5.digest(passWord.getBytes("UTF-8")));
    // } catch (NoSuchAlgorithmException e) {
    // e.printStackTrace();
    // }
    // catch (UnsupportedEncodingException e) {
    // e.printStackTrace();
    // }
    // return encrypt;
    // }

    /**
     * 导出excel 2
     * 
     * @param listHead
     *            导出的字段名称
     * @param list
     *            导出的数据
     * @param xlsName
     *            导出的excel名字
     * @param response
     *            全局变量
     * @param sheetName
     *            excl表头名称
     * @throws Exception
     */
    public static void resultSetToExcel(List<Object> listHead, List<Object[]> list, String xlsName, String sheetName,
            HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        wb.setSheetName(0, sheetName);
        HSSFFont font = wb.createFont();
        font.setFontName("楷体");
        font.setFontHeight((short) 220);
        HSSFCellStyle cs2 = wb.createCellStyle();
        HSSFFont font2 = wb.createFont();
        font2.setFontName("宋体");// 设置字体
        font2.setFontHeightInPoints((short) 11);// 字体大小
        cs2.setFont(font2);
        cs2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
        cs2.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
        cs2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        cs2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        cs2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        cs2.setFillForegroundColor(HSSFColor.RED.index);// 设置背景色
        cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // 标题 样式
        HSSFRow rowheader = sheet.createRow(0);
        HSSFCell cellheader = rowheader.createCell((short) 0);
        // cellheader.setEncoding(HSSFCell.ENCODING_UTF_16);
        HSSFCellStyle cellstyleheader = wb.createCellStyle();// 样式
        HSSFFont font1 = wb.createFont();
        font1.setFontName("宋体");// 设置字体
        font1.setFontHeightInPoints((short) 18);// 字体大小
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        cellstyleheader.setFont(font1);
        cellstyleheader.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellstyleheader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 指定单元格垂直居中对齐
        cellstyleheader.setWrapText(true); // 指定单元格自动换行
        cellstyleheader.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellstyleheader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellstyleheader.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellstyleheader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellstyleheader.setFont(font1);
        cellheader.setCellStyle(cellstyleheader);
        cellheader = rowheader.createCell((short) 0);
        // cellheader.setEncoding(HSSFCell.ENCODING_UTF_16);
        cellheader.setCellValue(sheetName);
        cellheader.setCellStyle(cellstyleheader);
        sheet.addMergedRegion(new org.apache.poi.ss.util.Region(0, (short) 0, 1, (short) (listHead.size() - 1))); // 合并单元格
        sheet.setColumnWidth((short) 0, (short) 7000);

        // 表头样式
        HSSFCellStyle titlestyle = wb.createCellStyle();// 样式
        HSSFFont titlesfont = wb.createFont();
        titlesfont.setFontName("宋体");// 设置字体
        titlesfont.setFontHeightInPoints((short) 10);// 字体大小
        titlesfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        titlestyle.setFont(titlesfont);
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 指定单元格垂直居中对齐
        titlestyle.setWrapText(true); // 指定单元格自动换行
        titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

        // 表列 样式
        HSSFRow row = sheet.createRow(2);
        HSSFCell cell = row.createCell((short) 0);
        // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        HSSFCellStyle cellstyle = wb.createCellStyle();// 样式
        HSSFFont fonttable = wb.createFont();
        fonttable.setFontHeightInPoints((short) 10);// 字体大小
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 指定单元格居中对齐
        cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 指定单元格垂直居中对齐
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平对齐居中
        cellstyle.setWrapText(true); // 指定单元格自动换行
        cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cellstyle.setFont(fonttable);
        cell.setCellStyle(cellstyle);

        // 循环字段名称
        if (null != listHead && listHead.size() > 0) {
            for (int i = 0; i < listHead.size(); i++) {
                cell = row.createCell((short) i);
                // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(String.valueOf(listHead.get(i)));
                cell.setCellStyle(titlestyle);
                sheet.setColumnWidth((short) i, (short) 2000);
            }
        }
        // 循环字段信息
        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(i + 3);
                for (int j = 0; j < list.get(i).length; j++) {
                    cell = row.createCell((short) j);
                    // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    cell.setCellValue(parseString(list.get(i)[j]));
                    cell.setCellStyle(cellstyle);
                }
            }
        }
        String datenowFile = "" + xlsName + ".xls";
        try {

            // response.setContentType("application/x-download,charset=utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition",
                "attachment;filename=\"" + java.net.URLEncoder.encode(datenowFile, "UTF-8") + "\"");
        wb.write(response.getOutputStream());
    }

    private static String parseString(Object obj) {
        String value = "";
        try {
            value = String.valueOf(obj);
            if (value.equals("null")) {
                value = "";
            }
        } catch (Exception e) {
            value = "";
        }
        return value;
    }

    public static void resultEnterToExcel(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setCharacterEncoding("UTF-8");
        File tempPath = new File(request.getSession().getServletContext().getRealPath("/"));
        if (!tempPath.exists()) {
            return;
        } else {
            String fileName = "radioTree.xls";
            fileName = tempPath.toString() + "\\" + fileName;
            InputStream inp = new FileInputStream(fileName);
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            HSSFSheet sheet = wb.getSheetAt(0);
            for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext();) {
                HSSFRow row = (HSSFRow) rit.next();
                for (short index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                    @SuppressWarnings("unused")
                    HSSFCell cell = row.getCell(index);
                }
            }
        }

    }

    // public static void main(String[] args) throws IOException {
    // resultEnterToExcel();
    // }

    @SuppressWarnings("unused")
    public static int createTitle(HSSFWorkbook wb, HSSFSheet sheet, String titleName, int hangshu,
            List<Object[]> listhead) {
        // 标题 样式
        HSSFRow rowheader = sheet.createRow(hangshu);
        HSSFCell cellheader = rowheader.createCell((short) 0);
        // cellheader.setEncoding(HSSFCell.ENCODING_UTF_16);
        HSSFCellStyle cellstyleheader = wb.createCellStyle();// 样式
        HSSFFont font1 = wb.createFont();
        font1.setFontName("宋体");// 设置字体
        font1.setFontHeightInPoints((short) 18);// 字体大小
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        cellstyleheader.setFont(font1);
        cellstyleheader.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellstyleheader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 指定单元格垂直居中对齐
        cellstyleheader.setWrapText(true); // 指定单元格自动换行
        cellstyleheader.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellstyleheader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellstyleheader.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellstyleheader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellstyleheader.setFont(font1);
        cellheader.setCellStyle(cellstyleheader);
        cellheader = rowheader.createCell((short) 0);
        // cellheader.setEncoding(HSSFCell.ENCODING_UTF_16);
        cellheader.setCellValue(titleName);
        cellheader.setCellStyle(cellstyleheader);
        sheet.addMergedRegion(new org.apache.poi.ss.util.Region(0, (short) 0, 1, (short) (listhead.get(0).length - 1))); // 合并单元格
        sheet.setColumnWidth((short) 0, (short) 7000);
        hangshu = hangshu + 2;
        // 表头样式
        HSSFCellStyle titlestyle = wb.createCellStyle();// 样式
        HSSFFont titlesfont = wb.createFont();
        titlesfont.setFontName("宋体");// 设置字体
        titlesfont.setFontHeightInPoints((short) 10);// 字体大小
        titlesfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        titlestyle.setFont(titlesfont);
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 指定单元格垂直居中对齐
        titlestyle.setWrapText(true); // 指定单元格自动换行
        titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

        hangshu = createRow(wb, sheet, titlestyle, listhead, hangshu);

        return hangshu;
    }

    // 创建Row
    public static int createRow(HSSFWorkbook wb, HSSFSheet sheet, HSSFCellStyle titlestyle, List<Object[]> list,
            int hangshu) {
        // 表列 样式
        HSSFCellStyle cellstyle = wb.createCellStyle();// 样式
        HSSFFont fonttable = wb.createFont();
        fonttable.setFontHeightInPoints((short) 10);// 字体大小
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 指定单元格居中对齐
        cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 指定单元格垂直居中对齐
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 水平对齐居中
        cellstyle.setWrapText(true); // 指定单元格自动换行
        cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cellstyle.setFont(fonttable);

        HSSFRow row = null;
        HSSFCell cell = null;
        if (null != list && list.size() > 0) {
            // hangshu = hangshu+1;
            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(hangshu);
                for (int j = 0; j < list.get(i).length; j++) {
                    cell = row.createCell((short) j);
                    // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                    cell.setCellValue(parseString(list.get(i)[j]));
                    if (titlestyle == null) {
                        cell.setCellStyle(cellstyle);
                    } else {
                        cell.setCellStyle(titlestyle);
                    }
                }
                hangshu++;
            }
        }
        return hangshu;
    }

}
