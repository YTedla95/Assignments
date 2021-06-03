import java.util.*;

public class DiGraph implements DiGraphInterface
{
	// in here go all your data and methods for the graph
	  // and the topo sort operation
	Hashtable <String, Node> table = new Hashtable <String, Node> ();
	ArrayList<Long> idNodes = new ArrayList<Long>();
	ArrayList<String> Labels = new ArrayList <String>();
	ArrayList<Long> idEdges = new ArrayList <Long>();
	long nn=0;
	long en=0;
	public DiGraph ( ) 
	{ // default constructor
	    // explicitly include this
	    // we need to have the default constructor
	    // if you then write others, this one will still be there
	
	}
	
@Override
	public boolean addNode(long idNum, String label)
{
	// TODO Auto-generated method stub
	if(table.contains(label) || idNodes.contains(idNum) || idNum<0)
	{
		return false;
	}
	else
	{
		Node node = new Node(idNum, label);
		table.put(label,node);
		idNodes.add(idNum);
		Labels.add(label);
		this.nn++;
		return true;
	}
}
	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel,
			long weight, String eLabel) 
	{
		// TODO Auto-generated method stub
		if(idEdges.contains(idNum) || idNum<0)
		{
			return false;
		}
		else
		{
			Edge addEdge = new Edge(idNum, sLabel, dLabel, weight, eLabel);
			Node nodeS=table.get(sLabel);
			Node nodeD=table.get(dLabel);
			nodeS.addToEdge(addEdge);
			nodeD.addFromEdge(addEdge);
			idEdges.add(idNum);
			en++;
			return true;
		}
	}

	@Override
	public boolean delNode(String label) 
	{
		// TODO Auto-generated method stub
		if(table.contains(label))
		{
			Node deleteNode = table.get(label);
			Labels.remove(deleteNode.label);
			idNodes.remove(deleteNode.idNum);
			for(	int i=0; i<deleteNode.getToEdge().size();i++)
			{
				this.delEdge(deleteNode.label, deleteNode.getToEdge().get(i).destinationLabel);
			}
			for(int i=0; i < deleteNode.getFromEdge().size();i++)
			{
				this.delEdge(deleteNode.getFromEdge().get(i).sourceLabel,deleteNode.label);
			}
			this.nn--;
			table.remove(label);
			return true;
		}
		else
		{
		return false;
		}
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel)
	{
		// TODO Auto-generated method stub
		Node sourceNode = table.get(sLabel);
		Node destinationNode = table.get(dLabel);
		String dlabel = destinationNode.label;
		String slabel = sourceNode.label;
		LinkedList<Edge> sList = sourceNode.getToEdge();
		LinkedList<Edge> dList = destinationNode.getFromEdge();
		for (int i = 0;i<sList.size();i++)
		{
			if (sList.get(i).destinationLabel.compareTo(dlabel) == 0) 
			{
				sList.remove(i);
				
				for (int k = 0; k < dList.size(); k++) 
				{
					if (dList.get(k).sourceLabel.compareTo(slabel) == 0) 
					{
						idEdges.remove(dList.get(k));
						dList.remove(k);
						return true;
					}
				}
			}
			this.en--;
		}
		return false;
	}

	@Override
	public long numNodes() 
	{
		return nn;
		// TODO Auto-generated method stub
	}

	@Override
	public long numEdges() 
	{
		// TODO Auto-generated method stub
		return en;
	}

	@Override
	public void print()
	{
		// TODO Auto-generated method stub

		for (int i = 0; i < idNodes.size(); i++)
		{
			System.out.println("(" + idNodes.get(i) + ")" + Labels.get(i));
			for (int j = 0; j < table.get(Labels.get(i)).getToEdge().size(); j++) 
			{
				System.out.print("  (" + table.get(Labels.get(i)).getToEdge().get(j).getID() + ")--");
				if (table.get(Labels.get(i)).getToEdge().get(j).getLabel() != " ") 
				{
					System.out.print(table.get(Labels.get(i)).getToEdge().get(j).getLabel() + ",");
				}
				System.out.print(table.get(Labels.get(i)).getToEdge().get(j).getWeight() + "--> " + table.get(Labels.get(i)).getToEdge().get(j).destinationLabel);
				System.out.println("");
			}
			System.out.println("");
		}
	}

	@Override
	public String[] topoSort()
	{
		String[] tL= new String[300];
		int index=0;
		int degree=0;
		int i=0;
		
		// TODO Auto-generated method stub
		while (i < Labels.size()) {
			LinkedList<Edge> currentEdge = table.get(Labels.get(i)).getFromEdge();
			degree = currentEdge.size();
			for (int j = 1; j <= index; j++) 
			{
				if (tL[j-1].compareTo(table.get(Labels.get(i)).label) == 0) 
				{
					degree--;
				}
				for (int k = 0; k < currentEdge.size(); k++) 
				{
					if ((currentEdge.get(k) != null) && tL[j-1].compareTo(currentEdge.get(k).sourceLabel) == 0)
					{
						degree--;
					}
				}
			}
			if (degree == 0 )
			{
				tL[index] = table.get(Labels.get(i)).label;
				index++;
				i = 0;
			} 
			else 
			{
				i++;
			}
		}
		if (index == this.numNodes()) {
			String[] rL = new String[index];
			for (int x = 0; x<=index; x++){
				rL[x] = tL[x];
			}
			return rL;
		} else {
		return null;
	}
}
class Node
{
	
	 long idNum;
	 String label;
	 LinkedList<Edge> toEdge= new LinkedList<Edge>();
	 LinkedList<Edge> fromEdge= new LinkedList<Edge>();
	 
	 Node()
	 {}
	 Node(long idNum, String label) {
		// TODO Auto-generated method stub
		this.idNum=idNum;
		this.label=label;
	}
	 public void addToEdge(Edge edge)
	 {
		 toEdge.add(edge);
	 }

	 public LinkedList<Edge> getToEdge()
	 {
		 return toEdge;
	 }
	 public void addFromEdge(Edge edge)
	 {
		 fromEdge.add(edge);
	 }
	 public LinkedList<Edge> getFromEdge()
	 {
		 return fromEdge;
	 }
}

class Edge
{
	long idNumber,weight;
	String sourceLabel;
	String destinationLabel;
	String edgeLabel;
	public Edge(long idNum,String sLabel, String dLabel,long weight,String eLabel)
	{
		idNumber=idNum;
		this.weight=weight;
		sourceLabel=sLabel;
		destinationLabel=dLabel;
		edgeLabel=eLabel;
	}
	public long getWeight()
	{
		return weight;
	}
	public String getLabel()
	{
		return edgeLabel;
	}
	public long getID()
	{
		return idNumber;
	}
}
}