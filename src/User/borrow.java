/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Main.login;
import Admin.Admin;
import config.Session;
import config.config;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class borrow extends javax.swing.JFrame {

    private final List<BorrowItem> borrowedBooks = new ArrayList<>();
    private Long selectedBorrowRowId;

    public borrow() {
        initComponents();
        styleActionButton(deletePanel, deleteLabel, new Color(30, 95, 95));
        ensureBooksQuantityColumn();
        booksGrid.setOpaque(true);
        booksGrid.setBackground(new Color(14, 14, 18));
        booksGrid.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        booksGrid.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 10));
        galleryScroll.setBorder(null);
        galleryScroll.setViewportBorder(null);
        galleryScroll.getViewport().setOpaque(true);
        galleryScroll.getViewport().setBackground(new Color(14, 14, 18));
        galleryScroll.getVerticalScrollBar().setUnitIncrement(16);
        getContentPane().setComponentZOrder(frame, getContentPane().getComponentCount() - 1);
        ensureBorrowDateColumn();
        ensureBorrowBookColumns();
        backfillBorrowDates();
        loadBorrowedBooks();

        if (!Session.isLoggedIn()) {
            JOptionPane.showMessageDialog(this, "You must login first!");
            login log = new login();
            log.setVisible(true);
            this.dispose();
        }
    }

    private void ensureBorrowDateColumn() {
        try (Connection conn = config.connectDB();
             PreparedStatement pst = conn.prepareStatement("ALTER TABLE tbl_borrower ADD COLUMN borrow_date TEXT")) {
            pst.executeUpdate();
        } catch (Exception ignored) {
        }
    }

    private void ensureBooksQuantityColumn() {
        try (Connection conn = config.connectDB();
             PreparedStatement pst = conn.prepareStatement("ALTER TABLE tbl_books ADD COLUMN b_quantity INTEGER DEFAULT 0")) {
            pst.executeUpdate();
        } catch (Exception ignored) {
        }
    }

    private void ensureBorrowBookColumns() {
        String[] alters = {
            "ALTER TABLE tbl_borrower ADD COLUMN book_title TEXT",
            "ALTER TABLE tbl_borrower ADD COLUMN book_image TEXT"
        };
        for (String sql : alters) {
            try (Connection conn = config.connectDB();
                 PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.executeUpdate();
            } catch (Exception ignored) {
            }
        }
    }

    private void backfillBorrowDates() {
        try (Connection conn = config.connectDB();
             PreparedStatement pst = conn.prepareStatement(
                     "UPDATE tbl_borrower " +
                     "SET borrow_date = datetime('now', 'localtime') " +
                     "WHERE COALESCE(TRIM(borrow_date), '') = '' " +
                     "AND COALESCE(status, '') = 'Borrowed'")) {
            pst.executeUpdate();
        } catch (Exception ignored) {
        }
    }

    private void loadBorrowedBooks() {
        borrowedBooks.clear();
        booksGrid.removeAll();
        selectedBorrowRowId = null;

        try (Connection conn = config.connectDB();
             PreparedStatement pst = conn.prepareStatement(
                     "SELECT br.rowid AS borrow_row_id, br.book_id, " +
                     "COALESCE(br.book_title, b.b_title, '') AS book_title, " +
                     "COALESCE(br.book_image, b.b_image, '') AS b_image, " +
                     "br.U_name, br.U_email, " +
                     "CASE WHEN COALESCE(br.borrow_date, '') = '' THEN '' ELSE strftime('%m/%d/%Y', br.borrow_date) END AS borrow_day, " +
                     "CASE WHEN COALESCE(br.borrow_date, '') = '' THEN '' " +
                     "ELSE printf('%d:%s %s', " +
                     "CASE CAST(strftime('%H', br.borrow_date) AS INTEGER) % 12 " +
                     "WHEN 0 THEN 12 ELSE CAST(strftime('%H', br.borrow_date) AS INTEGER) % 12 END, " +
                     "strftime('%M', br.borrow_date), " +
                     "CASE WHEN CAST(strftime('%H', br.borrow_date) AS INTEGER) >= 12 THEN 'PM' ELSE 'AM' END) END AS borrow_time " +
                     "FROM tbl_borrower br " +
                     "LEFT JOIN tbl_books b ON b.b_id = br.book_id " +
                     "WHERE COALESCE(br.status, '') = 'Borrowed' " +
                     "ORDER BY br.borrow_date DESC, br.rowid DESC");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                BorrowItem item = new BorrowItem(
                        rs.getLong("borrow_row_id"),
                        rs.getInt("book_id"),
                        rs.getString("book_title"),
                        rs.getString("b_image"),
                        rs.getString("U_name"),
                        rs.getString("U_email"),
                        rs.getString("borrow_day"),
                        rs.getString("borrow_time")
                );
                borrowedBooks.add(item);
                booksGrid.add(createBorrowCard(item));
            }

            if (!borrowedBooks.isEmpty()) {
                selectedBorrowRowId = borrowedBooks.get(0).borrowRowId;
                highlightSelectedCard();
            }

            booksGrid.revalidate();
            booksGrid.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to load borrowed books: " + e.getMessage());
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        booksGrid.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        galleryScroll.setViewportView(booksGrid);
        getContentPane().add(galleryScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 8, 416, 342));

        deletePanel.setBackground(new java.awt.Color(30, 95, 95));
        deletePanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deletePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteSelectedBorrowRecord();
            }
        });
        deletePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deleteLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        deleteLabel.setForeground(new java.awt.Color(255, 255, 255));
        deleteLabel.setText("Delete");
        deleteLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteSelectedBorrowRecord();
            }
        });
        deletePanel.add(deleteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 30));

        getContentPane().add(deletePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 118, 30));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/bck.png")));
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 296, -1, 50));

        frame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/ima.jpg")));
        getContentPane().add(frame, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 360));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new borrow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JPanel booksGrid;
    private javax.swing.JLabel deleteLabel;
    private javax.swing.JPanel deletePanel;
    private javax.swing.JLabel frame;
    private javax.swing.JScrollPane galleryScroll;
    // End of variables declaration//GEN-END:variables

    private void backMouseClicked(java.awt.event.MouseEvent evt) {
        Admin ud = new Admin();
        ud.setVisible(true);
        this.dispose();
    }

    private JPanel createBorrowCard(BorrowItem item) {
        JPanel card = new JPanel();
        card.setOpaque(true);
        card.setBackground(new Color(14, 14, 18));
        card.setBorder(BorderFactory.createEmptyBorder(1, 1, 3, 1));
        card.setPreferredSize(new Dimension(116, 236));
        card.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(loadPosterImage(item.imagePath, 114, 156));
        card.add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 114, 156));

        JLabel titleLabel = new JLabel("<html><div style='text-align:center;'>Book : " + safeTextOrDefault(item.title, "Unknown book") + "</div></html>");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        titleLabel.setForeground(new Color(245, 245, 245));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        card.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 152, 108, 22));

        JLabel nameLabel = new JLabel("<html><div style='text-align:center;'>Name : " + safeText(item.borrowerName) + "</div></html>");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
        nameLabel.setForeground(new Color(222, 222, 222));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        card.add(nameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 172, 108, 16));

        JLabel emailLabel = new JLabel("<html><div style='text-align:center;'>Email : " + safeText(item.borrowerEmail) + "</div></html>");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
        emailLabel.setForeground(new Color(196, 196, 196));
        emailLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        card.add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 188, 108, 16));

        JLabel dateLabel = new JLabel("<html><div style='text-align:center;'>Date : " + safeText(item.borrowDate) + "</div></html>");
        dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
        dateLabel.setForeground(new Color(170, 255, 190));
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        card.add(dateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 204, 108, 12));

        JLabel timeLabel = new JLabel("<html><div style='text-align:center;'>Time : " + safeText(item.borrowTime) + "</div></html>");
        timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
        timeLabel.setForeground(new Color(170, 255, 190));
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        card.add(timeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 218, 108, 12));

        java.awt.event.MouseAdapter clickHandler = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedBorrowRowId = item.borrowRowId;
                highlightSelectedCard();
            }
        };
        card.addMouseListener(clickHandler);
        imageLabel.addMouseListener(clickHandler);
        titleLabel.addMouseListener(clickHandler);
        nameLabel.addMouseListener(clickHandler);
        emailLabel.addMouseListener(clickHandler);
        dateLabel.addMouseListener(clickHandler);
        timeLabel.addMouseListener(clickHandler);

        return card;
    }

    private void highlightSelectedCard() {
        for (int i = 0; i < booksGrid.getComponentCount(); i++) {
            JPanel card = (JPanel) booksGrid.getComponent(i);
            if (i < borrowedBooks.size() && borrowedBooks.get(i).borrowRowId == selectedBorrowRowId) {
                card.setBackground(new Color(22, 34, 58));
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(90, 140, 230), 1),
                        BorderFactory.createEmptyBorder(1, 1, 3, 1)
                ));
            } else {
                card.setBackground(new Color(14, 14, 18));
                card.setBorder(BorderFactory.createEmptyBorder(1, 1, 3, 1));
            }
        }
        booksGrid.repaint();
    }

    private void deleteSelectedBorrowRecord() {
        if (selectedBorrowRowId == null) {
            JOptionPane.showMessageDialog(this, "Please select a borrowed book first.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Delete this borrowed record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        Connection conn = null;
        try {
            conn = config.connectDB();
            conn.setAutoCommit(false);

            int bookId = -1;
            try (PreparedStatement selectPst = conn.prepareStatement(
                    "SELECT book_id FROM tbl_borrower WHERE rowid = ? AND COALESCE(status,'') = 'Borrowed'")) {
                selectPst.setLong(1, selectedBorrowRowId);
                try (ResultSet rs = selectPst.executeQuery()) {
                    if (rs.next()) {
                        bookId = rs.getInt("book_id");
                    }
                }
            }

            if (bookId <= 0) {
                conn.rollback();
                JOptionPane.showMessageDialog(this, "The selected borrowed record could not be found.");
                loadBorrowedBooks();
                return;
            }

            int deleted;
            try (PreparedStatement pst = conn.prepareStatement("DELETE FROM tbl_borrower WHERE rowid = ? AND COALESCE(status,'') = 'Borrowed'")) {
                pst.setLong(1, selectedBorrowRowId);
                deleted = pst.executeUpdate();
            }

            if (deleted > 0) {
                try (PreparedStatement stockPst = conn.prepareStatement(
                        "UPDATE tbl_books SET b_quantity = COALESCE(b_quantity, 0) + 1 WHERE b_id = ?")) {
                    stockPst.setInt(1, bookId);
                    stockPst.executeUpdate();
                }
                conn.commit();
                JOptionPane.showMessageDialog(this, "Borrowed record deleted successfully.");
                loadBorrowedBooks();
            } else {
                conn.rollback();
                JOptionPane.showMessageDialog(this, "No borrowed record was deleted.");
            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (Exception ignored) {
                }
            }
            JOptionPane.showMessageDialog(this, "Unable to delete borrowed record: " + e.getMessage());
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

    private ImageIcon loadPosterImage(String imagePath, int width, int height) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return createEmptyPoster(width, height);
        }
        File file = new File(imagePath);
        if (!file.exists()) {
            file = new File(System.getProperty("user.dir"), imagePath);
        }
        if (!file.exists()) {
            return createEmptyPoster(width, height);
        }
        try {
            BufferedImage source = ImageIO.read(file);
            if (source == null) {
                return createEmptyPoster(width, height);
            }
            double scale = Math.min((double) width / source.getWidth(), (double) height / source.getHeight());
            int drawWidth = Math.max(1, (int) Math.round(source.getWidth() * scale));
            int drawHeight = Math.max(1, (int) Math.round(source.getHeight() * scale));
            Image scaled = source.getScaledInstance(drawWidth, drawHeight, Image.SCALE_SMOOTH);
            BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = canvas.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setColor(new Color(8, 8, 10));
            g2.fillRect(0, 0, width, height);
            g2.drawImage(scaled, (width - drawWidth) / 2, (height - drawHeight) / 2, null);
            g2.dispose();
            return new ImageIcon(canvas);
        } catch (Exception e) {
            return createEmptyPoster(width, height);
        }
    }

    private ImageIcon createEmptyPoster(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setColor(new Color(8, 8, 10));
        g2.fillRect(0, 0, width, height);
        g2.setColor(new Color(55, 55, 65));
        g2.drawRect(0, 0, width - 1, height - 1);
        g2.dispose();
        return new ImageIcon(image);
    }

    private String safeText(String text) {
        return text == null ? "" : text;
    }

    private String safeTextOrDefault(String text, String fallback) {
        String cleaned = safeText(text).trim();
        return cleaned.isEmpty() ? fallback : cleaned;
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

    private static class BorrowItem {
        final long borrowRowId;
        final int bookId;
        final String title;
        final String imagePath;
        final String borrowerName;
        final String borrowerEmail;
        final String borrowDate;
        final String borrowTime;

        BorrowItem(long borrowRowId, int bookId, String title, String imagePath,
                String borrowerName, String borrowerEmail, String borrowDate, String borrowTime) {
            this.borrowRowId = borrowRowId;
            this.bookId = bookId;
            this.title = title;
            this.imagePath = imagePath;
            this.borrowerName = borrowerName;
            this.borrowerEmail = borrowerEmail;
            this.borrowDate = borrowDate;
            this.borrowTime = borrowTime;
        }
    }
}
