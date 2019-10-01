import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;

/**
 * Follows the directions given in Assignment4.pdf
 */
public class Assignment4 {

    private static Graph graph;

    private static int buildGraph(String input) {
        graph = new Graph();

        List<String> lines = null;
        try {
            lines = Files.readAllLines(new File(input).toPath());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Failed to read file: " + input);
        }

        for (String line : lines) {
            String[] split = line.split(",");
            String town1 = split[0];
            String town2 = split[1];

            if (!graph.townNames.contains(town1))
                graph.townNames.add(town1);

            if (!graph.townNames.contains(town2))
                graph.townNames.add(town2);

            graph.insert(town1, town2, Integer.parseInt(split[2]));
        }


        return graph.townNames.size();
    }


    public static void main(String args[]) {

        // build the graph from the input file
        int count = buildGraph(args[0]);

        System.out.println(count + " towns added to graph");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            System.out.println("Cannot accept input");
            return;
        }

        String town1, town2;
        while (true) {

            try {
                System.out.print("First town: ");
                town1 = br.readLine();
                if (town1.length() == 0)
                    return;

                System.out.print("Second town: ");
                town2 = br.readLine();
            } catch (Exception e) {
                System.out.println("Input error: " + e);
                return;
            }

            graph.shortestPath(town1, town2);
        }
    }
}
