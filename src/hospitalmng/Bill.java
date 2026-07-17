package hospitalmng;

public class Bill {

    protected int billId;

    protected int patientId;

    protected double consultationFees;

    protected double medicineCharges;

    public Bill(int billId,
                int patientId,
                double consultationFees,
                double medicineCharges) {

        this.billId = billId;

        this.patientId = patientId;

        this.consultationFees = consultationFees;

        this.medicineCharges = medicineCharges;
    }

    public double calculateTotal() {

        return consultationFees + medicineCharges;
    }
}