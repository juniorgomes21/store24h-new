package br.apc.chefx.entity;

public class Board9 extends Board {
    private final int SIZE = 9;
    private final int COLUMN_SIZE = 3;
    private final int ROW_SIZE = 3;

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public int[][] matrix() {
        int[][] matrix = new int[3][3];
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                anInt = 0;
            }
        }
        return matrix;
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