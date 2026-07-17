package hospitalmng;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReportGenerator {

    public static void generatePatientReport() {

        try {

            Connection con =
                    hmsDBConnection.getConnection();

            String query =
                    "SELECT * FROM patients";

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(query);

            FileWriter fw =
                    new FileWriter("patients_report.txt");

            BufferedWriter bw =
                    new BufferedWriter(fw);

            bw.write(
                    "===== PATIENT REPORT =====");

            bw.newLine();

            bw.newLine();

            while(rs.next()) {

                bw.write(
                        "Patient ID : "
                        + rs.getInt("patientid"));

                bw.newLine();

                bw.write(
                        "Name : "
                        + rs.getString("name"));

                bw.newLine();

                bw.write(
                        "Age : "
                        + rs.getInt("age"));

                bw.newLine();

                bw.write(
                        "Gender : "
                        + rs.getString("gender"));

                bw.newLine();

                bw.write(
                        "Disease : "
                        + rs.getString("disease"));

                bw.newLine();

                bw.write(
                        "Mobile : "
                        + rs.getLong("mobile"));

                bw.newLine();

                bw.write(
                        "Address : "
                        + rs.getString("address"));

                bw.newLine();

                bw.write(
                        "------------------------");

                bw.newLine();
            }

            bw.close();

            fw.close();

            System.out.println(
                    "Patient Report Generated");

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public static void generateBillingReport() {

        try {

            Connection con =
                    hmsDBConnection.getConnection();

            String query =
                    "SELECT * FROM bills";

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(query);

            FileWriter fw =
                    new FileWriter("billing_report.txt");

            BufferedWriter bw =
                    new BufferedWriter(fw);

            bw.write(
                    "===== BILLING REPORT =====");

            bw.newLine();

            bw.newLine();

            while(rs.next()) {

                bw.write(
                        "Bill ID : "
                        + rs.getInt("billid"));

                bw.newLine();

                bw.write(
                        "Patient ID : "
                        + rs.getInt("patientid"));

                bw.newLine();

                bw.write(
                        "Consultation Fees : "
                        + rs.getDouble("consultationfees"));

                bw.newLine();

                bw.write(
                        "Medicine Charges : "
                        + rs.getDouble("medicinecharges"));

                bw.newLine();

                bw.write(
                        "Room Charges : "
                        + rs.getDouble("roomcharges"));

                bw.newLine();

                bw.write(
                        "Total Amount : "
                        + rs.getDouble("totalamount"));

                bw.newLine();

                bw.write(
                        "-------------------------");

                bw.newLine();
            }

            bw.close();

            fw.close();

            System.out.println(
                    "Billing Report Generated");

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}