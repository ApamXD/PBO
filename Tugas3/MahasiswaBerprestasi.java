public class MahasiswaBerprestasi extends Mahasiswa {

    private String prestasi;
    private String beasiswa;

    public MahasiswaBerprestasi(String nim, String nama, String jurusan,
                                double ipk, int semester,
                                String prestasi, String beasiswa) {
        super(nim, nama, jurusan, ipk, semester);
        this.prestasi = prestasi;
        this.beasiswa = beasiswa;
        System.out.println("[Constructor Turunan] Objek MahasiswaBerprestasi dibuat.");
    }

    public void tampilkanPrestasi() {
        System.out.println("\n===== Detail Mahasiswa Berprestasi =====");
        System.out.println("NPM       : " + getNim());
        System.out.println("Nama      : " + getNama());
        System.out.println("Jurusan   : " + getJurusan());
        System.out.println("IPK       : " + getIpk());
        System.out.println("Semester  : " + getSemester());
        System.out.println("Prestasi  : " + prestasi);
        System.out.println("Beasiswa  : " + beasiswa);
        System.out.println("Status    : " + getStatusKelulusan());

        String sql = "INSERT INTO mahasiswa_berprestasi (npm, prestasi, beasiswa) "
                   + "VALUES ('" + getNim() + "', '" + prestasi + "', '"
                   + beasiswa + "') "
                   + "ON DUPLICATE KEY UPDATE prestasi = '" + prestasi
                   + "', beasiswa = '" + beasiswa + "';";
        System.out.println("\n[SQL Prestasi] >> " + sql);
        System.out.println("========================================");
    }

    public String updateBeasiswa(String beasiswaBaru) {
        String beasiswaLama = this.beasiswa;
        this.beasiswa = beasiswaBaru;

        String sql = "UPDATE mahasiswa_berprestasi "
                   + "SET beasiswa = '" + beasiswaBaru + "' "
                   + "WHERE npm = '" + getNim() + "';";

        System.out.println("\n[UPDATE BEASISWA] SQL yang dieksekusi:");
        System.out.println("  >> " + sql);

        String hasil = "Beasiswa " + getNama() + " berhasil diubah dari ["
                     + beasiswaLama + "] menjadi [" + beasiswaBaru + "].";
        System.out.println("  >> " + hasil);
        return hasil;
    }

    public String getPrestasi() { return prestasi; }
    public String getBeasiswa() { return beasiswa; }
}
