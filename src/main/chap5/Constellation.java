package main.chap5;

import java.time.MonthDay;

public enum Constellation {
	
	ARIES("牡羊座", MonthDay.of(3, 21), MonthDay.of(4, 19)),
	TAURUS("牡牛座", MonthDay.of(4, 20), MonthDay.of(5, 20)),	
	GEMINI("双子座", MonthDay.of(5, 21), MonthDay.of(6, 21)),	
	CANCER("蟹座", MonthDay.of(6, 22), MonthDay.of(7, 22)),	
	LEO("獅子座", MonthDay.of(7, 23), MonthDay.of(8, 22)),	
	VIRGO("乙女座", MonthDay.of(8, 23), MonthDay.of(9, 22)),	
	LIBRA("天秤座", MonthDay.of(9, 23), MonthDay.of(10, 23)),	
	SCORPIO("蠍座", MonthDay.of(10, 24), MonthDay.of(11, 22)),	
	SAGITTARIUS("射手座", MonthDay.of(11, 23), MonthDay.of(12, 21)),	
	CAPRICORN("山羊座", MonthDay.of(12, 22), MonthDay.of(1, 19)),	
	AQUARIUS("水瓶座", MonthDay.of(1, 20), MonthDay.of(2, 18)),	
	PISCES("魚座", MonthDay.of(2, 19), MonthDay.of(3, 20));
	
	private String name;
	private MonthDay startDate;
	private MonthDay endDate;
	
	private Constellation(String name, MonthDay startDate, MonthDay endDate) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;		
	}

	//入力年月に対して、該当する星座名を返す
	static public String getType(int month, int day) {
		MonthDay baseMonthDay = MonthDay.of(month, day);
		//山羊座の期間は年をまたぐため、個別の条件が必要である
		if(baseMonthDay.compareTo(Constellation.CAPRICORN.startDate) >= 0
				|| Constellation.CAPRICORN.endDate.compareTo(baseMonthDay) >= 0) {
			return Constellation.CAPRICORN.getName();
		}else {
			Constellation[] constellations = Constellation.values();
			for(Constellation constellation : constellations) {
				if(baseMonthDay.compareTo(constellation.getStartDate()) >= 0
						&& constellation.getEndDate().compareTo(baseMonthDay) >= 0){
					return constellation.getName();
				}
			}
		}
		//コンパイルエラー回避のためのreturn。本来処理はここまでこない。
		return "error";
	}

	protected String getName() {
		return name;
	}

	protected MonthDay getStartDate() {
		return startDate;
	}

	protected MonthDay getEndDate() {
		return endDate;
	}
}
