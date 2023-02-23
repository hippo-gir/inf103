package Dijkstra;

public interface ASetInterface 
{	
	public void ajouter(VertexInterface x);
	/* ajouter le sommet x � l'ensemble A */
	
	public boolean appartient(VertexInterface x);
	/* renvoie true si x appartient � A, false sinon */
}
