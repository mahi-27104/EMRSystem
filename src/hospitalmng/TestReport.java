package hospitalmng;

public class TestReport {

    public static void main(String[] args) {

        ReportGenerator.generatePatientReport();

        ReportGenerator.generateBillingReport();
    }
}