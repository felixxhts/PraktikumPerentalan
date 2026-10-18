package view;
import model.Penyewaan;

public class StrukView {
    public void cetak(Penyewaan p) {
        System.out.println("========== STRUK PENYEWAAN ==========");
        System.out.println("ID Penyewaan: " + p.getId());
        System.out.println("Nama Penyewa: " + p.getNamaPenyewa());
        System.out.println("Kendaraan: " + p.getKendaraan() + " (" + p.getKendaraan().getPlatNomor() + ")");
        System.out.println("Lama Sewa: " + p.getLamaHari() + " hari");
        System.out.println("Total Biaya: " + Format.rupiah(p.getTotalBiaya()));
        System.out.println("=====================================");
    }
}
