package ch.bfh.medicaldispenser;

public class Medicament {

    int pharmaCode;
    String medName;
    String composition;
    String therapy;
    String indication;
    String dosis;
    String contraIndication;

    public Medicament(int pharmaCode, String medName, String composition, String therapy, String indication, String dosis, String contraIndication) {
        this.pharmaCode = pharmaCode;
        this.medName = medName;
        this.composition = composition;
        this.therapy = therapy;
        this.indication = indication;
        this.dosis = dosis;
        this.contraIndication = contraIndication;
    }

    public int getPharmaCode() {
        return pharmaCode;
    }

    public String getMedName() {
        return medName;
    }

    public String getComposition() {
        return composition;
    }

    public String getTherapy() {
        return therapy;
    }

    public String getIndication() {
        return indication;
    }

    public String getDosis() {
        return dosis;
    }

    public String getContraIndication() {
        return contraIndication;
    }
}
