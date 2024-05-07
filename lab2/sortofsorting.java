import java.io.*;
import java.util.*;

public class sortofsorting {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String[]> results = new ArrayList<>(); 
        //use arraylist cus it can contains array

        int i = 0;
        while (i < 500) {
            int n = Integer.parseInt(reader.readLine());

            if (n == 0) {
                break;
            }

            String[] names = new String[n];
            for (int j = 0; j < n; j++) {
                names[j] = reader.readLine();
            }

            // Sorting based on the first two characters
            Arrays.sort(names, new Comparator<String>() {
                public int compare(String s1, String s2) {
                    
                    //compareTo is a method in the String class that compares two strings lexicographically
                    //you dont need to manually define the -1 +1 and 0 part cus compareTo does that
                    return s1.substring(0, 2).compareTo(s2.substring(0, 2));
                }
            });

            results.add(names);
            i++;
        }

        // Printing the sorted names
        for (String[] names : results) {
            for (String name : names) {
                System.out.println(name);
                }
            System.out.println();
            }
        }
    }

