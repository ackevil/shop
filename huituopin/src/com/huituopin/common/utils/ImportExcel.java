package com.huituopin.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 2015.6.18
 * @author chaozhang
 * 导入excel表格
 */
public class ImportExcel {
//	public static void main(String[] args) {
//		ImportExcel e = new ImportExcel();
//		try {
//			e.readXls();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}

	  /**
	   * 通过传入参数导入excel
	   * @throws IOException
	   */
	  public static List<List<String>> readXls(String path) throws IOException{  
	    InputStream is = new FileInputStream(path);  
	    HSSFWorkbook hssfWorkbook = new HSSFWorkbook( is);  
	    Map<String,List<String>> excelMap = new HashMap<String, List<String>>();
	    List<List<String>> excelData = new ArrayList<List<String>>();//导出文件的数据
	    // 循环工作表Sheet  
	    for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){  
	      HSSFSheet hssfSheet = hssfWorkbook.getSheetAt( numSheet);  
	      if(hssfSheet == null){  
	        continue;  
	      }  
	      // 循环行Row   
	      for(int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++){ 
	    	  List<String> valueList = new ArrayList<String>();//获得每一条数据的值
	        HSSFRow hssfRow = hssfSheet.getRow( rowNum);  
	        if(hssfRow == null){  
	          continue;  
	        }  
	        // 循环列Cell    
	        for(int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++){  
	          HSSFCell hssfCell = hssfRow.getCell( (short) cellNum);  
	          if(hssfCell == null || "".equals(hssfCell)){  
	            continue;  
	          }else{
		        	 valueList.add(getValue( hssfCell));
	          }
	        }  
	        	  excelData.add(valueList);
	      } 
	    }  
	    return excelData;
	  }  
	    
	  @SuppressWarnings("static-access")  
	  private static String getValue(HSSFCell hssfCell){  
	    if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN){  
	      return String.valueOf( hssfCell.getBooleanCellValue());  
	    }else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC){  
	      return String.valueOf( hssfCell.getNumericCellValue());  
	    }else{  
	      return String.valueOf( hssfCell.getStringCellValue());  
	    }  
	  }  
	    
}
