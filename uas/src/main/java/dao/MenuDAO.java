package dao;

import model.Menu;
import util.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    
    public List<Menu> getAllMenu() {
        List<Menu> listMenu = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Menu menu = new Menu();
                menu.setIdMenu(rs.getInt("id_menu"));
                menu.setNamaMenu(rs.getString("nama_menu"));
                menu.setHarga(rs.getBigDecimal("harga"));
                menu.setKategori(rs.getString("kategori"));
                menu.setStok(rs.getInt("stok"));
                listMenu.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listMenu;
    }
    
    public boolean tambahMenu(Menu menu) {
        String sql = "INSERT INTO menu (nama_menu, harga, kategori, stok) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, menu.getNamaMenu());
            pstmt.setBigDecimal(2, menu.getHarga());
            pstmt.setString(3, menu.getKategori());
            pstmt.setInt(4, menu.getStok());
            
            int row = pstmt.executeUpdate();
            return row > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}