package model;

import java.math.BigDecimal;

public class Menu {
    
    private int idMenu;
    private String namaMenu;
    private BigDecimal harga;
    private String kategori;   
    private int stok;
    private String status;

    public Menu() {}

    public Menu(String namaMenu, BigDecimal harga, String kategori, int stok) {
        this.namaMenu = namaMenu;
        this.harga = harga;
        this.kategori = kategori;
        this.stok = stok;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
    return namaMenu + " - Rp " + harga;
}
}