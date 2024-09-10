import java.util.*;

class Dungeon {

    public static int findMinimumPath(char[][] arr, int i, int j, int[][] visited) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length)
            return Integer.MAX_VALUE - 1;

        if (arr[i][j] == 'G')
            return 0;

        if (visited[i][j] == 1)
            return Integer.MAX_VALUE - 1;

        visited[i][j] = 1;

        int top = Integer.MAX_VALUE - 1, bottom = Integer.MAX_VALUE - 1, left = Integer.MAX_VALUE - 1,
                right = Integer.MAX_VALUE - 1;

        if (i - 1 >= 0 && visited[i - 1][j] != 1) {
            top = 1 + findMinimumPath(arr, i - 1, j, visited);
        }
        if (i + 1 < arr.length && visited[i + 1][j] != 1) {
            bottom = 1 + findMinimumPath(arr, i + 1, j, visited);
        }

        if (j - 1 >= 0 && visited[i][j - 1] != 1) {
            left = 1 + findMinimumPath(arr, i, j - 1, visited);
        }

        if (j + 1 < arr[0].length && visited[i][j + 1] != 1) {
            right = 1 + findMinimumPath(arr, i, j + 1, visited);
        }

        visited[i][j] = 0;

        int res = Math.min(top, bottom);
        res = Math.min(res, left);
        res = Math.min(res, right);
        return res;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Dimention");
        System.out.println("Row:");
        int x = sc.nextInt();
        System.out.println("Column:");
        int y = sc.nextInt();

        char[][] mat = new char[x][y];
        int[][] visited = new int[x][y];
        for (char[] a : mat)
            Arrays.fill(a, '.');

        System.out.println("Enter position of Adventurer:");
        System.out.println("Row:");
        int a1 = sc.nextInt();
        System.out.println("Column:");
        int a2 = sc.nextInt();

        System.out.println("Enter position of Gold:");
        System.out.println("Row:");
        int g1 = sc.nextInt();
        System.out.println("Column:");
        int g2 = sc.nextInt();

        mat[a1][a2] = 'A';
        mat[g1][g2] = 'G';

        System.out.println("Minimum Path:" + findMinimumPath(mat, a1, a2, visited));
    }
}