import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;
public class AdminLogin extends javax.swing.JFrame {

    public AdminLogin() {
        
        initComponents();
        setSize(605,413);
        setLocationRelativeTo(null);
        //to make jframe appear in centre//
        setVisible(true);
        getContentPane().setBackground(Color.PINK);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb1 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        lb3 = new javax.swing.JLabel();
        tf1 = new javax.swing.JTextField();
        pf1 = new javax.swing.JPasswordField();
        bt1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lb1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        lb1.setText("Admin Login");
        getContentPane().add(lb1);
        lb1.setBounds(193, 30, 170, 50);

        lb2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb2.setText("Email");
        getContentPane().add(lb2);
        lb2.setBounds(90, 110, 90, 30);

        lb3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb3.setText("Password");
        getContentPane().add(lb3);
        lb3.setBounds(90, 190, 100, 30);
        getContentPane().add(tf1);
        tf1.setBounds(240, 110, 180, 30);
        getContentPane().add(pf1);
        pf1.setBounds(240, 190, 180, 26);

        bt1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bt1.setText("Login");
        bt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt1ActionPerformed(evt);
            }
        });
        getContentPane().add(bt1);
        bt1.setBounds(180, 290, 120, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt1ActionPerformed
        
        String email=tf1.getText();
        String password=pf1.getText();
        try
        {
         ResultSet rs =DBloader.executeQuery("select * from admin where email= '"+email+"' and password= '"+password+"'","pos2024");
         if (rs.next())
         {
             Global.adminemail=email;
          JOptionPane.showMessageDialog(this,"login successful");
          
          AdminHome obj=new AdminHome();
          dispose();
         }
         else
         {
             JOptionPane.showMessageDialog(this,"email or password is incorrect");
         }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
             
    }//GEN-LAST:event_bt1ActionPerformed

    
    public static void main(String args[]) {
        
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
     
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt1;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JPasswordField pf1;
    private javax.swing.JTextField tf1;
    // End of variables declaration//GEN-END:variables
}
