package br.com.apc.chef.entities;

public class Board9 extends Board {
	
	@SuppressWarnings(value = {"unused" })
    private final int SIZE = 9;
    private final int COLUMN_SIZE = 3;
    private final int ROW_SIZE = 3;

    @Override
    public int getColumn() {
        return COLUMN_SIZE;
    }

    @Override
    public int getRow() {
        return ROW_SIZE;
    }
}