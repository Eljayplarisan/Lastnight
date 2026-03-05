/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Admin.admimUser;
import Main.login;
import config.Session;
import config.config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Nino
 */
public class userProfile extends javax.swing.JFrame {

    /**
     * Creates new form userProfile
     */
    public userProfile() {
        initComponents();
        loadUserProfile();
        
        if (!Session.isLoggedIn()) {
        JOptionPane.showMessageDialog(this, "You must login first!");
        login log = new login();
        log.setVisible(true);
        this.dispose();
        }
    }
    
    private void loadUserProfile() {
        String sql = "SELECT u_fname, u_email, u_role FROM tbl_data WHERE u_uname = ?";
        try (Connection conn = config.connectDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, Session.getUsername()); // get logged-in username
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                LBName.setText(rs.getString("u_fname"));
                LBEmail.setText(rs.getString("u_email"));
                LBRole.setText(rs.getString("u_role"));
            } else {
                JOptionPane.showMessageDialog(this, "User not found!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading profile: " + e.getMessage());
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        lbRole = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        backphoto = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        LBRole = new javax.swing.JLabel();
        LBName = new javax.swing.JLabel();
        LBEmail = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/profile.png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 110, 70));

        lbName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setText("Name:");
        jPanel2.add(lbName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, 30));

        lbEmail.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbEmail.setForeground(new java.awt.Color(255, 255, 255));
        lbEmail.setText("Email: ");
        jPanel2.add(lbEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, 40));

        lbRole.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbRole.setForeground(new java.awt.Color(255, 255, 255));
        lbRole.setText("Role:");
        jPanel2.add(lbRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, 40));

        jPanel1.setBackground(new java.awt.Color(73, 105, 164));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Home");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 30));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 120, 30));

        backphoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/fan.jpg"))); // NOI18N
        backphoto.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                backphotoAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        backphoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backphotoMouseClicked(evt);
            }
        });
        jPanel2.add(backphoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 540, 360));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(LBRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 130, 20));
        jPanel3.add(LBName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 20));
        jPanel3.add(LBEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 110, 20));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 240, 110));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 360));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backphotoAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_backphotoAncestorMoved
   
    }//GEN-LAST:event_backphotoAncestorMoved

    private void backphotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backphotoMouseClicked
  
    }//GEN-LAST:event_backphotoMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        userDashboard ud = new userDashboard();
        ud.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new userProfile().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LBEmail;
    private javax.swing.JLabel LBName;
    private javax.swing.JLabel LBRole;
    private javax.swing.JLabel backphoto;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbRole;
    // End of variables declaration//GEN-END:variables

   
}
