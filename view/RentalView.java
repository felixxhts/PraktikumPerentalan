package view;

import java.util.List;
import java.util.Scanner;
import model.Kendaraan;
import model.Penyewaan;


public class RentalView {
    private final Scanner input = new Scanner(System.in);

    public String tampilkanMenu() {
        System.out.println("=== Aplikasi Rental Kendaraan ===");    
        System.out.println("1. Tampilkan Daftar Kendaraan");
        System.out.println("2. Tambah Kendaraan");
        System.out.println("3. Sewa Kendaraan");
        System.out.println("4. Kembalikan Kendaraan");
        System.out.println("5. Tampilkan Riwayat Pesanan");
        System.out.println("0. Keluar");
        return bacaTeks("Pilih menu: ");
    }

    public void tampilkanDaftarKendaraan(List<Kendaraan> kendaraanList) {
        System.out.println("=== Daftar Kendaraan ===");
        if(kendaraanList.isEmpty()) {
            System.out.println("Belum ada kendaraan yang tersedia.");
            return;
        }
        for (Kendaraan kendaraan : kendaraanList) {
            System.out.println(
            "Merk: " + kendaraan.getMerk() + 
            " | Plat Nomor: " + kendaraan.getPlatNomor() + 
            " | Jenis: " + kendaraan.getJenis() + 
            " | Tarif Per Hari: " + Format.rupiah(kendaraan.getTarifPerHari()) + 
            " | Aktif: " + (kendaraan.isAktif() ? "Ya" : "Tidak")
            );
        }
    }

    public void tampilkanRiwayatPenyewaan(List<Penyewaan> penyewaanList) {
        System.out.println("=== Riwayat Penyewaan ===");
        if(penyewaanList.isEmpty()) {
            System.out.println("Belum ada riwayat penyewaan.");
            return;
        }
        for (Penyewaan penyewaan : penyewaanList) {
            System.out.println(
            "ID Sewa: " + penyewaan.getId() + 
            " | Nama Penyewa: " + penyewaan.getNamaPenyewa() + 
            " | Kendaraan: " + penyewaan.getKendaraan().getMerk() +
            " | Plat Nomor: " + penyewaan.getKendaraan().getPlatNomor() +
            " | Jenis: " + penyewaan.getKendaraan().getJenis() +
            " | Lama Hari: " + penyewaan.getLamaHari() +
            " | Total Biaya: " + Format.rupiah(penyewaan.getTotalBiaya()) +
            " | Aktif: " + (penyewaan.isAktif() ? "Ya" : "Tidak")
            );
        }
    }

    public String bacaTeks(String label) {
        System.out.print(label);
        return input.nextLine();
    }

    public long bacaAngka(String label) {
        System.out.print(label);
        while (!input.hasNextLong()) {
            System.out.println("Input harus berupa angka. Silakan coba lagi.");
            input.next(); // Clear the invalid input
            System.out.print(label);
        }
        long angka = input.nextLong();
        input.nextLine(); 
        return angka;
    }

    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }

}