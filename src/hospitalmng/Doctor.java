package hospitalmng;

	public class Doctor {

	    private int doctorId;

	    private String doctorName;

	    private String specialization;

	    private double fees;

	    private long mobile;

	    public Doctor(int doctorId,
	                  String doctorName,
	                  String specialization,
	                  double fees,
	                  long mobile) {

	        this.doctorId = doctorId;

	        this.doctorName = doctorName;

	        this.specialization = specialization;

	        this.fees = fees;

	        this.mobile = mobile;
	    }

	    public int getDoctorId() {

	        return doctorId;
	    }

	    public String getDoctorName() {

	        return doctorName;
	    }

	    public String getSpecialization() {

	        return specialization;
	    }

	    public double getFees() {

	        return fees;
	    }

	    public long getMobile() {

	        return mobile;
	    }
	}

