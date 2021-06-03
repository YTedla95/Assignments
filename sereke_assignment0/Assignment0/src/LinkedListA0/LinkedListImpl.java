package LinkedListA0;

public class LinkedListImpl implements LIST_Interface {
	  Node root;//this will be the entry point to your linked list (the head)
	  int size=0;
	  int modcount=0;
	  Node current;
	  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
	    root=new Node(0); //Note that the root's data is not a true part of your data set!
	  }
	  
	  //implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!
	  
	  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
	    return root;
	  }
	@Override
	public boolean insert(Node n, int index) {
		// TODO Auto-generated method stub
		
		if(index<0 || index>size)
		{
			return false;
		}
		Node node= n;
		Node current=root;
		for(int i=0; i<index && current.getNext()!=null;i++)
		{
			current=current.getNext();
		}
		n.next=current.next;
		current.next=n;
		size++;
		return true;
	}

	@Override
	public boolean remove(int index) {
		// TODO Auto-generated method stub
		if(index<0 || index>=size)
		{
			return false;
		}
		Node current=root;
		for(int i=0; i<index; i++)
		{
			
			current=current.getNext();
		}
		current.next=current.getNext().getNext();
		size--;
		return true;
	}

	@Override
	public Node get(int index) {
		// TODO Auto-generated method stub
		if(index<0 || index>=size)
		{
			return null;
		}
		Node current= root;
		for(int i=0; i<=index; i++)
		{
			current=current.getNext();
		}
		return current;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(size == 0)
		{
			return true;
		}
		else
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		root=new Node(0);
		size=0;
	}
	}