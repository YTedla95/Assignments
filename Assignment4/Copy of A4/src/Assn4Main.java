import java.io.*;
import java.util.Random;
import java.util.Scanner;

class Assn3Main extends SPLT{
	public static void main (String[] args) {
    	if (args.length==0) {
	      // there are no command like args
	      System.out.println("mode 1");
	      System.out.println("we will do the interactive test driver");
	      Scanner sc=new Scanner(new InputStreamReader(System.in));
	      SPLT t = null;
	      while (true) 
	      {  
	         String  scan = sc.next();
	      	   switch(scan)
	      	   {
	      	   case"new": t=new SPLT();
	      	   break;
	      		case "i": t.insert(sc.next());
	     		break;
	      		case"r":t.remove(sc.next());
	      		break;
	      		case"c":System.out.println(t.contains(sc.next()));
	      		break;
	      		case"x":System.out.println(t.findMax());
	      		break;
	      		case"n":System.out.println(t.findMin());
	      		break;
	      		case"v":System.out.println(t.val());
	      		break;
	      		case"p":t.printTree();
	      		break;
	      		case"q": return;
	      		case"s":System.out.println(t.size());
	      		break;
	      		case"h":System.out.println(t.height());
	      		break;
	      		case "e": t=null;
	      	   }
	        
	    }
    	}
	    else {  
	      String cmd;
	      SPLT t=null;
	      String assocData=null;
	      System.out.println("mode 2");
	      System.out.println("here are the args: \n");
	      int na = args.length; 
	      for (int i=0; i < na; i++) {
	        cmd = args[i];
	        System.out.println("command: "+cmd);
	        switch (cmd) {
	          case "new": t=new SPLT();
	         break;
	          case "i":assocData = args[++i];
	        	  t.insert(assocData);
	          		  break;
	          case "r":assocData = args[++i];
	        	  t.remove(assocData);
	        	  break;
	          case "c":
	        	    assocData = args[++i];
	        	    System.out.println(t.contains(assocData));
	          case "x":assocData = args[++i];
	          			System.out.println(t.findMax());
	          case "n":assocData = args[++i];
	        	  		System.out.println(t.findMin());
	          case "v":assocData = args[++i];
	          			System.out.println(t.val());
	          case "e":t=null;
	          case "s": 
	        	  System.out.println(t.size());
	          case "h":	
	        	  System.out.println(t.height());
	          case "q": return;
	          case "p":t.printTree();
	            assocData = args[++i];
	        }
	        System.out.println();
	      }
	      System.out.println();     
	    }
	  }
	}

 class SPLT 
{
	public  SPLTNode root;
	public SPLTNode left;
	public SPLTNode right;
	public SPLTNode par;
	public SPLT()
	{
		root=null;
	}
	public void insert(String r)
	{
		if(this.root==null)
		{
			this.root = new SPLTNode(r,null,null,par);
		}
		root=this.insert(r, this.root);
		//root= insert(r,root);
	}
	public int size(){
		return size(root);
	}
	
	public void randomInsert(){
		
		for (int i = 0; i < 20; i++){
			insert(toString());
		}
	}
	
	private int size(SPLTNode n) {
		if (n == null || root.val == null){
			return 0;
		}
		int this_num = 1;
		int left_num = 0;
		int right_num = 0;
		if (n.left != null){
			left_num = size(n.left);
		}
		if (n.right != null){
			right_num = size(n.right);
		}
			
			return this_num + left_num + right_num;
	}
	
	public SPLTNode insert(String r, SPLTNode s)
	{
		if (s==null)
		{
			return new SPLTNode(r,null,null,null);
		}
		int compareResult = r.compareTo(s.val);
		if(compareResult==0)
		{
			this.splay(s);
		}
		if (compareResult < 0)
		{ 
		if(s.left==null)
			{
			s.left= new SPLTNode(r,null,null,par);
			this.splay(s.left);
			}
		else
		{
			s.left = insert(r,s.left);
		}
		}
		else if (compareResult > 0)
		{
			if(s.right==null)
			{
				s.right= new SPLTNode(r,null,null,par);
				this.splay(s.right);
			}
		else
		{
			s.right = insert (r,s.right);
		}
		}
		return s;
	}
	public void remove(String r)
	{
		root=remove(r,root);
	}
	public SPLTNode remove(String r, SPLTNode s)
	{
		if (s==null)
		{
			return s;
		}
		int compareResult= r.compareTo(s.val);
		
		if (compareResult<0)
		{
			s.left=remove(r,s.left);
		}
		else if (compareResult > 0)
		{
			s.right = remove(r,s.right);
		}
		else if (s.left !=null && s.right !=null )
		{
			s.val = findMin(s.right);
			s.right= remove (s.val, s.right);
		}
		else
		{
			s= (s.left != null) ? s.left : s.right;
		}
		return s;
	}
	public String findMin()
	{
		return(this.root==null) ? null : this.findMin(this.root);
		//return findMin(root);
	}
	public String findMin(SPLTNode s)
	{
		if(s.left==null)
		{
			String r=s.val;
			this.splay(root);
			return r;
		}
		else
		{
			splay(root);
			return this.findMin(s.left);
		}
	}
	
