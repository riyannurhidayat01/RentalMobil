package model;

public class Mobil {
    public static final double PAJAK_PERSEN = 0.1;
    private String platNomor;
    private String merk;
    private int tahun;
    private boolean tersedia = true;

    public Mobil(String plat, String merk, int tahun) {
        this.platNomor = plat;
        this.merk = merk;
        this.tahun = tahun;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public String getMerk() {
        return merk;
    }

    public int getTahun() {
        return tahun;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    public Object[] toRow() {
        return new Object[]{platNomor, merk, tahun, tersedia ? "Tersedia" : "Disewa"};
    }
}
