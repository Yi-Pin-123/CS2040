import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Collections;

public class KattisQuest {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Create a Tree to store the energy values
        TreeSet<Long> energies = new TreeSet<>();
        
        // Create a new hashmap, key is energy and value is a priority queue
        HashMap<Long, PriorityQueue<Long>> energyToGold = new HashMap<>();

        int N = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            String command = input[0];

            if ("add".equals(command)) {
                long energy = Long.parseLong(input[1]);
                long gold = Long.parseLong(input[2]);

                // If energy is not a key in the hashmap, add energy to tree.
                //If its already inside then nothing will happen cus tree no duplicates
                energies.add(energy);
                // Add or update the priority queue for the specific energy level
                energyToGold.computeIfAbsent(energy, k -> new PriorityQueue<>(Collections.reverseOrder())).add(gold);
            } else if ("query".equals(command)) {
                long energy_left = Long.parseLong(input[1]);
                long total_gold = 0;

                while (!energies.isEmpty() && energy_left > 0) {
                    // Use the floor function to get the energy value that is smaller than or equal to energy_left.
                    Long energy = energies.floor(energy_left);
                    if (energy == null) break;

                    // Get the priority queue in the hashmap and dequeue the highest gold value
                    PriorityQueue<Long> goldQueue = energyToGold.get(energy);
                    total_gold += goldQueue.poll();
                    energy_left -= energy;

                    // If the priority queue becomes empty, delete the key-value pair and remove the energy from the tree.
                    if (goldQueue.isEmpty()) {
                        energyToGold.remove(energy);
                        energies.remove(energy);
                    }
                }

                System.out.println(total_gold);
            }
        }
    }
}
