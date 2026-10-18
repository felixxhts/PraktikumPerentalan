package model;

import java.util.ArrayList;
import java.util.List;

public class PenyewaanRepository {
    private final List<Penyewaan> data = new ArrayList<>();

        public String buatIdBaru(){
        return String.format("SW-%03d", data.size()+1);
    }

    public void tambahPenyewaan(Penyewaan penyewaan) {
        data.add(penyewaan);
    }
    
    public List<Penyewaan> semua() {
        return new ArrayList<>(data);
    }

    public Penyewaan cariAktifByPlat(String platNomor) {
        for (Penyewaan penyewaan : data) {
            if (penyewaan.isAktif() && penyewaan.getKendaraan().getPlatNomor().equalsIgnoreCase(platNomor)) {
                return penyewaan;
            }
        }
        return null;
    }
}
