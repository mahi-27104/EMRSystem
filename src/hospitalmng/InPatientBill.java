package hospitalmng;

public class InPatientBill extends Bill {

    private double roomCharges;

    public InPatientBill(int billId,
                         int patientId,
                         double consultationFees,
                         double medicineCharges,
                         double roomCharges) {

        super(billId,
              patientId,
              consultationFees,
              medicineCharges);

        this.roomCharges = roomCharges;
    }

    @Override
    public double calculateTotal() {

        return consultationFees
                + medicineCharges
                + roomCharges;
    }
}