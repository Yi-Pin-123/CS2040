import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IslandCounter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            String[] parts = line.split(" ");
            int R = Integer.parseInt(parts[0]);
            int C = Integer.parseInt(parts[1]);
            
            char[][] matrix = new char[R][C];
            for (int i = 0; i < R; i++) {
                String row = reader.readLine();
                for (int j = 0; j < C; j++) {
                    matrix[i][j] = row.charAt(j);
                }
            }
            
            
            int count = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (matrix[i][j] == 'L') {
                        dfs(matrix, i, j, R, C);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }
        
    private static void dfs(char[][] matrix, int i, int j, int R, int C) {
        if (i < 0 || i >= R || j < 0 || j >= C || (matrix[i][j] != 'L' && matrix[i][j] != 'C')) {
            return;
        }

        matrix[i][j] = 'V'; // Mark as visited

        // Recursive calls for adjacent cells
        dfs(matrix, i + 1, j, R, C); // down
        dfs(matrix, i - 1, j, R, C); // up
        dfs(matrix, i, j + 1, R, C); // right
        dfs(matrix, i, j - 1, R, C); // left
    }        

}


