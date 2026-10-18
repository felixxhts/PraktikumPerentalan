package model;

import java.util.ArrayList;
import java.util.List;

public class KendaraanRepository {
    private final List<Kendaraan> data = new ArrayList<>();

    public void tambahKendaraan(Kendaraan kendaraan) {
        data.add(kendaraan);
    }

    public List<Kendaraan> semua() {
        return new ArrayList<>(data);
    }

    public Kendaraan cariByPlat(String platNomor) {
        for (Kendaraan kendaraan : data) {
            if (kendaraan.getPlatNomor().equals(platNomor)) {
                return kendaraan;
            }
        }
        return null;
    }
}
