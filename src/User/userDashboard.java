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
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
        styleActionButton(books, book);
        styleActionButton(books1, book1);
        styleActionButton(log, Logout);
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

    private void styleActionButton(JPanel panel, JLabel label) {
        final Font originalFont = label.getFont();
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setOpaque(false);
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setOpaque(false);

        final JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, Math.max(1, panel.getWidth()), Math.max(1, panel.getHeight()));
        panel.add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        panel.setComponentZOrder(backgroundLabel, panel.getComponentCount() - 1);
        panel.setComponentZOrder(label, 0);
        label.setBounds(0, 0, Math.max(1, panel.getWidth()), Math.max(1, panel.getHeight()));

        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                backgroundLabel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
                backgroundLabel.setIcon(new ImageIcon(createGlossyButtonImage(panel.getWidth(), panel.getHeight(), false)));
                label.setBounds(0, 0, panel.getWidth(), panel.getHeight());
                fitLabelText(label, originalFont, panel.getWidth() - 16);
            }
        });

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backgroundLabel.setIcon(new ImageIcon(createGlossyButtonImage(panel.getWidth(), panel.getHeight(), true)));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backgroundLabel.setIcon(new ImageIcon(createGlossyButtonImage(panel.getWidth(), panel.getHeight(), false)));
            }
        });

        backgroundLabel.setIcon(new ImageIcon(createGlossyButtonImage(
                Math.max(1, panel.getWidth()), Math.max(1, panel.getHeight()), false
        )));
        fitLabelText(label, originalFont, Math.max(1, panel.getWidth() - 16));
    }

    private void fitLabelText(JLabel label, Font baseFont, int maxWidth) {
        int targetSize = baseFont.getSize();
        while (targetSize > 11) {
            Font testFont = new Font(baseFont.getName(), baseFont.getStyle(), targetSize);
            int textWidth = label.getFontMetrics(testFont).stringWidth(label.getText());
            if (textWidth <= maxWidth) {
                label.setFont(testFont);
                return;
            }
            targetSize--;
        }
        label.setFont(new Font(baseFont.getName(), baseFont.getStyle(), 11));
    }

    private BufferedImage createGlossyButtonImage(int width, int height, boolean hover) {
        int w = Math.max(width, 1);
        int h = Math.max(height, 1);
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color outer = hover ? new Color(227, 231, 239) : new Color(210, 214, 224);
        Color top = hover ? new Color(187, 74, 255) : new Color(164, 43, 248);
        Color bottom = hover ? new Color(104, 0, 232) : new Color(91, 0, 215);
        Color shineTop = new Color(255, 255, 255, hover ? 180 : 165);
        Color shineBottom = new Color(255, 255, 255, 20);

        int arc = h - 2;
        g2.setColor(outer);
        g2.fillRoundRect(0, 0, w - 1, h - 1, arc, arc);

        g2.setPaint(new java.awt.GradientPaint(0, 2, top, 0, h - 3, bottom));
        g2.fillRoundRect(3, 3, w - 6, h - 6, Math.max(10, arc - 6), Math.max(10, arc - 6));

        g2.setPaint(new java.awt.GradientPaint(0, 5, shineTop, 0, h / 2, shineBottom));
        g2.fillRoundRect(12, 5, Math.max(1, w - 24), Math.max(8, (h / 2) - 3), Math.max(8, arc - 18), Math.max(8, arc - 18));

        g2.setColor(new Color(255, 255, 255, 170));
        g2.drawRoundRect(1, 1, w - 3, h - 3, arc, arc);
        g2.dispose();
        return image;
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
        books.add(book, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 135, 34));

        jPanel2.add(books, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 135, 34));

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
        log.add(Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 135, 34));

        jPanel2.add(log, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, 135, 34));

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
        books1.add(book1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 135, 34));

        jPanel2.add(books1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 135, 34));

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
