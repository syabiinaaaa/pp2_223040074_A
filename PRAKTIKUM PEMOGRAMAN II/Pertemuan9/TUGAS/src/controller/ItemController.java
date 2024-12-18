package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import org.apache.ibatis.session.SqlSession;

import model.*;
import view.ItemView;

public class ItemController {
    private ItemView view;
    private ItemMapper mapper;

    public ItemController(ItemView view, ItemMapper mapper) {
        this.view = view;
        this.mapper = mapper;

        this.view.addAddItemListener(new AddItemListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addDeleteItemListener(new DeleteItemListener());
        this.view.addUpdateItemListener(new UpdateItemListener());
    }

    class AddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String weight = view.getWeightInput();
            String qty = view.getQtyInput();
            String price = view.getPriceInput();
            
            try (SqlSession session = MyBatisUtil.getSqlSession()) {
                ItemMapper mapper = session.getMapper(ItemMapper.class);

                if (!name.isEmpty() && !weight.isEmpty() && !qty.isEmpty() && !price.isEmpty()) {
                    int weightInt = Integer.parseInt(weight);
                    int qtyInt = Integer.parseInt(qty);
                    int priceInt = Integer.parseInt(price);

                    Item item = new Item();
                    item.setName(name);
                    item.setWeight(weightInt);
                    item.setQty(qtyInt);
                    item.setPrice(priceInt);

                    mapper.insertItem(item);
                    session.commit();
                    JOptionPane.showMessageDialog(view, "Item added successfully!");
                } else {
                    JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Please enter valid numbers for weight, quantity, and price.");
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setItemList(new String[0][0]);
    
            List<Item> items = mapper.getAllItems();
    
            String[][] itemArray = items.stream()
                .map(item -> new String[] {
                    String.valueOf(item.getId()),
                    item.getName(),
                    String.valueOf(item.getWeight()),
                    String.valueOf(item.getQty()),
                    String.valueOf(item.getPrice())
                })
                .toArray(String[][]::new);
    
            view.setItemList(itemArray);
        }
    }
    
    

    class DeleteItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedId = view.getSelectedItemId();
            if (selectedId != null) {
                try (SqlSession session = MyBatisUtil.getSqlSession()) {
                    ItemMapper mapper = session.getMapper(ItemMapper.class);
    
                    // Hapus item berdasarkan ID
                    mapper.deleteItemById(Integer.parseInt(selectedId));
                    session.commit(); // Commit transaksi
    
                    JOptionPane.showMessageDialog(view, "Item deleted successfully!");
                    
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select an item to delete.");
            }
        }
    }
    
    class UpdateItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedId = view.getSelectedItemId();
            if (selectedId != null) {
                String name = view.getNameInput();
                String weight = view.getWeightInput();
                String qty = view.getQtyInput();
                String price = view.getPriceInput();
    
                try (SqlSession session = MyBatisUtil.getSqlSession()) {
                    ItemMapper mapper = session.getMapper(ItemMapper.class);
    
                    if (!name.isEmpty() && !weight.isEmpty() && !qty.isEmpty() && !price.isEmpty()) {
                        int weightInt = Integer.parseInt(weight);
                        int qtyInt = Integer.parseInt(qty);
                        int priceInt = Integer.parseInt(price);
    
                        Item item = new Item();
                        item.setId(Integer.parseInt(selectedId)); 
                        item.setName(name);
                        item.setWeight(weightInt);
                        item.setQty(qtyInt);
                        item.setPrice(priceInt);
    
                        mapper.updateItem(item);
                        session.commit(); // Commit transaksi
                        JOptionPane.showMessageDialog(view, "Item updated successfully!");
                        
                    } else {
                        JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Please enter valid numbers for weight, quantity, and price.");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select an item to update.");
            }
        }
    }
    
}
