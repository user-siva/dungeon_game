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

class Dungeon4 {

    public static int bfs(char[][] arr, int i, int j, boolean isMonster, boolean trigger) {
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
            if (trigger) {
                if (arr[currRow][currCol] == 'T') {
                    if (!isMonster)
                        System.out.println("Minimum Path:" + paths);
                    return currSteps;
                }
            } else if (arr[currRow][currCol] == 'G') {
                if (!isMonster)
                    System.out.println("Minimum Path:" + paths);
                return currSteps;
            }

            int[] rows = { 1, -1, 0, 0 };
            int[] cols = { 0, 0, 1, -1 };

            for (int d = 0; d < 4; d++) {
                int nextCol = currCol + rows[d];
                int nextRow = currRow + cols[d];

                if (nextCol >= 0 && nextCol < n && nextRow >= 0 && nextRow < m && !visited[nextRow][nextCol]) {
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

        System.out.println("Enter position of Triggers:");
        System.out.println("Row:");
        int t1 = sc.nextInt();
        System.out.println("Column:");
        int t2 = sc.nextInt();

        System.out.println("Enter position of Gold:");
        System.out.println("Row:");
        int g1 = sc.nextInt();
        System.out.println("Column:");
        int g2 = sc.nextInt();

        mat[a1][a2] = 'A';
        mat[m1][m2] = 'M';
        mat[g1][g2] = 'G';
        mat[t1][t2] = 'T';

        int monster = bfs(mat, m1, m2, true, false);

        int adventurer = bfs(mat, a1, a2, false, false);

        int trigger = bfs(mat, a1, a2, false, true);

        int triggerToGold = bfs(mat, t1, t2, false, false);

        if (adventurer < monster)
            System.out.println("Minimum number of steps:" + adventurer);
        else if (trigger <= monster)
            System.out.println("Minimum Steps:" + (trigger + triggerToGold));
        else {
            System.out.println("No possible Solution");
        }
    }
}