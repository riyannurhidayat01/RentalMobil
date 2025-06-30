package app;

import model.Mobil;
import model.Pelanggan;
import model.Rental;
import service.MongoDBService;
import service.RentalService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main_GUI extends JFrame {

    private List<Mobil> daftarMobil = new ArrayList<>();
    private List<Pelanggan> daftarPelanggan = new ArrayList<>();
    private RentalService rentalService = new RentalService();

    private JTable tabelMobil;
    private DefaultTableModel modelMobil;

    public Main_GUI() {
        setTitle("Aplikasi Rental Mobil - Manajemen");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblWelcome = new JLabel("Selamat datang di Aplikasi Rental Mobil", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblWelcome, BorderLayout.NORTH);

        daftarMobil.add(new Mobil("B1234ABC", "Toyota Avanza", 2020));
        daftarMobil.add(new Mobil("D5678DEF", "Honda Brio", 2022));
        daftarPelanggan.add(new Pelanggan("P01", "Riyan"));
        daftarPelanggan.add(new Pelanggan("P02", "Siti"));

        JPanel panelTombol = new JPanel(new FlowLayout());
        JButton btnLihatMobil = new JButton("Lihat Mobil");
        JButton btnSewaMobil = new JButton("Sewa Mobil");
        panelTombol.add(btnLihatMobil);
        panelTombol.add(btnSewaMobil);
        add(panelTombol, BorderLayout.CENTER);

        modelMobil = new DefaultTableModel(new Object[]{"Plat", "Merk", "Tahun", "Status"}, 0);
        tabelMobil = new JTable(modelMobil);
        JScrollPane scrollPane = new JScrollPane(tabelMobil);
        add(scrollPane, BorderLayout.SOUTH);

        btnLihatMobil.addActionListener(e -> tampilkanMobil());
        btnSewaMobil.addActionListener(e -> formSewaMobil());
    }

    private void tampilkanMobil() {
        modelMobil.setRowCount(0);
        for (Mobil m : daftarMobil) {
            modelMobil.addRow(m.toRow());
        }
    }

    private void formSewaMobil() {
        JTextField tfPlat = new JTextField();
        JTextField tfIdPelanggan = new JTextField();
        JTextField tfTglPinjam = new JTextField();
        JTextField tfTglKembali = new JTextField();

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Plat Mobil:"));
        panel.add(tfPlat);
        panel.add(new JLabel("ID Pelanggan:"));
        panel.add(tfIdPelanggan);
        panel.add(new JLabel("Tanggal Pinjam (yyyy-mm-dd):"));
        panel.add(tfTglPinjam);
        panel.add(new JLabel("Tanggal Kembali (yyyy-mm-dd):"));
        panel.add(tfTglKembali);

        int result = JOptionPane.showConfirmDialog(null, panel, "Sewa Mobil", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String plat = tfPlat.getText();
            String id = tfIdPelanggan.getText();
            String tglPinjam = tfTglPinjam.getText();
            String tglKembali = tfTglKembali.getText();

            Mobil m = cariMobil(plat);
            Pelanggan p = cariPelanggan(id);

            if (m == null) {
                JOptionPane.showMessageDialog(this, "Mobil tidak ditemukan.");
            } else if (p == null) {
                JOptionPane.showMessageDialog(this, "Pelanggan tidak ditemukan.");
            } else if (!m.isTersedia()) {
                JOptionPane.showMessageDialog(this, "Mobil sedang disewa.");
            } else {
                m.setTersedia(false);
                Rental rental = new Rental(m, p, tglPinjam, tglKembali);
                rentalService.tambahRental(rental);
                MongoDBService.simpanMobil(m);
                JOptionPane.showMessageDialog(this, "Mobil berhasil disewa oleh " + p.getNama());
                tampilkanMobil();
            }
        }
    }

    private Mobil cariMobil(String plat) {
        for (Mobil m : daftarMobil) {
            if (m.getPlatNomor().equalsIgnoreCase(plat)) {
                return m;
            }
        }
        return null;
    }

    private Pelanggan cariPelanggan(String id) {
        for (Pelanggan p : daftarPelanggan) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login_GUI().setVisible(true));
    }
}
