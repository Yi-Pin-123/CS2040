import java.io.*;
import java.util.*;

public class coconut {
    // Declare 'head' as a static field of the class
    private static int head = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Reading input and splitting
        String[] input = reader.readLine().split(" ");
        int s = Integer.parseInt(input[0]); // syllables
        int n = Integer.parseInt(input[1]); // number of players

        // Initialize the game progress
        ArrayList<int[]> game = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            game.add(new int[]{i, 1, 1});
        }

        boolean decreaseHead = false;

        // Game logic
        while (game.size() > 1) {
            int index = move(s, game.size(), decreaseHead);
            decreaseHead = false;

            int[] currentHand = game.get(index);
            switch (currentHand[2]) {
                case 1:
                    currentHand[2] = 2;
                    game.set(index, currentHand);
                    int[] newHand = {currentHand[0], 2, 2};
                    game.add(index + 1, newHand);
                    decreaseHead = true;
                    break;
                case 3:
                    game.remove(index);
                    decreaseHead = true;
                    break;
                default:
                    currentHand[2]++;
                    game.set(index, currentHand);
                    break;
            }
        }

        // Print the result
        System.out.println(game.get(0)[0] + 1);
    }

    private static int move(int syllables, int gameSize, boolean decreaseHead) {
        /***System.out.print(head);
        System.out.print(",");
        System.out.print(syllables);
        System.out.print(",");
        System.out.print(gameSize);
        System.out.print(decreaseHead); for debugging***/
        if (decreaseHead) {
            head--;
        }
        head = (head + syllables) % gameSize;
        if (head == 0) {
            head = gameSize;
        }
        //System.out.println(head);
        return head - 1;
    }
}
