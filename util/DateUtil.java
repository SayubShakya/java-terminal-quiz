package util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long timeDifference = date2.getTime() - date1.getTime();
        return timeUnit.convert(timeDifference, TimeUnit.MILLISECONDS);
    }
}
