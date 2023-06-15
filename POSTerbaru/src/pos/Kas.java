/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pos;


import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Random;

/**
 *
 * @author user
 */

public class Kas extends javax.swing.JFrame {

    /**
     * Creates new form Kas
     */
    
    static String invoiceID;  
    float dibayar;
    static float kembalian;
    float totalBelanja;   
    
    Pulsa pulsa = new Pulsa();
    Token token = new Token();
    static Posframe posframe = new Posframe();
    
    public void kembalian(){
        dibayar = Float.valueOf(dibayarTextField.getText());
        totalBelanja = Float.valueOf(TotalBelanjaTextField.getText());
        kembalian =  dibayar - totalBelanja;
    }
    
    
    
    
    public Kas() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TotalBelanjaTextField = new javax.swing.JTextField();
        dibayarTextField = new javax.swing.JTextField();
        KembalianTextField = new javax.swing.JTextField();
        BayarButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(445, 291));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TotalBelanjaTextField.setEditable(false);
        TotalBelanjaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalBelanjaTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(TotalBelanjaTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 130, -1));
        getContentPane().add(dibayarTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 130, -1));

        KembalianTextField.setEditable(false);
        getContentPane().add(KembalianTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 130, -1));

        BayarButton.setText("BAYAR");
        BayarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BayarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(BayarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, 110, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/Cash (1).png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BayarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BayarButtonActionPerformed
        // TODO add your handling code here:
        kembalian();
        KembalianTextField.setText(String.valueOf(kembalian));
        
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        Transaksi.daftarBelanja.add(new String[] {posframe.getDaftarBelanja(),TotalBelanjaTextField.getText(),dibayarTextField.getText(),KembalianTextField.getText(),timeStamp});  
            if (kembalian < 0) {
                 // this gets caught in the catch block
                    System.out.println("dijalankan");
                    JOptionPane.showMessageDialog(rootPane, "Uang tidak cukup!");
                    posframe.setIndexOfTransaksi(posframe.getIndexOfTransaksi() + 1);
                }else{
        try {
                // Buat pernyataan SQL untuk memasukkan data transaksi ke dalam database
                //Statement stmt=DBconnector.connection.createStatement();
                String sql = "INSERT INTO transaksi (daftar_belanja, total_belanja, jumlah_bayar,kembalian,jenis_pembayaran,invoice_id) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = DBconnector.connection.prepareStatement(sql);
                System.out.println(posframe.getIndexOfTransaksi());
                statement.setString(1, Transaksi.daftarBelanja.get(posframe.getIndexOfTransaksi())[0]);
                statement.setFloat(2, Float.valueOf(Transaksi.daftarBelanja.get(posframe.getIndexOfTransaksi())[1]));
                statement.setFloat(3, Float.valueOf(Transaksi.daftarBelanja.get(posframe.getIndexOfTransaksi())[2]));
                statement.setFloat(4, Float.valueOf(Transaksi.daftarBelanja.get(posframe.getIndexOfTransaksi())[3]));
                statement.setString(5, "Kas");
                statement.setString(6, invoiceID);
                statement.executeUpdate();

                JOptionPane.showMessageDialog(rootPane, "Pembayaran Berhasil !");
                posframe.setDaftarBelanja("");
                posframe.setIndexOfTransaksi(posframe.getIndexOfTransaksi() + 1);
                System.out.println(posframe.getIndexOfTransaksi());
                
                System.out.println(Posframe.getIsBuyToken());
                
                if (Posframe.getIsBuyToken()){
                    Random random = new Random();
                    int min = 1000; // Minimum 9-digit number (inclusive)
                    int max = 9999;
                    int empatdigit1 = random.nextInt(max - min + 1) + min;
                    int empatdigit2 = random.nextInt(max - min + 1) + min;
                    int empatdigit3 = random.nextInt(max - min + 1) + min;
                    int empatdigit4 = random.nextInt(max - min + 1) + min;
                    int empatdigit5 = random.nextInt(max - min + 1) + min;

                    token.setToken(Integer.toString(empatdigit1)+" "+Integer.toString(empatdigit2)+" "+Integer.toString(empatdigit3)+" "+Integer.toString(empatdigit4)+" "+Integer.toString(empatdigit5));

                    JOptionPane.showMessageDialog(rootPane, "Token : "+token.getToken());
                }
                posframe.setIsBuyToken(false);

                TotalBelanjaTextField.setText("");
                KembalianTextField.setText("");
                dibayarTextField.setText("0");
                totalBelanja = 0;
                posframe.setTotalHarga(0);
                setVisible(false);               
                posframe.setVisible(true);
                




            } catch (SQLException e) {
                System.out.println("Salah coy");
                e.printStackTrace();
            }
            }

    }//GEN-LAST:event_BayarButtonActionPerformed

    private void TotalBelanjaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalBelanjaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalBelanjaTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(Kas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BayarButton;
    private javax.swing.JTextField KembalianTextField;
    public javax.swing.JTextField TotalBelanjaTextField;
    private javax.swing.JTextField dibayarTextField;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}