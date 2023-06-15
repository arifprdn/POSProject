/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pos;

import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javax.swing.ImageIcon;

/**
 *
 * @author user
 */
public class QRIS extends javax.swing.JFrame {
    static String invoiceID;
    
    Pulsa pulsa = new Pulsa();
    Token token = new Token();
    Posframe posframe = Kas.posframe;
    float totalBelanja;
    
    
    
    
    
    public static void generateQR(String konten, String fileName){
        
        try {
            
                    String filePath = "../"+fileName; 
                    int width = 300; // Lebar QR code
                    int height = 300; // Tinggi QR code
                    String fileType = "png"; // Format file QR code (png, jpg, dll.)
                    File qrFile = new File(filePath);
                    // Delete qr code sebelumnya
                    qrFile.delete();
                    // Membuat QR code writer
                    QRCodeWriter qrCodeWriter = new QRCodeWriter();

                    // Mengatur parameter QR code seperti level koreksi kesalahan dan karakter encoding
                    java.util.Map<EncodeHintType, Object> hints = new java.util.HashMap<>();
                    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

                    // Membuat bit matrix yang mewakili QR code
                    BitMatrix bitMatrix = qrCodeWriter.encode(konten, BarcodeFormat.QR_CODE, width, height, hints);

                    // Mengubah bit matrix menjadi gambar QR code menggunakan BufferedImage
                    BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                        }
                    }

                    // Menyimpan gambar QR code ke dalam file
                    ImageIO.write(qrImage, fileType, qrFile);
                    System.out.println("QR code berhasil dibuat!");
                    
                    String iconPath = "../"+fileName; // Replace with the path to your icon image

                    ImageIcon icon = new ImageIcon(iconPath);
                    QRISImage.setIcon(icon);
  
                } catch (WriterException | IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
    }
    
    
    
    
        

    /**
     * Creates new form QRIS
     */
    public QRIS() {
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

        QRISImage = new javax.swing.JLabel();
        AcceptButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(556, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(QRISImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 280, 290));

        AcceptButton.setText("Accept Payment");
        AcceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcceptButtonActionPerformed(evt);
            }
        });
        getContentPane().add(AcceptButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 610, 188, 51));

        CancelButton.setText("Cancel Payment");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(CancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 690, 188, 45));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos/Qris (1).png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        posframe.setVisible(true);
        posframe.setDaftarBelanja("");
        posframe.setIndexOfTransaksi(posframe.getIndexOfTransaksi() + 1);
        posframe.setIsBuyToken(false);
        posframe.setTotalHarga(0);
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void AcceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcceptButtonActionPerformed
        // TODO add your handling code here:
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        Transaksi.daftarBelanja.add(new String[] {posframe.getDaftarBelanja(),String.valueOf(totalBelanja),String.valueOf(totalBelanja),"0",timeStamp});

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
                statement.setString(5, "QRIS");
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
                posframe.setTotalHarga(0);
                setVisible(false);               
                posframe.setVisible(true);
        } catch (SQLException e) {
                    System.out.println("Salah coy");
                    e.printStackTrace();
                }
    }//GEN-LAST:event_AcceptButtonActionPerformed

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
            java.util.logging.Logger.getLogger(QRIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QRIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QRIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QRIS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QRIS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AcceptButton;
    private javax.swing.JButton CancelButton;
    private static javax.swing.JLabel QRISImage;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}