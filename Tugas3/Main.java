public class Main {

    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("         PROGRAM MANAJEMEN DATA MAHASISWA         ");
        System.out.println("===========================================");

        System.out.println("\n======================================");
        System.out.println("  CONSTRUCTOR 1 : Default");
        System.out.println("=====================================");
        Mahasiswa mhs1 = new Mahasiswa();
        mhs1.tampilkanInfo();
        mhs1.create();
        mhs1.read();
        System.out.println("  Status Kelulusan : " + mhs1.getStatusKelulusan());
        System.out.println("  Aktif            : " + mhs1.isAktif());

        System.out.println("\n======================================");
        System.out.println("  CONSTRUCTOR 2 : NPM + Nama");
        System.out.println("======================================");
        Mahasiswa mhs2 = new Mahasiswa("2410010497", "Sahrawardi");
        mhs2.tampilkanInfo();
        mhs2.create();
        mhs2.read();
        mhs2.update("jurusan", "Teknologi Informasi");
        mhs2.update("ipk", "3.20");
        mhs2.tampilkanInfo();
        System.out.println("  Status Kelulusan : " + mhs2.getStatusKelulusan());
        System.out.println("  Aktif            : " + mhs2.isAktif());
        System.out.println("  IPK              : " + mhs2.getIpk());

        System.out.println("\n======================================");
        System.out.println("  CONSTRUCTOR 3 : Semua Atribut");
        System.out.println("======================================");
        Mahasiswa mhs3 = new Mahasiswa(
                "2410010497",
                "Sahrawardi",
                "Teknologi Informasi",
                3.75,
                3
        );
        mhs3.tampilkanInfo();
        mhs3.create();

        String queryRead = mhs3.read();
        System.out.println("  Query dikembalikan : " + queryRead);

        mhs3.update("semester", "4");
        mhs3.tampilkanInfo();
        System.out.println("  Status Kelulusan : " + mhs3.getStatusKelulusan());
        System.out.println("  Aktif            : " + mhs3.isAktif());

        mhs3.delete();

        System.out.println("\n====================================================");
        System.out.println("         CLASS TURUNAN : MahasiswaBerprestasi     ");
        System.out.println("====================================================");

        MahasiswaBerprestasi mbp = new MahasiswaBerprestasi(
                "2410010497",
                "Sahrawardi",
                "Teknologi Informasi",
                3.90,
                3,
                "Juara 1 Olimpiade Nasional Programming",
                "Beasiswa Unggulan Kemendikbud"
        );

        mbp.tampilkanInfo();
        mbp.create();
        mbp.read();
        mbp.update("ipk", "3.95");

        mbp.tampilkanPrestasi();

        String konfirmasi = mbp.updateBeasiswa("Beasiswa LPDP S2 Dalam Negeri");
        System.out.println("  Konfirmasi : " + konfirmasi);

        mbp.delete();

        System.out.println("\n======================================");
        System.out.println("  VERIFIKASI DATA OBJEK TURUNAN");
        System.out.println("======================================");
        System.out.println("  IPK              : " + mbp.getIpk());
        System.out.println("  Status Kelulusan : " + mbp.getStatusKelulusan());
        System.out.println("  Aktif            : " + mbp.isAktif());
        System.out.println("  Prestasi         : " + mbp.getPrestasi());
        System.out.println("  Beasiswa Baru    : " + mbp.getBeasiswa());

        System.out.println("\n====================================================");
    }
}
