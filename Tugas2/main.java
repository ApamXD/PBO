package Tugas2;

public class main {
    public static void main(String[] args) {

        Mahasiswa mhs1 = new Mahasiswa();
        mhs1.setNama("Sahrawardi");
        mhs1.setNpm("2410010497");
        mhs1.setUmur(21);
        mhs1.setIpk(3.9);
        mhs1.setJurusan("Teknik Informatika");

        
        mhs1.ubahIPK(3.8);

        mhs1.tampilkanInfo();   
        System.out.println("Predikat : " + mhs1.getPredikat());
        System.out.println("Status   : " + (mhs1.isLulus() ? "Lulus" : "Tidak Lulus"));

        System.out.println();


        Mahasiswa mhs2 = new Mahasiswa(
            "Hendra", "2410010501",
            25, 2.75, "Teknik Informatika"
        );


        mhs2.tampilkanInfo();   
        System.out.println("Predikat : " + mhs2.getPredikat());
        System.out.println("Status   : " + (mhs2.isLulus() ? "Lulus" : "Tidak Lulus"));

        System.out.println();


    }
}
