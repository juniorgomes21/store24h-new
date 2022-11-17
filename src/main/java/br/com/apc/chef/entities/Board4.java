package br.com.apc.chef.entities;

public class Board4 extends Board {
	
	@SuppressWarnings(value = {"unused" })
    private final int SIZE = getRow()*getColumn();
    private final int COLUMN_SIZE = 2;
    private final int ROW_SIZE = 2;

    @Override
    public int getColumn() {
        return COLUMN_SIZE;
    }

    @Override
    public int getRow() {
        return ROW_SIZE;
    }
}
