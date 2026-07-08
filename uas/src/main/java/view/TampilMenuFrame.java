package view;

import dao.MenuDAO;
import model.Menu;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TampilMenuFrame extends JFrame {

    private JTable tableMenu;
    private DefaultTableModel tableModel;
    private MenuDAO menuDAO;

    public TampilMenuFrame() {
        menuDAO = new MenuDAO();

        setTitle("Tampil Data Menu - Cafe");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setup Table
        String[] kolom = {"ID", "Nama Menu", "Harga", "Kategori", "Stok"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableMenu = new JTable(tableModel);

        JButton btnRefresh = new JButton("Refresh");
        JButton btnTambah = new JButton("Tambah Menu Baru");
        JButton btnPelanggan = new JButton("Data Pelanggan");
        JButton btnPesanan = new JButton("Data Pesanan");

        btnRefresh.addActionListener(e -> loadDataMenu());
        btnTambah.addActionListener(e -> {
            new TambahMenuFrame(this).setVisible(true);
        });
        btnPelanggan.addActionListener(e -> {
            new TampilPelangganFrame().setVisible(true);
        });
        btnPesanan.addActionListener(e -> {
            new TampilPesananFrame().setVisible(true);
        });

        JPanel panelButton = new JPanel();
        panelButton.add(btnRefresh);
        panelButton.add(btnTambah);
        panelButton.add(btnPelanggan);
        panelButton.add(btnPesanan);

        add(new JScrollPane(tableMenu), BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        loadDataMenu(); // Load data pertama kali
    }

    public void loadDataMenu() {
        tableModel.setRowCount(0); // Clear table
        List<Menu> listMenu = menuDAO.getAllMenu();

        for (Menu m : listMenu) {
            Object[] row = {
                m.getIdMenu(),
                m.getNamaMenu(),
                m.getHarga(),
                m.getKategori(),
                m.getStok()
            };
            tableModel.addRow(row);
        }
    }
}
