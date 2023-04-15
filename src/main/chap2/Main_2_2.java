package main.chap2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main_2_2 {

	public static void main(String[] args) {
		
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dataTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		
		System.out.println(dataTimeFormatter.format(localDateTime));
		
		ZonedDateTime japanDataTime = ZonedDateTime.of(2018, 1, 1, 9, 0, 0, 0, ZoneId.of("Asia/Tokyo"));
		ZonedDateTime ukDataTime = ZonedDateTime.of(2018, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
		System.out.println(ukDataTime.isEqual(japanDataTime));
		
		MonthDay yamanohi = MonthDay.of(8, 11);
		System.out.println(yamanohi);
		
		
		Duration duration = Duration.ofHours(2);
		System.out.println(localDateTime.plus(duration));
		System.out.println(localDateTime.minus(duration));
		
	    LocalDateTime localDateTime1 = LocalDateTime.of(2018, 1, 1, 0, 0, 0);
	    LocalDateTime localDateTime2 = LocalDateTime.of(2018, 1, 1, 3, 0, 0);
	 
	    // localDateTime1とlocalDateTime2の間隔を求める
	    Duration duration2 = Duration.between(localDateTime1, localDateTime2);
	    System.out.println(duration2);
		
		
	}

}
