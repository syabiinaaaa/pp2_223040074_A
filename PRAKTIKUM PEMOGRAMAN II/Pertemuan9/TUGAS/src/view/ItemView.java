package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ItemView extends JFrame {
    private JTextField txtName = new JTextField(20);
    private JTextField txtWeight = new JTextField(20);
    private JTextField txtQty = new JTextField(20);
    private JTextField txtPrice = new JTextField(20);
    private JButton btnAdd = new JButton("Add Item");
    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");
    private int selectedRow = -1;
    private JTable itemTable;
    private DefaultTableModel tableModel;

    public ItemView() {
        setTitle("Item Management");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.add(new JLabel("Nama Barang:"));
        panel.add(txtName);
        panel.add(new JLabel("Berat:"));
        panel.add(txtWeight);
        panel.add(new JLabel("Jumlah:"));
        panel.add(txtQty);
        panel.add(new JLabel("Harga:"));
        panel.add(txtPrice);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        panel.add(buttonPanel);
    
        String[] columnNames = {"ID", "Nama Barang", "Berat", "Jumlah", "Harga"};
        tableModel = new DefaultTableModel(columnNames, 0);
        itemTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(itemTable);
    
        itemTable.getSelectionModel().addListSelectionListener(e -> {
            selectedRow = itemTable.getSelectedRow();
            if (selectedRow != -1) {
                String id = (String) tableModel.getValueAt(selectedRow, 0);
                txtName.setText((String) tableModel.getValueAt(selectedRow, 1));
                txtWeight.setText((String) tableModel.getValueAt(selectedRow, 2));
                txtQty.setText((String) tableModel.getValueAt(selectedRow, 3));
                txtPrice.setText((String) tableModel.getValueAt(selectedRow, 4));
            }
        });
    
        add(panel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }
    
    public String getSelectedItemId() {
        if (selectedRow != -1) {
            return (String) tableModel.getValueAt(selectedRow, 0); 
        }
        return null;
    }

    public String getNameInput() {
        return txtName.getText();
    }

    public String getWeightInput() {
        return txtWeight.getText();
    }

    public String getQtyInput() {
        return txtQty.getText();
    }

    public String getPriceInput() {
        return txtPrice.getText();
    }

    public void addItemToTable(String id, String name, String weight, String qty, String price) {
        String[] row = {id, name, weight, qty, price};
        tableModel.addRow(row);
    }

    public void setItemList(String[][] items) {
        tableModel.setRowCount(0);
        for (String[] item : items) {
            tableModel.addRow(item);
        }
    }
    
    public void addAddItemListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

    public void addUpdateItemListener(ActionListener listener) {
        btnUpdate.addActionListener(listener);
    }

    public void addDeleteItemListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
}
