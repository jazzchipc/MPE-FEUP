package logic.algorithm;

import logic.graph.Graph;
import logic.graph.Node;

import java.util.ArrayList;

public abstract class Algorithm {

    private String name;
    Graph graph;
    ArrayList<Node> bestRoute;
    int numOfIterations;

    ArrayList<Integer> historyOfBestCosts;

    public static long MAX_PROCESS_TIME_MILLIS = 1000;

    private long endTime = 0;

    /**
     * Constructor.
     * @param graph graph to be parsed.
     */
    Algorithm(String name, Graph graph){
        this.name = name;
        this.graph=graph;
        this.historyOfBestCosts = new ArrayList<>();
    }

    public void setNewBestRoute(ArrayList<Node> newBestRoute){
        setNewBestRoute(newBestRoute, this.graph.getRouteCost(newBestRoute));
    }

    public void setNewBestRoute(ArrayList<Node> newBestRoute, int costOfNewBestRoute){
        this.bestRoute = newBestRoute;
        this.historyOfBestCosts.add(costOfNewBestRoute);
    }

    /**
     * Gets the cost of the best route found, if available.
     * @return cost of bestRoute; -1 if bestRoute was not calculated.
     */
    public int getBestRouteCost(){
        return this.graph.getRouteCost(this.bestRoute);
    }

    /**
     * Prints the results of the algorithm in a user-friendly way, including the best route and its cost.
     */
    public void printResults(){
        if(bestRoute==null || bestRoute.size()==0){
            System.out.println("Best route is not defined!");
            return;
        }

        boolean firstPrint = true;
        System.out.print("Route(");
        for(Node node: bestRoute){
            if(firstPrint){
                firstPrint = false;
                System.out.print(node.getName());
            } else System.out.print(", "+node.getName());
        }
        System.out.println(")");
        System.out.println("Cost = "+getBestRouteCost());
        System.out.println("Number of iterations = " + this.numOfIterations);
        System.out.println("Number of nodes = " + this.graph.getNodesAmount());
    }


    public String getName() {
        return name;
    }

    /**
     * Implementation of the algorithm.
     */
    public abstract void computeSolution();

    public void startTimer(){
        this.endTime = System.currentTimeMillis() + MAX_PROCESS_TIME_MILLIS;
    }

    protected boolean timerEnded(){
        return (System.currentTimeMillis() > this.endTime);
    }
}
