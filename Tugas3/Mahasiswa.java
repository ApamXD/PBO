public class Mahasiswa {

    private String nim;
    private String nama;
    private String jurusan;
    private double ipk;
    private int semester;

    public Mahasiswa() {
        this.nim      = "000000";
        this.nama     = "Tidak Diketahui";
        this.jurusan  = "Tidak Diketahui";
        this.ipk      = 0.0;
        this.semester = 1;
        System.out.println("[Constructor 1] Objek Mahasiswa dibuat dengan nilai default.");
    }

    public Mahasiswa(String nim, String nama) {
        this.nim      = nim;
        this.nama     = nama;
        this.jurusan  = "Belum Ditentukan";
        this.ipk      = 0.0;
        this.semester = 1;
        System.out.println("[Constructor 2] Objek Mahasiswa dibuat dengan NPM dan Nama.");
    }

    public Mahasiswa(String nim, String nama, String jurusan, double ipk, int semester) {
        this.nim      = nim;
        this.nama     = nama;
        this.jurusan  = jurusan;
        this.ipk      = ipk;
        this.semester = semester;
        System.out.println("[Constructor 3] Objek Mahasiswa dibuat dengan semua atribut.");
    }

    public void tampilkanInfo() {
        System.out.println("\n----- Info Mahasiswa -----");
        System.out.println("NPM      : " + nim);
        System.out.println("Nama     : " + nama);
        System.out.println("Jurusan  : " + jurusan);
        System.out.println("IPK      : " + ipk);
        System.out.println("Semester : " + semester);
        System.out.println("--------------------------");
    }

    public void create() {
        String sql = "INSERT INTO mahasiswa (npm, nama, jurusan, ipk, semester) "
                   + "VALUES ('" + nim + "', '" + nama + "', '"
                   + jurusan + "', " + ipk + ", " + semester + ");";
        System.out.println("\n[CREATE] SQL yang dieksekusi:");
        System.out.println("  >> " + sql);
        System.out.println("  >> Data mahasiswa berhasil ditambahkan ke database.");
    }

    public void update(String kolom, String nilaiBaru) {
        String sql = "UPDATE mahasiswa SET " + kolom + " = '" + nilaiBaru
                   + "' WHERE npm = '" + nim + "';";
        System.out.println("\n[UPDATE] SQL yang dieksekusi:");
        System.out.println("  >> " + sql);
        System.out.println("  >> Data berhasil diperbarui.");

        switch (kolom) {
            case "nama"    -> this.nama    = nilaiBaru;
            case "jurusan" -> this.jurusan = nilaiBaru;
            case "ipk"     -> this.ipk     = Double.parseDouble(nilaiBaru);
            case "semester"-> this.semester= Integer.parseInt(nilaiBaru);
        }
    }

    public void delete() {
        String sql = "DELETE FROM mahasiswa WHERE npm = '" + nim + "';";
        System.out.println("\n[DELETE] SQL yang dieksekusi:");
        System.out.println("  >> " + sql);
        System.out.println("  >> Data mahasiswa dengan NPM " + nim + " berhasil dihapus.");
    }

    public String read() {
        String sql = "SELECT * FROM mahasiswa WHERE npm = '" + nim + "';";
        System.out.println("\n[READ] SQL yang dieksekusi:");
        System.out.println("  >> " + sql);
        System.out.println("  >> Data mahasiswa ditemukan.");
        return sql;
    }

    public double getIpk() {
        return ipk;
    }

    public String getStatusKelulusan() {
        if (ipk >= 3.51) return "Cumlaude";
        else if (ipk >= 3.01) return "Sangat Memuaskan";
        else if (ipk >= 2.76) return "Memuaskan";
        else if (ipk >= 2.00) return "Cukup";
        else return "Tidak Lulus";
    }

    public boolean isAktif() {
        return semester >= 1 && semester <= 14;
    }

    public String getNim()           { return nim; }
    public String getNama()          { return nama; }
    public String getJurusan()       { return jurusan; }
    public int    getSemester()      { return semester; }
    public void   setIpk(double ipk) { this.ipk = ipk; }
    public void   setSemester(int s) { this.semester = s; }
}
