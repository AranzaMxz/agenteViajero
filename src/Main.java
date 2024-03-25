// Problema del agente viajero - fuerza bruta
// Alumna: Aranza Alondra Muñoz Chávez
// Materia: Inteligencia Artificial
// Grupo: 6CM4
import javax.jnlp.ClipboardService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main{

    HashMap<String, String[][]> grafica = new HashMap<>();
    ArrayList<List> recorridos = new ArrayList<>(); // Lista que contiene todos los recorridos
    ArrayList<Integer> pesos = new ArrayList<>(); // Lista que contiene todos los pesos

    public String[] nodos =
    {
        "A","B","C","D","E","F","G","H","I","J","K","L","M","N"
    };
    // Definimos las conexiones de cada nodo como "coordenadas"
    public String[][][] coordenadas =
            {
                    // {Destino, peso}
                    /* A */ {{"B","9"}, {"E","15"}, {"J","22"}}, // [a,b,c] , a = [b,9] a[0] = b
                    /* B */ {{"A","9"}, {"C","11"}, {"E","17"}},
                    /* C */ {{"B","11"}, {"D","19"}},
                    /* D */ {{"C","19"}, {"E","15"}, {"F","14"}},
                    /* E */ {{"A","15"}, {"B","17"}, {"D","15"},{"G","14"}, {"H","16"}},
                    /* F */ {{"D","14"}, {"I","12"}, {"H","15"}},
                    /* G */ {{"E","14"}, {"L","25"}, {"K","14"}},
                    /* H */ {{"E","16"}, {"I","15"}, {"F","15"}, {"L","17"}},
                    /* I */ {{"F","12"}, {"H","15"}, {"M","19"}},
                    /* J */ {{"A","22"}, {"K","6"}},
                    /* K */ {{"G","14"}, {"J","6"}, {"N","14"}},
                    /* L */ {{"G","25"}, {"H","17"}, {"M","18"}, {"N","18"}},
                    /* M */ {{"I","19"}, {"L","18"}, {"N","24"}},
                    /* N */ {{"M","24"}, {"L","18"}, {"K","14"}},
            };
    public void algoritmo()
    {
        String nodoInicial = "E"; // Nodo inicial

        // Primero construimos la gráfica que contiene los nodos y sus posibles caminos
        construirGrafica();
        // Vamos a encontrar los posibles caminos que empiecen y terminen con el nodo inicial
        busqueda(nodoInicial);

    }
    public void construirGrafica()
    {
        // Colocamos cada ciudad con sus respectivos caminos
        for (int i = 0 ; i < nodos.length ; i++)
        {
            grafica.put(nodos[i],coordenadas[i]);
        }
    }
    public void busqueda(String nodo)
    {
        String otroCamino;
        int costoOtrocamino;
        String[][] caminos = grafica.get(nodo); // obtenemos los posibles caminos del nodo inicial

        // iteramos sobre sus posibles caminos
        for (int i = 0 ; i < caminos.length ; i++)
        {
            List<String> nuevoCamino = new ArrayList<>(); // con cada iteración se empieza un nuevo camino

            nuevoCamino.add(nodo); // agregamos el nodo inicial

            otroCamino = caminos[i][0]; // posibles nodos a ir desde el nodo inicial
            costoOtrocamino = Integer.parseInt(caminos[i][1]); //costo desde ese nodo

            buscarCaminos(otroCamino, nuevoCamino, costoOtrocamino);
        }
        // imprimimos los recorridos guardados
        imprimirRecorridos();

        // buscamos el/los recorrido(s) con el menor peso
        recorridoMenorPeso();
    }
    private void buscarCaminos(String nodo, List<String> caminoActual, int pesoAcumulado)
    {
        // si ya contiene el nodo y aún no recorremos todos los nodos regresamos
        if (caminoActual.contains(nodo) && !(caminoActual.size() == nodos.length))
            return;

        caminoActual.add(nodo); // agregamos el nodo al camino actual, si aún no está
        String[][] caminos = grafica.get(nodo); // obtenemos los posibles caminos del nodo recién agregado

        // si el camino actual ya recorrió todos los nodos y además el último nodo es el nodo inicial, guardamos el recorrido
        if (caminoActual.size()== nodos.length+1 && caminoActual.get(0).equals(caminoActual.get(caminoActual.size() - 1))) // acabamos un recorrido
        {
            recorridos.add(new ArrayList<>(caminoActual)); // lo guardamos como un nuevo camino
            pesos.add(pesoAcumulado); // guardamos el peso total
            return;
        }

        // usamos recursividad para encontrar el siguiente nodo a recorrer
        for (int i = 0; i < caminos.length; i++)
        {
            String sigNodo = caminos[i][0]; // siguiente nodo en los caminos posibles
            int costo = Integer.parseInt(caminos[i][1]); // el costo del siguiente nodo
            // volvemos a llamar a la función y le pasamos el siguiente nodo, el camino actual sin perder lo anterior y el peso hasta ahora más el costo de ese nodo
            buscarCaminos(sigNodo,new ArrayList<>(caminoActual),pesoAcumulado+costo);
        }
    }

    private void imprimirRecorridos()
    {
        // imprimimos todos los recorridos
        System.out.println("\nRecorridos:\n");
        for (int i = 0 ; i < recorridos.size();i ++)
        {
            System.out.println((i+1)+") "+ recorridos.get(i) + "= " + pesos.get(i)); //+ pesos.get(i)
        }
    }
    private void recorridoMenorPeso()
    {
        int menor = 0; // almacenamos el peso menor
        ArrayList<Integer> listaDelMenor = new ArrayList<>(); //almacenamos los índices de los menores pesos

        // buscamos el menor peso
        for (int i = 0; i < pesos.size(); i ++)
        {

            if (menor == 0) // el primer peso es hasta ahora el menor
                menor = pesos.get(i);
            else if (pesos.get(i)<menor)
            {
                menor = pesos.get(i);
                listaDelMenor.clear(); // limpiamos porque ahora hay un nuevo menor
                listaDelMenor.add(i); // agregamos el índice del nuevo menor

            }
            else if (pesos.get(i).equals(menor)) // si hay otro peso igual que el menor, también guardamos su índice
            {
                    listaDelMenor.add(i);

            }
        }
        // Imprimimos los recorridos con menor peso
        System.out.println("\nEl/los recorrido(s) con menor peso son: \n");
        for (int i = 0; i < listaDelMenor.size(); i++)
        {
                    System.out.println((listaDelMenor.get(i) + 1) + ") " + recorridos.get(listaDelMenor.get(i)) + " = " + menor);
        }
    }
}
