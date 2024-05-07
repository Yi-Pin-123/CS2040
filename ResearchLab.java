import java.io.*;
import java.util.PriorityQueue;
import java.util.Comparator;

public class ResearchLab {

    static int unlocks = 0;

    static class Workstation {
        int start_use;
        int end_use;
        int unlock_until;

        Workstation(int start, int end, int unlock) {
            this.start_use = start;
            this.end_use = end;
            this.unlock_until = unlock;
        }
    }
    
    static class Researcher {
        int arrival_time;
        int use_time;

        Researcher(int arrival_time, int use_time) {
            this.arrival_time = arrival_time;
            this.use_time = use_time;
        }
    }    
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Reading Number_of_researchers and minutes_of_inactivity
        String[] firstLine = reader.readLine().split(" ");
        int Number_of_researchers = Integer.parseInt(firstLine[0]);
        int minutes_of_inactivity = Integer.parseInt(firstLine[1]);

        // Priority queues setup
        PriorityQueue<Workstation> queue1 = new PriorityQueue<>(Comparator.comparingInt(w -> w.unlock_until));
        //PriorityQueue<Workstation> queue2 = new PriorityQueue<>(Comparator.comparingInt(w -> w.unlock_until));
        PriorityQueue<Researcher> researcherQueue = new PriorityQueue<>(Comparator.comparingInt(r -> r.arrival_time));
        
        // Process each researcher
        for (int i = 0; i < Number_of_researchers; i++) {
            String[] line = reader.readLine().split(" ");
            int arrival_time = Integer.parseInt(line[0]);
            int use_time = Integer.parseInt(line[1]);
            Researcher researcher = new Researcher(arrival_time, use_time);
            researcherQueue.add(researcher);
        }    
        while (!researcherQueue.isEmpty()) {
            Researcher researcher = researcherQueue.poll();
            boolean queued = false;

            while (!queued) {
                if (queue1.isEmpty()) {
                    //System.out.println("queue 1 empty");
                    createStation(researcher.arrival_time, researcher.use_time, minutes_of_inactivity, queue1);
                    queued = true;
                    
                } else {
                    Workstation workstation = queue1.poll();
                    if (workstation.end_use > researcher.arrival_time) {
                        //System.out.println("all station in use");
                        queue1.add(workstation);
                        createStation(researcher.arrival_time, researcher.use_time, minutes_of_inactivity, queue1);
                        queued = true;
                        
                    } else if (workstation.unlock_until >= researcher.arrival_time) {
                        workstation.start_use = researcher.arrival_time;
                        workstation.end_use = researcher.arrival_time + researcher.use_time;
                        workstation.unlock_until = workstation.end_use + minutes_of_inactivity;

                        queue1.add(workstation);
                        queued = true;
                        //System.out.println("station updated");
                    
                    /***    
                    } else {
                        System.out.println("station locked, disposed, proceeding to next station");
                    ***/  
                    }
                }
            }

        }  

        // Finally, return the result
        System.out.println(Number_of_researchers - unlocks);
    } 
    private static void createStation(int arrival_time, int use_time, int minutes_of_inactivity, PriorityQueue<Workstation> queue) {
        int end_use = arrival_time + use_time;
        int unlock_until = end_use + minutes_of_inactivity;
        Workstation newStation = new Workstation(arrival_time, end_use, unlock_until);
        queue.add(newStation);
        
        unlocks++;
        
        //System.out.println("new station created at researcher with arrival time " + arrival_time);
    }
}
