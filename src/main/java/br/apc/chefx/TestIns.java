package br.apc.chefx;

public class TestIns {
    public static void main(String[] args) {
//        List<int[]> lol = Arrays.asList(matrix());
//        lol.forEach(
//            e -> System.out.println(Arrays.asList(Arrays.asList(e)))
//        );

        matrix();
    }

    public static String[][] matrix() {
        //[row][column]
        String[][] matrix = new String[3][3];

        for (int i = 0; i < matrix[0].length; i++) {
            System.out.printf("[");
            String ln = "";
            for (int j = 0; j < matrix[i].length; j++) {
                if(j == matrix[i].length-1) {
                    System.out.printf("(%d,%d)", i, j);
                } else {
                    System.out.printf("(%d,%d), ", i, j);
                }
                matrix[i][j] = "";
            }
            System.out.printf("]\n");
        }

//        for (int[] ints : matrix) {
//            for (int anInt : ints) {
//                System.out.println(anInt);
//            }
//        }
        return matrix;
    }

}
