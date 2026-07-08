package view;

import dao.PesananDAO;
import model.Pesanan;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TampilPesananFrame extends JFrame {

    private JTable tablePesanan;
    private DefaultTableModel tableModel;
    private PesananDAO pesananDAO;

    public TampilPesananFrame() {
        pesananDAO = new PesananDAO();

        setTitle("Tampil Data Pesanan - Cafe");
        setSize(850, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setup Table
        String[] kolom = {"ID", "Tanggal", "Nama Pelanggan", "No Meja", "Total Harga", "Status"};
        tableModel = new DefaultTableModel(kolom, 0);
        tablePesanan = new JTable(tableModel);

        JButton btnRefresh = new JButton("Refresh");
        JButton btnTambah = new JButton("Tambah Pesanan Baru");

        btnRefresh.addActionListener(e -> loadDataPesanan());
        btnTambah.addActionListener(e -> {
            new TambahPesananFrame(this).setVisible(true);
        });

        JPanel panelButton = new JPanel();
        panelButton.add(btnRefresh);
        panelButton.add(btnTambah);

        add(new JScrollPane(tablePesanan), BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        loadDataPesanan();
    }

    public void loadDataPesanan() {
        tableModel.setRowCount(0);
        List<Pesanan> listPesanan = pesananDAO.getAllPesanan();

        for (Pesanan p : listPesanan) {
            Object[] row = {
                p.getIdPesanan(),
                p.getTanggal(),
                p.getNamaPelanggan(),
                p.getNoMeja(),
                p.getTotalHarga(),
                p.getStatus()
            };
            tableModel.addRow(row);
        }
    }
}
