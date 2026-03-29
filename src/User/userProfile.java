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
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
        editNamePanel = new javax.swing.JPanel();
        editNameLabel = new javax.swing.JLabel();
        editEmailPanel = new javax.swing.JPanel();
        editEmailLabel = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/profile.png"))); // NOI18N
        jLabel8.setToolTipText("Click to change profile picture");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
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
        jPanel2.setComponentZOrder(backphoto, 0);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(LBRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 130, 20));
        jPanel3.add(LBName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 20));
        jPanel3.add(LBEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 110, 20));

        editNamePanel.setBackground(new java.awt.Color(73, 105, 164));
        editNamePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        editNamePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editNamePanelMouseClicked(evt);
            }
        });
        editNamePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editNameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        editNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        editNameLabel.setText("Edit Name");
        editNamePanel.add(editNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 30));
        jPanel3.add(editNamePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 120, 30));

        editEmailPanel.setBackground(new java.awt.Color(73, 105, 164));
        editEmailPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        editEmailPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editEmailPanelMouseClicked(evt);
            }
        });
        editEmailPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editEmailLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        editEmailLabel.setForeground(new java.awt.Color(255, 255, 255));
        editEmailLabel.setText("Edit Email");
        editEmailPanel.add(editEmailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 30));
        jPanel3.add(editEmailPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 120, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 240, 150));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 360));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backphotoAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_backphotoAncestorMoved
   
    }//GEN-LAST:event_backphotoAncestorMoved

    private void backphotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backphotoMouseClicked
        chooseProfileImage();
    }//GEN-LAST:event_backphotoMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        chooseProfileImage();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void editNamePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editNamePanelMouseClicked
        String newName = JOptionPane.showInputDialog(this, "Enter new full name:", LBName.getText());
        if (newName != null) {
            newName = newName.trim();
            if (!newName.isEmpty()) {
                updateProfileField("u_fname", newName);
                LBName.setText(newName);
            }
        }
    }//GEN-LAST:event_editNamePanelMouseClicked

    private void editEmailPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editEmailPanelMouseClicked
        String newEmail = JOptionPane.showInputDialog(this, "Enter new email:", LBEmail.getText());
        if (newEmail != null) {
            newEmail = newEmail.trim();
            if (!newEmail.isEmpty()) {
                updateProfileField("u_email", newEmail);
                LBEmail.setText(newEmail);
            }
        }
    }//GEN-LAST:event_editEmailPanelMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        userDashboard ud = new userDashboard();
        ud.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void chooseProfileImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select profile picture");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));

        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            if (selectedFile.exists()) {
                ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
                Image scaledImage = icon.getImage().getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_SMOOTH);
                jLabel8.setIcon(new ImageIcon(scaledImage));
                JOptionPane.showMessageDialog(this, "Profile picture updated.");
            }
        }
    }

    private void updateProfileField(String field, String value) {
        String sql = "UPDATE tbl_data SET " + field + " = ? WHERE u_uname = ?";
        try (Connection conn = config.connectDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, value);
            pst.setString(2, Session.getUsername());
            int updated = pst.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(this, "Profile updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Unable to update profile.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating profile: " + e.getMessage());
        }
    }
    
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
    private javax.swing.JPanel editEmailPanel;
    private javax.swing.JLabel editEmailLabel;
    private javax.swing.JPanel editNamePanel;
    private javax.swing.JLabel editNameLabel;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbRole;
    // End of variables declaration//GEN-END:variables

   
}
