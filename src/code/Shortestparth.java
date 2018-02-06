package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Shortestparth
{
    Node[] vertices;
    int size;
    PriorityQueue<Node> queue;

    public Shortestparth(int size)
    {
        this.size = size;
        vertices = new Node[size];
        addNodes();
        queue = new PriorityQueue<>(size, new Comparator<Node>() {  
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });
    }

    public class Node
    {
        int name;
        int cost;
        Neighbor neighborList;
        int state;
        Node previous;//0 -> new, 1 -> in q, 2 -> visited
        Node(int name)
        {
            this.name = name;
            this.state = 0;
            this.cost = Integer.MAX_VALUE;
        }
    }
    public class Neighbor
    {
        int index;
        int weight;
        Neighbor next;
        public Neighbor(int index, Neighbor next, int weight)
        {
            this.index = index;
            this.next = next;
            this.weight = weight;
        }
    }
    public void addNodes()
    {
        for (int i = 0; i < size; i++)
        {
            addNode(i);
        }
    }

    public void addNode(int name)
    {
        vertices[name] = new Node(name);
    }
    public void addEdge(int sourceName, int desName, int weight)
    {
        int sourceIndex = sourceName;
        int desIndex = desName;
        Node sourceNode = vertices[sourceIndex];
        Node desNode = vertices[desIndex];
        sourceNode.neighborList = new Neighbor(desIndex, sourceNode.neighborList, weight);
        desNode.neighborList = new Neighbor(sourceIndex, desNode.neighborList, weight);
    }
    
    public List<Integer> path(){
    	Node sourceNode = vertices[4];
    	List<Integer> path = new ArrayList<Integer>();
    	for(Node node = sourceNode; node!=null ; node = node.previous){
    		path.add(node.name);
    	}
    	Collections.reverse(path);
    	return path;
    }
    
    public void dijkstra()
    {
    	Node sourceNode = vertices[1];
        queue.offer(sourceNode);
        sourceNode.state = 1;
        sourceNode.cost = 0;
        while (!queue.isEmpty())
        {
            Node visitedNode = queue.poll();
            visitedNode.state = 2;
            Neighbor connectedEdge = visitedNode.neighborList;
            while (connectedEdge != null)
            {
                Node neighbor = vertices[connectedEdge.index];
                if (neighbor.state == 0)
                {
                    queue.offer(neighbor);
                    neighbor.state = 1;
                }
                if (neighbor.state != 2 && (connectedEdge.weight + visitedNode.cost) < neighbor.cost)
                {
                    neighbor.cost = connectedEdge.weight + visitedNode.cost;
                    neighbor.previous = visitedNode;
                }
                connectedEdge = connectedEdge.next;
            }
            
        }  
	
        }
    }
