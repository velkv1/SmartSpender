package ch.bfh.medicaldispenser.ui.PlanErfassen;

public class CHMEDHandler {

    private String compressedPlan;
    private String b64Data;


    public CHMEDHandler(String compressedPlan) {
        this.compressedPlan = compressedPlan;
        b64Data = compressedPlan.substring(9);
    }

    public void parseCHMED() {

    }


}
