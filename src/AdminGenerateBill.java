import java.awt.Color;
 import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
public class AdminGenerateBill extends javax.swing.JFrame {
    
    ArrayList<Cart> al;
    mytablemodel tm;
    public AdminGenerateBill() {
        al=new ArrayList<>();
        tm=new mytablemodel();
        initComponents();
       jTable1.setModel(tm);
        
        showCategories();
       Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
       getContentPane().setBackground(Color.PINK);
       int width=(int) d.getWidth();
       int height=(int) d.getHeight();
       setSize(width,height);  
       setVisible(true);
    }
    File selectedfile;
    int grandTotal;
            void showCategories()
    { int count=1;
        try
        { 
            jPanel1.removeAll();
            ResultSet rs=DBloader.executeQuery("select * from categories", "pos2024");
            while(rs.next())
            {
               String catname= rs.getString("catname");
               String photo=rs.getString("photo");
               JButton bt1=new JButton();
               bt1.setText(catname);
             try
              {
              BufferedImage bi = ImageIO.read(new File (photo));
                bi = scale(bi,bt1.getWidth(), bt1.getHeight());
                bt1.setIcon(new ImageIcon(bi));    
         }
         catch(Exception ex)
         { 
            ex.printStackTrace();
         }
             bt1.setHorizontalTextPosition(SwingConstants.CENTER);
             bt1.setVerticalTextPosition(SwingConstants.BOTTOM);
             bt1.setBounds(75, count*135, 140, 120);
             jPanel1.add(bt1);
             jPanel1.repaint();
             count++;
             bt1.addActionListener(new ActionListener()
             {
                 @Override
                 public void actionPerformed(ActionEvent e)
                 {
                     
                     showproducts(catname);
                 }
             }
             );           
             }
           
            }
            catch(Exception ex)
                {
                    ex.printStackTrace(); 
                }
        jPanel1.setPreferredSize(new Dimension(250,150*count));
    }
    class mytablemodel extends AbstractTableModel
    {

        @Override
        public int getRowCount() {
            return al.size();
        }
        
        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public Object getValueAt(int i, int j) {
         Cart obj=al.get(i);
         if(j==0)
         {
             return obj.productname;
         }
         else if(j==1)
         {
             return obj.catname;
         }
         else if(j==2)
         {
             return obj.quantity;
         }
         else if(j==3)
        {return obj.offerprice;
            
        }
         else
         {
             return obj.perPrice;
         }
        }
         
