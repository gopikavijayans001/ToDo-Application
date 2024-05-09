package com.project.todoapplication.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CommonUtils {

    public static String getCurrentTime(){
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String currentTime= formatter.format(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
        return currentTime;
    }
}
