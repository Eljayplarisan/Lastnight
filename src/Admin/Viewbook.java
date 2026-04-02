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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
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
        booksGrid.setBackground(new Color(10, 10, 14));
        booksGrid.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        booksGrid.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 14));
        galleryScroll.getViewport().setOpaque(false);
        galleryScroll.setOpaque(false);
        galleryScroll.getViewport().setBackground(new Color(10, 10, 14));
        galleryScroll.getVerticalScrollBar().setUnitIncrement(14);
        detailPanel.setOpaque(true);
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
        detailPanel.setVisible(true);

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
            if (!books.isEmpty()) {
                showBookDetails(books.get(0));
            }

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
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        booksGrid.setBackground(new java.awt.Color(10, 10, 14));
        booksGrid.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        booksGrid.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 12, 14));
        galleryScroll.setViewportView(booksGrid);

        galleryScroll.setBorder(null);
        getContentPane().add(galleryScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 18, 364, 326));

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

        getContentPane().add(deletePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 270, 120, 30));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/bck.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, 50));

        detailPanel.setBackground(new java.awt.Color(18, 20, 28));
        detailPanel.setBorder(null);
        detailPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        detailImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailPanel.add(detailImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 164, 108));

        detailTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        detailTitle.setForeground(new java.awt.Color(245, 247, 255));
        detailTitle.setText("Title : ");
        detailPanel.add(detailTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 132, 175, 22));

        detailAuthor.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        detailAuthor.setForeground(new java.awt.Color(206, 212, 232));
        detailAuthor.setText("Author : ");
        detailPanel.add(detailAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 164, 175, 22));

        detailPublisher.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        detailPublisher.setForeground(new java.awt.Color(206, 212, 232));
        detailPublisher.setText("Publisher : ");
        detailPanel.add(detailPublisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 196, 175, 22));

        detailYear.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        detailYear.setForeground(new java.awt.Color(206, 212, 232));
        detailYear.setText("Year Publish : ");
        detailPanel.add(detailYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 228, 175, 22));

        getContentPane().add(detailPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 195, 290));

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
        card.setBackground(new Color(12, 12, 16));
        card.setBorder(BorderFactory.createEmptyBorder(5, 5, 7, 5));
        card.setPreferredSize(new Dimension(92, 150));
        card.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(loadScaledImage(item.imagePath, 80, 108));
        card.add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 4, 80, 108));

        JLabel titleLabel = new JLabel("<html><center>" + safeText(item.title) + "</center></html>");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        titleLabel.setForeground(new Color(255, 196, 66));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        card.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 116, 78, 24));

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
        detailImage.setIcon(loadScaledImage(item.imagePath, 164, 108));
        detailImage.setVisible(true);
        detailTitle.setText("Title : " + safeText(item.title));
        detailAuthor.setText("Author : " + safeText(item.author));
        detailPublisher.setText("Publisher : " + safeText(item.publisher));
        detailYear.setText("Year Publish : " + safeText(item.publishYear));

        detailPanel.setVisible(true);
    }

    private void clearBookDetails() {
        detailImage.setIcon(null);
        detailImage.setVisible(false);
        detailTitle.setText("Title : Select a book");
        detailAuthor.setText("Author : ");
        detailPublisher.setText("Publisher : ");
        detailYear.setText("Year Publish : ");
    }

    private ImageIcon loadScaledImage(String imagePath, int width, int height) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return createPlaceholderIcon(width, height, "No Image");
        }

        File file = new File(imagePath);
        if (!file.exists()) {
            file = new File(System.getProperty("user.dir"), imagePath);
        }
        if (!file.exists()) {
            return createPlaceholderIcon(width, height, "No Image");
        }

        try {
            BufferedImage source = ImageIO.read(file);
            if (source == null) {
                ImageIcon raw = new ImageIcon(file.getAbsolutePath());
                if (raw.getIconWidth() <= 0 || raw.getIconHeight() <= 0) {
                    return null;
                }
                source = new BufferedImage(raw.getIconWidth(), raw.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D sourceGraphics = source.createGraphics();
                sourceGraphics.drawImage(raw.getImage(), 0, 0, null);
                sourceGraphics.dispose();
            }

            double scale = Math.min((double) width / source.getWidth(), (double) height / source.getHeight());
            int drawWidth = Math.max(1, (int) Math.round(source.getWidth() * scale));
            int drawHeight = Math.max(1, (int) Math.round(source.getHeight() * scale));
            Image scaled = source.getScaledInstance(drawWidth, drawHeight, Image.SCALE_SMOOTH);

            BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = canvas.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(scaled, (width - drawWidth) / 2, (height - drawHeight) / 2, null);
            g2.dispose();
            return new ImageIcon(canvas);
        } catch (Exception e) {
            return createPlaceholderIcon(width, height, "No Image");
        }
    }

    private ImageIcon createPlaceholderIcon(int width, int height, String text) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(new GradientPaint(0, 0, new Color(34, 36, 46), 0, height, new Color(18, 20, 28)));
        g2.fillRoundRect(0, 0, width - 1, height - 1, 18, 18);
        g2.setColor(new Color(82, 90, 122));
        g2.setStroke(new BasicStroke(2f));
        g2.drawRoundRect(1, 1, width - 3, height - 3, 18, 18);
        g2.setColor(new Color(214, 220, 240));
        g2.setFont(new Font("Tahoma", Font.BOLD, Math.max(11, Math.min(15, width / 8))));
        java.awt.FontMetrics fm = g2.getFontMetrics();
        int textX = (width - fm.stringWidth(text)) / 2;
        int textY = (height + fm.getAscent()) / 2 - 4;
        g2.drawString(text, Math.max(8, textX), textY);
        g2.dispose();
        return new ImageIcon(image);
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
