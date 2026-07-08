package model;

public class Pelanggan {

    private int idPelanggan;
    private String namaPelanggan;
    private String noHp;

    public Pelanggan() {}

    public Pelanggan(String namaPelanggan, String noHp) {
        this.namaPelanggan = namaPelanggan;
        this.noHp = noHp;
    }

    public int getIdPelanggan() { return idPelanggan; }
    public void setIdPelanggan(int idPelanggan) { this.idPelanggan = idPelanggan; }

    public String getNamaPelanggan() { return namaPelanggan; }
    public void setNamaPelanggan(String namaPelanggan) { this.namaPelanggan = namaPelanggan; }

    public String getNoHp() { return noHp; }
    public void setNoHp(String noHp) { this.noHp = noHp; }

    // Dipakai supaya tampil rapi di JComboBox (mis. di TambahPesananFrame)
    @Override
    public String toString() {
        return namaPelanggan + " - " + noHp;
    }
}
