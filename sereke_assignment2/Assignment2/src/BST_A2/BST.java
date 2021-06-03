package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  
  public BST(){ size=0; root=null; }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }

@Override
public boolean insert(String s) {
	// TODO Auto-generated method stub
	if (root==null){
	  root=new BST_Node(s);
	 size++;
	return true;
	}
	
	if (root.insertNode(s)==true)
	{
		size++;
		return true;
	}
	else
	{
		return false;
	}
	}


@Override
public boolean remove(String s) {
	// TODO Auto-generated method stub
	if (root==null)
	{
	return false;
	}
	if(!contains(s))
	{
		return false;
	}
	else if(s.compareTo(root.data)==0)
	{
		if (root.right==null && root.left==null)
		{
			root=null;
			size--;
			return true;
		}
		else if(root.right!=null)
		{
			root=root.right.findMin();
			size--;
			return true;
		}
		else
		{
			root=root.left.findMax();
			size--;
			return true;
		}
	}
		else if(root.containsNode(s) && size==2)
		{
			if(root.left!=null)
			{
				root.left=null;
				size--;
				return true;
			}
			else
			{
				root.right=null;
				size--;
				return true;
			}
		}
	
	else
	{
		if(root.removeNode(s))
		{
			size--;
			return true;
		}
		else
		{
			return false;
		}
	}
	
}

@Override
public String findMin() {
	// TODO Auto-generated method stub
	if(root==null)
	{
		return null;
	}
	return root.findMin().getData();
}
@Override
public String findMax() {
	// TODO Auto-generated method stub
	if(root==null)
	{
		return null;
	}
	return root.findMax().getData();
}

@Override
public boolean empty() {
	// TODO Auto-generated method stub
	if(root==null)
	{
		return true;
	}
	else{
		return false;
	}
}

@Override
public boolean contains(String s) {
	// TODO Auto-generated method stub
	if(root==null){
		return false;
	}
	else
	return root.containsNode(s);
}

@Override
public int size() {
	// TODO Auto-generated method stub
	return size;
}

@Override
public int height() {
	// TODO Auto-generated method stub
	if(empty())
	{
		return 0;
	}
	else
	{
		return root.getHeight();
	}
}

}