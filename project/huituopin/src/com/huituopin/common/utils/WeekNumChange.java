package com.huituopin.common.utils;

import javax.persistence.Entity;
/**
 * 对周数进行转换
 * */
@Entity
public class WeekNumChange {
		public static String change(String num){
			String str="";
			int len=0;
			if(num.indexOf("第")!=-1&&num.indexOf("周")!=-1){
				len=5;
			}else if((num.indexOf("第")==-1&&num.indexOf("周")!=-1)||(num.indexOf("第")!=-1&&num.indexOf("周")==-1)){
				len=4;
			}
			if(num.equals("十周")||num.equals("第十周")||num.equals("十")){
				str=num.replace("十", "10");
			}else{
				if(num.length()<len){
					str=num.replace("一", "1").replace("二", "2").replace("三", "3").replace("四", "4").replace("五", "5").replace("六", "6").replace("七", "7").replace("八", "8").replace("九", "9")
							.replace("十", "0");
				}else{
					str=num.replace("一", "1").replace("二", "2").replace("三", "3").replace("四", "4").replace("五", "5").replace("六", "6").replace("七", "7").replace("八", "8").replace("九", "9")
							.replace("十", "");
				}
				
			}
			
			return str;
		}
		public static void main(String[] args) {
			String s=change("十周");
			String s0=change("二周");
			String s7=change("第十周");
			String s1=change("第三十周");
			String s4=change("三十周");
			String s2=change("第三十二周");
			String s3=change("三十二周");
			String ss="二十八周";
			String s5="十八";
			String s6="十";
			System.out.println(ss.length()+","+s5.length()+","+s6.length());
			System.out.println(s+","+s1+","+s2+","+s4+","+s7+","+s3+","+s0);
		}
}
