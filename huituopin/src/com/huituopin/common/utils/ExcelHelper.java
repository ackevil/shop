package com.huituopin.common.utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
  
/**    
 * 操作Excel表格的功能类    
 * @author：   
 * @version    
 */ 
public class ExcelHelper {  
	
    
        private POIFSFileSystem fs;      
        private HSSFWorkbook wb;      
        private HSSFSheet sheet;      
        private HSSFRow row;      
        /**    
         * 读取Excel表格表头的内容    
         * @param InputStream    
         * @return String 表头内容的数组    
         *     
         */     
        public String[] readExcelTitle(InputStream is) {      
            try {      
                fs = new POIFSFileSystem(is);      
                wb = new HSSFWorkbook(fs);      
            } catch (IOException e) {      
                e.printStackTrace();      
            }      
            sheet = wb.getSheetAt(0); 
//            for(int i =0 ;i<sheet.getLastRowNum() i++){
            row = sheet.getRow(1);      
            //标题总列数      
            int colNum = row.getPhysicalNumberOfCells();      
            String[] title = new String[colNum];      
            for (int i=0; i<colNum; i++) {      
                title[i] = getTitleValue(row.getCell((short) i));      
            }      
            return title;      
        }      
   
              
        /**    
         * 获取单元格数据内容为字符串类型的数据    
         * @param cell Excel单元格    
         * @return String 单元格数据内容，若为字符串的要加单引号    
         */     
        public String getStringCellValue(HSSFCell cell) {      
            String strCell = "";      
            switch (cell.getCellType()) {      
            case HSSFCell.CELL_TYPE_STRING:      
                strCell = "'" + cell.getStringCellValue() + "'";      
                break;      
            case HSSFCell.CELL_TYPE_NUMERIC:      
                   strCell = String.valueOf(cell.getNumericCellValue());     
                break;      
            case HSSFCell.CELL_TYPE_BOOLEAN:      
                strCell = String.valueOf(cell.getBooleanCellValue());      
                break;      
            case HSSFCell.CELL_TYPE_BLANK:      
                strCell = "''";      
                break;     
            default:      
                strCell = "''";      
                break;      
            }      
            if (strCell.equals("''") || strCell == null) {      
                return "";      
            }      
            if (cell == null) {      
                return "";      
            }      
            return strCell;      
        }      
          
        public String getTitleValue(HSSFCell cell) {      
            String strCell =  cell.getStringCellValue();      
            return strCell;      
        }    
        
        public static void main(String[] args) {
        	String path="C:/Users/yutu/Downloads/企业基本信息 (4).xls";
        	  InputStream is;
			try {
				is = new FileInputStream(path);
				 ExcelHelper excelReader = new ExcelHelper();  
	              String[] colName = excelReader.readExcelTitle(is); 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
    	}
              
  
//    /** 
//     * Excel 2003 
//     */  
//    private final static String XLS = "xls";  
//    /** 
//     * Excel 2007 
//     */  
//    private final static String XLSX = "xlsx";  
//    /** 
//     * 分隔符 
//     */  
//    private final static String SEPARATOR = "|";  
//  
//    /** 
//     * 由Excel文件的Sheet导出至List 
//     *  
//     * @param file 
//     * @param sheetNum 
//     * @return 
//     */  
//    public static List<String> exportListFromExcel(File file, int sheetNum)  
//            throws IOException {  
//        return exportListFromExcel(new FileInputStream(file),  
//                FilenameUtils.getExtension(file.getName()), sheetNum);  
//    }  
//  
//    /** 
//     * 由Excel流的Sheet导出至List 
//     *  
//     * @param is 
//     * @param extensionName 
//     * @param sheetNum 
//     * @return 
//     * @throws IOException 
//     */  
//    public static List<String> exportListFromExcel(InputStream is,  
//            String extensionName, int sheetNum) throws IOException {  
//  
//        Workbook workbook = null;  
//  
//        if (extensionName.toLowerCase().equals(XLS)) {  
//            workbook =  new HSSFWorkbook(is);  
//        } else if (extensionName.toLowerCase().equals(XLSX)) {  
//            workbook = new XSSFWorkbook(is);  
//        }  
//        return exportListFromExcel(workbook, sheetNum);  
//    }  
//  
//    /** 
//     * 由指定的Sheet导出至List 
//     *  
//     * @param workbook 
//     * @param sheetNum 
//     * @return 
//     * @throws IOException 
//     */  
//    private static List<String> exportListFromExcel(Workbook workbook,  
//            int sheetNum) {  
//  
//        Sheet sheet = workbook.getSheetAt(sheetNum);  
//  
//        // 解析公式结果  
//        FormulaEvaluator evaluator = workbook.getCreationHelper()  
//                .createFormulaEvaluator();  
//  
//        List<String> list = new ArrayList<String>();  
//  
//        int minRowIx = sheet.getFirstRowNum();  
//        int maxRowIx = sheet.getLastRowNum();  
//        for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {  
//            Row row = sheet.getRow(rowIx);  
//            StringBuilder sb = new StringBuilder();  
//  
//            short minColIx = row.getFirstCellNum();  
//            short maxColIx = row.getLastCellNum();  
//            for (short colIx = minColIx; colIx <= maxColIx; colIx++) {  
//                Cell cell = row.getCell(new Integer(colIx));  
//                CellValue cellValue = evaluator.evaluate(cell);  
//                if (cellValue == null) {  
//                    continue;  
//                }  
//                // 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了  
//                // 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html  
//                switch (cellValue.getCellType()) {  
//                case Cell.CELL_TYPE_BOOLEAN:  
//                    sb.append(SEPARATOR + cellValue.getBooleanValue());  
//                    break;  
//                case Cell.CELL_TYPE_NUMERIC:  
//                    // 这里的日期类型会被转换为数字类型，需要判别后区分处理  
////                    if (DateUtil.isCellDateFormatted(cell)) {  
////                        sb.append(SEPARATOR + cell.getDateCellValue());  
////                    } else {  
//                        sb.append(SEPARATOR + cellValue.getNumberValue());  
////                    }  
//                    break;  
//                case Cell.CELL_TYPE_STRING:  
//                    sb.append(SEPARATOR + cellValue.getStringValue());  
//                    break;  
//                case Cell.CELL_TYPE_FORMULA:  
//                    break;  
//                case Cell.CELL_TYPE_BLANK:  
//                    break;  
//                case Cell.CELL_TYPE_ERROR:  
//                    break;  
//                default:  
//                    break;  
//                }  
//            }  
//            list.add(sb.toString());  
//        }  
//        return list;  
//    }  
//    
//    public static void main(String[] args) {
//    	String path="C:/Users/yutu/Downloads/企业基本信息 (4).xls";
//    	try {
//			exportListFromExcel(new File(path),0);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
 }  
