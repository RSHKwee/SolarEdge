package Zandbak;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
	public static void main(String[] args) {
		LocalDateTime l_date = LocalDateTime.now();
		LocalDate loc_Date = l_date.toLocalDate();

		String pattern = "yyyy-MM-dd HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

		String s_date = l_date.format(formatter);
		System.out.println(s_date);

		LocalDateTime l_enddate = LocalDateTime.of(2018, 11, 23, 1, 1);
		LocalDate loc_EndDate = l_enddate.toLocalDate();

		Period p = Period.between(loc_EndDate, loc_Date);
		System.out.println("#jaar: " + p.getYears() + " #maanden: " + p.getMonths() + " #dagen: " + p.getDays());

		DateTimeFormatter l_formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		formatter = formatter.withLocale(Locale.GERMANY); // Locale specifies human language for translating, and cultural
		                                                  // norms for lowercase/uppercase and abbreviations and such.
		                                                  // Example: Locale.US or Locale.CANADA_FRENCH
		LocalDate date = LocalDate.parse("2005-nov-12", l_formatter);

	}
}
