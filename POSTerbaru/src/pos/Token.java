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
public class Token extends Barang {
        private String noMeteran;
        private float nominal;
        private String token;
        
        public String getNoMeteran() {
            return noMeteran;
        }

        public void setNoMeteran(String noMeteran) {
            this.noMeteran = noMeteran;
        }
        
        public float getNominal() {
            return nominal;
        }

        public void setNominal(float nominal) {
            this.nominal = nominal;
        }
        
        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        static ArrayList<Token> daftarToken;
        public static void loadItemFromDB(){
            daftarToken=new ArrayList<Token>();
         Token tokenku; 
        try{
            Statement stmt=DBconnector.connection.createStatement();
            String sql="SELECT * FROM token";

            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                tokenku=new Token();
                tokenku.setKode(Integer.toString(rs.getInt("Kode")));
                tokenku.setNama(rs.getString("nama"));
                tokenku.setHarga(rs.getFloat("harga"));
                tokenku.setNominal(rs.getFloat("nominal"));
                daftarToken.add(tokenku);
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        }
        
    }