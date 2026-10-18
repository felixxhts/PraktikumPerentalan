package app;

import controller.RentalController;
import model.Kendaraan;

public class Main {
    public static void main(String[] args) {
        RentalController rentalController = new RentalController();


        rentalController.tambahDataAwal(new Kendaraan("B 1234 ABC", "Toyota", "MOBIL", 500000));
        rentalController.tambahDataAwal(new Kendaraan("B 5678 DEF", "Honda", "MOTOR", 200000));

        rentalController.jalankan();
    }
}
