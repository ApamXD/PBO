package dao;

import model.Pesanan;
import model.DetailPesanan;
import util.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PesananDAO {

    // Simpan Pesanan + Detail Pesanan sekaligus
    public boolean simpanPesanan(Pesanan pesanan) {
        String sqlPesanan = "INSERT INTO pesanan (id_pelanggan, no_meja, total_harga) VALUES (?, ?, ?)";
        String sqlDetail = "INSERT INTO detail_pesanan (id_pesanan, id_menu, qty, harga_satuan, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection()) {
            conn.setAutoCommit(false); // Transaction

            // Simpan Pesanan
            try (PreparedStatement pstmt = conn.prepareStatement(sqlPesanan, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, pesanan.getIdPelanggan());
                pstmt.setString(2, pesanan.getNoMeja());
                pstmt.setBigDecimal(3, pesanan.getTotalHarga());

                pstmt.executeUpdate();

                // Ambil ID Pesanan yang baru dibuat
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int idPesananBaru = rs.getInt(1);
                    pesanan.setIdPesanan(idPesananBaru);

                    // Simpan Detail Pesanan
                    try (PreparedStatement pstmtDetail = conn.prepareStatement(sqlDetail)) {
                        for (DetailPesanan detail : pesanan.getDetailList()) {
                            pstmtDetail.setInt(1, idPesananBaru);
                            pstmtDetail.setInt(2, detail.getIdMenu());
                            pstmtDetail.setInt(3, detail.getQty());
                            pstmtDetail.setBigDecimal(4, detail.getHargaSatuan());
                            pstmtDetail.setBigDecimal(5, detail.getSubtotal());
                            pstmtDetail.executeUpdate();
                        }
                    }
                }
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Ambil semua pesanan (JOIN ke pelanggan supaya nama pelanggan ikut tampil)
    public List<Pesanan> getAllPesanan() {
        List<Pesanan> list = new ArrayList<>();
        String sql = "SELECT p.*, pl.nama_pelanggan FROM pesanan p " +
                     "JOIN pelanggan pl ON p.id_pelanggan = pl.id_pelanggan " +
                     "ORDER BY p.tanggal DESC";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pesanan p = new Pesanan();
                p.setIdPesanan(rs.getInt("id_pesanan"));
                p.setTanggal(rs.getTimestamp("tanggal"));
                p.setIdPelanggan(rs.getInt("id_pelanggan"));
                p.setNamaPelanggan(rs.getString("nama_pelanggan"));
                p.setNoMeja(rs.getString("no_meja"));
                p.setTotalHarga(rs.getBigDecimal("total_harga"));
                p.setStatus(rs.getString("status"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
