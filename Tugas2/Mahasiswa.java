package Tugas2;

public class Mahasiswa {
    private String  nama;    
    private String  npm;    
    private int     umur;   
    private double  ipk;     
    private String  jurusan; 

    public Mahasiswa() {                  
        nama    = "";
        npm     = "";
        umur    = 0;
        ipk     = 0.0;
        jurusan = "Teknik Informatika";
    }
    
    
    public Mahasiswa(String nama, String nim,    
                     int umur, double ipk,
                     String jurusan) {
        this.nama    = nama;
        this.npm     = nim;
        this.umur    = umur;
        this.ipk     = ipk;
        this.jurusan = jurusan;
        
    }
    
    
    public String getNama() {
        return nama;
    }

    public String getNpm() {
        return npm;
    }

    public int getUmur() {
        return umur;
    }

    public double getIpk() {
        return ipk;
    }

    public String getJurusan() {
        return jurusan;
    }

    
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public void setIpk(double ipk) {
        this.ipk = ipk;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }


    public void tampilkanInfo() {              
        System.out.println("===== Data Mahasiswa =====");
        System.out.println("Nama    : " + nama);
        System.out.println("NIM     : " + npm);
        System.out.println("Umur    : " + umur + " tahun");
        System.out.println("IPK     : " + ipk);
        System.out.println("Jurusan : " + jurusan);
    }

    public void ubahIPK(double ipkBaru) {      
        if (ipkBaru >= 0.0 && ipkBaru <= 4.0) {
            this.ipk = ipkBaru;
            System.out.println("IPK berhasil diperbarui.");
        } else {
            System.out.println("IPK tidak valid!");
        }
    }

    public String getPredikat() {             
        if      (ipk >= 3.5) return "Cumlaude";
        else if (ipk >= 3.0) return "Sangat Memuaskan";
        else if (ipk >= 2.5) return "Memuaskan";
        else                   return "Cukup";
    }

    public boolean isLulus() {                
        return ipk >= 2.0;
    }
}
