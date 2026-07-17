package hospitalmng;

import javax.swing.*;
import java.awt.event.*;

public class Dashboard extends JFrame
implements ActionListener {

    JLabel title;

    JButton patientBtn,
            doctorBtn,
            appointmentBtn,
            billingBtn,
            logoutBtn,
            patientReportBtn,
            billingReportBtn;

    public Dashboard() {

        title = new JLabel(
                "HOSPITAL MANAGEMENT SYSTEM");

        title.setBounds(120,30,300,40);

        add(title);

        patientBtn = new JButton(
                "Patient Module");

        patientBtn.setBounds(120,100,220,40);

        add(patientBtn);

        doctorBtn = new JButton(
                "Doctor Module");

        doctorBtn.setBounds(120,170,220,40);

        add(doctorBtn);

        appointmentBtn = new JButton(
                "Appointment Module");

        appointmentBtn.setBounds(120,240,220,40);

        add(appointmentBtn);

        billingBtn = new JButton(
                "Billing Module");

        billingBtn.setBounds(120,310,220,40);

        add(billingBtn);

        logoutBtn = new JButton(
                "Logout");

        logoutBtn.setBounds(120,380,220,40);

        add(logoutBtn);
        patientReportBtn =
                new JButton("Generate Patient Report");

        patientReportBtn.setBounds(
                120,450,220,40);

        add(patientReportBtn);

        billingReportBtn =
                new JButton("Generate Billing Report");

        billingReportBtn.setBounds(
                120,510,220,40);

        add(billingReportBtn);

        patientReportBtn.addActionListener(this);

        billingReportBtn.addActionListener(this);

        patientBtn.addActionListener(this);

        doctorBtn.addActionListener(this);

        appointmentBtn.addActionListener(this);

        billingBtn.addActionListener(this);

        logoutBtn.addActionListener(this);

        setTitle("Dashboard");

        setSize(500,700);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == patientBtn) {

            new PatientUI();
        }

        if(e.getSource() == doctorBtn) {

        	new DoctorUI();
        }

        if(e.getSource() == appointmentBtn) {

           new AppointmentUI();
        }

        if(e.getSource() == billingBtn) {

            new BillingUI();
        }
        if(e.getSource() == patientReportBtn) {

            ReportGenerator.generatePatientReport();

            JOptionPane.showMessageDialog(this,
                    "Patient Report Generated");
        }

        if(e.getSource() == billingReportBtn) {

            ReportGenerator.generateBillingReport();

            JOptionPane.showMessageDialog(this,
                    "Billing Report Generated");
        }

        if(e.getSource() == logoutBtn) {

            new LoginUI();

            dispose();
        }
    }
}