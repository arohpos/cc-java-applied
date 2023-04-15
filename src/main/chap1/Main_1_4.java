package main.chap1;

public class Main_1_4 {

	public static void main(String[] args) {
		String string = "CodeCamp";
		
		//target.matches(正規表現)
		System.out.println(string.matches("CodeCamp"));
		System.out.println(string.matches("CodeCampGATE"));
		System.out.println(string.matches("codecamp"));

		System.out.println("---");
		
		//一つ目のバックスラッシュはエスケープシークエンスのためのもの
		System.out.println("1".matches("\\d"));
		System.out.println("a".matches("\\w"));
		System.out.println("あ".matches("\\W"));
		System.out.println("Javあ".matches("Jav."));
		System.out.println("Jav".matches("Jav."));
		
		System.out.println("---");
		
		System.out.println("Javaaa".matches("Java*"));
		System.out.println("Java".matches("Java*"));
		System.out.println("Jav".matches("Java*"));
		System.out.println("Javaaaaa".matches("Java{5}"));
		System.out.println("03-1234-5678".matches("\\d{2}-\\d{4}-\\d{4}"));
		
		System.out.println("---");
		
		String testText = "Lorem ipsum dolor sit amet,aliquip malorum omnesque sea at.";
		String[] split = testText.split("\\W");
		for(String s : split) {
			System.out.println(s);
		}
		
		
	}

}
