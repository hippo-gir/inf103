package Dijkstra;
import java.util.ArrayList;

public interface GraphInterface 
{
	public int getWeight(VertexInterface x, VertexInterface y);
	/*renvoie le poids de l'arc (x,y) si il existe , -1 sinon */

	public ArrayList<VertexInterface> getAllVertices();

	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex);
}
