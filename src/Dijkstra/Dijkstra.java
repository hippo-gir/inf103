package Dijkstra;
import java.util.ArrayList;

public class Dijkstra
{
	public static PreviousInterface dijkstra(GraphInterface g, VertexInterface r)
	{
		int posInf = 100000000; // sinon dépasement d'entier
		ASet A = new ASet();
		Pi pi = new Pi();
		Previous pere = new Previous(); // par défault, le père de r sera null
		ArrayList<VertexInterface> t = g.getAllVertices();
		A.ajouter(r);
		VertexInterface pivot = r;
		for (VertexInterface vertex : t)
		{
			pi.set(vertex, posInf);
		}
		pi.set(r, 0);
		int n = t.size();
		for (int i = 1; i < n; i++)
		{
			// ArrayList<VertexInterface> successors = g.getSuccessors(pivot);
			// System.out.println(successors);
			int pi_pivot = pi.get(pivot);
			ArrayList<VertexInterface> successors = g.getSuccessors(pivot);
			for (VertexInterface v : successors)
			{
				if (!A.appartient(v))
				{
					int poids_inter = pi_pivot + g.getWeight(pivot, v);
					if (poids_inter < pi.get(v))
					{
						pi.set(v, poids_inter);
						pere.set(v, pivot);
					}
				}
			}
			pivot = pi.distMinA(A, g);
			if (pi.get(pivot) == posInf) // on est pas sur que le labyrinthe ai une solution, ici cela s'arrête si il y a une exception
				// throw new Exception("Le labyrinthe n'a pas de solution");
				return pere;
			A.ajouter(pivot);
		}
	return pere;
	}
}
