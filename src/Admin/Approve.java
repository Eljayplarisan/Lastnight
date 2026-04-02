    
package Admin;

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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Approve extends javax.swing.JFrame {

    public Approve() {
        initComponents();
        styleActionButton(approvePanel, approveLabel, new Color(54, 153, 73));
        getData();
        
        if (!Session.isLoggedIn()) {
        JOptionPane.showMessageDialog(this, "You must login first!");
        login log = new login();
        log.setVisible(true);
        this.dispose();
        }
    }

    void getData(){
        config con = new config();
        String sql = "SELECT * FROM tbl_data";
        con.displayData(sql, utable);
    }
    
    @SuppressWarnings("unchecked")
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        utable = new javax.swing.JTable();
        approvePanel = new javax.swing.JPanel();
        approveLabel = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        frame = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        utable.setModel(new javax.swing.table.DefaultTableModel(
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
        utable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                utableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(utable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 450, 320));

        approvePanel.setBackground(new java.awt.Color(54, 153, 73));
        approvePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        approvePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                approvePanelMouseClicked(evt);
            }
        });
        approvePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        approveLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        approveLabel.setForeground(new java.awt.Color(255, 255, 255));
        approveLabel.setText("Approve");
        approveLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                approveLabelMouseClicked(evt);
            }
        });
        approvePanel.add(approveLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 30));

        getContentPane().add(approvePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 170, 120, 30));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/bck.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 50, 50));

        frame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/ima.jpg"))); // NOI18N
        getContentPane().add(frame, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 350));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void utableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_utableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_utableMouseClicked

    private void approveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_approveMouseClicked
        approveSelectedUser();
    }//GEN-LAST:event_approveMouseClicked

    private void approveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_approveMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_approveMouseEntered

    private void approvePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_approvePanelMouseClicked
        approveSelectedUser();
    }//GEN-LAST:event_approvePanelMouseClicked

    private void approveLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_approveLabelMouseClicked
        approveSelectedUser();
    }//GEN-LAST:event_approveLabelMouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        Admin ad = new Admin();
        ad.setVisible(true);
        this.dispose();// TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(Approve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Approve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Approve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Approve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Approve().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel approveLabel;
    private javax.swing.JPanel approvePanel;
    private javax.swing.JLabel back;
    private javax.swing.JLabel frame;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable utable;
    // End of variables declaration//GEN-END:variables

    private void approveSelectedUser() {
        int row = utable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a user to approve.");
            return;
        }

        Object idValue = utable.getValueAt(row, 0);
        if (idValue == null) {
            JOptionPane.showMessageDialog(this, "Selected row does not contain a valid ID.");
            return;
        }

        try {
            int userId = Integer.parseInt(idValue.toString());
            config con = new config();
            String sql = "UPDATE tbl_data SET u_status = ? WHERE u_id = ?";
            con.updateRecord(sql, "Approve", userId);
            JOptionPane.showMessageDialog(this, "User approved successfully.");
            getData();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid user ID selected.");
        }
    }

    private void loadTableData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void styleActionButton(JPanel panel, JLabel label, Color baseColor) {
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
                backgroundLabel.setIcon(new ImageIcon(createGlossyButtonImage(panel.getWidth(), panel.getHeight(), baseColor, false)));
                label.setBounds(0, 0, panel.getWidth(), panel.getHeight());
                fitLabelText(label, originalFont, panel.getWidth() - 16);
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

    private BufferedImage createGlossyButtonImage(int width, int height, Color baseColor, boolean hover) {
        int w = Math.max(width, 1);
        int h = Math.max(height, 1);
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color outer = hover ? adjustColor(baseColor, 55) : adjustColor(baseColor, 35);
        Color top = hover ? adjustColor(baseColor, 22) : adjustColor(baseColor, 8);
        Color bottom = hover ? adjustColor(baseColor, -12) : adjustColor(baseColor, -24);
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

    
}
