package com.revivalmodding.revivalcore.util.extra;

import java.time.LocalDate;

public class AprilFools {
    public static String NOTE = "Hey you there!, You opened this file. Please do not spoil anything here!!";
    
    public static boolean isAprilFools() {
    	LocalDate date = LocalDate.now();
    	return date.getDayOfMonth() == 1 && date.getMonthValue() == 4;
    }
}
