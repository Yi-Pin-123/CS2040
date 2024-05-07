import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Define the Runner class
class Runner {
    String name;
    double Leg1;
    double Leg2;

    public Runner(String name, double Leg1, double Leg2) {
        this.name = name;
        this.Leg1 = Leg1;
        this.Leg2 = Leg2;
    }
}


public class bestrelayteam {
    public static void main(String[] args) throws IOException {
        //Create a reader br to read the inputs
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Create a list to store the Runner's object
        List<Runner> runners = new ArrayList<>();
        
        // Read the number of runners
        int n = Integer.parseInt(br.readLine()); 
        // Read and store runner's details
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            String name = input[0];
            double Leg1 = Double.parseDouble(input[1]);
            double Leg2 = Double.parseDouble(input[2]);
            runners.add(new Runner(name, Leg1, Leg2));
        }
        findBestTeam(runners);
    }

    //find the best team starts here
    public static void findBestTeam(List<Runner> runners) {
        double bestTime = Double.MAX_VALUE;
        List<Runner> bestTeam = new ArrayList<>();
        
        for (Runner Leg1runner : runners) {
            //create a list without current Leg1runner
            List<Runner> remainingRunners = new ArrayList<>(runners);
            remainingRunners.remove(Leg1runner);
            //sort remaining runners
            remainingRunners.sort(Comparator.comparingDouble(runner -> runner.Leg2));
            
            //totalTime  to store time taken for this batch of runner
            double totalTime = Leg1runner.Leg1;
            
            //currentTeam stores this batch of runner object
            List<Runner> currentTeam = new ArrayList<>();
            currentTeam.add(Leg1runner);
            
            for (int i = 0; i < 3; i++) {
                //add Leg2 time of top 3 remaining runners to totalTime
                totalTime += remainingRunners.get(i).Leg2;
                currentTeam.add(remainingRunners.get(i));
            }
            
            if (totalTime < bestTime) {
                bestTime = totalTime;
                bestTeam = currentTeam;
            }
            
        }
        System.out.println(bestTime);
        for (Runner runner : bestTeam) {
            System.out.println(runner.name);
        }
    }
    
}

/***
Consider every runner for the first leg 
then find the three fastest runners (based on Leg2 time) for the remaining legs, 
excluding the runner chosen for the first leg.
***/