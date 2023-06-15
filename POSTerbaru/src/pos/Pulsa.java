/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Pulsa extends Barang {
        private String operator;
        private String noTelp;
        private float nominal;
        
        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getNoTelp() {
            return noTelp;
        }

        public void setNoTelp(String noTelp) {
            this.noTelp = noTelp;
        }

        public float getNominal() {
            return nominal;
        }

        public void setNominal(float nominal) {
            this.nominal = nominal;
        }
        

        static ArrayList<Pulsa> daftarPulsa;
        public static void loadItemFromDB(){
            daftarPulsa=new ArrayList<Pulsa>();
         Pulsa pulsa; 
        try{
            Statement stmt=DBconnector.connection.createStatement();
            String sql="SELECT * FROM pulsa";

            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                pulsa=new Pulsa();
                pulsa.setKode(Integer.toString(rs.getInt("Kode")));
                pulsa.setNama(rs.getString("nama"));
                pulsa.setHarga(rs.getFloat("harga"));
                pulsa.operator=rs.getString("operator");
                pulsa.nominal=rs.getFloat("nominal");
                daftarPulsa.add(pulsa);
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        }
        
    }