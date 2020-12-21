package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;

import javax.swing.table.AbstractTableModel;

public class MyTableModel2 extends AbstractTableModel {
    private static final int INDEX_COLUMN_NUMBER = 0;
    private static final int X_COLUMN_NUMBER = 1;
    private static final int Y_COLUMN_NUMBER = 2;
    TabulatedFunction myFunction;

    public MyTableModel2(TabulatedFunction myFunction) {
        this.myFunction = myFunction;
    }

    @Override
    public int getRowCount() {
        return myFunction.getCount();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_COLUMN_NUMBER:
                return rowIndex;
            case X_COLUMN_NUMBER:
                return myFunction.getX(rowIndex);
            case Y_COLUMN_NUMBER:
                return myFunction.getY(rowIndex);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_COLUMN_NUMBER:
                return false;
            case X_COLUMN_NUMBER:

            case Y_COLUMN_NUMBER:
                return true;
        }
        return false;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case INDEX_COLUMN_NUMBER:
                return "Index";
            case X_COLUMN_NUMBER:
                return "xValues";
            case Y_COLUMN_NUMBER:
                return "yValues";
        }
        return super.getColumnName(column);
    }
}
