package main.chap1;

public class Main_1_2{

	public static void main(String[] args) {
		String a = "CodeCamp";
		String b = "codecamp";
		
		//文字列同士の内容が同じか調べる
		if(a.equals(b)) {
			System.out.println("equals true");
		}else {
			System.out.println("equals false");
		}
		
		//大文字小文字を無視して、文字列同士の内容が同じが調べる
		if(a.equalsIgnoreCase(b)) {
			System.out.println("equalsIgnoreCase true");
		}else {
			System.out.println("equalsIgnoreCase false");			
		}
		
		//文字列の長さを調べる。
		System.out.println("aの長さは" + a.length());
		
		//空文字列か調べる
		a = "";
		//a = null;
		if(a.isEmpty()){
			System.out.println("isEmpty true");
		}else {
			System.out.println("isEmpty false");
		}
		
		String c = "JavaSctipt";
		
		//文字列の中に、引数で指定した文字列が含まれるか調べる
		if(c.contains("vaSc")) {
			System.out.println("contains true");
		}else {
			System.out.println("contains false");
		}
		
		//引数で指定した文字列で始まるか調べる。
		if(c.startsWith("Jav")) {
			System.out.println("startWith true");
		}else {
			System.out.println("startWith false");
		}
		
		//引数で指定した文字列が、先頭から何文字目に出力するか調べる
		String target = "a";
		System.out.println("[" + target + "]は、" + c.lastIndexOf(target) + "文字目に出現します。");
		
		String d = "Java応用";
		System.out.println(d.substring(0, 4));
		
		//文字の変換
		String e = "   AbcdE";
		System.out.println(e.toLowerCase());
		System.out.println(e.toUpperCase());
		System.out.println(e.trim());
		System.out.println(e.replace("bcd", "XXX"));
		
	}

}
