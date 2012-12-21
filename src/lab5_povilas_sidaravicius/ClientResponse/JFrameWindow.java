/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5_povilas_sidaravicius.ClientResponse;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PovilasSid
 */
public class JFrameWindow extends AbstractTableModel {

    private Object[][] data;
    private String[] columnNames;

    public JFrameWindow(Object[][] data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndes, int columnIndex) {
        return true;
    }

    @Override
    public String getColumnName(int index) {
        return columnNames[index];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = value;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public JFrameWindow(String[] columnNames, Object[][] data) {
        this.columnNames = columnNames;
        this.data = data;
    }
}
