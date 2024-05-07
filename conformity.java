import java.io.*;
import java.util.*;


public class conformity {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Map<String, Integer> courseCombi_count = new HashMap<>();
        int maxStudents = 1;
        int totalStudents = 0;

        for (int i = 0; i < n; i++) {
            String[] courses = reader.readLine().split(" ");
            Arrays.sort(courses);
            
            StringBuilder keyBuilder = new StringBuilder();
            for (String course : courses) {
                keyBuilder.append(course);
            }
            String key = keyBuilder.toString();

            courseCombi_count.put(key, courseCombi_count.getOrDefault(key, 0) + 1);
            maxStudents = Math.max(maxStudents, courseCombi_count.get(key));
        }

        for (int count : courseCombi_count.values()) {
            if (count == maxStudents) {
                totalStudents += count;
            }
        }

        System.out.println(totalStudents);
    }
}

