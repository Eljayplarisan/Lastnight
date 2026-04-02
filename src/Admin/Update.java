/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;
import Main.login;
import config.Session;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import config.config;

/**
 *
 * @author Eljay
 */
public class Update extends javax.swing.JFrame {

    private Integer selectedUserId;
    private JTextField firstInvalidField;
    private final Color defaultTextFieldBackground = new Color(248, 250, 255);
    private final Color errorTextFieldBackground = new Color(255, 235, 238);
    private final javax.swing.border.Border defaultTextFieldBorder = BorderFactory.createLineBorder(new Color(176, 184, 206), 1);
    private final javax.swing.border.Border errorTextFieldBorder = BorderFactory.createLineBorder(new Color(220, 53, 69), 3);
    
    public Update() {
        initComponents();
        jPanel1.setComponentZOrder(bckgrnd, jPanel1.getComponentCount() - 1);
        styleActionButton(up, jLabel1);
        initializeTextFieldStyles();
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
        String sql = "SELECT u_id, u_fname, u_uname, u_email, u_password FROM tbl_data";
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
        pss = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        uname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
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

        pss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pss.setForeground(new java.awt.Color(255, 255, 255));
        pss.setText("Password");
        jPanel1.add(pss, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, -1));
        jPanel1.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 100, 30));
        jPanel1.add(uname, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 100, 30));

        email.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 100, 30));
        jPanel1.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 100, 30));

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

        jPanel1.add(up, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 230, 120, 30));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 40, 395, 300));

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void upMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upMouseClicked
        resetValidationStyles();

        if (selectedUserId == null) {
            JOptionPane.showMessageDialog(this, "Please select a user from the table first.");
            return;
        }

        if (!validateRequiredFields()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required text fields before updating.");
            if (firstInvalidField != null) {
                SwingUtilities.invokeLater(() -> {
                    firstInvalidField.setEditable(true);
                    firstInvalidField.setEnabled(true);
                    firstInvalidField.setFocusable(true);
                    firstInvalidField.requestFocusInWindow();
                    firstInvalidField.grabFocus();
                    firstInvalidField.setCaretPosition(firstInvalidField.getText().length());
                });
            }
            return;
        }

        try (Connection con = getConnection()) {
            PreparedStatement checkPst = con.prepareStatement(
                    "SELECT 1 FROM tbl_data WHERE u_id = ?");
            checkPst.setInt(1, selectedUserId);
            ResultSet rs = checkPst.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "The selected user could not be found. Please choose the user again.");
                return;
            }

            PreparedStatement pst = con.prepareStatement(
                    "UPDATE tbl_data SET u_fname=?, u_uname=?, u_email=?, u_password=? WHERE u_id=?");
            pst.setString(1, name.getText().trim());
            pst.setString(2, uname.getText().trim());
            pst.setString(3, email.getText().trim());
            pst.setString(4, pass.getText().trim());
            pst.setInt(5, selectedUserId);

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "User details updated successfully.");
                getData();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "No changes were saved. Please try again.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Unable to update the user right now: " + ex.getMessage());
        }
    }//GEN-LAST:event_upMouseClicked

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }

        selectedUserId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
        clearInputFieldsOnly();
        SwingUtilities.invokeLater(() -> {
            name.setEditable(true);
            name.setEnabled(true);
            name.setFocusable(true);
            name.requestFocusInWindow();
            name.grabFocus();
            name.setCaretPosition(name.getText().length());
        });
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
    private javax.swing.JLabel back;
    private javax.swing.JLabel bckgrnd;
    private javax.swing.JTextField email;
    private javax.swing.JLabel eml;
    private javax.swing.JLabel fname;
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
        selectedUserId = null;
        clearInputFieldsOnly();
        table.clearSelection();
    }

    private void clearInputFieldsOnly() {
        name.setText("");
        uname.setText("");
        email.setText("");
        pass.setText("");
        resetValidationStyles();
    }

    private void initializeTextFieldStyles() {
        styleTextField(name);
        styleTextField(uname);
        styleTextField(email);
        styleTextField(pass);
        table.setRowHeight(24);
    }

    private void resetValidationStyles() {
        name.setBorder(defaultTextFieldBorder);
        name.setBackground(defaultTextFieldBackground);
        name.setForeground(new Color(42, 48, 66));
        uname.setBorder(defaultTextFieldBorder);
        uname.setBackground(defaultTextFieldBackground);
        uname.setForeground(new Color(42, 48, 66));
        email.setBorder(defaultTextFieldBorder);
        email.setBackground(defaultTextFieldBackground);
        email.setForeground(new Color(42, 48, 66));
        pass.setBorder(defaultTextFieldBorder);
        pass.setBackground(defaultTextFieldBackground);
        pass.setForeground(new Color(42, 48, 66));
    }

    private boolean validateRequiredFields() {
        boolean isValid = true;
        firstInvalidField = null;

        if (name.getText().trim().isEmpty()) {
            markFieldError(name);
            isValid = false;
        }
        if (uname.getText().trim().isEmpty()) {
            markFieldError(uname);
            isValid = false;
        }
        if (email.getText().trim().isEmpty()) {
            markFieldError(email);
            isValid = false;
        }
        if (pass.getText().trim().isEmpty()) {
            markFieldError(pass);
            isValid = false;
        }

        return isValid;
    }

    private void markFieldError(JTextField field) {
        if (firstInvalidField == null) {
            firstInvalidField = field;
        }
        field.setBorder(errorTextFieldBorder);
        field.setBackground(errorTextFieldBackground);
        field.setForeground(new Color(160, 32, 48));
        field.revalidate();
        field.repaint();
    }

    private void styleTextField(JTextField field) {
        field.setBackground(defaultTextFieldBackground);
        field.setForeground(new Color(42, 48, 66));
        field.setCaretColor(new Color(80, 56, 190));
        field.setBorder(defaultTextFieldBorder);
        field.setFont(new Font("Tahoma", Font.PLAIN, 12));
        field.setEditable(true);
        field.setEnabled(true);
        field.setFocusable(true);
        field.setMargin(new Insets(4, 8, 4, 8));
        field.setSelectionColor(new Color(196, 180, 255));
        field.setSelectedTextColor(new Color(32, 24, 68));
    }

    private void styleActionButton(javax.swing.JPanel panel, javax.swing.JLabel label) {
        final Font originalFont = label.getFont();
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

        final javax.swing.JLabel backgroundLabel = new javax.swing.JLabel();
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

    private void fitLabelText(javax.swing.JLabel label, Font baseFont, int maxWidth) {
        int targetSize = baseFont.getSize();
        while (targetSize > 11) {
            Font testFont = new Font(baseFont.getName(), baseFont.getStyle(), targetSize);
            int textWidth = label.getFontMetrics(testFont).stringWidth(label.getText().trim());
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
