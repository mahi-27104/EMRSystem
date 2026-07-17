package hospitalmng;


public class Patient {

    private int patientId;

    private String name;

    private int age;

    private String gender;

    private String disease;

    private long mobile;

    private String address;

    public Patient(int patientId,
                   String name,
                   int age,
                   String gender,
                   String disease,
                   long mobile,
                   String address) {

        this.patientId = patientId;

        this.name = name;

        this.age = age;

        this.gender = gender;

        this.disease = disease;

        this.mobile = mobile;

        this.address = address;
    }

    public int getPatientId() {

        return patientId;
    }

    public String getName() {

        return name;
    }

    public int getAge() {

        return age;
    }

    public String getGender() {

        return gender;
    }

    public String getDisease() {

        return disease;
    }

    public long getMobile() {

        return mobile;
    }

    public String getAddress() {

        return address;
    }
}
