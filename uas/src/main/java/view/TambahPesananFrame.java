package view;

import dao.MenuDAO;
import dao.PesananDAO;
import dao.PelangganDAO;
import model.Menu;
import model.Pesanan;
import model.DetailPesanan;
import model.Pelanggan;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TambahPesananFrame extends JFrame {

    private TampilPesananFrame parentFrame; // Untuk refresh tabel setelah tambah

    private JComboBox<Pelanggan> cmbPelanggan;
    private JComboBox<Menu> cmbMenu;
    private JSpinner spinnerQty;
    private JTable tablePesananSementara;
    private DefaultTableModel tableModel;
    private List<DetailPesanan> detailList = new ArrayList<>();
    private JLabel lblTotal;
    private JTextField txtNoMeja;

    private MenuDAO menuDAO;
    private PesananDAO pesananDAO;
    private PelangganDAO pelangganDAO;

    public TambahPesananFrame() {
        this(null);
    }

    public TambahPesananFrame(TampilPesananFrame parent) {
        this.parentFrame = parent;
        menuDAO = new MenuDAO();
        pesananDAO = new PesananDAO();
        pelangganDAO = new PelangganDAO();

        setTitle("Tambah Pesanan Baru");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel Atas
        JPanel panelAtas = new JPanel(new GridLayout(2, 2, 10, 10));
        panelAtas.add(new JLabel("Pelanggan:"));
        cmbPelanggan = new JComboBox<>();
        loadPelangganToCombo();
        panelAtas.add(cmbPelanggan);

        panelAtas.add(new JLabel("No Meja:"));
        txtNoMeja = new JTextField();
        panelAtas.add(txtNoMeja);

        add(panelAtas, BorderLayout.NORTH);

        // Panel Tambah Item
        JPanel panelTambah = new JPanel();
        cmbMenu = new JComboBox<>();
        loadMenuToCombo();

        spinnerQty = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JButton btnTambahItem = new JButton("Tambah ke Pesanan");
        btnTambahItem.addActionListener(e -> tambahItemKePesanan());

        panelTambah.add(new JLabel("Menu:"));
        panelTambah.add(cmbMenu);
        panelTambah.add(new JLabel("Qty:"));
        panelTambah.add(spinnerQty);
        panelTambah.add(btnTambahItem);

        // Tabel Pesanan Sementara
        String[] kolom = {"ID Menu", "Nama Menu", "Harga", "Qty", "Subtotal"};
        tableModel = new DefaultTableModel(kolom, 0);
        tablePesananSementara = new JTable(tableModel);

        JPanel panelTengah = new JPanel(new BorderLayout());
        panelTengah.add(panelTambah, BorderLayout.NORTH);
        panelTengah.add(new JScrollPane(tablePesananSementara), BorderLayout.CENTER);
        add(panelTengah, BorderLayout.CENTER);

        // Panel Bawah
        JPanel panelBawah = new JPanel();
        lblTotal = new JLabel("Total: Rp 0");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnSimpanPesanan = new JButton("Simpan Pesanan");
        btnSimpanPesanan.addActionListener(e -> simpanPesanan());

        panelBawah.add(lblTotal);
        panelBawah.add(btnSimpanPesanan);
        add(panelBawah, BorderLayout.SOUTH);
    }

    private void loadPelangganToCombo() {
        cmbPelanggan.removeAllItems();
        for (Pelanggan p : pelangganDAO.getAllPelanggan()) {
            cmbPelanggan.addItem(p);
        }
    }

    private void loadMenuToCombo() {
        cmbMenu.removeAllItems();
        for (Menu m : menuDAO.getAllMenu()) {
            cmbMenu.addItem(m);
        }
    }

    private void tambahItemKePesanan() {
        Menu selectedMenu = (Menu) cmbMenu.getSelectedItem();
        int qty = (int) spinnerQty.getValue();

        if (selectedMenu == null) return;

        DetailPesanan detail = new DetailPesanan(selectedMenu.getIdMenu(), qty, selectedMenu.getHarga());
        detailList.add(detail);

        // Tambah ke tabel
        Object[] row = {
            selectedMenu.getIdMenu(),
            selectedMenu.getNamaMenu(),
            selectedMenu.getHarga(),
            qty,
            detail.getSubtotal()
        };
        tableModel.addRow(row);

        updateTotal();
    }

    private void updateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (DetailPesanan d : detailList) {
            total = total.add(d.getSubtotal());
        }
        lblTotal.setText("Total: Rp " + total);
    }

    private void simpanPesanan() {
        if (detailList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pesanan masih kosong!");
            return;
        }

        Pelanggan selectedPelanggan = (Pelanggan) cmbPelanggan.getSelectedItem();
        if (selectedPelanggan == null) {
            JOptionPane.showMessageDialog(this, "Silakan pilih pelanggan! (Tambahkan data pelanggan dulu jika kosong)");
            return;
        }

        Pesanan pesanan = new Pesanan();
        pesanan.setIdPelanggan(selectedPelanggan.getIdPelanggan());
        pesanan.setNoMeja(txtNoMeja.getText().trim());

        // Hitung total
        BigDecimal total = BigDecimal.ZERO;
        for (DetailPesanan d : detailList) {
            total = total.add(d.getSubtotal());
        }
        pesanan.setTotalHarga(total);
        pesanan.setDetailList(detailList);

        if (pesananDAO.simpanPesanan(pesanan)) {
            JOptionPane.showMessageDialog(this, "Pesanan berhasil disimpan!");
            if (parentFrame != null) {
                parentFrame.loadDataPesanan(); // Refresh tabel
            }
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan pesanan!");
        }
    }
}
