import java.util.*;

class Path {
    int i;
    int j;
    int steps;
    StringBuilder path = new StringBuilder();

    Path(int i, int j, int step, String path) {
        this.i = i;
        this.j = j;
        this.steps = step;
        this.path.append(path);
    }
}

class Dungeon6 {

    public static int bfs(char[][] arr, int i, int j, boolean isMonster) {
        int m = arr.length, n = arr[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<Path> q = new PriorityQueue<>((a, b) -> a.steps - b.steps);

        q.add(new Path(i, j, 0, "(" + i + "," + j + ")" + "->"));
        while (!q.isEmpty()) {
            Path p = q.remove();
            int currRow = p.i;
            int currCol = p.j;
            int currSteps = p.steps;
            StringBuilder paths = p.path;
            if (arr[currRow][currCol] == 'G') {
                System.out.println("Minimum Path:" + paths);
                return currSteps;
            }

            int[] rows = { 1, -1, 0, 0 };
            int[] cols = { 0, 0, 1, -1 };

            for (int d = 0; d < 4; d++) {
                int nextCol = currCol + rows[d];
                int nextRow = currRow + cols[d];

                if (nextCol >= 0 && nextCol < n && nextRow >= 0 && nextRow < m && !visited[nextRow][nextCol]
                        && (arr[nextRow][nextCol] != 'P' || isMonster)) {
                    visited[nextRow][nextCol] = true;
                    q.add(new Path(nextRow, nextCol, currSteps + 1,
                            paths + "(" + nextRow + "," + nextCol + ")" + "->"));
                }
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Dimention");
        System.out.println("Row:");
        int x = sc.nextInt();
        System.out.println("Column:");
        int y = sc.nextInt();

        char[][] mat = new char[x][y];
        for (char[] a : mat)
            Arrays.fill(a, '.');

        System.out.println("Enter position of Adventurer:");
        System.out.println("Row:");
        int a1 = sc.nextInt();
        System.out.println("Column:");
        int a2 = sc.nextInt();

        System.out.println("Enter position of Monster:");
        System.out.println("Row:");
        int m1 = sc.nextInt();
        System.out.println("Column:");
        int m2 = sc.nextInt();

        System.out.println("Enter Number of Pits:");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter position of Pit:");
            System.out.println("Row:");
            int p1 = sc.nextInt();
            System.out.println("Column:");
            int p2 = sc.nextInt();
            mat[p1][p2] = 'P';
        }

        System.out.println("Enter position of Gold:");
        System.out.println("Row:");
        int g1 = sc.nextInt();
        System.out.println("Column:");
        int g2 = sc.nextInt();

        mat[a1][a2] = 'A';
        mat[m1][m2] = 'M';
        mat[g1][g2] = 'G';

        int adventurer = bfs(mat, a1, a2, false);
        int monster = bfs(mat, m1, m2, true);

        if (monster <= adventurer)
            System.out.println("No Possible Solution");
        else
            System.out.println("Minimum Steps:" + adventurer);

    }
}