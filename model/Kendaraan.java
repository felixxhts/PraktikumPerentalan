package model;

public class Kendaraan {
    private final String platNomor;
    private final String merk;
    private final String jenis;
    private final long tarifPerHari;
    private boolean aktif = true;

    public Kendaraan(String platNomor, String merk, String jenis, long tarifPerHari) {
        this.platNomor = platNomor;
        this.merk = merk;
        this.jenis = jenis;
        this.tarifPerHari = tarifPerHari;
    }

    public long hitungBiayaSewa(int hari) {
        long totalBiaya = tarifPerHari * hari;
        if (jenis.equals("MOBIL")){
            totalBiaya += 50000; // biaya tambahan untuk mobil
        }else if (jenis.equals("MOTOR")){
            if (hari > 7) {
                totalBiaya = totalBiaya / 10;
            }
        }
        return totalBiaya;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public String getMerk() {
        return merk;
    }

    public String getJenis() {
        return jenis;
    }

    public long getTarifPerHari() {
        return tarifPerHari;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setTersedia(boolean tersedia) {
        this.aktif = tersedia;
    }
}