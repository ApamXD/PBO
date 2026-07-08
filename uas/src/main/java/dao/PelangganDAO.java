package dao;

import model.Pelanggan;
import util.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganDAO {

    public List<Pelanggan> getAllPelanggan() {
        List<Pelanggan> listPelanggan = new ArrayList<>();
        String sql = "SELECT * FROM pelanggan";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pelanggan p = new Pelanggan();
                p.setIdPelanggan(rs.getInt("id_pelanggan"));
                p.setNamaPelanggan(rs.getString("nama_pelanggan"));
                p.setNoHp(rs.getString("no_hp"));
                listPelanggan.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPelanggan;
    }

    public boolean tambahPelanggan(Pelanggan pelanggan) {
        String sql = "INSERT INTO pelanggan (nama_pelanggan, no_hp) VALUES (?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pelanggan.getNamaPelanggan());
            pstmt.setString(2, pelanggan.getNoHp());

            int row = pstmt.executeUpdate();
            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
