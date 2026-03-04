/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import config.config;

/**
 *
 * @author Eljay
 */
public class Update extends javax.swing.JFrame {
    
    public Update() {
        initComponents();
        getData();
    }
    
    void getData(){
        config con = new config();
        String sql = "SELECT * FROM tbl_data";
        con.displayData(sql, table);
    }
     
 
    
    private void loadTableData() {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0); // clear existing rows

    try (Connection con = getConnection();
         PreparedStatement pst = con.prepareStatement(
             "SELECT u_id, u_fname, u_uname, u_email, u_password FROM tbl_data");
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("u_id"),
                rs.getString("u_fname"),
                rs.getString("u_uname"),
                rs.getString("u_email"),
                rs.getString("u_password")
            });
        }

    } catch (Exception e) {
       
    }
}
    
    private Connection getConnection() throws SQLException {
    try {
        Class.forName("org.sqlite.JDBC"); // make sure sqlite-jdbc.jar is added
    } catch (ClassNotFoundException e) {
        throw new SQLException("SQLite JDBC Driver not found.");
    }

    String url = "jdbc:sqlite:my_data.db"; // DB must be in project root
    return DriverManager.getConnection(url);
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        fname = new javax.swing.JLabel();
        unme = new javax.swing.JLabel();
        eml = new javax.swing.JLabel();
        User_id = new javax.swing.JLabel();
        pss = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        uname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        pass = new javax.swing.JTextField();
        up = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        back = new javax.swing.JLabel();
        bckgrnd = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fname.setForeground(new java.awt.Color(255, 255, 255));
        fname.setText("Full name");
        jPanel1.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        unme.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        unme.setForeground(new java.awt.Color(255, 255, 255));
        unme.setText("User name");
        jPanel1.add(unme, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        eml.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eml.setForeground(new java.awt.Color(255, 255, 255));
        eml.setText("Email");
        jPanel1.add(eml, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        User_id.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        User_id.setForeground(new java.awt.Color(255, 255, 255));
        User_id.setText("User Id");
        jPanel1.add(User_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        pss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pss.setForeground(new java.awt.Color(255, 255, 255));
        pss.setText("Password");
        jPanel1.add(pss, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, -1));
        jPanel1.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 90, -1));
        jPanel1.add(uname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 90, -1));

        email.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 90, -1));
        jPanel1.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 80, -1));
        jPanel1.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 90, -1));

        up.setBackground(new java.awt.Color(30, 95, 95));
        up.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        up.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                upMouseClicked(evt);
            }
        });
        up.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(" Update");
        up.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 80, 30));

        jPanel1.add(up, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 120, 30));

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 360, 280));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/bck.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 50, 50));

        bckgrnd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/ima.jpg"))); // NOI18N
        jPanel1.add(bckgrnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 360));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void upMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upMouseClicked
   
       String idText = id.getText().trim();

    if (idText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a user first!");
        return;
    }

    if (!idText.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "Invalid ID format!");
        return;
    }

    int userId = Integer.parseInt(idText);

    try (Connection con = getConnection()) {
        PreparedStatement checkPst = con.prepareStatement(
                "SELECT 1 FROM tbl_data WHERE u_id = ?");
        checkPst.setInt(1, userId);
        ResultSet rs = checkPst.executeQuery();

        if (!rs.next()) {
            JOptionPane.showMessageDialog(this, "ID does not exist in database!");
            return;
        }

        PreparedStatement pst = con.prepareStatement(
                "UPDATE tbl_data SET u_fname=?, u_uname=?, u_email=?, u_password=? WHERE u_id=?");
        pst.setString(1, name.getText().trim());
        pst.setString(2, uname.getText().trim());
        pst.setString(3, email.getText().trim());
        pst.setString(4, pass.getText().trim());
        pst.setInt(5, userId);

        int rowsUpdated = pst.executeUpdate();

        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "User Updated Successfully!");
            loadTableData();   // refresh table
            clearFields();     // clear form
        } else {
            JOptionPane.showMessageDialog(this, "Update Failed!");
        }

    } catch (Exception ex) {
      
    }
    }//GEN-LAST:event_upMouseClicked

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
    int selectedRow = table.getSelectedRow();

    User_id.setText(table.getValueAt(selectedRow, 0).toString());
    fname.setText(table.getValueAt(selectedRow, 1).toString());
    unme.setText(table.getValueAt(selectedRow, 2).toString());
    eml.setText(table.getValueAt(selectedRow, 3).toString());
    pss.setText(table.getValueAt(selectedRow, 4).toString());        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
      Admin au = new Admin();
      au.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_backMouseClicked

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Update().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel User_id;
    private javax.swing.JLabel back;
    private javax.swing.JLabel bckgrnd;
    private javax.swing.JTextField email;
    private javax.swing.JLabel eml;
    private javax.swing.JLabel fname;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField pass;
    private javax.swing.JLabel pss;
    private javax.swing.JTable table;
    private javax.swing.JTextField uname;
    private javax.swing.JLabel unme;
    private javax.swing.JPanel up;
    // End of variables declaration//GEN-END:variables


    private void clearFields() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
