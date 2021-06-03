package BST_A2;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  
  BST_Node(String data){ this.data=data; }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- fill in these methods ------------------------------------------
  //
  // at the moment, they are stubs returning false or some
  // appropriate "fake" value
  // you make them work properly
  // add the meat of correct implementation logic to them
  //
  // you may use recursive or iterative implementations

  public boolean containsNode(String s)
  {
	  if (data==null)
	  {
	  return false;   
	  }
	  int compareResult=s.compareTo(data);
	  if(compareResult<0)
	  {
		  if (left==null)
		  {
			  return false;
		  }
		  return left.containsNode(s);
			  
		 
	  }
	  else if(compareResult>0)
	  {
		  if(right==null)
		  {
			  return false;
		  }
		  return right.containsNode(s);
			  
	  }
	
	return true;
  }
  
  public boolean insertNode(String s)
  { 
	  int compareTo=s.compareTo(data);
	  if(compareTo<0)
	  {
		  if(left==null)
		  {
			  left= new BST_Node(s);
			  return true;
			  
		  }
		  else
			  {
				 return left.insertNode(s);
			  }
		  
	  }
	  else if(compareTo>0)
	  {
		  if(right==null)
		  {
			  right=new BST_Node(s);
			  return true;
		  }
		  else
		  {
			  return right.insertNode(s);
		  }
	  }
	 
	return false;
  }
  public boolean removeNode(String s) 
  {

		if (right != null) 
		{
			if (s.compareTo(right.data) == 0) 
			{
				if (right.right == null && right.left == null) 
				{ 
					right = null;
					return true;
				}
				if (right.left != null) 
				{ 
					String holder;
					holder = right.left.findMax().getData();
					right.removeNode(holder);
					right.data = holder;

					return true;
				} 
				else
				{ 
					right = right.right;
					return true;
				}
			}
		} 
		if (left != null)
		{
			if (left.data.compareTo(s) == 0) 
			{				
				if(left.right!=null)
				{
					left=left.right;
					return true;
				}
				if (left.left!=null) 
				{ 
					String temp;
					temp = left.left.findMax().getData();
					left.removeNode(temp);
					left.data = temp;
					return true;
				}
				else
				{ 
					left = null;
					return true;
				}
				
			}
		}
	  int compareResult=s.compareTo(data);
	  if(compareResult>0 && right!=null || left==null)
	  {
		  right.removeNode(s);
	  }
	  else if(compareResult<0 && left!=null || right==null)
	  {
		  left.removeNode(s);
	  }
	  return false;
  }
  public BST_Node findMin()
  {
	  if(left!=null)
	  {
		  return left.findMin();
	  }
	  
	  return this; 
}
  public BST_Node findMax()
  {
	  if (right!= null)
	  {
		  return right.findMax();
	  } 
	  return this;
  }
  public int getHeight()
  { 
	  
	  if(left==null && right==null)
	  {
		  return 0;
	  }
	  else if(left!=null && right!=null)
	  {
		  return Math.max(left.getHeight()+1,right.getHeight()+1);
	  }
	  else if(left==null)
	  {
		  return right.getHeight()+1;
	  }
	  else if(right==null)
	  {
		  return left.getHeight()+1;
	  }
	  else
	  {
		  return -1;
	  }

  }

  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}