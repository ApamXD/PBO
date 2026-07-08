package view;

import dao.MenuDAO;
import model.Menu;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class TambahMenuFrame extends JFrame {
    
    private TampilMenuFrame parentFrame;  // Untuk refresh tabel setelah tambah
    
    private JTextField txtNama;
    private JTextField txtHarga;
    private JComboBox<String> cmbKategori;
    private JTextField txtStok;
    private MenuDAO menuDAO;

    public TambahMenuFrame(TampilMenuFrame parent) {
        this.parentFrame = parent;
        menuDAO = new MenuDAO();
        
        setTitle("Tambah Menu Baru");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Nama Menu
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nama Menu:"), gbc);
        gbc.gridx = 1;
        txtNama = new JTextField(20);
        add(txtNama, gbc);
        
        // Harga
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Harga:"), gbc);
        gbc.gridx = 1;
        txtHarga = new JTextField(20);
        add(txtHarga, gbc);
        
        // Kategori
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Kategori:"), gbc);
        gbc.gridx = 1;
        cmbKategori = new JComboBox<>(new String[]{"coffee", "snack", "makanan_berat", "dessert"});
        add(cmbKategori, gbc);
        
        // Stok
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Stok:"), gbc);
        gbc.gridx = 1;
        txtStok = new JTextField(20);
        add(txtStok, gbc);
        
        // Tombol
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton btnSimpan = new JButton("Simpan Menu");
        btnSimpan.addActionListener(e -> simpanMenu());
        add(btnSimpan, gbc);
    }
    
    private void simpanMenu() {
        try {
            String nama = txtNama.getText().trim();
            BigDecimal harga = new BigDecimal(txtHarga.getText().trim());
            String kategori = (String) cmbKategori.getSelectedItem();
            int stok = Integer.parseInt(txtStok.getText().trim());
            
            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama menu tidak boleh kosong!");
                return;
            }
            
            Menu menu = new Menu(nama, harga, kategori, stok);
            
            if (menuDAO.tambahMenu(menu)) {
                JOptionPane.showMessageDialog(this, "Menu berhasil ditambahkan!");
                if (parentFrame != null) {
                    parentFrame.loadDataMenu();  // Refresh tabel
                }
                this.dispose();  // Tutup window tambah
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan menu!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Input salah! Cek harga dan stok.");
        }
    }
}