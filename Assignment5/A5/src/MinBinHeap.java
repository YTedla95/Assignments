public class MinBinHeap implements HeapInterface{

	// in here go all your data and methods for the heap
	public static final int DEFAULT_CAPACITY=20000;
	public int size=0;
	public EntryPair[] array;
	
	  public MinBinHeap ( ) 
	  { // default constructor
	    // explicitly include this
	    // we need to have the default constructor
	    // if you then write others, this one will still be there
		  array= new EntryPair[DEFAULT_CAPACITY];
		  size=0;
	  }
	@Override
	public void insert(EntryPair entry) {
		// TODO Auto-generated method stub
		int hole = ++size;
		for(; hole>1 && entry.getPriority()-array[hole/2].getPriority()<0; hole/=2)
		{
			array[hole]=array[hole/2];
		}
			array[hole]=entry;
	}
	@Override
	public void delMin() {
		// TODO Auto-generated method stub
		if(size==0)
		{
			return;
		}
		
		array[1] = array[size--];
		percolateDown(1);
	}
	private void percolateDown(int i) {
		// TODO Auto-generated method stub
		int child;
		EntryPair tmp= array[i];
		for(; i*2 <= size; i=child)
		{
			child = i * 2;

			   if( child != size &&	array[child + 1 ].getPriority() -  array[ child ].getPriority()<0)
			   {

			   child++;
			   }

			   if( array[ child ].getPriority() - tmp.getPriority() < 0 )
			   {

			   	array[ i ] = array[ child ];
			   }

			  else

				break;
		}
		array[i]= tmp;
	}
	@Override
	public EntryPair getMin() {
		// TODO Auto-generated method stub
		return array[1];
		
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	@Override
	public void build(EntryPair[] entries) {
		// TODO Auto-generated method stub
		for (int i=0;i<entries.length;i++)
		{
			array[i+1]=entries[i];
			size=size+1;
		}		
		 for( int i = size / 2; i > 0; i-- )
             percolateDown( i );
	}
}
