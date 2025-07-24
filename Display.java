
import timothy.DatabaseConnection;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Display extends JFrame {

    private JTextField txtName, txtAddress, txtContact;
    private JRadioButton rbMale, rbFemale;
    private ButtonGroup genderGroup;
    private JTable table;
    private JButton btnRegister, btnExit;

    public Display() {
        initComponents();
        loadTableData(); 
    }

    private void initComponents() {
        setLayout(null);
        setTitle("Registration Display");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Table on the left
        table = new JTable(new DefaultTableModel(new Object[]{"ID", "Name", "Gender", "Address", "Contact"}, 0));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 400, 440);
        add(scrollPane);

        // Form on the right
        JLabel lblName = new JLabel("Name:");
        JLabel lblGender = new JLabel("Gender:");
        JLabel lblAddress = new JLabel("Address:");
        JLabel lblContact = new JLabel("Contact:");

        txtName = new JTextField();
        txtAddress = new JTextField();
        txtContact = new JTextField();

        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);

        btnRegister = new JButton("Register");
        btnExit = new JButton("Exit");

        // Positioning
        int x = 430, y = 30, w = 100, h = 25;
        lblName.setBounds(x, y, w, h);        txtName.setBounds(x + 80, y, 200, h);
        lblGender.setBounds(x, y += 40, w, h); rbMale.setBounds(x + 80, y, 80, h); rbFemale.setBounds(x + 170, y, 80, h);
        lblAddress.setBounds(x, y += 40, w, h); txtAddress.setBounds(x + 80, y, 200, h);
        lblContact.setBounds(x, y += 40, w, h); txtContact.setBounds(x + 80, y, 200, h);
        btnRegister.setBounds(x + 10, y += 50, 120, 30); btnExit.setBounds(x + 150, y, 120, 30);

        add(lblName); add(txtName);
        add(lblGender); add(rbMale); add(rbFemale);
        add(lblAddress); add(txtAddress);
        add(lblContact); add(txtContact);
        add(btnRegister); add(btnExit);

        // Action Listeners
        btnRegister.addActionListener(e -> registerActionPerformed());
        btnExit.addActionListener(e -> System.exit(0));
    }

    // ✅ Button logic
    private void registerActionPerformed() {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String gender = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : "";

        if (name.isEmpty() || gender.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_db", "root", "");
             PreparedStatement ps = conn.prepareStatement("INSERT INTO students(name, gender, address, mobile) VALUES (?, ?, ?, ?)")) {

            ps.setString(1, name);
            ps.setString(2, gender);
            ps.setString(3, address);
            ps.setString(4, contact);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Registered successfully!");
            loadTableData();   // ✅ Update table
            clearFields();     // ✅ Reset form

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    // ✅ Load data from MySQL
    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // clear

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_db", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String contact = rs.getString("mobile");

                model.addRow(new Object[]{id, name, gender, address, contact});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
        }
    }

    // ✅ Clear text fields
    private void clearFields() {
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        genderGroup.clearSelection();
    }

    // ✅ Run the form
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Display().setVisible(true));
    }
}




public class Display extends javax.swing.JFrame {
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Display.class.getName());

    /**
     * Creates new form Display
     */
    public Display() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        register = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtContact = new javax.swing.JTextField();
        rMale = new javax.swing.JCheckBox();
        rFemale = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setBackground(new java.awt.Color(204, 255, 204));
        jLabel1.setText("ID");

        jLabel2.setBackground(new java.awt.Color(102, 255, 102));
        jLabel2.setText("Name");

        jLabel3.setBackground(new java.awt.Color(204, 255, 204));
        jLabel3.setText("Address");

        jLabel4.setBackground(new java.awt.Color(204, 255, 204));
        jLabel4.setText("Gender");

        jLabel5.setBackground(new java.awt.Color(204, 255, 204));
        jLabel5.setText("Contact");

        exit.setBackground(new java.awt.Color(204, 255, 204));
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        register.setBackground(new java.awt.Color(204, 255, 204));
        register.setText("Register");
        register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });

        buttonGroup1.add(rMale);
        rMale.setText("male");

        buttonGroup1.add(rFemale);
        rFemale.setText("female");
        rFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rFemaleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rMale)
                                .addGap(27, 27, 27)
                                .addComponent(rFemale))
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(exit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(register)
                        .addGap(58, 58, 58)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(exit)
                            .addComponent(register)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rMale)
                        .addComponent(rFemale)))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rFemaleActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
      
        System.exit(0); // Close the application
    



    }//GEN-LAST:event_exitActionPerformed

    private void registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerActionPerformed
 register.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String name = txtName.getText();
        String gender = rMale.isSelected() ? "Male" : (rFemale.isSelected() ? "Female" : "");
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        if (name.isEmpty() || gender.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO students (name, gender, address, contact) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, gender);
            stmt.setString(3, address);
            stmt.setString(4, contact);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registration successful!");
            loadTableData(); // Load data into table
            clearFields();   // Optional: reset form fields

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
        }
    }
});

        // TODO add your handling code here:
    }//GEN-LAST:event_registerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Display().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox rFemale;
    private javax.swing.JCheckBox rMale;
    private javax.swing.JButton register;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

}
