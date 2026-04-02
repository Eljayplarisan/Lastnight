
package Admin;

import Main.login;
import User.borrow;
import config.Session;
import config.config;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
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


public class Admin extends javax.swing.JFrame {

    public Admin() {
        initComponents();
        styleActionButton(dashboard, jLabel7);
        styleActionButton(User, userBtn);
        styleActionButton(updte, update);
        styleActionButton(del, Delte);
        styleActionButton(addbooks, jLabel1);
        styleActionButton(list, transac);
        styleActionButton(view, book);
        styleActionButton(jPanel6, jLabel5);
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
               
            } else {
                JOptionPane.showMessageDialog(this, "User not found!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading profile: " + e.getMessage());
        }
    }

    private void styleActionButton(JPanel panel, JLabel label) {
        final Color baseColor = panel.getBackground();
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

        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                backgroundLabel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
                backgroundLabel.setIcon(new ImageIcon(createGlossyButtonImage(panel.getWidth(), panel.getHeight(), baseColor, false)));
            }
        });

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backgroundLabel.setIcon(new ImageIcon(createGlossyButtonImage(panel.getWidth(), panel.getHeight(), baseColor, true)));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backgroundLabel.setIcon(new ImageIcon(createGlossyButtonImage(panel.getWidth(), panel.getHeight(), baseColor, false)));
            }
        });
        
        backgroundLabel.setIcon(new ImageIcon(createGlossyButtonImage(
                Math.max(1, panel.getWidth()), Math.max(1, panel.getHeight()), baseColor, false
        )));
    }

    private BufferedImage createGlossyButtonImage(int width, int height, Color baseColor, boolean hover) {
        int w = Math.max(width, 1);
        int h = Math.max(height, 1);
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color outer = hover ? adjustColor(baseColor, 110) : adjustColor(baseColor, 80);
        Color top = hover ? adjustColor(baseColor, 45) : adjustColor(baseColor, 25);
        Color bottom = hover ? adjustColor(baseColor, -35) : adjustColor(baseColor, -20);
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

    private Color adjustColor(Color color, int amount) {
        int red = Math.max(0, Math.min(255, color.getRed() + amount));
        int green = Math.max(0, Math.min(255, color.getGreen() + amount));
        int blue = Math.max(0, Math.min(255, color.getBlue() + amount));
        return new Color(red, green, blue);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lbEmail = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        profile = new javax.swing.JLabel();
        LBEmail = new javax.swing.JLabel();
        LBName = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        updte = new javax.swing.JPanel();
        update = new javax.swing.JLabel();
        User = new javax.swing.JPanel();
        userBtn = new javax.swing.JLabel();
        dashboard = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        del = new javax.swing.JPanel();
        Delte = new javax.swing.JLabel();
        addbooks = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        list = new javax.swing.JPanel();
        transac = new javax.swing.JLabel();
        view = new javax.swing.JPanel();
        book = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(2147483647, 2147400000));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbEmail.setBackground(new java.awt.Color(255, 255, 255));
        lbEmail.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbEmail.setForeground(new java.awt.Color(255, 255, 255));
        lbEmail.setText("Email:");
        jPanel7.add(lbEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 60));

        lbName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setText("Name:");
        jPanel7.add(lbName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 40));

        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/profile.png"))); // NOI18N
        jPanel7.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, 70));

        LBEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LBEmail.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.add(LBEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 120, 30));

        LBName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LBName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.add(LBName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 100, 30));

        jPanel6.setBackground(new java.awt.Color(73, 105, 164));
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText(" Logout");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, -1, 70, 30));

        jPanel7.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 310, 120, 30));

        updte.setBackground(new java.awt.Color(73, 105, 164));
        updte.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        updte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updteMouseClicked(evt);
            }
        });
        updte.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        update.setBackground(new java.awt.Color(191, 153, 114));
        update.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("Update");
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateMouseClicked(evt);
            }
        });
        updte.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 60, 30));

        jPanel7.add(updte, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 130, 30));

        User.setBackground(new java.awt.Color(73, 105, 164));
        User.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        User.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserMouseClicked(evt);
            }
        });
        User.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userBtn.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        userBtn.setForeground(new java.awt.Color(255, 255, 255));
        userBtn.setText("Add User");
        userBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userBtnMouseClicked(evt);
            }
        });
        User.add(userBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, -1, 70, 30));

        jPanel7.add(User, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 130, 30));

        dashboard.setBackground(new java.awt.Color(73, 105, 164));
        dashboard.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dashboard.setForeground(new java.awt.Color(255, 255, 255));
        dashboard.setAlignmentY(2.2F);
        dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dashboardMouseEntered(evt);
            }
        });
        dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Approve");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        dashboard.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, -1, 90, 30));

        jPanel7.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 130, 30));

        del.setBackground(new java.awt.Color(73, 105, 164));
        del.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delMouseClicked(evt);
            }
        });
        del.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Delte.setBackground(new java.awt.Color(191, 153, 114));
        Delte.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        Delte.setForeground(new java.awt.Color(255, 255, 255));
        Delte.setText("Delete");
        Delte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DelteMouseClicked(evt);
            }
        });
        del.add(Delte, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 60, 30));

        jPanel7.add(del, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 130, 30));

        addbooks.setBackground(new java.awt.Color(73, 105, 164));
        addbooks.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addbooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addbooksMouseClicked(evt);
            }
        });
        addbooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Add Books");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        addbooks.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 4, 90, 20));

        jPanel7.add(addbooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 130, 30));

        list.setBackground(new java.awt.Color(73, 105, 164));
        list.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        list.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        transac.setBackground(new java.awt.Color(73, 105, 164));
        transac.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        transac.setForeground(new java.awt.Color(255, 255, 255));
        transac.setText("Transaction list");
        transac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transacMouseClicked(evt);
            }
        });
        list.add(transac, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 120, 30));

        jPanel7.add(list, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 130, 30));

        view.setBackground(new java.awt.Color(73, 105, 164));
        view.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        view.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        book.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        book.setForeground(new java.awt.Color(255, 255, 255));
        book.setText("View Books");
        book.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookMouseClicked(evt);
            }
        });
        view.add(book, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -1, 90, 30));

        jPanel7.add(view, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 130, 30));

        background.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        background.setForeground(new java.awt.Color(255, 255, 255));
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/fan.jpg"))); // NOI18N
        jPanel7.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 360));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 360));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 360));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseEntered

    }//GEN-LAST:event_dashboardMouseEntered

    private void UserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserMouseClicked
       
    }//GEN-LAST:event_UserMouseClicked

    private void userBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userBtnMouseClicked
        AddUser au = new AddUser();
        au.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_userBtnMouseClicked

    private void updteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updteMouseClicked

    }//GEN-LAST:event_updteMouseClicked

    private void updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseClicked
        Update up = new Update();
        up.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_updateMouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        login log = new login();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel6MouseClicked

    private void DelteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DelteMouseClicked
     Delete del = new Delete();
     del.setVisible(true);
     this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_DelteMouseClicked

    private void delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_delMouseClicked

    private void addbooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbooksMouseClicked
       Books bk = new Books();
       bk.setVisible(true);
       this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_addbooksMouseClicked

    private void transacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transacMouseClicked
       borrow bw = new borrow();
       bw.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_transacMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        Approve au = new Approve();
        au.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void bookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookMouseClicked
        Viewbook vb = new Viewbook();
        vb.setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_bookMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       Books bk = new Books();
       bk.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Delte;
    private javax.swing.JLabel LBEmail;
    private javax.swing.JLabel LBName;
    private javax.swing.JPanel User;
    private javax.swing.JPanel addbooks;
    private javax.swing.JLabel background;
    private javax.swing.JLabel book;
    private javax.swing.JPanel dashboard;
    private javax.swing.JPanel del;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbName;
    private javax.swing.JPanel list;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel transac;
    private javax.swing.JLabel update;
    private javax.swing.JPanel updte;
    private javax.swing.JLabel userBtn;
    private javax.swing.JPanel view;
    // End of variables declaration//GEN-END:variables
}
