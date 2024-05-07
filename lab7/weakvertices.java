import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class weakvertices {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();
            int N = Integer.parseInt(line);

            if (N == -1) {
                break;
            }

            boolean[] vertex = new boolean[N];
            Arrays.fill(vertex, false);

            int[][] matrix = new int[N][N];

            for (int i = 0; i < N; i++) {
                line = br.readLine();
                String[] inputs = line.split(" ");
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(inputs[j]);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        if (i != j && j != k && i != k) {
                            if (matrix[i][j] == 1 && matrix[i][k] == 1 && matrix[j][k] == 1) {
                                vertex[i] = vertex[j] = vertex[k] = true;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                if (!vertex[i]) {
                    System.out.print(i + " ");
                }
            }
        }
    }
}
