package hospitalmng;
	
    import javax.swing.*;
	import java.awt.event.*;
	import java.sql.*;

	public class LoginUI extends JFrame implements ActionListener {

	    JLabel l1, l2, title;

	    JTextField t1;

	    JPasswordField p1;

	    JButton loginBtn, resetBtn;

	    public LoginUI() {

	        title = new JLabel("HOSPITAL MANAGEMENT SYSTEM");
	        title.setBounds(120,30,300,30);
	        add(title);

	        l1 = new JLabel("Username");
	        l1.setBounds(50,100,100,30);
	        add(l1);

	        t1 = new JTextField();
	        t1.setBounds(160,100,180,30);
	        add(t1);

	        l2 = new JLabel("Password");
	        l2.setBounds(50,160,100,30);
	        add(l2);

	        p1 = new JPasswordField();
	        p1.setBounds(160,160,180,30);
	        add(p1);

	        loginBtn = new JButton("Login");
	        loginBtn.setBounds(70,240,120,40);
	        add(loginBtn);

	        resetBtn = new JButton("Reset");
	        resetBtn.setBounds(220,240,120,40);
	        add(resetBtn);

	        loginBtn.addActionListener(this);

	        resetBtn.addActionListener(this);

	        setTitle("Login Page");

	        setSize(450,400);

	        setLayout(null);

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        setVisible(true);
	    }

	    public void actionPerformed(ActionEvent e) {

	        if(e.getSource() == loginBtn) {

	            try {

	                String username = t1.getText();

	                String password =
	                        String.valueOf(p1.getPassword());

	                Connection con =
	                        hmsDBConnection.getConnection();

	                String query =
	                "SELECT * FROM users WHERE username=? AND password=?";

	                PreparedStatement ps =
	                        con.prepareStatement(query);

	                ps.setString(1, username);

	                ps.setString(2, password);

	                ResultSet rs = ps.executeQuery();

	                if(rs.next()) {

	                    JOptionPane.showMessageDialog(this,
	                            "Login Successful");

	                    new Dashboard();

	                    AutoBackup backup =
	                            new AutoBackup();

	                    Thread t =
	                            new Thread(backup);

	                    t.start();

	                    dispose();
	                }
	                else {

	                    JOptionPane.showMessageDialog(this,
	                            "Invalid Username or Password");
	                }

	            } catch(Exception ex) {

	                ex.printStackTrace();
	            }
	        }

	        if(e.getSource() == resetBtn) {

	            t1.setText("");

	            p1.setText("");
	        }
	    }

	    public static void main(String[] args) {

	        new LoginUI();
	    }
	}

