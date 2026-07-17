package hospitalmng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PatientCollection {

    ArrayList<Patient> patientList =
            new ArrayList<>();

    HashMap<Integer, Patient> patientMap =
            new HashMap<>();

    public void addPatient(Patient p) {

        patientList.add(p);

        patientMap.put(
                p.getPatientId(),
                p
        );
    }

    public void displayPatients() {

        Iterator<Patient> itr =
                patientList.iterator();

        while(itr.hasNext()) {

            Patient p = itr.next();

            System.out.println(
                    p.getPatientId()
                    + " "
                    + p.getName()
                    + " "
                    + p.getDisease()
            );
        }
    }

    public void searchPatient(int id) {

        if(patientMap.containsKey(id)) {

            Patient p =
                    patientMap.get(id);

            System.out.println(
                    "Patient Found");

            System.out.println(
                    p.getPatientId()
                    + " "
                    + p.getName()
                    + " "
                    + p.getDisease()
            );
        }

        else {

            System.out.println(
                    "Patient Not Found");
        }
    }
}