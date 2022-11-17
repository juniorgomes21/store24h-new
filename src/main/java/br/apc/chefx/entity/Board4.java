package br.apc.chefx.entity;

public class Board4 extends Board {
    private final int SIZE = 9;
    private final int COLUMN_SIZE = 3;
    private final int ROW_SIZE = 3;

    public int getSIZE() {
        return SIZE;
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public int[][] matrix() {
        return new int[0][];
    }

    @Override
    public int getColumn() {
        return COLUMN_SIZE;
    }

    @Override
    public int getRow() {
        return ROW_SIZE;
    }
}
