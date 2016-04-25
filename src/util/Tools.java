package util;

public class Tools {
	public static String stringCover(int length, String str) {
		StringBuilder sb = new StringBuilder(str);
		for (; sb.length() < length; sb.append(" "))
			;
		return sb.toString();
	}

	public static String stringCover(int length, int a) {
		StringBuilder sb = new StringBuilder(String.valueOf(a));
		for (; sb.length() < length; sb.append(" "))
			;
		return sb.toString();
	}

	public static String stringCover(int length, String... strs) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			StringBuilder tmp = new StringBuilder(strs[i]);
			for (; tmp.length() < length; tmp.append(" "))
				;
			sb.append(tmp);
		}
		return sb.toString();

	}
}
