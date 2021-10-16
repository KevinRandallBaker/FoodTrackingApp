public class Stack<E>
{
	private E[] array;
	private int topOfStack = -1;
	private int capacity;
	private static int DEFAULT_CAPACITY = 10;
	
	public Stack() 
	{
		this.capacity = DEFAULT_CAPACITY;
		this.array = (E[]) new Object[DEFAULT_CAPACITY];
	}
	
	public Stack(int capacity)
	{
		this.capacity = capacity;
		this.array = (E[]) new Object[this.capacity];
	}
	
	public E push(E object)
	{
		if(topOfStack == this.array.length - 1)
		{
			reallocate();
			System.out.println("At capacity");
			//return;
		}
		
		topOfStack++;
		this.array[topOfStack] = object;
		return object;
		
	}
	
	private void reallocate()
    {
        E[] temp = (E[]) new Object[this.capacity*2];
    
        for(int i = 0; i < topOfStack + 1; i++) // Iterates through the size of Q
        {
        	temp[i] = array[i]; // Assigns value to the new array
        }									     // based on front mod by capacity
        										 // similar to how we iterate through toString
        this.array = temp; // Assignes Q to the newly resized array
        //front = 0; // Assigning front to 0 since all the new elements begin at 0 now
        //rear = size; // Putting rear to size since rear would be at (n - 1)
        this.capacity = capacity * 2; // Reassigning capacity
    }

	public E pop()
	{
		if(isEmpty())
		{
			System.out.println("Nothing to delete, stack is empty.");
			return null;
		}
		
		return this.array[topOfStack--];
	}
	
	public E peek()
	{
		if(isEmpty())
		{
			System.out.println("Nothing to delete, stack is empty.");
			return null;
		}
		
		return this.array[topOfStack];
	}
	
	public boolean isEmpty()
	{
		return this.topOfStack == -1;
	}
	
//	public int getSize()
//	{
//		return this.array.length;
//	}
	
	public int getTop()
	{
		return this.topOfStack;
	}
	
	public void setTop(int topOfStack)
	{
		this.topOfStack = topOfStack;
	}

	@Override
	public String toString() 
	{
		String s = "";
		
		for(int i = 0; i < topOfStack + 1; i++)
		{
			s += this.array[i];
		}
		
		
		return s;
	}
	
	
}
