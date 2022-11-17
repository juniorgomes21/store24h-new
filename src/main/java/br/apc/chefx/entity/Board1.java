package br.apc.chefx.entity;

public class Board1 extends Board{
    private final int SIZE = 9;
    private final int COLUMN_SIZE = 3;
    private final int ROW_SIZE = 3;

    @Override
    int getSize() {
        return SIZE;
    }

    @Override
    int[][] matrix() {
        return new int[0][];
    }

    @Override
    int getColumn() {
        return COLUMN_SIZE;
    }

    @Override
    int getRow() {
        return ROW_SIZE;
    }
}
