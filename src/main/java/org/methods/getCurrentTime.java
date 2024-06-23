package org.methods;

import java.time.LocalDateTime;

public class getCurrentTime {

    public void setTime(int i) {
        LocalDateTime locaDate = LocalDateTime.now();
        int hours = locaDate.getHour();
        int minutes = locaDate.getMinute();
        int seconds = locaDate.getSecond();
        String formattedTime = String.format("[%02d:%02d:%02d]", hours, minutes, seconds);
        System.out.println(formattedTime + " Realizando Fila N°: " + i);
    }
}
