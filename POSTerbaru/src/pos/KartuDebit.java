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
public class KartuDebit extends javax.swing.JFrame {

    /**
     * Creates new form KartuDebit
     */
    
    String bank;
    String nomorKartu;
    static String invoiceID;
    
    Token token = new Token();
    static Posframe posframe = Kas.posframe;
    
    
    public KartuDebit() {
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

        BankComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        NoKartuTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TotalBelanjaTextField = new javax.swing.JTextField();
        BayarButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BankComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mandiri", "BRI", "BNI", "BCA", "BSI" }));
        getContentPane().add(BankComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, 224, -1));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 38, 57, -1));
        getContentPane().add(NoKartuTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, 224, -1));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 190, 54, -1));

        TotalBelanjaTextField.setEditable(false);
        getContentPane().add(TotalBelanjaTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 227, -1));

        BayarButton.setText("BAYAR");
        BayarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BayarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(BayarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 90, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/Debit (1).png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 390));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BayarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BayarButtonActionPerformed
        // TODO add your handling code here:
        bank = (String) BankComboBox.getSelectedItem();
        nomorKartu = NoKartuTextField.getText();
        
        if (NoKartuTextField.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Masukkan Nomor Kartu Anda !");
        }else{
        
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            Transaksi.daftarBelanja.add(new String[] {posframe.getDaftarBelanja(),TotalBelanjaTextField.getText(),TotalBelanjaTextField.getText(),"0",timeStamp});

            try {
                    // Buat pernyataan SQL untuk memasukkan data transaksi ke dalam database
                    //Statement stmt=DBconnector.connection.createStatement();
                    String sql = "INSERT INTO transaksi (daftar_belanja, total_belanja, jumlah_bayar,kembalian,jenis_pembayaran,invoice_id) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = DBconnector.connection.prepareStatement(sql);
                    System.out.println(posframe.getIndexOfTransaksi());
                    statement.setString(1, Transaksi.daftarBelanja.get(posframe.getIndexOfTransaksi())[0]);
                    statement.setFloat(2, Float.valueOf(Transaksi.daftarBelanja.get(posframe.getIndexOfTransaksi())[1]));
                    statement.setFloat(3, Float.valueOf(Transaksi.daftarBelanja.get(posframe.getIndexOfTransaksi())[1]));
                    statement.setFloat(4, 0.f);
                    statement.setString(5, "Debit "+bank+" "+nomorKartu);
                    statement.setString(6, invoiceID);
                    statement.executeUpdate();

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
                    NoKartuTextField.setText("");
                    posframe.setTotalHarga(0);
                    setVisible(false);               
                    posframe.setVisible(true);





                } catch (SQLException e) {
                    System.out.println("Salah coy");
                    e.printStackTrace();
                }
        }
        
    }//GEN-LAST:event_BayarButtonActionPerformed

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
            java.util.logging.Logger.getLogger(KartuDebit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KartuDebit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KartuDebit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KartuDebit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KartuDebit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BankComboBox;
    private javax.swing.JButton BayarButton;
    private javax.swing.JTextField NoKartuTextField;
    public javax.swing.JTextField TotalBelanjaTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}