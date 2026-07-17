package hospitalmng;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class BillingUI extends JFrame
implements ActionListener {

    JLabel l1,l2,l3,l4,l5,l6;

    JTextField t1,t2,t3,t4,t5;

    JComboBox<String> cb;

    JButton addBtn,
            calculateBtn,
            displayBtn,
            clearBtn;

    JTable table;

    DefaultTableModel model;

    JScrollPane sp;

    public BillingUI() {

        l1 = new JLabel("Bill ID");
        l1.setBounds(30,30,120,30);
        add(l1);

        t1 = new JTextField();
        t1.setBounds(180,30,180,30);
        add(t1);

        l2 = new JLabel("Patient ID");
        l2.setBounds(30,90,120,30);
        add(l2);

        t2 = new JTextField();
        t2.setBounds(180,90,180,30);
        add(t2);

        l3 = new JLabel("Consultation Fees");
        l3.setBounds(30,150,140,30);
        add(l3);

        t3 = new JTextField();
        t3.setBounds(180,150,180,30);
        add(t3);

        l4 = new JLabel("Medicine Charges");
        l4.setBounds(30,210,140,30);
        add(l4);

        t4 = new JTextField();
        t4.setBounds(180,210,180,30);
        add(t4);

        l5 = new JLabel("Room Charges");
        l5.setBounds(30,270,140,30);
        add(l5);

        t5 = new JTextField();
        t5.setBounds(180,270,180,30);
        add(t5);

        l6 = new JLabel("Patient Type");
        l6.setBounds(30,330,120,30);
        add(l6);

        cb = new JComboBox<>();

        cb.addItem("InPatient");

        cb.addItem("OutPatient");

        cb.setBounds(180,330,180,30);

        add(cb);

        addBtn = new JButton("Save Bill");
        addBtn.setBounds(430,40,150,40);
        add(addBtn);

        calculateBtn = new JButton("Calculate");
        calculateBtn.setBounds(430,110,150,40);
        add(calculateBtn);

        displayBtn = new JButton("Display Bills");
        displayBtn.setBounds(430,180,150,40);
        add(displayBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(430,250,150,40);
        add(clearBtn);

        addBtn.addActionListener(this);

        calculateBtn.addActionListener(this);

        displayBtn.addActionListener(this);

        clearBtn.addActionListener(this);

        model = new DefaultTableModel();

        table = new JTable(model);

        model.addColumn("Bill ID");
        model.addColumn("Patient ID");
        model.addColumn("Consultation");
        model.addColumn("Medicine");
        model.addColumn("Room");
        model.addColumn("Total");

        sp = new JScrollPane(table);

        sp.setBounds(30,420,720,220);

        add(sp);

        setTitle("Billing System");

        setSize(820,720);

        setLayout(null);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            Connection con =
                    hmsDBConnection.getConnection();

            int billId =
                    Integer.parseInt(t1.getText());

            int patientId =
                    Integer.parseInt(t2.getText());

            double consultation =
                    Double.parseDouble(t3.getText());

            double medicine =
                    Double.parseDouble(t4.getText());

            double room =
                    Double.parseDouble(t5.getText());

            String patientType =
                    cb.getSelectedItem().toString();

            double total = 0;

            if(patientType.equals("InPatient")) {

                InPatientBill bill =
                        new InPatientBill(
                                billId,
                                patientId,
                                consultation,
                                medicine,
                                room
                        );

                total = bill.calculateTotal();
            }

            else {

                OutPatientBill bill =
                        new OutPatientBill(
                                billId,
                                patientId,
                                consultation,
                                medicine
                        );

                total = bill.calculateTotal();
            }

            if(e.getSource() == calculateBtn) {

                JOptionPane.showMessageDialog(this,
                        "Total Bill : " + total);
            }

            if(e.getSource() == addBtn) {

                String query =
                "INSERT INTO bills VALUES(?,?,?,?,?,?)";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setInt(1, billId);

                ps.setInt(2, patientId);

                ps.setDouble(3, consultation);

                ps.setDouble(4, medicine);

                ps.setDouble(5, room);

                ps.setDouble(6, total);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Bill Saved Successfully");
            }

            if(e.getSource() == displayBtn) {

                model.setRowCount(0);

                String query =
                        "SELECT * FROM bills";

                Statement st =
                        con.createStatement();

                ResultSet rs =
                        st.executeQuery(query);

                while(rs.next()) {

                    model.addRow(new Object[] {

                            rs.getInt("billid"),

                            rs.getInt("patientid"),

                            rs.getDouble("consultationfees"),

                            rs.getDouble("medicinecharges"),

                            rs.getDouble("roomcharges"),

                            rs.getDouble("totalamount")
                    });
                }
            }

            if(e.getSource() == clearBtn) {

                t1.setText("");

                t2.setText("");

                t3.setText("");

                t4.setText("");

                t5.setText("");

                cb.setSelectedIndex(0);

                model.setRowCount(0);
            }

        } catch(Exception ex) {

            ex.printStackTrace();
        }
    }
}