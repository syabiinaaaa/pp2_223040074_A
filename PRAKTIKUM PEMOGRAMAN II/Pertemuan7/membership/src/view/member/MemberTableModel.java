package Pertemuan7.membership.src.view.member;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import Pertemuan7.membership.src.model.Member;

public class MemberTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nama", "Jenis Member"};
    private final List<Member> data;

    public MemberTableModel(List<Member> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Member rowItem = data.get(row);
        switch (col) {
            case 0:
                return rowItem.getNama();
            case 1:
                return rowItem.getJenisMember().getNama();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Member value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}
