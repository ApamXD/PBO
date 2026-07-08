package view;

import dao.PelangganDAO;
import model.Pelanggan;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TampilPelangganFrame extends JFrame {

    private JTable tablePelanggan;
    private DefaultTableModel tableModel;
    private PelangganDAO pelangganDAO;

    public TampilPelangganFrame() {
        pelangganDAO = new PelangganDAO();

        setTitle("Tampil Data Pelanggan - Cafe");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setup Table
        String[] kolom = {"ID", "Nama Pelanggan", "No HP"};
        tableModel = new DefaultTableModel(kolom, 0);
        tablePelanggan = new JTable(tableModel);

        JButton btnRefresh = new JButton("Refresh");
        JButton btnTambah = new JButton("Tambah Pelanggan Baru");

        btnRefresh.addActionListener(e -> loadDataPelanggan());
        btnTambah.addActionListener(e -> {
            new TambahPelangganFrame(this).setVisible(true);
        });

        JPanel panelButton = new JPanel();
        panelButton.add(btnRefresh);
        panelButton.add(btnTambah);

        add(new JScrollPane(tablePelanggan), BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        loadDataPelanggan();
    }

    public void loadDataPelanggan() {
        tableModel.setRowCount(0);
        List<Pelanggan> listPelanggan = pelangganDAO.getAllPelanggan();

        for (Pelanggan p : listPelanggan) {
            Object[] row = {
                p.getIdPelanggan(),
                p.getNamaPelanggan(),
                p.getNoHp()
            };
            tableModel.addRow(row);
        }
    }
}
