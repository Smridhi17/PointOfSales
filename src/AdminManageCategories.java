
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel; 
public final class AdminManageCategories extends javax.swing.JFrame {
    ArrayList<Categories> al;
    mytablemodel tm;
    
    /**
     * Creates new form AdminManageCategories
     */
    public AdminManageCategories() {
        al=new ArrayList<>();
        tm=new mytablemodel(); 
        initComponents();
        getContentPane().setBackground(Color.PINK);
        jTable1.setModel(tm);
       
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        int width=(int) d.getWidth();
        int height=(int) d.getHeight();
        setSize(width,height);
        setTitle("Manage Categories");
        ShowAlreadyAddedCategories();
        
        
    }
    int count;
    void ShowAlreadyAddedCategories()
    {
        try
        {
    ResultSet rs=   DBloader.executeQuery("select * from categories", "pos2024");
    al.clear();
       while(rs.next())
       {   count++;
           String catname=rs.getString("catname");
           String desc=rs.getString("desc");
           al.add(new Categories(catname,desc));
       }
    tm.fireTableDataChanged();
    
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    }
    class mytablemodel extends AbstractTableModel
    {

        @Override
        public int getRowCount() {
            return al.size();
             
        }

        @Override
        public int getColumnCount() {
            return 2;
           
        }

        @Override
        public Object getValueAt(int i, int j) {
           Categories obj= al.get(i);
           if(j==0)
           {
           return obj.catname;
           }
           else if(j==1)
           {
            return obj.desc;
        }
           else
           {
               return 0;
           }
    }
        @Override
        public String getColumnName(int j)
        {
            String col[]={"Category Name","Description"};
            return col[j];
        }
            
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jFileChooser2 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Manage Your Categories");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(370, 10, 288, 61);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Add Categories");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(210, 80, 150, 25);

        jLabel3.setText("Category Name:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(100, 120, 104, 28);
        getContentPane().add(tf1);
        tf1.setBounds(246, 120, 180, 26);

        jLabel4.setText("Description:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(100, 190, 101, 40);

        ta1.setColumns(20);
        ta1.setRows(5);
        jScrollPane1.setViewportView(ta1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(240, 200, 220, 87);

        jLabel5.setText("Photo:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(100, 330, 40, 32);

        jLabel6.setText("jLabel6");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(240, 340, 120, 130);

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(250, 510, 83, 26);

        jButton2.setText("Choose");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(380, 350, 87, 26);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("View Categories");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(830, 70, 172, 24);

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
        jScrollPane2.setViewportView(jTable1);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(690, 120, 420, 360);

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(880, 500, 83, 26);

        pack();
    }// </editor-fold>//GEN-END:initComponents
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
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String catname= tf1.getText();
        String desc=ta1.getText();
        ResultSet rs;
        rs = DBloader.executeQuery("select * from categories where catname='"+catname+"'", "pos2024");
 
        try{
        if(rs.next())
        {
        JOptionPane.showMessageDialog(this, "This category is already added");
        
        
        }
        else
        {
             String path = SaveFile.saveFile(selectedfile);
        rs.moveToInsertRow();
        rs.updateString("catname", catname);
        rs.updateString("desc", desc);
        rs.updateString("photo",path);
        
        
        
        rs.insertRow();
        JOptionPane.showMessageDialog(this, "Category added successfully");
        ShowAlreadyAddedCategories();
        }
            
            }
        catch(Exception ex){
        ex.printStackTrace();
        
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    File selectedfile;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       int ans= jFileChooser1.showOpenDialog(this);
       if(ans==JFileChooser.APPROVE_OPTION)
       {
         selectedfile=jFileChooser1.getSelectedFile();
        
         try
         {
              BufferedImage bi = ImageIO.read(selectedfile);
                
                bi = scale(bi,140 , 80);
                
                jLabel6.setIcon(new ImageIcon(bi));
             
             
         }
         catch(Exception ex)
         {
         }
           String selected = null;
       }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int column=0;
        int row=jTable1.getSelectedRow();
        String catname=(String) jTable1.getModel().getValueAt(row, column);
        try{
        ResultSet rs= DBloader.executeQuery("select * from categories where catname= '" +catname+"'","pos2024");
       if(rs.next())
       {
           rs.deleteRow();
           ShowAlreadyAddedCategories();
           JOptionPane.showMessageDialog(this,"Category Deleted Successfully");
           
       }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(AdminManageCategories.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminManageCategories.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminManageCategories.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminManageCategories.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminManageCategories().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea ta1;
    private javax.swing.JTextField tf1;
    // End of variables declaration//GEN-END:variables
}
