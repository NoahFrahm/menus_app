import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class getDate {
    public static void main(String[] args) {
//        2021-10-04
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }
}
