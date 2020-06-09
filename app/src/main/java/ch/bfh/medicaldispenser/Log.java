package ch.bfh.medicaldispenser;

public class Log {

    private String date;
    private String time;
    private String medName;
    private String intake;

    public Log (String date, String time, String medName,String intake) {
        this.date = date;
        this.time = time;
        this.medName = medName;
        this.intake = intake;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getIntake() {
        return intake;
    }

    public String getMedName() {
        return medName;
    }



}
