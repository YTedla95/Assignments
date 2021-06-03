package SPLT_A4;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node par; //parent...not necessarily required, but can be useful in splay tree
  boolean justMade; //could be helpful if you change some of the return types on your BST_Node insert.
            //I personally use it to indicate to my SPLT insert whether or not we increment size.
  BST_Node(String data){ 
    this.data=data;
    this.justMade=true;
  }
  
  BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
    this.data=data;
    this.left=left;
    this.right=right;
    this.par=par;
    this.justMade=true;
  }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- Some example methods that could be helpful ------------------------------------------
  //
  // add the meat of correct implementation logic to them if you wish

  // you MAY change the signatures if you wish...names too (we will not grade on delegation for this assignment)
  // make them take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations
BST_Node newNode;
  public BST_Node containsNode(String s)
  {
	  if(data==s)
	  {
		  splay(this);
		  return this;
	  }
	  int compareResult=s.compareTo(data);
	  if(compareResult<0)
	  {
		  if(left==null)
		  {
			  splay(this);
			  return this;
		  }
		  return left.containsNode(s);
	  }
	  if(compareResult>0)
	  {
		  if(right==null)
		  {
			  splay(this);
			  return this;
		  }
		  return right.containsNode(s);
	  }
	  return null;
  }
  
  public BST_Node insertNode(String s)
  { 
	  BST_Node ll;
	  int compareResult=s.compareTo(data);
	  if(compareResult<0)
	  {
		  if(left==null)
		  {
			  left= new BST_Node(s,null,null,this);
			  ll=left;
			  splay(left);
			 return ll;
		  }
		  else
			  {
				 return left.insertNode(s);
			  }
		  
	  }
	   if(compareResult>0)
	  {
		  if(right==null)
		  {
			  right=new BST_Node(s,null,null,this);
			  ll=right;
			  splay(right);
			  return ll;
		  }
			  return right.insertNode(s);
		  }
	   splay(this);
	   return this;
	  }

  public BST_Node findMin()
  {
	  if(left!=null)
	  {
		  
		  return left.findMin();
	  }
	  splay(this);
	  return this; 
}
  public BST_Node findMax()
  {
	  if (right!= null)
	  {
		  return right.findMax();
	  } 
	  splay(this);
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
  public void splay(BST_Node toSplay) 
  { BST_Node B,C,p,g,gg,root;
	 p=toSplay.par;
	 g=(p==null) ? null: p.par;
	 gg=(g==null) ? null: g.par;
	 if(p==null)
	 {
		 return ;
	 }
	 if(g==null)
	 {
		 if(p.left==toSplay)
		 {
			 B=toSplay.right;
			 toSplay.right=p;
			 p.par=toSplay;
			 p.left=B;
			 if (B!= null)
				 B.par=p;
			 toSplay.par=null;
		 }
		 else
		 {
			 B=toSplay.left;
			 toSplay.left=p;
			 p.par=toSplay;
			 p.right=B;
			 if(B!= null)
			 B.par=p;
			 toSplay.par=null;
		 }
	 }
	 if (toSplay==p.right && p==g.right) {       // zig-zig-R
	        C=p.left;
	        B=toSplay.left; 
	        toSplay.par=gg; 
	        if (gg!=null) 
	        {
	        	if (gg.left==g) 
	        	{
	        		gg.left=toSplay; 
	        	}
	        	else
	        	{ 
	        		gg.right=toSplay; 
	        	} 
	        }
	        p.par=toSplay;
	        toSplay.left=p;
	        g.par=p;
	        p.left=g;
	        p.right=B;
	        if (B!=null) B.par=p;
	        g.right=C; 
	        if (C!=null) C.par=g;
	      }
	      else if (toSplay==p.left && p==g.left) {  // zig-zig-L
	        B=toSplay.right;
	        C=p.right; 
	        toSplay.par=gg; 
	        if (gg!=null) 
	        { 
	        	if (gg.left==g)
	        	{ 
	        		gg.left=toSplay;
	        	}
	        	else 
	        	{ 
	        		gg.right=toSplay;
	        	}
	        }
	        toSplay.right=p; 
	        p.par=toSplay;
	        p.right=g;
	        g.par=p; 
	        g.left=C; 
	        if (C!=null) C.par=g;
	        p.left=B; 
	        if (B!=null) B.par=p;
	      }
	      else if (toSplay==p.right && p==g.left) {  // zig-zag-L
	        B=toSplay.left; 
	        C=toSplay.right; 
	        toSplay.par=gg; 
	        if (gg!=null) 
	        {
	        	if (gg.left==g) 
	        	{
	        		gg.left=toSplay; 
	        	} 
	        	else 
	        	{ 
	        		gg.right=toSplay; 
	        	}
	        }
	        toSplay.left=p;
	        p.par=toSplay; 
	        toSplay.right=g;
	        g.par=toSplay;
	        g.left=C;
	        if (C!=null) C.par=g;
	        p.right=B;
	        if (B!=null) B.par=p;
	      } 
	      else if (toSplay==p.left && p==g.right) {  // zig-zag-R
	        B=toSplay.left; 
	        C=toSplay.right; 
	        toSplay.par=gg; 
	        if (gg!=null) 
	        { 
	        	if (gg.left==g)
	        	{
	        		gg.left=toSplay; 
	        	} else 
	        	{ 
	        		gg.right=toSplay; 
	        	}
	        }
	        toSplay.left=g; 
	        g.par=toSplay;
	        toSplay.right=p; 
	        p.par=toSplay;
	        g.right=B;
	        if (B!=null) B.par=g;
	        p.left=C;
	        if (C!=null) C.par=p;
	      } 
	      else
	      {
	    	  return;
	      }
  
  // not done, so recurse... keep splaying
   this.splay(toSplay);
}	
  
		  private void rotateWithRightChild(BST_Node r)
		  {
			  BST_Node r2=r.par;
			  r.par=r2.par;
			  r2.left=r.right;
			  r.right=r2;
		  }
		  private void rotateWithLeftChild(BST_Node l)
		  {
			  BST_Node l2=l.par;
			  l.par=l2.par;
			  l2.right=l.left;
			  l.left=l2;
		  }
 }
	 

  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
