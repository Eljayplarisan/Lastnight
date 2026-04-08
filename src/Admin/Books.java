/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Main.login;
import config.Session;
import config.config;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Eljay
 */
public class Books extends javax.swing.JFrame {

    private static final String IMAGE_FOLDER = "book_images";
    private String selectedImagePath;

    /**
     * Creates new form Books
     */
    public Books() {
        initComponents();
        getContentPane().setComponentZOrder(Frame, getContentPane().getComponentCount() - 1);
        ensureBooksImageColumn();
        ensureBooksQuantityColumn();
        styleActionButton(add, addbks, add.getBackground());
        styleActionButton(selectImagePanel, selectImageLabel, selectImagePanel.getBackground());
        styleTextField(booktitle);
        styleTextField(author);
        styleTextField(Publisher);
        styleTextField(yearpub);
        styleTextField(quantity);
        bindPreviewTitle();
        showSelectedImage(null);

        if (!Session.isLoggedIn()) {
            JOptionPane.showMessageDialog(this, "You must login first!");
            login log = new login();
            log.setVisible(true);
            this.dispose();
        }
    }

    private void ensureBooksImageColumn() {
        try (Connection conn = config.connectDB();
             PreparedStatement pst = conn.prepareStatement("ALTER TABLE tbl_books ADD COLUMN b_image TEXT")) {
            pst.executeUpdate();
        } catch (Exception ignored) {
            // Column already exists or DB rejected duplicate alter; safe to continue.
        }
    }

    private void ensureBooksQuantityColumn() {
        try (Connection conn = config.connectDB();
             PreparedStatement pst = conn.prepareStatement("ALTER TABLE tbl_books ADD COLUMN b_quantity INTEGER DEFAULT 0")) {
            pst.executeUpdate();
        } catch (Exception ignored) {
            // Column already exists or DB rejected duplicate alter; safe to continue.
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        yearpub = new javax.swing.JTextField();
        Publisher = new javax.swing.JTextField();
        b_title = new javax.swing.JLabel();
        b_author = new javax.swing.JLabel();
        b_publsher = new javax.swing.JLabel();
        b_publish = new javax.swing.JLabel();
        b_quantity = new javax.swing.JLabel();
        add = new javax.swing.JPanel();
        addbks = new javax.swing.JLabel();
        booktitle = new javax.swing.JTextField();
        author = new javax.swing.JTextField();
        quantity = new javax.swing.JTextField();
        back = new javax.swing.JLabel();
        Frame = new javax.swing.JLabel();
        previewPanel = new javax.swing.JPanel();
        previewImage = new javax.swing.JLabel();
        previewTitle = new javax.swing.JLabel();
        selectImagePanel = new javax.swing.JPanel();
        selectImageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(yearpub, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 240, 80, 30));
        getContentPane().add(Publisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 180, 170, 30));

