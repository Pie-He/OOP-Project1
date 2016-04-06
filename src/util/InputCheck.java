package util;

import java.util.regex.*;
//以下用正则表达式判断玩家输入是否符合要求
public class InputCheck {
	//判断输入是否为整数
	public static boolean inputCheckInteger(String input) {
		Pattern pattern = Pattern.compile("^[+-]?[0-9]+$");
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	//判断是否为1~6的整数
	public static boolean inputCheckIntRange(String input,int lb,int ub){
		StringBuffer index=new StringBuffer("^[");
		for(;lb<=ub;lb++){
			index.append(lb);
		}
		index.append("]$");
		Pattern pattern = Pattern.compile(index.toString());
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
	
	public static boolean inputCheckNotNull(String input){
		return input.trim().length()>0;
	}
	
	public static boolean check(String inputStr,String regular){
		Pattern pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(inputStr);
		return matcher.matches();
	}
}
