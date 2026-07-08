package view;

import dao.PelangganDAO;
import model.Pelanggan;
import javax.swing.*;
import java.awt.*;

public class TambahPelangganFrame extends JFrame {

    private TampilPelangganFrame parentFrame;

    private JTextField txtNama;
    private JTextField txtNoHp;
    private PelangganDAO pelangganDAO;

    public TambahPelangganFrame(TampilPelangganFrame parent) {
        this.parentFrame = parent;
        pelangganDAO = new PelangganDAO();

        setTitle("Tambah Pelanggan Baru");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Nama Pelanggan
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nama Pelanggan:"), gbc);
        gbc.gridx = 1;
        txtNama = new JTextField(20);
        add(txtNama, gbc);

        // No HP
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("No HP:"), gbc);
        gbc.gridx = 1;
        txtNoHp = new JTextField(20);
        add(txtNoHp, gbc);

        // Tombol
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton btnSimpan = new JButton("Simpan Pelanggan");
        btnSimpan.addActionListener(e -> simpanPelanggan());
        add(btnSimpan, gbc);
    }

    private void simpanPelanggan() {
        String nama = txtNama.getText().trim();
        String noHp = txtNoHp.getText().trim();

        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama pelanggan tidak boleh kosong!");
            return;
        }
        if (noHp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No HP tidak boleh kosong!");
            return;
        }

        Pelanggan pelanggan = new Pelanggan(nama, noHp);

        if (pelangganDAO.tambahPelanggan(pelanggan)) {
            JOptionPane.showMessageDialog(this, "Pelanggan berhasil ditambahkan!");
            if (parentFrame != null) {
                parentFrame.loadDataPelanggan();
            }
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan pelanggan!");
        }
    }
}
