/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Admin.Approve;
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
public class userDashboard extends javax.swing.JFrame {

    /**
     * Creates new form userDashboars
     */
    public userDashboard() {
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
        String sql = "SELECT u_fname, u_email FROM tbl_data WHERE u_uname = ?";
        try (Connection conn = config.connectDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, Session.getUsername());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                LBName.setText(rs.getString("u_fname"));
                LBEmail.setText(rs.getString("u_email"));
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

        jPanel2 = new javax.swing.JPanel();
        books = new javax.swing.JPanel();
        book = new javax.swing.JLabel();
        log = new javax.swing.JPanel();
        Logout = new javax.swing.JLabel();
        Text = new javax.swing.JLabel();
        books1 = new javax.swing.JPanel();
        book1 = new javax.swing.JLabel();
        profile = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        LBName = new javax.swing.JLabel();
        LBEmail = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        books.setBackground(new java.awt.Color(73, 105, 164));
        books.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        books.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                booksMouseClicked(evt);
            }
        });
        books.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        book.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        book.setForeground(new java.awt.Color(255, 255, 255));
        book.setText("Borrow Books");
        book.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookMouseClicked(evt);
            }
        });
        books.add(book, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 30));

        jPanel2.add(books, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 120, 30));

        log.setBackground(new java.awt.Color(73, 105, 164));
        log.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        log.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Logout.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        Logout.setForeground(new java.awt.Color(255, 255, 255));
        Logout.setText("Logout");
        Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMouseClicked(evt);
            }
        });
        log.add(Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 70, 30));

        jPanel2.add(log, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 310, 120, 30));

        Text.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Text.setForeground(new java.awt.Color(255, 255, 255));
        Text.setText("UserDashboard");
        jPanel2.add(Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 190, 40));

        books1.setBackground(new java.awt.Color(73, 105, 164));
        books1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        books1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                books1MouseClicked(evt);
            }
        });
        books1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        book1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        book1.setForeground(new java.awt.Color(255, 255, 255));
        book1.setText("Return Books");
        book1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                book1MouseClicked(evt);
            }
        });
        books1.add(book1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jPanel2.add(books1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 120, 30));

        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/profile.png"))); // NOI18N
        jPanel2.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, 70));

        lbName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setText("Name:");
        jPanel2.add(lbName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 40));

        lbEmail.setBackground(new java.awt.Color(255, 255, 255));
        lbEmail.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbEmail.setForeground(new java.awt.Color(255, 255, 255));
        lbEmail.setText("Email:");
        jPanel2.add(lbEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 60));

        LBName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LBName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(LBName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 100, 30));

        LBEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LBEmail.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(LBEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 120, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/fan.jpg"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 360));

        setSize(new java.awt.Dimension(557, 401));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void books1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_books1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_books1MouseClicked

    private void book1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_book1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_book1MouseClicked

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        login log = new login();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LogoutMouseClicked

    private void booksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_booksMouseClicked

    }//GEN-LAST:event_booksMouseClicked

    private void bookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookMouseClicked
        brrw_bks bb = new brrw_bks();
        bb.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bookMouseClicked

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
            java.util.logging.Logger.getLogger(userDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LBEmail;
    private javax.swing.JLabel LBName;
    private javax.swing.JLabel Logout;
    private javax.swing.JLabel Text;
    private javax.swing.JLabel book;
    private javax.swing.JLabel book1;
    private javax.swing.JPanel books;
    private javax.swing.JPanel books1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbName;
    private javax.swing.JPanel log;
    private javax.swing.JLabel profile;
    // End of variables declaration//GEN-END:variables
}
