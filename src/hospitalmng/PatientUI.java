package hospitalmng;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class PatientUI extends JFrame
implements ActionListener {

    JLabel l1,l2,l3,l4,l5,l6,l7;

    JTextField t1,t2,t3,t4,t5,t6,t7;

    JButton addBtn,
            updateBtn,
            deleteBtn,
            searchBtn,
            displayBtn,
            clearBtn;

    JTable table;

    DefaultTableModel model;

    JScrollPane sp;

    public PatientUI() {

        l1 = new JLabel("Patient ID");
        l1.setBounds(30,30,100,30);
        add(l1);

        t1 = new JTextField();
        t1.setBounds(150,30,150,30);
        add(t1);

        l2 = new JLabel("Name");
        l2.setBounds(30,80,100,30);
        add(l2);

        t2 = new JTextField();
        t2.setBounds(150,80,150,30);
        add(t2);

        l3 = new JLabel("Age");
        l3.setBounds(30,130,100,30);
        add(l3);

        t3 = new JTextField();
        t3.setBounds(150,130,150,30);
        add(t3);

        l4 = new JLabel("Gender");
        l4.setBounds(30,180,100,30);
        add(l4);

        t4 = new JTextField();
        t4.setBounds(150,180,150,30);
        add(t4);

        l5 = new JLabel("Disease");
        l5.setBounds(30,230,100,30);
        add(l5);

        t5 = new JTextField();
        t5.setBounds(150,230,150,30);
        add(t5);

        l6 = new JLabel("Mobile");
        l6.setBounds(30,280,100,30);
        add(l6);

        t6 = new JTextField();
        t6.setBounds(150,280,150,30);
        add(t6);

        l7 = new JLabel("Address");
        l7.setBounds(30,330,100,30);
        add(l7);

        t7 = new JTextField();
        t7.setBounds(150,330,150,30);
        add(t7);

        addBtn = new JButton("Add");
        addBtn.setBounds(350,30,150,40);
        add(addBtn);

        updateBtn = new JButton("Update");
        updateBtn.setBounds(350,90,150,40);
        add(updateBtn);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(350,150,150,40);
        add(deleteBtn);

        searchBtn = new JButton("Search");
        searchBtn.setBounds(350,210,150,40);
        add(searchBtn);

        displayBtn = new JButton("Display All");
        displayBtn.setBounds(350,270,150,40);
        add(displayBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(350,330,150,40);
        add(clearBtn);

        addBtn.addActionListener(this);

        updateBtn.addActionListener(this);

        deleteBtn.addActionListener(this);

        searchBtn.addActionListener(this);

        displayBtn.addActionListener(this);

        clearBtn.addActionListener(this);

        model = new DefaultTableModel();

        table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Gender");
        model.addColumn("Disease");
        model.addColumn("Mobile");
        model.addColumn("Address");

        sp = new JScrollPane(table);

        sp.setBounds(30,420,750,220);

        add(sp);

        setTitle("Patient Management");

        setSize(850,720);

        setLayout(null);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            Connection con =
                    hmsDBConnection.getConnection();

            int patientId =
                    Integer.parseInt(t1.getText());

            String name = t2.getText();

            int age =
                    Integer.parseInt(t3.getText());

            String gender = t4.getText();

            String disease = t5.getText();

            long mobile =
                    Long.parseLong(t6.getText());

            String address = t7.getText();
            Validator.validatePatient(
                    name,
                    age,
                    mobile
            );

            if(e.getSource() == addBtn) {

                String query =
                "INSERT INTO patients VALUES(?,?,?,?,?,?,?)";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setInt(1, patientId);

                ps.setString(2, name);

                ps.setInt(3, age);

                ps.setString(4, gender);

                ps.setString(5, disease);

                ps.setLong(6, mobile);

                ps.setString(7, address);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Patient Added Successfully");
            }

            if(e.getSource() == updateBtn) {

                String query =
                "UPDATE patients SET name=?,age=?,gender=?,disease=?,mobile=?,address=? WHERE patientid=?";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setString(1, name);

                ps.setInt(2, age);

                ps.setString(3, gender);

                ps.setString(4, disease);

                ps.setLong(5, mobile);

                ps.setString(6, address);

                ps.setInt(7, patientId);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Patient Updated Successfully");
            }

            if(e.getSource() == deleteBtn) {

                String query =
                "DELETE FROM patients WHERE patientid=?";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setInt(1, patientId);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Patient Deleted Successfully");
            }

            if(e.getSource() == searchBtn) {

                model.setRowCount(0);

                String query =
                "SELECT * FROM patients WHERE patientid=?";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setInt(1, patientId);

                ResultSet rs = ps.executeQuery();

                while(rs.next()) {

                    model.addRow(new Object[] {

                            rs.getInt("patientid"),

                            rs.getString("name"),

                            rs.getInt("age"),

                            rs.getString("gender"),

                            rs.getString("disease"),

                            rs.getLong("mobile"),

                            rs.getString("address")
                    });
                }
            }

            if(e.getSource() == displayBtn) {

                model.setRowCount(0);

                String query =
                        "SELECT * FROM patients";

                Statement st =
                        con.createStatement();

                ResultSet rs =
                        st.executeQuery(query);

                while(rs.next()) {

                    model.addRow(new Object[] {

                            rs.getInt("patientid"),

                            rs.getString("name"),

                            rs.getInt("age"),

                            rs.getString("gender"),

                            rs.getString("disease"),

                            rs.getLong("mobile"),

                            rs.getString("address")
                    });
                }
            }

            if(e.getSource() == clearBtn) {

                t1.setText("");

                t2.setText("");

                t3.setText("");

                t4.setText("");

                t5.setText("");

                t6.setText("");

                t7.setText("");

                model.setRowCount(0);
            }

        } 
        catch(InvalidDataException ex) {

            JOptionPane.showMessageDialog(this,
                    ex.getMessage());
        }

        catch(Exception ex) {

            ex.printStackTrace();
        }
    }
}