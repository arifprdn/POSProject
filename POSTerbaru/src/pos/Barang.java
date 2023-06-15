/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos;

import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author Tento
 */
public class Barang {
    private String Kode;
    private String nama;
    private float Harga;
    
    public String getKode() {
        return Kode;
    }

    public void setKode(String Kode) {
        this.Kode = Kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public float getHarga() {
        return Harga;
    }

    public void setHarga(float Harga) {
        this.Harga = Harga;
    }
    
    
    static ArrayList<Barang> daftarBarang;
    
    public static void loadItemFromDB(){
        daftarBarang=new ArrayList<Barang>();
     Barang barang; 
    try{
        Statement stmt=DBconnector.connection.createStatement();
        String sql="SELECT * FROM barang";
        
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next()){
            barang=new Barang();
            barang.Kode=Integer.toString(rs.getInt("Kode"));
            barang.nama=rs.getString("nama");
            barang.Harga=rs.getFloat("harga");
            daftarBarang.add(barang);
        }
    }
    catch(Exception ex){
        System.out.println(ex);
    }
    }
    
    
}


