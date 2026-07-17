package hospitalmng;


	import javax.swing.*;
	import javax.swing.table.DefaultTableModel;
	import java.awt.event.*;
	import java.sql.*;

	public class DoctorUI extends JFrame
	implements ActionListener {

	    JLabel l1,l2,l3,l4,l5;

	    JTextField t1,t2,t4,t5;

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

	    public DoctorUI() {

	        l1 = new JLabel("Doctor ID");
	        l1.setBounds(30,30,120,30);
	        add(l1);

	        t1 = new JTextField();
	        t1.setBounds(180,30,180,30);
	        add(t1);

	        l2 = new JLabel("Doctor Name");
	        l2.setBounds(30,90,120,30);
	        add(l2);

	        t2 = new JTextField();
	        t2.setBounds(180,90,180,30);
	        add(t2);

	        l3 = new JLabel("Specialization");
	        l3.setBounds(30,150,120,30);
	        add(l3);

	        cb = new JComboBox<>();

	        cb.addItem("Cardiologist");
	        cb.addItem("Neurologist");
	        cb.addItem("Orthopedic");
	        cb.addItem("Dermatologist");
	        cb.addItem("Pediatrician");
	        cb.addItem("ENT Specialist");

	        cb.setBounds(180,150,180,30);

	        add(cb);

	        l4 = new JLabel("Consultation Fees");
	        l4.setBounds(30,210,140,30);
	        add(l4);

	        t4 = new JTextField();
	        t4.setBounds(180,210,180,30);
	        add(t4);

	        l5 = new JLabel("Mobile Number");
	        l5.setBounds(30,270,120,30);
	        add(l5);

	        t5 = new JTextField();
	        t5.setBounds(180,270,180,30);
	        add(t5);

	        addBtn = new JButton("Add");
	        addBtn.setBounds(420,30,150,40);
	        add(addBtn);

	        updateBtn = new JButton("Update");
	        updateBtn.setBounds(420,90,150,40);
	        add(updateBtn);

	        deleteBtn = new JButton("Delete");
	        deleteBtn.setBounds(420,150,150,40);
	        add(deleteBtn);

	        searchBtn = new JButton("Search");
	        searchBtn.setBounds(420,210,150,40);
	        add(searchBtn);

	        displayBtn = new JButton("Display All");
	        displayBtn.setBounds(420,270,150,40);
	        add(displayBtn);

	        clearBtn = new JButton("Clear");
	        clearBtn.setBounds(420,330,150,40);
	        add(clearBtn);

	        addBtn.addActionListener(this);

	        updateBtn.addActionListener(this);

	        deleteBtn.addActionListener(this);

	        searchBtn.addActionListener(this);

	        displayBtn.addActionListener(this);

	        clearBtn.addActionListener(this);

	        model = new DefaultTableModel();

	        table = new JTable(model);

	        model.addColumn("Doctor ID");
	        model.addColumn("Doctor Name");
	        model.addColumn("Specialization");
	        model.addColumn("Fees");
	        model.addColumn("Mobile");

	        sp = new JScrollPane(table);

	        sp.setBounds(30,420,700,220);

	        add(sp);

	        setTitle("Doctor Management");

	        setSize(800,720);

	        setLayout(null);

	        setVisible(true);
	    }

	    public void actionPerformed(ActionEvent e) {

	        try {

	            Connection con =
	                    hmsDBConnection.getConnection();

	            int doctorId =
	                    Integer.parseInt(t1.getText());

	            String doctorName =
	                    t2.getText();

	            String specialization =
	                    cb.getSelectedItem().toString();

	            double fees =
	                    Double.parseDouble(t4.getText());
	            Validator.validateDoctorFees(fees);

	            long mobile =
	                    Long.parseLong(t5.getText());

	            if(e.getSource() == addBtn) {

	                String query =
	                "INSERT INTO doctors VALUES(?,?,?,?,?)";

	                PreparedStatement ps =
	                        con.prepareStatement(query);

	                ps.setInt(1, doctorId);

	                ps.setString(2, doctorName);

	                ps.setString(3, specialization);

	                ps.setDouble(4, fees);

	                ps.setLong(5, mobile);

	                ps.executeUpdate();

	                JOptionPane.showMessageDialog(this,
	                        "Doctor Added Successfully");
	            }

	            if(e.getSource() == updateBtn) {

	                String query =
	                "UPDATE doctors SET doctorname=?,specialization=?,fees=?,mobile=? WHERE doctorid=?";

	                PreparedStatement ps =
	                        con.prepareStatement(query);

	                ps.setString(1, doctorName);

	                ps.setString(2, specialization);

	                ps.setDouble(3, fees);

	                ps.setLong(4, mobile);

	                ps.setInt(5, doctorId);

	                ps.executeUpdate();

	                JOptionPane.showMessageDialog(this,
	                        "Doctor Updated Successfully");
	            }

	            if(e.getSource() == deleteBtn) {

	                String query =
	                "DELETE FROM doctors WHERE doctorid=?";

	                PreparedStatement ps =
	                        con.prepareStatement(query);

	                ps.setInt(1, doctorId);

	                ps.executeUpdate();

	                JOptionPane.showMessageDialog(this,
	                        "Doctor Deleted Successfully");
	            }

	            if(e.getSource() == searchBtn) {

	                model.setRowCount(0);

	                String query =
	                "SELECT * FROM doctors WHERE doctorid=?";

	                PreparedStatement ps =
	                        con.prepareStatement(query);

	                ps.setInt(1, doctorId);

	                ResultSet rs =
	                        ps.executeQuery();

	                while(rs.next()) {

	                    model.addRow(new Object[] {

	                            rs.getInt("doctorid"),

	                            rs.getString("doctorname"),

	                            rs.getString("specialization"),

	                            rs.getDouble("fees"),

	                            rs.getLong("mobile")
	                    });
	                }
	            }

	            if(e.getSource() == displayBtn) {

	                model.setRowCount(0);

	                String query =
	                        "SELECT * FROM doctors";

	                Statement st =
	                        con.createStatement();

	                ResultSet rs =
	                        st.executeQuery(query);

	                while(rs.next()) {

	                    model.addRow(new Object[] {

	                            rs.getInt("doctorid"),

	                            rs.getString("doctorname"),

	                            rs.getString("specialization"),

	                            rs.getDouble("fees"),

	                            rs.getLong("mobile")
	                    });
	                }
	            }

	            if(e.getSource() == clearBtn) {

	                t1.setText("");

	                t2.setText("");

	                t4.setText("");

	                t5.setText("");

	                cb.setSelectedIndex(0);

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
	

