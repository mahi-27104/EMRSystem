package hospitalmng;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class AppointmentUI extends JFrame
implements ActionListener {

    JLabel l1,l2,l3,l4,l5;

    JTextField t1,t2,t3,t4;

    JComboBox<String> cb;

    JButton addBtn,
            updateBtn,
            deleteBtn,
            searchBtn,
            displayBtn,
            clearBtn;

    JTable table;

    DefaultTableModel model;

    JScrollPane sp;

    public AppointmentUI() {

        l1 = new JLabel("Appointment ID");
        l1.setBounds(30,30,130,30);
        add(l1);

        t1 = new JTextField();
        t1.setBounds(180,30,180,30);
        add(t1);

        l2 = new JLabel("Patient ID");
        l2.setBounds(30,90,130,30);
        add(l2);

        t2 = new JTextField();
        t2.setBounds(180,90,180,30);
        add(t2);

        l3 = new JLabel("Doctor ID");
        l3.setBounds(30,150,130,30);
        add(l3);

        t3 = new JTextField();
        t3.setBounds(180,150,180,30);
        add(t3);

        l4 = new JLabel("Appointment Date");
        l4.setBounds(30,210,130,30);
        add(l4);

        t4 = new JTextField();
        t4.setBounds(180,210,180,30);

        t4.setToolTipText("yyyy-mm-dd");

        add(t4);

        l5 = new JLabel("Status");
        l5.setBounds(30,270,130,30);
        add(l5);

        cb = new JComboBox<>();

        cb.addItem("Booked");
        cb.addItem("Completed");
        cb.addItem("Cancelled");

        cb.setBounds(180,270,180,30);

        add(cb);

        addBtn = new JButton("Add");
        addBtn.setBounds(430,30,150,40);
        add(addBtn);

        updateBtn = new JButton("Update");
        updateBtn.setBounds(430,90,150,40);
        add(updateBtn);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(430,150,150,40);
        add(deleteBtn);

        searchBtn = new JButton("Search");
        searchBtn.setBounds(430,210,150,40);
        add(searchBtn);

        displayBtn = new JButton("Display All");
        displayBtn.setBounds(430,270,150,40);
        add(displayBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(430,330,150,40);
        add(clearBtn);

        addBtn.addActionListener(this);

        updateBtn.addActionListener(this);

        deleteBtn.addActionListener(this);

        searchBtn.addActionListener(this);

        displayBtn.addActionListener(this);

        clearBtn.addActionListener(this);

        model = new DefaultTableModel();

        table = new JTable(model);

        model.addColumn("Appointment ID");
        model.addColumn("Patient ID");
        model.addColumn("Doctor ID");
        model.addColumn("Date");
        model.addColumn("Status");

        sp = new JScrollPane(table);

        sp.setBounds(30,420,720,220);

        add(sp);

        setTitle("Appointment Management");

        setSize(820,720);

        setLayout(null);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            Connection con =
                    hmsDBConnection.getConnection();

            int appointmentId =
                    Integer.parseInt(t1.getText());

            int patientId =
                    Integer.parseInt(t2.getText());

            int doctorId =
                    Integer.parseInt(t3.getText());

            Date appointmentDate =
                    Date.valueOf(t4.getText());

            String status =
                    cb.getSelectedItem().toString();

            if(e.getSource() == addBtn) {

                String query =
                "INSERT INTO appointments VALUES(?,?,?,?,?)";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setInt(1, appointmentId);

                ps.setInt(2, patientId);

                ps.setInt(3, doctorId);

                ps.setDate(4, appointmentDate);

                ps.setString(5, status);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Appointment Added Successfully");
            }

            if(e.getSource() == updateBtn) {

                String query =
                "UPDATE appointments SET patientid=?,doctorid=?,appointmentdate=?,status=? WHERE appointmentid=?";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setInt(1, patientId);

                ps.setInt(2, doctorId);

                ps.setDate(3, appointmentDate);

                ps.setString(4, status);

                ps.setInt(5, appointmentId);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Appointment Updated Successfully");
            }

            if(e.getSource() == deleteBtn) {

                String query =
                "DELETE FROM appointments WHERE appointmentid=?";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setInt(1, appointmentId);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Appointment Deleted Successfully");
            }

            if(e.getSource() == searchBtn) {

                model.setRowCount(0);

                String query =
                "SELECT * FROM appointments WHERE appointmentid=?";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setInt(1, appointmentId);

                ResultSet rs =
                        ps.executeQuery();

                while(rs.next()) {

                    model.addRow(new Object[] {

                            rs.getInt("appointmentid"),

                            rs.getInt("patientid"),

                            rs.getInt("doctorid"),

                            rs.getDate("appointmentdate"),

                            rs.getString("status")
                    });
                }
            }

            if(e.getSource() == displayBtn) {

                model.setRowCount(0);

                String query =
                        "SELECT * FROM appointments";

                Statement st =
                        con.createStatement();

                ResultSet rs =
                        st.executeQuery(query);

                while(rs.next()) {

                    model.addRow(new Object[] {

                            rs.getInt("appointmentid"),

                            rs.getInt("patientid"),

                            rs.getInt("doctorid"),

                            rs.getDate("appointmentdate"),

                            rs.getString("status")
                    });
                }
            }

            if(e.getSource() == clearBtn) {

                t1.setText("");

                t2.setText("");

                t3.setText("");

                t4.setText("");

                cb.setSelectedIndex(0);

                model.setRowCount(0);
            }

        } catch(Exception ex) {

            ex.printStackTrace();
        }
    }
}