         @Override
         public String getColumnName(int j)
         {
             String col[]={"Product Name","Category Name","Quantity","Offer Price","perPrice"};
             return col[j];
         }
         }
        
    
    void showproducts(String catname)
    {    
        int count=1;
        try
        { 
            jPanel2.removeAll();
            ResultSet rs=DBloader.executeQuery("select *from products where category='"+catname+"'", "pos2024");
            while(rs.next())
            {
               String productname= rs.getString("productname");
               String photo=rs.getString("photo");
               JButton bt2=new JButton();
               bt2.setText(productname);
              try
              {
              BufferedImage bi = ImageIO.read(new File (photo));          
                bi = scale(bi,140, 60);
                bt2.setIcon(new ImageIcon(bi));
              }
              catch(Exception ex)
              {
                  ex.printStackTrace();
               }
             bt2.setHorizontalTextPosition(SwingConstants.CENTER);
             bt2.setVerticalTextPosition(SwingConstants.BOTTOM);
             bt2.setBounds(75, count*135, 200, 100);
             jPanel2.add(bt2);
             jPanel2.repaint();
             count++;
             bt2.addActionListener(new ActionListener()
             {
                 @Override
             public void actionPerformed(ActionEvent e)
             {
                 cart(productname);
             }
             });
            }count++;
        }  
        catch(Exception ex)
        {
            ex.printStackTrace(); 
        }
        jPanel2.setPreferredSize(new Dimension(250,150*count));
    }
    void cart(String productname)
    {
        String ans= JOptionPane.showInputDialog(this,"enter quantity");
       
        int quantity=Integer.parseInt(ans); 
        int offerprice;
                 String catname;
                 int perPrice;
                 int perTotal;
                 try{
                     ResultSet rs= DBloader.executeQuery("select * from products where productname ='"+productname+"'","pos2024"); 
                 if(rs.next())
                 {
                     int databasequantity=rs.getInt("quantity");
                     if(quantity<=databasequantity)
                     {
                     int quantity1=rs.getInt(quantity)-quantity;
                     rs.moveToCurrentRow();
                     rs.updateInt("quantity", quantity1);
                     rs.updateRow();
                     catname=rs.getString("category");
                       offerprice=rs.getInt("offerprice");
                      perTotal=quantity*offerprice;
                       grandTotal=grandTotal+perTotal;
                    jLabel6.setText(grandTotal+"");
                    Global.gtotal=grandTotal;
                    al.add(new Cart (productname,catname,quantity,offerprice,perTotal));
                        
                     
                 }
                 else{
                     JOptionPane.showMessageDialog(this,"Quantity exceeds");
                 }
                 
                 
                 }
                   tm.fireTableDataChanged();
             
                 }
                 
                 catch(Exception ex)
                 {
                     ex.printStackTrace();
                 }
    }
           
           
         /*  try
           {
              ResultSet rs2=DBloader.executeQuery("select * from products where '"+productname+"'", "pos2024");
              if(rs2.next())
              {
                 int databasequantity=rs2.getInt("quantity");
                 if(databasequantity>=quantity)
                 {    
                     ResultSet rs3=DBloader.executeQuery("select * from products where '"+productname+"'", "pos2024");
                 
                 if(rs3.next())
                 {
                     rs3.updateInt("quantity", databasequantity-quantity);
                     rs3.updateRow();
                 
                 }
                 int offerprice;
                 String catname;
                 int perPrice;
                 int perTotal;
    
       try
       {
       ResultSet rs= DBloader.executeQuery("select * from products where productname ='"+productname+"'","pos2024");
       if(rs.next())
       {
           catname=rs.getString("category");
           offerprice=rs.getInt("offerprice");
           perTotal=quantity*offerprice;
           grandTotal=grandTotal+perTotal;
           jLabel6.setText(grandTotal+"");
           Global.gtotal=grandTotal;
           al.add(new Cart (productname,catname,quantity,offerprice,perTotal));
       }
       tm.fireTableDataChanged();
           
       }
       catch(Exception ex)
       {
           ex.printStackTrace();
        }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Quantity Exceeds");
        }
          }
           }  
           catch(Exception ex)
           { 
               ex.printStackTrace();
           } 
           
    */
           
        
    public static BufferedImage scale(BufferedImage src, int w, int h)
    {
    BufferedImage img = 
            new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    int x, y;
    int ww = src.getWidth();
    int hh = src.getHeight();
    int[] ys = new int[h];
    for (y = 0; y < h; y++)
        ys[y] = y * hh / h;
    for (x = 0; x < w; x++) {
        int newX = x * ww / w;
        for (y = 0; y < h; y++) {
            int col = src.getRGB(newX, ys[y]);
            img.setRGB(x, y, col);
        }
    }
    return img;
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 24)); // NOI18N
        jLabel1.setText("BILLING FORM");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(540, 10, 210, 60);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Choose Categories");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(130, 80, 170, 30);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(70, 130, 330, 430);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Choose Products");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(570, 70, 170, 50);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(470, 130, 330, 440);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Your Cart");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(1020, 70, 150, 50);

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
        jScrollPane3.setViewportView(jTable1);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(880, 130, 400, 310);

        jLabel5.setText("Grand Total");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(940, 450, 90, 40);
        getContentPane().add(jLabel6);
        jLabel6.setBounds(1110, 470, 110, 30);

        jButton1.setText("Generate Bill");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(1000, 516, 190, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AdminGetDetails obj=new AdminGetDetails(al);
    }//GEN-LAST:event_jButton1ActionPerformed

    /*
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
            java.util.logging.Logger.getLogger(AdminGenerateBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminGenerateBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminGenerateBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminGenerateBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminGenerateBill().setVisible(true);
                AdminBillHistory obj1=new AdminBillHistory();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
