package model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Pesanan {
    private int idPesanan;
    private Timestamp tanggal;
    private int idPelanggan;
    private String namaPelanggan; // hasil JOIN, untuk ditampilkan di tabel
    private String noMeja;
    private BigDecimal totalHarga;
    private String status;

    private List<DetailPesanan> detailList = new ArrayList<>();

    // Constructor & Getter Setter
    public Pesanan() {}

    public int getIdPesanan() { return idPesanan; }
    public void setIdPesanan(int idPesanan) { this.idPesanan = idPesanan; }

    public Timestamp getTanggal() { return tanggal; }
    public void setTanggal(Timestamp tanggal) { this.tanggal = tanggal; }

    public int getIdPelanggan() { return idPelanggan; }
    public void setIdPelanggan(int idPelanggan) { this.idPelanggan = idPelanggan; }

    public String getNamaPelanggan() { return namaPelanggan; }
    public void setNamaPelanggan(String namaPelanggan) { this.namaPelanggan = namaPelanggan; }

    public String getNoMeja() { return noMeja; }
    public void setNoMeja(String noMeja) { this.noMeja = noMeja; }

    public BigDecimal getTotalHarga() { return totalHarga; }
    public void setTotalHarga(BigDecimal totalHarga) { this.totalHarga = totalHarga; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<DetailPesanan> getDetailList() { return detailList; }
    public void setDetailList(List<DetailPesanan> detailList) { this.detailList = detailList; }
}
