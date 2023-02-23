package Dijkstra;

public interface PiInterface 
{
	public int get(VertexInterface x);
	/* pi(x) */
	
	public void set(VertexInterface x, int y);
	/* setter de pi(x) */
	
	public VertexInterface distMinA(ASetInterface A, GraphInterface m);
	/* renvoie le sommet s minimisant pi sur les sommets n'appartenant pas à A */ 
}
