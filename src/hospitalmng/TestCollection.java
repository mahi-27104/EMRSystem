package hospitalmng;

public class TestCollection {

    public static void main(String[] args) {

        PatientCollection pc =
                new PatientCollection();

        Patient p1 =
                new Patient(
                        101,
                        "Ravi",
                        25,
                        "Male",
                        "Fever",
                        9876543210L,
                        "Vijayawada"
                );

        Patient p2 =
                new Patient(
                        102,
                        "Suresh",
                        30,
                        "Male",
                        "Diabetes",
                        9123456780L,
                        "Hyderabad"
                );

        pc.addPatient(p1);

        pc.addPatient(p2);

        pc.displayPatients();

        pc.searchPatient(101);
    }
}