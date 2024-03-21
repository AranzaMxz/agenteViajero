// Problema del agente viajero - fuerza bruta
// Alumna: Aranza Alondra Muñoz Chávez
// Materia: Inteligencia Artificial
// Grupo: 6CM4
import java.util.List;
import java.util.ArrayList;

public class Main{



    char[] ciudades = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N'}; // Lista que almacena los nombres de los puntos
    char puntoPartida = 'E'; // Punto en el que se iniciará el recorrido

    // Definimos la matriz que contendra los valores de cada arista

    public static int[][] MATRIZ = {
                   //  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  10 |  11 |  12 |  13 |
                   //  A  |  B  |  C  |  D  |  E  |  F  |  G  |  H  |  I  |  J  |  K  |  L  |  M  |  N  |
        /* 0  A */ {  0  ,  9  ,  0  ,  0  ,  15 ,  0  ,  0  ,  0  ,  0  ,  22 ,  0  ,  0  ,  0  ,  0  },
        /* 1  B */ {  9  ,  0  ,  11 ,  0  ,  17 ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  },
        /* 2  C */ {  0  ,  11 ,  0  ,  19 ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  },
        /* 3  D */ {  0  ,  0  ,  19 ,  0  ,  15 ,  14 ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  },
        /* 4  E */ {  15 ,  17 ,  0  ,  15 ,  0  ,  0  ,  14 ,  16 ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  },
        /* 5  F */ {  0  ,  0  ,  0  ,  14 ,  0  ,  0  ,  0  ,  15 ,  12 ,  0  ,  0  ,  0  ,  0  ,  0  },
        /* 6  G */ {  0  ,  0  ,  0  ,  0  ,  14 ,  0  ,  0  ,  0  ,  0  ,  0  ,  14 ,  25 ,  0  ,  0  },
        /* 7  H */ {  0  ,  0  ,  0  ,  0  ,  16 ,  15 ,  0  ,  0  ,  15 ,  0  ,  0  ,  17 ,  0  ,  0  },
        /* 8  I */ {  0  ,  0  ,  0  ,  0  ,  0  ,  12 ,  0  ,  15 ,  0  ,  0  ,  0  ,  0  ,  19 ,  0  },
        /* 9  J */ {  22 ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  6  ,  0  ,  0  ,  0  },
        /* 10 K */ {  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  14 ,  0  ,  0  ,  6  ,  0  ,  0  ,  0  ,  14 },
        /* 11 L */ {  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  25 ,  17 ,  0  ,  0  ,  0  ,  0  ,  18 ,  18 },
        /* 12 M */ {  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  19 ,  0  ,  0  ,  18 ,  0  ,  24 },
        /* 13 N */ {  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  0  ,  14 ,  18 ,  24 ,  0  }

    };
    int indicePartida = indiceInicial();
    public void algoritmo(){

        System.out.println("Indice del elemento inicial: "+indicePartida);

        elementoMenor(indicePartida); // Comenzamos desde el indice del nodo inicial

    }
    // Devuelve el índice del nodo inicial
    public int indiceInicial()
    {
        for (int i = 0 ; i < ciudades.length ; i ++)
        {
            if (puntoPartida == ciudades[i])
                return i;
        }
        System.out.println("Elemento inicial no encontrado");
        return -1;
    }
    public void elementoMenor(int indiceInicial)
    {
        int indiceActual = indiceInicial;
        int indiceTemp = 0;
        int menorTemporal = -1; // se guarda el elemento menor

        List<Integer> listaConexiones = new ArrayList<>();

        int x ;
        for (int i = 0 ; i < ciudades.length ; i++)
        {
            x = MATRIZ [i][indiceActual];
            if (x != 0)
            {
                if (menorTemporal == -1) // quiere decir que es el primer elemento a agregar
                {
                    menorTemporal = x;
                    indiceTemp = i;
                }
                else if (x < menorTemporal) // Hay un nuevo menor
                {
                    menorTemporal = x;
                    indiceTemp = i;
                }
            }
        }
        System.out.println("Menor temporal: " + menorTemporal + " [" + indiceTemp + "]");
        // Vamos a ver qué conexiones tiene el nodo actual
        conexiones(indiceTemp, listaConexiones);
        System.out.println("Lista de conexiones de " + ciudades[indiceTemp] + ": " + listaConexiones);
    }
    public boolean hayConexion(int indice)
    {
        if (MATRIZ[indicePartida][indice] !=0 ) // Existe conexión
            return true;
        return false;
    }
    public List conexiones(int indice, List lista)
    {
        int j = 0 ;
        for (int i = 0 ; i < ciudades.length ; i++)
        {
            if (MATRIZ[i][indice] != 0)
                lista.add(i);
            if (j < lista.size())
            {
                // Si tiene conexión con el nodo partida se agrega un -1
                if (lista.get(j).equals(indicePartida))
                    lista.add(-1);
                j++;
            }
        }
        return lista;
    }
}