        b_title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_title.setForeground(new java.awt.Color(255, 255, 255));
        b_title.setText("Book Title");
        getContentPane().add(b_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 40, -1, -1));

        b_author.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_author.setForeground(new java.awt.Color(255, 255, 255));
        b_author.setText("Book Author");
        getContentPane().add(b_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 100, -1, -1));

        b_publsher.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_publsher.setForeground(new java.awt.Color(255, 255, 255));
        b_publsher.setText("Publisher");
        getContentPane().add(b_publsher, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 160, -1, -1));

        b_publish.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_publish.setForeground(new java.awt.Color(255, 255, 255));
        b_publish.setText("Year Publish");
        getContentPane().add(b_publish, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 220, -1, -1));

        b_quantity.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_quantity.setForeground(new java.awt.Color(255, 255, 255));
        b_quantity.setText("Quantity");
        getContentPane().add(b_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 220, -1, -1));

        add.setBackground(new java.awt.Color(30, 95, 95));
        add.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });
        add.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addbks.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addbks.setForeground(new java.awt.Color(255, 255, 255));
        addbks.setText("Add Books");
        addbks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addbksMouseClicked(evt);
            }
        });
        add.add(addbks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 34));

        getContentPane().add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 145, 34));

        booktitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                booktitleActionPerformed(evt);
            }
        });
        getContentPane().add(booktitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 60, 170, 30));
        getContentPane().add(author, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 120, 170, 30));
        getContentPane().add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 240, 80, 30));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/bck.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 50, 50));

        previewPanel.setBackground(new java.awt.Color(247, 247, 252));
        previewPanel.setBorder(BorderFactory.createLineBorder(new Color(215, 220, 235), 1));
        previewPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        previewImage.setOpaque(true);
        previewImage.setBackground(new java.awt.Color(255, 255, 255));
        previewImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        previewPanel.add(previewImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 200, 185));

        previewTitle.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        previewTitle.setForeground(new java.awt.Color(55, 60, 80));
        previewTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        previewTitle.setText("No image selected");
        previewPanel.add(previewTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 212, 200, 32));

        getContentPane().add(previewPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 45, 230, 260));

        selectImagePanel.setBackground(new java.awt.Color(30, 95, 95));
        selectImagePanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        selectImagePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectImagePanelMouseClicked(evt);
            }
        });
        selectImagePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectImageLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        selectImageLabel.setForeground(new java.awt.Color(255, 255, 255));
        selectImageLabel.setText("Choose Image");
        selectImageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectImageLabelMouseClicked(evt);
            }
        });
        selectImagePanel.add(selectImageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 34));

        getContentPane().add(selectImagePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 150, 34));

        Frame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/ima.jpg"))); // NOI18N
        getContentPane().add(Frame, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 360));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        saveBook();
    }//GEN-LAST:event_addMouseClicked

    private void addbksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addbksMouseClicked
        saveBook();
    }//GEN-LAST:event_addbksMouseClicked

    private void booktitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_booktitleActionPerformed
    }//GEN-LAST:event_booktitleActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
       Admin ua = new Admin();
       ua.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void selectImagePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectImagePanelMouseClicked
        chooseImage();
    }//GEN-LAST:event_selectImagePanelMouseClicked

    private void selectImageLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectImageLabelMouseClicked
        chooseImage();
    }//GEN-LAST:event_selectImageLabelMouseClicked

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
            java.util.logging.Logger.getLogger(Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Books().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Frame;
    private javax.swing.JTextField Publisher;
    private javax.swing.JPanel add;
    private javax.swing.JLabel addbks;
    private javax.swing.JTextField author;
    private javax.swing.JLabel b_author;
    private javax.swing.JLabel b_publish;
    private javax.swing.JLabel b_quantity;
    private javax.swing.JLabel b_publsher;
    private javax.swing.JLabel b_title;
    private javax.swing.JLabel back;
    private javax.swing.JTextField booktitle;
    private javax.swing.JLabel previewImage;
    private javax.swing.JPanel previewPanel;
    private javax.swing.JLabel previewTitle;
    private javax.swing.JTextField quantity;
    private javax.swing.JPanel selectImagePanel;
    private javax.swing.JLabel selectImageLabel;
    private javax.swing.JTextField yearpub;
    // End of variables declaration//GEN-END:variables

    private void chooseImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif", "bmp", "webp"));

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedImagePath = chooser.getSelectedFile().getAbsolutePath();
            showSelectedImage(selectedImagePath);
        }
    }

    private void saveBook() {
        if (booktitle.getText().trim().isEmpty()
                || author.getText().trim().isEmpty()
                || Publisher.getText().trim().isEmpty()
                || yearpub.getText().trim().isEmpty()
                || quantity.getText().trim().isEmpty()
                || selectedImagePath == null) {
            JOptionPane.showMessageDialog(this, "Please complete all book details, quantity, and choose an image.");
            return;
        }

        int parsedQuantity;
        try {
            parsedQuantity = Integer.parseInt(quantity.getText().trim());
            if (parsedQuantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.");
                quantity.requestFocusInWindow();
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity.");
            quantity.requestFocusInWindow();
            return;
        }

        try {
            String storedImagePath = copyImageToProject(selectedImagePath);
            config con = new config();
            String sql = "INSERT INTO tbl_books (b_title, b_author, b_publisher, b_publish, b_image, b_quantity) VALUES (?, ?, ?, ?, ?, ?)";
            con.addRecord(sql, booktitle.getText().trim(), author.getText().trim(), Publisher.getText().trim(), yearpub.getText().trim(), storedImagePath, parsedQuantity);
            JOptionPane.showMessageDialog(this, "Book added successfully.");
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to add book: " + e.getMessage());
        }
    }

    private String copyImageToProject(String sourcePath) throws Exception {
        Path imageDir = new File(IMAGE_FOLDER).toPath();
        Files.createDirectories(imageDir);

        String extension = "";
        int dotIndex = sourcePath.lastIndexOf('.');
        if (dotIndex >= 0) {
            extension = sourcePath.substring(dotIndex);
        }

        String fileName = "book_" + UUID.randomUUID().toString().replace("-", "") + extension;
        Path targetPath = imageDir.resolve(fileName);
        Files.copy(new File(sourcePath).toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        return IMAGE_FOLDER + File.separator + fileName;
    }

    private void clearForm() {
        booktitle.setText("");
        author.setText("");
        Publisher.setText("");
        yearpub.setText("");
        quantity.setText("");
        selectedImagePath = null;
        showSelectedImage(null);
    }

    private void showSelectedImage(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            previewImage.setIcon(null);
            previewTitle.setText(getPreviewTitleText());
            previewImage.revalidate();
            previewImage.repaint();
            return;
        }

        File file = new File(imagePath);
        if (!file.exists()) {
            previewImage.setIcon(null);
            previewTitle.setText("Image file not found");
            previewImage.revalidate();
            previewImage.repaint();
            return;
        }

        ImageIcon rawIcon = new ImageIcon(file.getAbsolutePath());
        Image scaled = rawIcon.getImage().getScaledInstance(200, 185, Image.SCALE_SMOOTH);
        previewImage.setIcon(new ImageIcon(scaled));
        previewTitle.setText(getPreviewTitleText());
        previewImage.revalidate();
        previewImage.repaint();
        previewPanel.revalidate();
        previewPanel.repaint();
    }

    private void bindPreviewTitle() {
        booktitle.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                refreshPreviewTitle();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                refreshPreviewTitle();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                refreshPreviewTitle();
            }
        });
    }

    private void refreshPreviewTitle() {
        previewTitle.setText(getPreviewTitleText());
    }

    private String getPreviewTitleText() {
        String title = booktitle.getText().trim();
        return title.isEmpty() ? "Book cover preview" : title;
    }

    private void styleTextField(javax.swing.JTextField field) {
        field.setBackground(new Color(248, 250, 255));
        field.setForeground(new Color(42, 48, 66));
        field.setCaretColor(new Color(25, 25, 25));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(176, 184, 206), 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        field.setFont(new Font("Tahoma", Font.PLAIN, 12));
    }

    private void styleActionButton(javax.swing.JPanel panel, javax.swing.JLabel label, Color baseColor) {
        final Font originalFont = label.getFont();
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setOpaque(false);
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
