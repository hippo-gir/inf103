package Dijkstra;

public interface ASetInterface 
{	
	public void ajouter(VertexInterface x);
	/* ajouter le sommet x à l'ensemble A */
	
	public boolean appartient(VertexInterface x);
	/* renvoie true si x appartient à A, false sinon */
}
