import java.io.File;
import java.util.Scanner;
import java.util.SortedMap;

public class Graph {
    int vertexCt;  // Number of vertices in the graph.
    GraphNode[] G;  // Adjacency list for graph.
    String graphName;  //The file from which the graph was created.

    public Graph() {
        this.vertexCt = 0;
        this.graphName = "";
      }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "The Graph " + graphName + " \n" );

        for (int i = 0; i < vertexCt; i++) {
            sb.append( G[i].toString() );
        }
        return sb.toString();
    }

    public boolean makeGraph(String filename) {
        try {
            graphName = filename;
            Scanner reader = new Scanner( new File( "resources/" + filename ) );
            System.out.println( "\n" + filename );
            vertexCt = Integer.parseInt(reader.nextLine());
            G = new GraphNode[vertexCt];
            for (int i = 0; i < vertexCt; i++) {
                G[i] = new GraphNode( i );
            }
            for (int i = 0; i < vertexCt; i++) {
                String[] values = reader.nextLine().split(" ");
                int v1 = Integer.parseInt(values[0]);
                G[v1].nodeName = values[1];
                for (int v = 2; v <values.length; v++){
                    int v2=Integer.parseInt(values[v]);
                    G[v1].addEdge(v1,v2);
                }
             }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        String[] files  = {"Tester.txt", "Tester2.txt", "WesternUS.txt", "BritishIsles.txt", "NortheastUS.txt", "CentralEurope.txt", "IberianPeninsula.txt",
                "SouthernNigeria.txt", "SouthernSouthKorea.txt", "NortheastUS2.txt", "SouthernUS.txt"};

//        String[] files  = {"Tester.txt", "Tester2.txt"};

        Graph graph1;
        for (String file:files) {
            graph1 = new Graph();
            graph1.makeGraph(file);
            System.out.println(graph1.toString());

            SupplySolution greedySolution = new GreedySolution(graph1).findGreedySolution();
            System.out.println("Greedy Solution\n" + greedySolution.toString());

            SupplySolution partialSolution = new SupplySolution(graph1.vertexCt);
            SupplySolution optimalSolution = new OptimalSolution(graph1).findOptimalSolution(0, greedySolution.supplyCt, partialSolution);
            System.out.println("Optimal Solution\n" + optimalSolution.toString());
        }
    }
}