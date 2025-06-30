package model;

import java.io.Serializable;

public class Rental implements Serializable {
    private Mobil mobil;
    private Pelanggan pelanggan;
    private String tanggalPinjam;
    private String tanggalKembali;

    public Rental(Mobil mobil, Pelanggan pelanggan, String tanggalPinjam, String tanggalKembali) {
        this.mobil = mobil;
        this.pelanggan = pelanggan;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }

    public Mobil getMobil() {
        return mobil;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }

    @Override
    public String toString() {
        return "Mobil: " + mobil.getPlatNomor() +
               " | Pelanggan: " + pelanggan.getNama() +
               " | Pinjam: " + tanggalPinjam +
               " | Kembali: " + tanggalKembali;
    }
}
