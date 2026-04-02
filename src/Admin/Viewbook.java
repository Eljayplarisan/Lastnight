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
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Eljay
 */
public class Viewbook extends javax.swing.JFrame {

    private final List<BookItem> books = new ArrayList<>();
    private Integer selectedBookId;
    private BookItem selectedBook;

    public Viewbook() {
        initComponents();
        getContentPane().setComponentZOrder(frame, getContentPane().getComponentCount() - 1);
        ensureBooksImageColumn();
        styleActionButton(deletePanel, deleteLabel, new Color(30, 95, 95));
        booksGrid.setOpaque(false);
        booksGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        booksGrid.setLayout(new GridLayout(0, 2, 18, 18));
        galleryScroll.getViewport().setOpaque(false);
        galleryScroll.setOpaque(false);
        loadBooks();

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
        }
    }

    private void loadBooks() {
        books.clear();
        booksGrid.removeAll();
        selectedBook = null;
        selectedBookId = null;
        clearBookDetails();
        showGalleryView();

        try (Connection conn = config.connectDB();
             PreparedStatement pst = conn.prepareStatement(
                     "SELECT b_id, b_title, b_author, b_publisher, b_publish, COALESCE(b_image, '') AS b_image FROM tbl_books ORDER BY b_id DESC");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                BookItem item = new BookItem(
                        rs.getInt("b_id"),
                        rs.getString("b_title"),
                        rs.getString("b_author"),
                        rs.getString("b_publisher"),
                        rs.getString("b_publish"),
                        rs.getString("b_image")
                );
                books.add(item);
                booksGrid.add(createBookCard(item));
            }

            booksGrid.revalidate();
            booksGrid.repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to load books: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        galleryScroll = new javax.swing.JScrollPane();
        booksGrid = new javax.swing.JPanel();
        deletePanel = new javax.swing.JPanel();
        deleteLabel = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        frame = new javax.swing.JLabel();
        detailPanel = new javax.swing.JPanel();
        detailImage = new javax.swing.JLabel();
        detailTitle = new javax.swing.JLabel();
        detailAuthor = new javax.swing.JLabel();
        detailPublisher = new javax.swing.JLabel();
        detailYear = new javax.swing.JLabel();
        detailBackPanel = new javax.swing.JPanel();
        detailBackLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        booksGrid.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        booksGrid.setLayout(new java.awt.GridLayout(1, 0, 18, 18));
        galleryScroll.setViewportView(booksGrid);

        galleryScroll.setBorder(null);
        getContentPane().add(galleryScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 12, 360, 336));

        deletePanel.setBackground(new java.awt.Color(30, 95, 95));
        deletePanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deletePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletePanelMouseClicked(evt);
            }
        });
        deletePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deleteLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        deleteLabel.setForeground(new java.awt.Color(255, 255, 255));
        deleteLabel.setText("Delete");
        deleteLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteLabelMouseClicked(evt);
            }
        });
        deletePanel.add(deleteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 30));

        getContentPane().add(deletePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 228, 120, 30));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/bck.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, 50));

        detailPanel.setBackground(new java.awt.Color(236, 239, 255));
        detailPanel.setBorder(BorderFactory.createLineBorder(new Color(208, 213, 236), 1));
        detailPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        detailImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailPanel.add(detailImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 12, 158, 170));

        detailTitle.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        detailTitle.setForeground(new java.awt.Color(45, 50, 70));
        detailTitle.setText("Title : ");
        detailPanel.add(detailTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 188, 180, 20));

        detailAuthor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        detailAuthor.setForeground(new java.awt.Color(65, 70, 92));
        detailAuthor.setText("Author : ");
        detailPanel.add(detailAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 212, 180, 20));

        detailPublisher.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        detailPublisher.setForeground(new java.awt.Color(65, 70, 92));
        detailPublisher.setText("Publisher : ");
        detailPanel.add(detailPublisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 236, 180, 20));

        detailYear.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        detailYear.setForeground(new java.awt.Color(65, 70, 92));
        detailYear.setText("Year Publish : ");
        detailPanel.add(detailYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 260, 180, 20));

        getContentPane().add(detailPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 195, 290));

        detailBackPanel.setBackground(new java.awt.Color(30, 95, 95));
        detailBackPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        detailBackPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                detailBackPanelMouseClicked(evt);
            }
        });
        detailBackPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        detailBackLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        detailBackLabel.setForeground(new java.awt.Color(255, 255, 255));
        detailBackLabel.setText("Back");
        detailBackLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                detailBackLabelMouseClicked(evt);
            }
        });
        detailBackPanel.add(detailBackLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 30));

        getContentPane().add(detailBackPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 318, 90, 30));

        frame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/ima.jpg"))); // NOI18N
        getContentPane().add(frame, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 360));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void deletePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletePanelMouseClicked
        deleteSelectedBook();
    }//GEN-LAST:event_deletePanelMouseClicked

    private void deleteLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteLabelMouseClicked
        deleteSelectedBook();
    }//GEN-LAST:event_deleteLabelMouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
       Admin ua = new Admin();
       ua.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void detailBackPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailBackPanelMouseClicked
        showGalleryView();
    }//GEN-LAST:event_detailBackPanelMouseClicked

    private void detailBackLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailBackLabelMouseClicked
        showGalleryView();
    }//GEN-LAST:event_detailBackLabelMouseClicked

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
            java.util.logging.Logger.getLogger(Viewbook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Viewbook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Viewbook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Viewbook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Viewbook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JPanel booksGrid;
    private javax.swing.JLabel deleteLabel;
    private javax.swing.JPanel deletePanel;
    private javax.swing.JLabel detailAuthor;
    private javax.swing.JLabel detailBackLabel;
    private javax.swing.JPanel detailBackPanel;
    private javax.swing.JLabel detailImage;
    private javax.swing.JPanel detailPanel;
    private javax.swing.JLabel detailPublisher;
    private javax.swing.JLabel detailTitle;
    private javax.swing.JLabel detailYear;
    private javax.swing.JLabel frame;
    private javax.swing.JScrollPane galleryScroll;
    // End of variables declaration//GEN-END:variables

    private JPanel createBookCard(BookItem item) {
        JPanel card = new JPanel();
        card.setOpaque(true);
        card.setBackground(new Color(230, 234, 252));
        card.setBorder(BorderFactory.createEmptyBorder());
        card.setPreferredSize(new Dimension(125, 148));
        card.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(loadScaledImage(item.imagePath, 96, 92));
        card.add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 10, 96, 92));

        JLabel titleLabel = new JLabel("<html><center>" + safeText(item.title) + "</center></html>");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        titleLabel.setForeground(new Color(54, 58, 78));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        card.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 105, 22));

        java.awt.event.MouseAdapter clickHandler = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showBookDetails(item);
            }
        };
        card.addMouseListener(clickHandler);
        imageLabel.addMouseListener(clickHandler);
        titleLabel.addMouseListener(clickHandler);

        return card;
    }

    private void showBookDetails(BookItem item) {
        selectedBook = item;
        selectedBookId = item.id;
        detailImage.setIcon(loadScaledImage(item.imagePath, 158, 170));
        detailTitle.setText("Title : " + safeText(item.title));
        detailAuthor.setText("Author : " + safeText(item.author));
        detailPublisher.setText("Publisher : " + safeText(item.publisher));
        detailYear.setText("Year Publish : " + safeText(item.publishYear));

        for (Component component : booksGrid.getComponents()) {
            if (component instanceof JPanel) {
                component.setBackground(new Color(232, 236, 255));
            }
        }

        int index = books.indexOf(item);
        if (index >= 0 && index < booksGrid.getComponentCount()) {
            booksGrid.getComponent(index).setBackground(new Color(216, 223, 255));
        }

        galleryScroll.setVisible(false);
        detailPanel.setVisible(true);
        detailBackPanel.setVisible(true);
    }

    private void clearBookDetails() {
        detailImage.setIcon(null);
        detailTitle.setText("Title : ");
        detailAuthor.setText("Author : ");
        detailPublisher.setText("Publisher : ");
        detailYear.setText("Year Publish : ");
    }

    private void showGalleryView() {
        galleryScroll.setVisible(true);
        detailPanel.setVisible(false);
        detailBackPanel.setVisible(false);
    }

    private ImageIcon loadScaledImage(String imagePath, int width, int height) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return null;
        }

        File file = new File(imagePath);
        if (!file.exists()) {
            file = new File(System.getProperty("user.dir"), imagePath);
        }
        if (!file.exists()) {
            return null;
        }

        ImageIcon raw = new ImageIcon(file.getAbsolutePath());
        if (raw.getIconWidth() <= 0 || raw.getIconHeight() <= 0) {
            return null;
        }

        double scale = Math.min((double) width / raw.getIconWidth(), (double) height / raw.getIconHeight());
        int drawWidth = Math.max(1, (int) Math.round(raw.getIconWidth() * scale));
        int drawHeight = Math.max(1, (int) Math.round(raw.getIconHeight() * scale));
        Image scaled = raw.getImage().getScaledInstance(drawWidth, drawHeight, Image.SCALE_SMOOTH);

        BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = canvas.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(scaled, (width - drawWidth) / 2, (height - drawHeight) / 2, null);
        g2.dispose();
        return new ImageIcon(canvas);
    }

    private String safeText(String text) {
        return text == null ? "" : text;
    }

    private void deleteSelectedBook() {
        if (selectedBookId == null) {
            JOptionPane.showMessageDialog(this, "Please select a book first.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete the selected book?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        Connection conn = null;
        try {
            conn = config.connectDB();
            conn.setAutoCommit(false);

            int rowsDeleted;
            try (PreparedStatement deletePst = conn.prepareStatement("DELETE FROM tbl_books WHERE b_id = ?")) {
                deletePst.setInt(1, selectedBookId);
                rowsDeleted = deletePst.executeUpdate();
            }

            if (rowsDeleted > 0) {
                try (PreparedStatement reorderPst = conn.prepareStatement(
                        "UPDATE tbl_books SET b_id = b_id - 1 WHERE b_id > ?")) {
                    reorderPst.setInt(1, selectedBookId);
                    reorderPst.executeUpdate();
                }

                try (PreparedStatement resetSeqPst = conn.prepareStatement(
                        "UPDATE sqlite_sequence SET seq = COALESCE((SELECT MAX(b_id) FROM tbl_books), 0) WHERE name = 'tbl_books'")) {
                    resetSeqPst.executeUpdate();
                } catch (Exception ignored) {
                }

                conn.commit();
                JOptionPane.showMessageDialog(this, "Book deleted successfully.");
                loadBooks();
            } else {
                conn.rollback();
                JOptionPane.showMessageDialog(this, "No book was deleted. Please try again.");
            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (Exception ignored) {
                }
            }
            JOptionPane.showMessageDialog(this, "Unable to delete the selected book: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

    private void styleActionButton(JPanel panel, JLabel label, Color baseColor) {
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
                backgroundLabel.setIcon(new javax.swing.ImageIcon(createGlossyButtonImage(panel.getWidth(), panel.getHeight(), baseColor, false)));
                label.setBounds(0, 0, panel.getWidth(), panel.getHeight());
                fitLabelText(label, originalFont, panel.getWidth() - 16);
            }
        });

        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backgroundLabel.setIcon(new javax.swing.ImageIcon(createGlossyButtonImage(panel.getWidth(), panel.getHeight(), baseColor, true)));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backgroundLabel.setIcon(new javax.swing.ImageIcon(createGlossyButtonImage(panel.getWidth(), panel.getHeight(), baseColor, false)));
            }
        });

        backgroundLabel.setIcon(new javax.swing.ImageIcon(createGlossyButtonImage(
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

    private static class BookItem {
        final int id;
        final String title;
        final String author;
        final String publisher;
        final String publishYear;
        final String imagePath;

        BookItem(int id, String title, String author, String publisher, String publishYear, String imagePath) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.publisher = publisher;
            this.publishYear = publishYear;
            this.imagePath = imagePath;
        }
    }
}
