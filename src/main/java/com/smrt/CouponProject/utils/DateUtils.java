package com.smrt.CouponProject.utils;




import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class DateUtils {


    public static Date getStartDate() {
        return Date.from(Instant.now().minus((int) (Math.random() * 10), ChronoUnit.DAYS));
    }

    public static Date getEndDate() {

        return Date.from(Instant.now().plus((int) (Math.random() * 10), ChronoUnit.DAYS));
    }
}
