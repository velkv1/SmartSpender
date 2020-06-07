package ch.bfh.medicaldispenser;

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

}