	public String findMax()
	{
		return(this.root==null) ? null : this.findMin(this.root);
		//return findMax(root).val;
	}
	public String findMax(SPLTNode s)
	{
		if(s.right==null)
		{
			String rv= s.val;
			this.splay(root);
			return rv;
		}
		else
		{
			splay(root);
			return this.findMax();
		}
	}
	public boolean contains(String r)
	{
		return contains(r,root);
	}
	public boolean contains(String r, SPLTNode s)
	{
		if(s==null)
		{
			return false;
		}
		int compareResult = r.compareTo(s.val);
		
		if (compareResult < 0)
		{
			return contains(r,s.left);
		}
		else if(compareResult > 0)
		{
			return contains (r,s.right);
		}
		else
		{
			return true;
		}
	}
	public String val()
	{
		return root.val;
	}
	public boolean empty()
	{
		return root==null;
	}
	public int height()
	{
		return height(root);
	}
	public int height(SPLTNode s)
	{
		if(s==null)
		{
			return 0;
		}
		else
		{
			System.out.println(s.left);
			System.out.println(s.right);
			return Math.max(height(s.left),height(s.right));
		}
	}
	public void printTree()
	{
		if(root==null)
		{
			System.out.println("Tree is Empty");
		}
		else
		{
		printTreePreOrder(root);
		System.out.print(" ");
		}
	}
	public void printTreePreOrder(SPLTNode s)
	{
		if (s != null)
		{
		      printTreePreOrder(s.left);
		      System.out.println(s.val);
		      printTreePreOrder(s.right);
	}
	
	}

 public SPLTNode splay(SPLTNode s)
 {
	 SPLTNode B,C,p,g,gg;
	 p=s.par;
	 g=(p==null) ? null: p.par;
	 gg=(g==null) ? null: g.par;
	 if(p==null)
	 {
		 return null;
	 }
	 if(g==null)
	 {
		 if(p.left==s)
		 {
			 B=s.right;
			 s.right=p;
			 p.par=s;
			 p.left=B;
			 if (B!= null)
				 B.par=p;
			 s.par=null;
		 }
		 else
		 {
			 B=s.left;
			 s.left=p;
			 p.par=s;
			 p.right=B;
			 if(B!= null)
			 B.par=p;
			 s.par=null;
		 }
		 this.root=s;
	 }
	 if (s==p.right && p==g.right) {       // zig-zig-R
	        C=p.left; B=s.left; 
	        s.par=gg; if (gg!=null) { if (gg.left==g) { gg.left=s; } else { gg.right=s; } }
	        p.par=s; s.left=p;
	        g.par=p; p.left=g;
	        p.right=B; if (B!=null) B.par=p;
	        g.right=C; if (C!=null) C.par=g;
	      }
	      else if (s==p.left && p==g.left) {  // zig-zig-L
	        B=s.right; C=p.right; 
	        s.par=gg; if (gg!=null) { if (gg.left==g) { gg.left=s; } else { gg.right=s; } }
	        s.right=p; p.par=s;
	        p.right=g; g.par=p; 
	        g.left=C; if (C!=null) C.par=g;
	        p.left=B; if (B!=null) B.par=p;
	      }
	      else if (s==p.right && p==g.left) {  // zig-zag-L
	        B=s.left; C=s.right; 
	        s.par=gg; if (gg!=null) { if (gg.left==g) { gg.left=s; } else { gg.right=s; } }
	        s.left=p; p.par=s; 
	        s.right=g; g.par=s;
	        g.left=C; if (C!=null) C.par=g;
	        p.right=B; if (B!=null) B.par=p;
	      } 
	      else if (s==p.left && p==g.right) {  // zig-zag-R
	        B=s.left; C=s.right; 
	        s.par=gg; if (gg!=null) { if (gg.left==g) { gg.left=s; } else { gg.right=s; } }
	        s.left=g; g.par=s;
	        s.right=p; p.par=s;
	        g.right=B; if (B!=null) B.par=g;
	        p.left=C; if (C!=null) C.par=p;
	      } 
	 if (s.par==null) { this.root=s; }
     
     // not done, so recurse... keep splaying
     return this.splay(s);
 }	
 
class SPLTNode
{
	SPLTNode(String r)
	{
		this(r,null,null,null);
	}
	SPLTNode(String r, SPLTNode left, SPLTNode right, SPLTNode par)
	{
		 val = r; left=left; right=right; par=par;
	}
	String val;
	SPLTNode left;
	SPLTNode right;
	SPLTNode par;
}
}
  class MyRandom {

	  private static Random rn = new Random();

	  private MyRandom(){ }

	  public static int rand(int lo, int hi) {
	     int n = hi - lo + 1;
	     int i = rn.nextInt() % n;
	     if (i < 0) i = -i;
	     return lo + i;
	  }

	  public static String nextString(int lo, int hi) {
	     int n = rand(lo, hi);
	     byte b[] = new byte[n];
	     for (int i = 0; i < n; i++)
	     b[i] = (byte)rand('a', 'z');
	     return new String(b, 0);
	  }

	  public static String nextString() {
	     return nextString(5, 25);
	  }
 }