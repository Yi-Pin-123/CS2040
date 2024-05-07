import java.util.PriorityQueue;
import java.util.Scanner;

public class VaultTraversal {
    private static final int[] DX = {-1, 1, 0, 0}; // Direction vectors for rows (N, S, W, E)
    private static final int[] DY = {0, 0, -1, 1}; // Direction vectors for columns (N, S, W, E)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read vault dimensions
        int M = scanner.nextInt();
        int N = scanner.nextInt();

        // Read vault grid heights
        int[][] vaultGrid = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                vaultGrid[i][j] = scanner.nextInt();
            }
        }

        // Calculate and output the minimum ladder length
        int minLadderLength = calculateMinLadderLength(vaultGrid, M, N);
        System.out.println(minLadderLength);
    }

    private static int calculateMinLadderLength(int[][] grid, int M, int N) {
        int[][] dist = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Cell> pq = new PriorityQueue<>();
        pq.offer(new Cell(0, 0, 0)); // Start at the top-left corner with no height difference
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Cell current = pq.poll();
            if (current.x == M - 1 && current.y == N - 1) {
                return dist[M - 1][N - 1]; // Reached the end
            }

            for (int i = 0; i < 4; i++) { // Explore neighbors
                int nx = current.x + DX[i];
                int ny = current.y + DY[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                    int heightDiff = grid[current.x][current.y] < grid[nx][ny] ? grid[nx][ny] - grid[current.x][current.y] : 0;
                    int newHeightDiff = Math.max(dist[current.x][current.y], heightDiff);

                    if (dist[nx][ny] > newHeightDiff) {
                        dist[nx][ny] = newHeightDiff;
                        pq.offer(new Cell(nx, ny, newHeightDiff));
                    }
                }
            }
        }

        return dist[M - 1][N - 1];
    }

    static class Cell implements Comparable<Cell> {
        int x, y, heightDiff;

        Cell(int x, int y, int heightDiff) {
            this.x = x;
            this.y = y;
            this.heightDiff = heightDiff;
        }

        @Override
        public int compareTo(Cell other) {
            return Integer.compare(this.heightDiff, other.heightDiff);
        }
    }
}
