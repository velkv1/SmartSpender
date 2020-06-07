package ch.bfh.medicaldispenser;

import java.util.Calendar;

public class Medication {

    private int pharmacode;
    private String name;
    private String reason;
    private String takingTime;

    public Medication (int pharmacode, String name, String reason, String takingTime) {
        this.pharmacode = pharmacode;
        this.name = name;
        this.reason = reason;
        this.takingTime = takingTime;
    }

    public String getName() {
        return this.name;
    }

    public String getReason() {
        return this.reason;
    }

    public String getTakingTime() {
        return this.takingTime;
    }


    public Calendar getTime(){
        int time = 0;
        switch (takingTime) {
            case "Morgen":
                time = 9;
                break;
            case "Mittag":
                time = 12;
                break;
            case "Abend":
                time = 18;
                break;
            case "Nacht":
                time = 21;
                break;
        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, time);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }
}
