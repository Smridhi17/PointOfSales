
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.sql.*;

public class AdminBillHistory extends javax.swing.JFrame 
{
    ArrayList<Bill> al1;
    mytablemodel1 tm1;
    ArrayList<BillDetails> al2;
    mytablemodel2 tm2;
    
    public AdminBillHistory() {
        al1=new ArrayList<>();
        tm1=new mytablemodel1();
        al2=new ArrayList<>();
        tm2=new mytablemodel2();
        
        initComponents();
         
        jTable1.setModel(tm1);
        jTable2.setModel(tm2);
        showBill();
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        getContentPane().setBackground(Color.PINK);
        int width=(int) d.getWidth();
        int height=(int) d.getHeight();
        setSize(width,height);
        setVisible(true);
        
    }
    void showBill()
    {
        try
    {
     ResultSet rs= DBloader.executeQuery("select * from bill", "pos2024");
     while(rs.next())
     {
        int billid= rs.getInt("billid");
        String datetime=rs.getString("datetime");
        int gtotal=rs.getInt("gtotal");
        String adminemail=rs.getString("adminemail");
        String phoneno=rs.getString("phoneno");
        String paymenttype=rs.getString("paymenttype");
        
        al1.add(new Bill(billid,datetime,gtotal,adminemail,phoneno,paymenttype));
     }
     
     tm1.fireTableDataChanged();
    }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    class mytablemodel1 extends AbstractTableModel
    {

        @Override
        public int getRowCount() {
            return al1.size();
        }

        @Override
        public int getColumnCount() {
            return 6;
            
        }

        @Override
        public Object getValueAt(int i, int j) {
            Bill obj=al1.get(i);
            if(j==0)
            {
                return obj.billid;
            }
            else if(j==1)
            {
                return obj.datetime;
            }
            else if(j==2)
            {
                return obj.gtotal;
            }
            else if(j==3)
            {
                return obj.adminemail;
            }
            else if(j==4)
            {
                return obj.phoneno;
            }
            else
            {
                return obj.paymenttype;
            }
        }
        @Override 
        public String getColumnName(int j)
        {
            String col[]={"Bill Id","DateTime","Grand Total","Admin Email","Phone No","Payment type"};
            return col[j];
        
        }
    }
    class mytablemodel2 extends AbstractTableModel
    {

        @Override
        public int getRowCount() {
            return al2.size();
            
        }

        @Override
        public int getColumnCount() {
            return 5;
        }
        

        @Override
        public Object getValueAt(int i, int j) {
            BillDetails obj=al2.get(i);
            if(j==0)
            {
                return obj.billDetailid;
            }
            else if(j==1)
            {
                return obj.billid;
            }
            else if(j==2)
            {
                return obj.offerprice;
                
            }
            else if(j==3)
            {
                return obj.pname;
                
            }
            else 
            {
                return obj.quantity;
            }
            
            
        }
        @Override 
        public String getColumnName(int column)
        {String col[]={"Bill Detail Id", "Bill Id","Offer Price","Product Name","Quantity"};
            
        return col[column];
            
        }
        
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel1.setText("Bill History");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(540, 0, 230, 50);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 140, 560, 370);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(640, 140, 590, 370);

        jButton1.setText("Get Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(230, 550, 170, 40);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel2.setText("          Bill");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(240, 80, 150, 40);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel3.setText("      Bill Detail");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(870, 70, 170, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int column=0;
        int row=jTable1.getSelectedRow();
     int billid=(int) jTable1.getModel().getValueAt(row, column);
     try{
      ResultSet rs=DBloader.executeQuery("select * from billdetails where billid= '"+billid+"'", "pos2024");
      
      while(rs.next())
      { int billdetailsid=rs.getInt("billDetailsid");
      int billid2=rs.getInt("billid");
      String pname=rs.getString("pname");
      int offerprice=rs.getInt("offerprice");
      int quantity=rs.getInt("quantity");
      al2.add(new BillDetails(billdetailsid,billid2,pname,offerprice,quantity));
      
          
      }
      tm2.fireTableDataChanged();
     }
     catch(Exception ex)
     {
         ex.printStackTrace();
     }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AdminBillHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminBillHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminBillHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminBillHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminBillHistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
    

