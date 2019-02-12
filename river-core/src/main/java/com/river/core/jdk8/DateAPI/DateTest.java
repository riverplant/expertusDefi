package com.river.core.jdk8.DateAPI;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;

/**
 * DateApi
 * 
 * @author riverplant
 *
 */
public class DateTest {
	public static void main(String[] args) {
		testLocalDate();
	}
	private static void testLocalDate() {
     LocalDate localDate = LocalDate.of(2019, 01, 26);
     System.out.println(localDate.getYear());
     System.out.println(localDate.getMonth());
     System.out.println(localDate.getDayOfMonth());
     System.out.println(localDate.getDayOfYear());
     System.out.println(localDate.getDayOfWeek());
     localDate.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
	}
	
	private static void testLocalTime() {
	     LocalTime localTime = LocalTime.now();
	     System.out.println(localTime.getHour());
	     System.out.println(localTime.getMinute());
	     System.out.println(localTime.getSecond());
		}
	/**
	 * 整合LocalDate and LocalTime
	 */
	private static void combineLocalDateAndTime() {
		LocalDate localDate = LocalDate.now();
	     LocalTime localTime = LocalTime.now();
	     LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
	     System.out.println(localDateTime.toString());
	     
	     Calendar calendar = Calendar.getInstance();
	     System.out.println(localDateTime.now());
	     
		}
	
	private static void testInstant() throws InterruptedException {
		Instant start = Instant.now();
		Thread.sleep(1000);
		Instant end = Instant.now();
	    Duration duration = Duration.between(start, end);
	    System.out.println(duration.toMinutes());
		}
	
	private static void testDuration(){
		LocalTime start = LocalTime.now();
		LocalTime beforeTime = start.minusHours(1);
	    Duration duration = Duration.between(start, beforeTime);
	    System.out.println(duration.toMinutes());
		}
	
	private static void testPeriod() {
		Period period = Period.between(LocalDate.of(2019, 1, 26), LocalDate.of(2019, 1, 26));
	    System.out.println(period.getMonths());//期间有多少个月
		}
	
	private static void testDateFormat() {
		LocalDate localDate = LocalDate.now();
		String format1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
		//String format2 = localDate.format(DateTimeFormatter.ISO_LOCAL_TIME);
	    System.out.println(format1);
	    //System.out.println(format2);
	    
	    DateTimeFormatter mySelfFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
	    String format = localDate.format(mySelfFormatter);
		}
	
	private static void testDateParse() {
		String date1 = "20190126";
		LocalDate localDate = LocalDate.parse(date1,DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println(localDate);
		DateTimeFormatter mydatatimeformater = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		String date2 = "2019-01-26";
		LocalDate localDate2 = LocalDate.parse(date2,mydatatimeformater);
		System.out.println(localDate2);
		}
}
