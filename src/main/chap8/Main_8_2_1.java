package main.chap8;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class Main_8_2_1 {

	public static void main(String[] args) {
		ImmutablePair<String, String> tuple = getTuple();
		
		System.out.println(tuple.left);
		System.out.println(tuple.right);		
	}
	
	
	private static ImmutablePair<String, String> getTuple() {
		String left = "ひだり";
		String right = "みぎ";
		return ImmutablePair.of(left, right);
	}

}
