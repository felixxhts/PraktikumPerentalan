package controller;

import model.Kendaraan;
import model.KendaraanRepository;
import model.Penyewaan;
import model.PenyewaanRepository;
import view.RentalView;
import view.StrukView;

public class RentalController {
    private final KendaraanRepository kendaraanRepository = new KendaraanRepository();
    private final PenyewaanRepository penyewaanRepository = new PenyewaanRepository();
    private final RentalView view = new RentalView();
    private final StrukView strukView = new StrukView();

    public void tambahDataAwal(Kendaraan kendaraan) {
        kendaraanRepository.tambahKendaraan(kendaraan);
    }
    
    private java.util.List<Penyewaan> ambilRiwayatPenyewaan() {
        String[] namaMetode = {"semua", "semuaPenyewaan", "lihatSemua", "getAll"};

        for (String nama : namaMetode) {
            try {
                java.lang.reflect.Method method = penyewaanRepository.getClass().getMethod(nama);
                Object hasil = method.invoke(penyewaanRepository);
                if (hasil instanceof java.util.List<?>) {
                    return (java.util.List<Penyewaan>) hasil;
                }
            } catch (NoSuchMethodException ignored) {
            } catch (Exception ignored) {
            }
        }

        return new java.util.ArrayList<>();
    }

    public void jalankan(){
        while (true) {
            view.tampilkanMenu();
            String pilihan = view.tampilkanMenu();
            switch (pilihan) {
                case "1" -> view.tampilkanDaftarKendaraan(kendaraanRepository.semua());
                case "2" -> tambahkanKendaraan();
                case "3" -> sewakanKendaraan();
                case "4" -> kembalikanKendaraan();
                case "5" -> view.tampilkanRiwayatPenyewaan(ambilRiwayatPenyewaan());
                case "0" -> {
                    view.tampilkanPesan("Terimakasih Telah Menggunakan Aplikasi ini");
                    return;
                }
                default -> view.tampilkanPesan("Pilihan tidak valid!");
            }
        }
    }
    
    public void tambahkanKendaraan(){
        String plat = view.bacaTeks("Plat Nomor: ");
        if (plat.isEmpty() || kendaraanRepository.cariByPlat(plat) != null) {
            view.tampilkanPesan("Plat nomor tidak valid atau sudah ada.");
            return;
        }
        String merk = view.bacaTeks("Merk: ");
        String jenis = view.bacaTeks("Jenis: ");
        if(!jenis.equals("Motor") && !jenis.equals("Mobil")){
            view.tampilkanPesan("Jenis kendaraan harus 'Motor' atau 'Mobil'.");
            return;
        }

        long tarif = view.bacaAngka("Tarif Per Hari: ");
        if (tarif <= 0) {
            System.out.println("Tarif per hari harus lebih dari 0.");
            return;
    }
    kendaraanRepository.tambahKendaraan(new Kendaraan(plat, merk, jenis, tarif));
    view.tampilkanPesan("Kendaraan berhasil ditambahkan.");
}

    public void sewakanKendaraan(){
        String plat = view.bacaTeks("Plat Nomor Kendaraan: ");
        Kendaraan kendaraan = kendaraanRepository.cariByPlat(plat);
        if (kendaraan == null || !kendaraan.isAktif()) {
            view.tampilkanPesan("Kendaraan tidak tersedia.");
            return;
        }

        String namaPenyewa = view.bacaTeks("Nama Penyewa: ");
        int lamaHari = (int) view.bacaAngka("Lama Sewa (hari): ");
        if(lamaHari <= 1) {
            view.tampilkanPesan("Lama sewa harus lebih dari 1 hari.");
            return;
        }
        long totalBiaya = kendaraan.hitungBiayaSewa(lamaHari);
        Penyewaan penyewaan = new Penyewaan(plat, namaPenyewa, kendaraan, lamaHari, totalBiaya);
        penyewaanRepository.tambahPenyewaan(penyewaan);
        kendaraan.setTersedia(false);
        strukView.cetak(penyewaan);
    }

    public void kembalikanKendaraan(){
        String plat = view.bacaTeks("Plat Nomor Kendaraan: ");
        Penyewaan penyewaan = penyewaanRepository.cariAktifByPlat(plat);
        if (penyewaan == null || !penyewaan.isAktif()) {
            view.tampilkanPesan("Tidak ada penyewaan aktif untuk kendaraan ini.");
            return;
        }
            penyewaan.selesai();
            penyewaan.getKendaraan().setTersedia(true);
            view.tampilkanPesan("Kendaraan berhasil dikembalikan.");
    }
}
