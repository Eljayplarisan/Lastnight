
package Main;

import config.config;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;

public class register extends javax.swing.JFrame {


    public register() {
        initComponents();
        stylePasswordField();
    }

    private void stylePasswordField() {
        Password.setBackground(fname.getBackground());
        Password.setForeground(fname.getForeground());
        Password.setCaretColor(fname.getForeground());
        Password.setFont(fname.getFont());
        Password.setBorder(fname.getBorder());
        Password.setEchoChar('\u2022');
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        uname = new javax.swing.JTextField();
        regBTn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        Password = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        role = new javax.swing.JComboBox<>();
        showPassword = new javax.swing.JCheckBox();
        pic = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusCycleRoot(false);
        setMinimumSize(new java.awt.Dimension(50, 50));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Name:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Username:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, -1, -1));

        fname.setToolTipText("");
        fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameActionPerformed(evt);
            }
        });
        getContentPane().add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 130, 30));

        email.setToolTipText("");
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        getContentPane().add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 130, 30));

        uname.setToolTipText("");
        uname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unameActionPerformed(evt);
            }
        });
        getContentPane().add(uname, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 130, 30));

        regBTn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        regBTn.setText("Register");
        regBTn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regBTnMouseClicked(evt);
            }
        });
        regBTn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regBTnActionPerformed(evt);
            }
        });
        getContentPane().add(regBTn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 90, 30));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Email:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/bck.png"))); // NOI18N
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 50, 50));

        Password.setToolTipText("");
        Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordActionPerformed(evt);
            }
        });
        getContentPane().add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 130, 30));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Role:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User", "Staff" }));
        getContentPane().add(role, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 90, 30));

        showPassword.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        showPassword.setForeground(new java.awt.Color(255, 255, 255));
        showPassword.setText("Show Password");
        showPassword.setContentAreaFilled(false);
        showPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(showPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 130, -1));

        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo/nn.jpg"))); // NOI18N
        getContentPane().add(pic, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 360));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void regBTnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regBTnActionPerformed
        
        String firstName = fname.getText().trim();
        String username = uname.getText().trim();
        String userEmail = email.getText().trim();
        String userPassword = new String(Password.getPassword()).trim();
        String selectedRole = role.getSelectedItem().toString();

        if (firstName.isEmpty() || username.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        if (!isValidEmail(userEmail)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email like louie1@gmail.com.");
            email.requestFocus();
            return;
        }

        String status = "Pending";

        try {
            config con = new config();
            String sql = "INSERT INTO tbl_data (u_fname, u_uname, u_email, u_password, u_role, u_status) VALUES (?, ?, ?, ?, ?, ?)";

            con.addRecord(sql,
                firstName,
                username,
                userEmail,
                userPassword,
                selectedRole,
                status
            );

            JOptionPane.showMessageDialog(this, "New " + selectedRole + " added successfully!");

            fname.setText("");
            uname.setText("");
            email.setText("");
            Password.setText("");
            role.setSelectedIndex(0);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding user: " + ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_regBTnActionPerformed

    private void fnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnameActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void unameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unameActionPerformed

    private void regBTnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regBTnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_regBTnMouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        login log = new login();
        log.setVisible(true);
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_backMouseClicked

    private void PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordActionPerformed

    private void showPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordActionPerformed
        Password.setEchoChar(showPassword.isSelected() ? (char) 0 : '\u2022');
    }//GEN-LAST:event_showPasswordActionPerformed

    private boolean isValidEmail(String userEmail) {
        return userEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

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
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Password;
    private javax.swing.JLabel back;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel pic;
    private javax.swing.JButton regBTn;
    private javax.swing.JComboBox<String> role;
    private javax.swing.JCheckBox showPassword;
    private javax.swing.JTextField uname;
    // End of variables declaration//GEN-END:variables
}
