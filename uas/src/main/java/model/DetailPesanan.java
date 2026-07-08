package model;

import java.math.BigDecimal;

public class DetailPesanan {
    private int idDetail;
    private int idPesanan;
    private int idMenu;
    private int qty;
    private BigDecimal hargaSatuan;
    private BigDecimal subtotal;

    public DetailPesanan() {}

    public DetailPesanan(int idMenu, int qty, BigDecimal hargaSatuan) {
        this.idMenu = idMenu;
        this.qty = qty;
        this.hargaSatuan = hargaSatuan;
        this.subtotal = hargaSatuan.multiply(BigDecimal.valueOf(qty));
    }

    // Getter dan Setter lengkap
    public int getIdDetail() { return idDetail; }
    public void setIdDetail(int idDetail) { this.idDetail = idDetail; }

    public int getIdPesanan() { return idPesanan; }
    public void setIdPesanan(int idPesanan) { this.idPesanan = idPesanan; }

    public int getIdMenu() { return idMenu; }
    public void setIdMenu(int idMenu) { this.idMenu = idMenu; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public BigDecimal getHargaSatuan() { return hargaSatuan; }
    public void setHargaSatuan(BigDecimal hargaSatuan) { this.hargaSatuan = hargaSatuan; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
}