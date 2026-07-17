package hospitalmng;

public class Validator {

    public static void validatePatient(
            String name,
            int age,
            long mobile)
            throws InvalidDataException {

        if(name.trim().isEmpty()) {

            throw new InvalidDataException(
                    "Name cannot be empty");
        }

        if(age <= 0) {

            throw new InvalidDataException(
                    "Invalid age");
        }

        String mob =
                String.valueOf(mobile);

        if(mob.length() != 10) {

            throw new InvalidDataException(
                    "Mobile number must be 10 digits");
        }
    }

    public static void validateDoctorFees(
            double fees)
            throws InvalidDataException {

        if(fees <= 0) {

            throw new InvalidDataException(
                    "Fees must be greater than zero");
        }
    }
}