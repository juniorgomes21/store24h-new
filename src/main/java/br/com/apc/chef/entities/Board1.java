package br.com.apc.chef.entities;

public class Board1 extends Board{
		
	@SuppressWarnings(value = {"unused" })
    private final int SIZE = 1;
    private final int COLUMN_SIZE = 1;
    private final int ROW_SIZE = 1;

    @Override
    int getColumn() {
        return COLUMN_SIZE;
    }

    @Override
    int getRow() {
        return ROW_SIZE;
    }
}
