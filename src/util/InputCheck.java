package util;

import java.util.regex.*;
//������������ʽ�ж���������Ƿ����Ҫ��
public class InputCheck {
	//�ж������Ƿ�Ϊ����
	public static boolean inputCheckInteger(String input) {
		Pattern pattern = Pattern.compile("^[+-]?[0-9]+$");
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	//�ж��Ƿ�Ϊ1~6������
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
