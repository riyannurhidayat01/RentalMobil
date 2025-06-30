package thread;

import model.Rental;

public class RentalTask extends Thread {
    private Rental rental;

    public RentalTask(Rental rental) {
        this.rental = rental;
    }

    public void run() {
        System.out.println("Memproses rental untuk: " + rental.getPelanggan().getNama());
        try {
            Thread.sleep(1000); // simulasi proses
        } catch (InterruptedException ignored) {}
        System.out.println("Rental selesai untuk: " + rental.getMobil().getPlatNomor());
    }
}
