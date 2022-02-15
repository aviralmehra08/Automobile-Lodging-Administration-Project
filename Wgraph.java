import java.io.*;
import java.util.*;

public class Wgraph
{
	int distance[];
	Set<Integer> settled;
	PriorityQueue<Node> graph;
	int vertice;
	List<List<Node> > adj;
	public Wgraph(int vertice)
	{
		this.vertice = vertice;
		distance = new int[vertice];
		settled = new HashSet<Integer>();
		graph = new PriorityQueue<Node>(vertice, new Node());
	}
	public void dijkstra(List<List<Node> > adj, int src)
	{
		this.adj = adj;
		for (int i = 0; i < vertice; i++)
			distance[i] = Integer.MAX_VALUE;
		graph.add(new Node(src, 0));
		distance[src] = 0;
		while (settled.size() != vertice)
		{
			if (graph.isEmpty())
				return;
			int u = graph.remove().node;
			if (settled.contains(u))
				continue;

			settled.add(u);
			edge(u);
		}
	}
	public static void printDetails(List<String> list, List<String> slots)
	{
		for(int i=0;i<list.size();i++)
		System.out.println(list.get(i));
		
		Random r = new Random();
				int low = 10;
				int high = 100;
				int result = r.nextInt(high-low) + low;
				
				System.out.println("Token Number "+result);
	}
	public static boolean fcfs(List<String> time)
	{
		List<String> dup = new ArrayList<String>();
		for(String element: time)
		{
			if(!dup.contains(element))
			dup.add(element);
		}
		if(dup.size()< time.size())
		{
            System.out.println("Slot already booked. Choose another time.");
            return false;
        }

		else if(dup.size()==time.size())
		return true;

        return true;

		
	}
	public void edge(int u)
	{

		int edgeDistance = -1;
		int newDistance = -1;
		for (int i = 0; i < adj.get(u).size(); i++)
		{
			Node v = adj.get(u).get(i);
			if (!settled.contains(v.node))
			{
				edgeDistance = v.cost;
				newDistance = distance[u] + edgeDistance;
				if (newDistance < distance[v.node])
					distance[v.node] = newDistance;
				graph.add(new Node(v.node, distance[v.node]));
			}
		}
	}
	public static void main(String arg[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int vertices = 6;
		int source = 0;
		List<List<Node> > adj= new ArrayList<List<Node> >();
		for (int i = 0; i < vertices; i++)
		{
			List<Node> item = new ArrayList<Node>();
			adj.add(item);
		}
		adj.get(0).add(new Node(1, 5)); 
        adj.get(0).add(new Node(2, 3)); 
        adj.get(0).add(new Node(3, 2)); 
        adj.get(0).add(new Node(4, 3));
        adj.get(0).add(new Node(5, 3));
        adj.get(2).add(new Node(1, 2)); 
        adj.get(2).add(new Node(3, 3));
		Wgraph dpq = new Wgraph(vertices);
		dpq.dijkstra(adj, source);
		
		List<String> db = new ArrayList<String>();
		List<String> slot = new ArrayList<String>();
		List<String> time = new ArrayList<String>();
		System.out.println("Welcome User");
		System.out.println("Enter your choice");
		System.out.println("1. For New User");
		System.out.println("2. For Existing User");
		int input=1;
		int ch=Integer.parseInt(br.readLine());
        int k=0;
			do{
				switch(ch)
			{
				case 1:
				System.out.println("Enter your details");
				System.out.println("Enter name and mobile number");
				String str=br.readLine();
				System.out.println("Enter Desired Time");
				String s=br.readLine();
				time.add(s);
				db.add(str);
				boolean b=fcfs(time);
				
				if(db.contains(str))
					{
                        if(k>=1)
					{
                        System.out.println("Existing user");
                    }
                    k++;
                    if(b==false)
                    {
                        input=0;
                        break;
                    }
					System.out.println("Enter 2 to park or 0 to exit");
					ch=Integer.parseInt(br.readLine());
					if(ch==2)
						{
						input=1;
						}
					else
						{
						input=0;
						}
					break;
					}
				else 
				{
					System.out.println("Welcome New User");
					ch=2;
					input=1;
					break;
				}
				
				case 2:
				System.out.println("Parking Details");
				for (int i = 0; i < dpq.distance.length; i++)
			    System.out.println("Distance from source "+source + " to vertex " + i + " is "+ dpq.distance[i]+" Km");
				System.out.println("Choose a slot");
				String s1=br.readLine();
				slot.add(s1);
				printDetails(db,slot);
				System.out.println("Enter 1 to Add New User or Choose a different slot");
				System.out.println("Enter 0 to Exit");
				ch=Integer.parseInt(br.readLine());
				if(ch==1)
				{
					input=1;
					break;
				}
				else
				{
					input=0;
					break;
				}
				
				default:
				System.out.println("Wrong Choice User");
				input=0;

			}
		} while(input>0);

	}
}
class Node implements Comparator<Node>
{
	public int node;
	public int cost;
	public Node() {}
	public Node(int node, int cost)
	{
		this.node = node;
		this.cost = cost;
	}
	@Override public int compare(Node node1, Node node2)
	{

		if (node1.cost < node2.cost)
			return -1;

		if (node1.cost > node2.cost)
			return 1;

		return 0;
	}
}
