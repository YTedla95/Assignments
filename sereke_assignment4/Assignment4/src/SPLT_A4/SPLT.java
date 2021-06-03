package SPLT_A4;

public class SPLT implements SPLT_Interface{
  private BST_Node root;
  private int size;
  
  public SPLT() {
    this.size = 0;
  } 
  
  public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
    return root;
  }

@Override
public void insert(String s) 
{
	// TODO Auto-generated method stub
	if (root==null)
	{
		  root=new BST_Node(s);
		 size++;
	}
	else
	{
		if(contains(s)==false) 
		{
			root=root.insertNode(s);
			size++;
		}
	}
}

@Override
public void remove(String s) 
{
		if (root==null || contains(s)==false)
		{
		return;
		}
		else if(root.left==null)
		{
			root=root.right;
		}
		else  
		{
			BST_Node r=root.right;
			if(root.right==null)
			{
				root=root.left;
			}
			if(root.right!=null)
			{
			
				root=root.left.findMax();
				root.right=r;
				if(root.right==null)
				{
					root.right.par=root;
				}
			}
			
		}
		if(root!=null)
		{
			root.par=null;
		}
		size--;
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
	return root.findMax().getData();}

@Override
public boolean empty() {
	// TODO Auto-generated method stub
	if(root==null)
	{
		return true;
	}
	return false;
}

@Override
public boolean contains(String s) {
	// TODO Auto-generated method stub
	if(size==0)
	{
		return false;
	}
	if(root.data==s)
	{
		return true;
	}
	else
	{
		return false;
	}
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