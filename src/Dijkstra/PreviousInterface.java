package Dijkstra;

public interface PreviousInterface 
{
	public void set(VertexInterface i, VertexInterface j);
	/* set j comme le pere de i */
	
	public VertexInterface get(VertexInterface x);
	/* renvoie le p�re de x si il existe, x sinon */
}
