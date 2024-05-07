import java.util.*;

public class CannonYourHead {
    static class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        // points comparison
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Edge {
        Point to;
        double time_taken;

        Edge(Point to, double time_taken) {
            this.to = to;
            this.time_taken = time_taken;  
        }
    }

    private static double distance(Point a, Point b) {
        return Math.sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y));
    }

    private static double timeRunning(double distance) {
        return distance / 5.0;
    }

    private static double timeWithCannon(double targetDistance) {
        double additionalDistance = targetDistance - 50; 
        // takes into account overshoot or shoot not far enough
        return 2.0 + timeRunning(Math.abs(additionalDistance));
        
    }

    public static double findShortestTime(Point start, Point dest, List<Point> cannons) {
        List<Point> vertices = new ArrayList<>();
        vertices.add(start);
        vertices.addAll(cannons);
        vertices.add(dest);
        
        // dist stores the time taken from source to any point
        Map<Point, Double> dist = new HashMap<>();
        for (Point vertex : vertices) {
            dist.put(vertex, Double.MAX_VALUE);
        }
        dist.put(start, 0.0);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e.time_taken));
        pq.add(new Edge(start, 0));
        //enqueue the source fist
        
        //adj is the adjacency list
        Map<Point, List<Edge>> adj = new HashMap<>();
        for (Point point : vertices) {
            adj.put(point, new ArrayList<>());
        }

        // Build graph
        for (Point v1 : vertices) {
            for (Point v2 : vertices) {
                if (!v1.equals(v2)) {
                    double d = distance(v1, v2);
                    double time_taken = timeRunning(d);
                    // if it's a cannon, consider using it 
                    if (vertices.indexOf(v1) > 0 && vertices.indexOf(v1) <= cannons.size()) { 
                        time_taken = Math.min(time_taken, timeWithCannon(d)); 
                        //get the minimum time taken, which can be either by running only or by both
                    }
                    adj.get(v1).add(new Edge(v2, time_taken));
                }
            }
        }

        // Dijkstra's algorithm
        Set<Point> visited = new HashSet<>();
        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            Point current = currentEdge.to;
            if (visited.contains(current)) continue;
            visited.add(current);

            for (Edge neighbor : adj.get(current)) {
                if (!visited.contains(neighbor.to) && dist.get(current) + neighbor.time_taken < dist.get(neighbor.to)) {
                    dist.put(neighbor.to, dist.get(current) + neighbor.time_taken);
                    pq.add(new Edge(neighbor.to, dist.get(neighbor.to)));
                }
            }
        }

        // The time taken to the destination is the value in dist associated with dest
        return dist.get(dest);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Point start = new Point(scanner.nextDouble(), scanner.nextDouble());
        Point destination = new Point(scanner.nextDouble(), scanner.nextDouble());
        int n = scanner.nextInt();
        List<Point> cannons = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cannons.add(new Point(scanner.nextDouble(), scanner.nextDouble()));
        }

        double minTime = findShortestTime(start, destination, cannons);
        System.out.println(minTime);
        scanner.close();
    }
}
