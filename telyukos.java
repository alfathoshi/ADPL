public class User {
    public String role {get; set;}
    public void selectRole(string role){
        //Memilih Role
    }
}

public class Filter {
    public void filterHarga(float price){
        //Filter berdasarkan harga
    }
    public void filterJarak(float distance){
        //Filter berdasarkan jarak
    }
    public void filterFasilitas(string facility){
        //Filter berdasarkan fasilitas
    }
}


public abstract class Kos {
    private String namaKos;
    private int hargaSewa;

    public Kos(String namaKos, int hargaSewa) {
        this.namaKos = namaKos;
        this.hargaSewa = hargaSewa;
    }

    public String getNamaKos() {
        return namaKos;
    }

    public int getHargaSewa() {
        return hargaSewa;
    }

    public abstract int hitungBiayaSewa(int lamaSewa);
}

public class SistemSewaKos {
    public int hitungTotalBiayaSewa(List<Kos> daftarKos, int lamaSewa) {
        int totalBiaya = 0;
        for (Kos kos : daftarKos) {
            totalBiaya += kos.hitungBiayaSewa(lamaSewa);
        }
        return totalBiaya;
    }
}

class Mahasiswa implements User {

    private String nama;
    private String role;
    private String alamat;
    private String email;


    public Mahasiswa(String nama, String role, String alamat, String email) {
        this.nama = nama;
        this.role = role;
        this.alamat = alamat;
        this.email = email;
    }

    @Override
    public String getNama() {
        return nama;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public String getAlamat() {
        return alamat;
    }

    @Override
    public String getEmail() {
        return email;
    }
}

class PemilikKos implements User {

    private String nama;
    private String role;
    private String alamat;
    private String email;

    public PemilikKos(String nama, String role, String alamat, String email) {
        this.nama = nama;
        this.role = role;
        this.alamat = alamat;
        this.email = email;
    }

    @Override
    public String getNama() {
        return nama;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public String getAlamat() {
        return alamat;
    }

    @Override
    public String getEmail() {
        return email;
    }
}

interface IKos {
    String getNamaKos();
    int getHargaSewa();
}

class Kos implements IKos {
    private String namaKos;
    private int hargaSewa;

    public Kos(String namaKos, int hargaSewa) {
        this.namaKos = namaKos;
        this.hargaSewa = hargaSewa;
    }

    @Override
    public String getNamaKos() {
        return namaKos;
    }

    @Override
    public int getHargaSewa() {
        return hargaSewa;
    }
}


interface IPemesanankos {
    IKos getKos();
    int getLamaSewa();
  }
  
class Pemesanankos implements IPemesanankos {
    private final IKos kos;
    private final int lamaSewa;
  
    public Pemesanankos(IKos kos, int lamaSewa) {
      this.kos = kos;
      this.lamaSewa = lamaSewa;
    }
  
    @Override
    public IKos getKos() {
      return kos;
    }
  
    @Override
    public int getLamaSewa() {
      return lamaSewa;
    }
  }
  
class PembayaranKos {
    private final IMetodePembayaran metodePembayaran;
  
    public PembayaranKos(IMetodePembayaran metodePembayaran) {
      this.metodePembayaran = metodePembayaran;
    }
  
    public void lakukanPembayaran(IPemesanankos pemesanankos) {
      int biayaSewa = pemesanankos.getKos().getHargaSewa() * pemesanankos.getLamaSewa();
  
      if (metodePembayaran.prosesPembayaran(biayaSewa)) {
        // Kirim konfirmasi pembayaran
      } else {
        System.out.println("Pembayaran kos " + pemesanankos.getKos().getNamaKos() + " gagal!");
      }
    }
}
  


