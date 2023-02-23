package Dijkstra;
import java.util.Hashtable;
import java.util.ArrayList;

public class Pi implements PiInterface
{
    private Hashtable<VertexInterface, Integer> pi;

    public Pi()
    {
        this.pi = new Hashtable<VertexInterface, Integer>();
    }

    public int get(VertexInterface x) 
    {
        return pi.get(x);
    }

    public void set(VertexInterface x, int y) 
    {
        pi.put(x, y);
    }

    public VertexInterface distMinA(ASetInterface A, GraphInterface m)
    {
        ArrayList<VertexInterface> verticies = m.getAllVertices();
        int initialisateur = 1; 
        VertexInterface sommet_min = null;
        int min = 0; // équivalent de l'initialiser à null, on sait que min sera changé par la suite
        for (VertexInterface vertex : verticies)
        {
            if (!A.appartient(vertex))
            {
                if (initialisateur == 1)
                {
                    sommet_min = vertex;
                    min = this.get(vertex);
                    initialisateur = initialisateur + 1;
                }
                else
                {
                    int d = this.get(vertex);
                    if (d < min)
                    {
                        sommet_min = vertex;
                        min = d;
                    }
                }
            }
        }
        return sommet_min;
    }
}
