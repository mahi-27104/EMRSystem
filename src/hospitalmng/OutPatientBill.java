package hospitalmng;

public class OutPatientBill extends Bill {

    public OutPatientBill(int billId,
                          int patientId,
                          double consultationFees,
                          double medicineCharges) {

        super(billId,
              patientId,
              consultationFees,
              medicineCharges);
    }
}