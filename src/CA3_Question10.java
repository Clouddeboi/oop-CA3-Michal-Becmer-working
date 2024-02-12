import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *  Name: Michal Becmer
 *  Class Group: GD2A
 */
public class CA3_Question10 {

    public static void main(String[] args) throws FileNotFoundException {

        Map<String, HashSet<DistanceTo>> distances = new HashMap<>();//stores distances between cities
        Scanner s = new Scanner(new File("src//locations.txt"));//scanner to read file

        //Read distances from file and construct the graph
        while (s.hasNext())
        {
            int distance = Integer.parseInt(s.next());//reads distance between
            String start = s.next();//reads starting city
            String end = s.next();//reads ending city

            //if its not already on the map add it
            if (!distances.containsKey(start))
            {
                distances.put(start, new HashSet<>());
            }
            distances.get(start).add(new DistanceTo(end, distance));//adds new distanceTo for the distance between the starting and ending city
        }

        Scanner input = new Scanner(System.in);//scanner for user input
        System.out.println("Enter Starting City From List:\nPendleton\nPierre\nPueblo\nPeoria");
        String start = input.next().trim().toLowerCase();

        //Find the shortest distances using Dijkstra's algorithm
        TreeMap<String, Integer> quickest = findShortestDistances(distances, start);

        printShortest(quickest);//prints shortest distances
    }

    // Method to find the shortest distances using Dijkstra's algorithm
    private static TreeMap<String, Integer> findShortestDistances(Map<String, HashSet<DistanceTo>> distances, String startingCity) {

        PriorityQueue<DistanceTo> distanceQueue = new PriorityQueue<>();//priority queue to store distances from starting city
        distanceQueue.add(new DistanceTo(startingCity, 0));//adds staring city with a distance of 0

        TreeMap<String, Integer> quickest = new TreeMap<>();//stores shortest distances from start city to each city

        //keeps looping until priority queue is empty
        while (!distanceQueue.isEmpty())
        {
            //removes smallest distance element from the priority queue
            DistanceTo smallestDist = distanceQueue.poll();
            int dist = 0;
            if (!quickest.containsKey(smallestDist.getTarget()))//check if target city hasnt been visited
            {
                //update shortest distance(target city)
                dist = smallestDist.getDistance();
                quickest.put(smallestDist.getTarget(), dist);
                //loop over tge connected cities
                for (Map.Entry<String, HashSet<DistanceTo>> entry : distances.entrySet())
                {
                    if (entry.getKey().equals(smallestDist.getTarget()))
                    {
                        //add connected cities to priority queue
                        for (DistanceTo next : entry.getValue())
                        {
                            distanceQueue.add(new DistanceTo(next.getTarget(), next.getDistance() + dist));
                        }
                    }
                }
            }
        }
        return quickest;
    }

    // Method to print the shortest distances to all other cities
    private static void printShortest(TreeMap<String, Integer> shortestDistances) {
        for (Map.Entry<String, Integer> entry : shortestDistances.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
