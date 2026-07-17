package hospitalmng;

public class AutoBackup
implements Runnable {

    @Override
    public void run() {

        while(true) {

            try {

                System.out.println(
                        "Backup Started...");

                ReportGenerator
                        .generatePatientReport();

                ReportGenerator
                        .generateBillingReport();

                System.out.println(
                        "Backup Completed");

                Thread.sleep(10000);

            } catch(Exception e) {

                e.printStackTrace();
            }
        }
    }
}