/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author user
 */
public class Makanan extends Barang {

        private Date kadaluarsa;
        
        public Date getKadaluarsa() {
            return kadaluarsa;
        }

        public void setKadaluarsa(Date kadaluarsa) {
            this.kadaluarsa = kadaluarsa;
        }
        
        public static ArrayList<Makanan> daftarMakanan;
        
        public static void loadItemFromDB(){
                daftarMakanan=new ArrayList<>();
            try{
                Statement stmt=DBconnector.connection.createStatement();
                String sql="SELECT * FROM makanan";

                ResultSet rs=stmt.executeQuery(sql);
                while(rs.next()){
                    Makanan makanan=new Makanan();
                    makanan.setKode(Integer.toString(rs.getInt("Kode")));
                    makanan.setNama(rs.getString("nama"));
                    makanan.setHarga(rs.getFloat("harga"));
                    makanan.kadaluarsa = rs.getDate("Kadaluarsa");
                    System.out.println(makanan.kadaluarsa);
                    daftarMakanan.add(makanan);
                }
            }
            catch(Exception ex){
                System.out.println(ex);
            }
            }
        
    }
