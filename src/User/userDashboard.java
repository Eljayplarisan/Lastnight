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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Nino
 */
public class userDashboard extends javax.swing.JFrame {
    private static final String PROFILE_FOLDER = "profile_images";

    /**
     * Creates new form userDashboars
     */
    public userDashboard() {
        initComponents();
        styleActionButton(books, book);
        styleActionButton(books1, book1);
        styleActionButton(log, Logout);
        ensureProfileImageColumn();
        profile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profile.setToolTipText("Click to update profile");
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openProfileEditor();
            }
        });
        loadUserProfile();
        
        
    if (!Session.isLoggedIn()) {
        JOptionPane.showMessageDialog(this, "You must login first!");
        login log = new login();
        log.setVisible(true);
        this.dispose();
        }
    }

    private void loadUserProfile() {
        String sql = "SELECT u_fname, u_email, COALESCE(u_image, '') AS u_image FROM tbl_data WHERE u_uname = ?";
        try (Connection conn = config.connectDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, Session.getUsername());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                LBName.setText(rs.getString("u_fname"));
                LBEmail.setText(rs.getString("u_email"));
                loadProfileImage(rs.getString("u_image"));
            } else {
                JOptionPane.showMessageDialog(this, "User not found!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading profile: " + e.getMessage());
        }
    }

    private void ensureProfileImageColumn() {
        try (Connection conn = config.connectDB();
             PreparedStatement pst = conn.prepareStatement("ALTER TABLE tbl_data ADD COLUMN u_image TEXT")) {
            pst.executeUpdate();
        } catch (Exception ignored) {
        }
    }

    private void openProfileEditor() {
        String[] profileData = getCurrentProfileData();
        if (profileData == null) {
            JOptionPane.showMessageDialog(this, "Unable to load profile details right now.");
            return;
        }

        JTextField nameField = new JTextField(profileData[0], 16);
        JTextField emailField = new JTextField(profileData[1], 16);
        JPasswordField passwordField = new JPasswordField(profileData[3], 16);
        JCheckBox showPassword = new JCheckBox("Show Password");
        JButton chooseImageButton = new JButton("Choose Picture");
        JLabel imageName = new JLabel(profileData[2].isEmpty() ? "Current: Default profile" : "Current: Saved picture");
        final String[] selectedImagePath = { profileData[2] };

        showPassword.setOpaque(false);
        showPassword.addActionListener(evt -> passwordField.setEchoChar(showPassword.isSelected() ? (char) 0 : '\u2022'));
        passwordField.setEchoChar('\u2022');

        chooseImageButton.addActionListener(evt -> {
            String chosen = chooseProfileImage();
            if (chosen != null) {
                selectedImagePath[0] = chosen;
                imageName.setText("Selected: " + new File(chosen).getName());
            }
        });

        JPanel panel = new JPanel(new GridLayout(0, 1, 6, 6));
        panel.add(new JLabel("Name"));
        panel.add(nameField);
        panel.add(new JLabel("Email"));
        panel.add(emailField);
        panel.add(new JLabel("Password"));
        panel.add(passwordField);
        panel.add(showPassword);
        panel.add(chooseImageButton);
        panel.add(imageName);

        int option = JOptionPane.showConfirmDialog(this, panel, "Update User Profile", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option != JOptionPane.OK_OPTION) {
            return;
        }

        String newName = nameField.getText().trim();
        String newEmail = emailField.getText().trim();
        String newPassword = new String(passwordField.getPassword()).trim();
        if (newName.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please complete the name, email, and password fields.");
            return;
        }

        String storedImagePath = selectedImagePath[0];
        if (storedImagePath != null && !storedImagePath.trim().isEmpty() && !storedImagePath.equals(profileData[2])) {
            storedImagePath = saveProfileImage(storedImagePath);
        }

        try (Connection conn = config.connectDB();
             PreparedStatement pst = conn.prepareStatement(
                     "UPDATE tbl_data SET u_fname = ?, u_email = ?, u_password = ?, u_image = ? WHERE u_uname = ?")) {
            pst.setString(1, newName);
            pst.setString(2, newEmail);
            pst.setString(3, newPassword);
            pst.setString(4, storedImagePath == null ? profileData[2] : storedImagePath);
            pst.setString(5, Session.getUsername());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Profile updated successfully.");
            loadUserProfile();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to update profile: " + e.getMessage());
        }
    }

    private String[] getCurrentProfileData() {
        String sql = "SELECT u_fname, u_email, COALESCE(u_image, '') AS u_image, COALESCE(u_password, '') AS u_password FROM tbl_data WHERE u_uname = ?";
        try (Connection conn = config.connectDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, Session.getUsername());
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new String[] {
                        rs.getString("u_fname"),
                        rs.getString("u_email"),
                        rs.getString("u_image"),
                        rs.getString("u_password")
                    };
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to read profile: " + e.getMessage());
        }
        return null;
    }

    private String chooseProfileImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif", "bmp", "webp"));
        return chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION
                ? chooser.getSelectedFile().getAbsolutePath()
                : null;
    }

    private String saveProfileImage(String sourcePath) {
        try {
            Path targetDir = new File(PROFILE_FOLDER).toPath();
            Files.createDirectories(targetDir);
            String extension = getFileExtension(sourcePath);
            Path targetPath = targetDir.resolve("profile_" + UUID.randomUUID() + extension);
            Files.copy(new File(sourcePath).toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return targetPath.toString();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to save selected profile image: " + e.getMessage());
            return sourcePath;
        }
    }

    private void loadProfileImage(String imagePath) {
        ImageIcon defaultIcon = new javax.swing.ImageIcon(getClass().getResource("/Photo/profile.png"));
        if (imagePath == null || imagePath.trim().isEmpty()) {
            profile.setIcon(scaleProfileIcon(defaultIcon));
            return;
        }
        File file = new File(imagePath);
        if (!file.exists()) {
            profile.setIcon(scaleProfileIcon(defaultIcon));
            return;
        }
        profile.setIcon(scaleProfileIcon(new ImageIcon(imagePath)));
    }

    private ImageIcon scaleProfileIcon(ImageIcon icon) {
        int width = 120;
        int height = 90;
        int circleSize = 74;
        int x = 18;
        int y = 8;

        BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = canvas.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setColor(new Color(255, 211, 163));
        g2.fillOval(x - 4, y - 4, circleSize + 8, circleSize + 8);
        g2.setColor(new Color(18, 90, 112));
        g2.fillOval(x - 1, y - 1, circleSize + 2, circleSize + 2);
        g2.setColor(Color.WHITE);
        g2.fillOval(x, y, circleSize, circleSize);

        BufferedImage source = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D sg = source.createGraphics();
        icon.paintIcon(null, sg, 0, 0);
        sg.dispose();

        double scale = Math.max((double) circleSize / source.getWidth(), (double) circleSize / source.getHeight());
        int drawWidth = Math.max(1, (int) Math.round(source.getWidth() * scale));
        int drawHeight = Math.max(1, (int) Math.round(source.getHeight() * scale));
        int drawX = x + (circleSize - drawWidth) / 2;
        int drawY = y + (circleSize - drawHeight) / 2;

        g2.setClip(new Ellipse2D.Double(x, y, circleSize, circleSize));
        g2.drawImage(source, drawX, drawY, drawWidth, drawHeight, null);
        g2.setClip(null);
        g2.dispose();
        return new ImageIcon(canvas);
    }

    private String getFileExtension(String path) {
        int dotIndex = path.lastIndexOf('.');
        return dotIndex >= 0 ? path.substring(dotIndex) : ".png";
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
        Text.setText("User_Dashboard");
        jPanel2.add(Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 200, 40));

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
        jPanel2.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 12, 120, 90));

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
        jPanel2.add(LBName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 160, 30));

        LBEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LBEmail.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(LBEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 160, 30));

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
        Return rn = new Return();
        rn.setVisible(true);
        this.dispose();
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
