import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

class MyUtils {
    public static String getDateAfterToday(int years, int months, int days) {
        Period period = Period.of(years, months, days);
        return LocalDate.now().plus(period).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
