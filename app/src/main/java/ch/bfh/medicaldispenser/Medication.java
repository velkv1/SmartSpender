package ch.bfh.medicaldispenser;

public class Medication {

    private String name;
    private String description;
    private String takingTime;

    public Medication (String name, String description, String takingTime) {
        this.name = name;
        this.description = description;
        this.takingTime = takingTime;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTakingTime() {
        return this.takingTime;
    }

}
