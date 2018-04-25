#include <iostream>
#include <fstream>
#include <string.h>
#include <stdlib.h>
#include <cstring>
#include <cstdlib>


#define INF 10000

using namespace std;

float** LeerGrafo(int &tam)
{
   char nombrefichero[50];   // nombre del fichero de entrada
   char dato[10];            // palabra que se lee del fichero
   ifstream f;               // variable del tipo fichero
   float **matriz;           // matriz de adyacencia del grafo
   int i,j;

   cout << "Introduce el nombre del fichero con el grafo: ";
   cin >> nombrefichero;
   f.open(nombrefichero);
   if (!f)
   {
      cout << "Fallo al abrir el fichero " << nombrefichero << endl;
      cout << "Verifique la ruta de acceso y el nombre." << endl;
      tam = 0;
      return (NULL);
   }
   f >> tam;                 // leemos el tamanyo
                             // reservamos memoria
   matriz = new float* [tam+1];
   for (i=1;i<=tam;i++)
      matriz[i]= new float [tam+1];

                             // leemos la matriz
   for (i=1; i<=tam; i++)
      for (j=1; j <=tam ; j++)
      {
         f >> dato;
         if (strcmp(dato,"INF")==0)
            matriz[i][j] = INF;
         else
            matriz[i][j] = atof(dato);
      }

   if (!f)
   {
      cout << "Fallo en la lectura del fichero " << nombrefichero << endl;
      cout << "Verifique el contenido del fichero." << endl;
      tam = 0;
      return (NULL);
   }
   return (matriz);
}

float** CalcularCostesMinimos(int &tam, float **G, float **H)
{
  //Ponemos la matriz H a ceros
  for (int i = 1 ; i <= tam ; i++)
  {
    for (int j = 1 ; j <= tam ; j++)
    {
      H[i][j] = G[i][j];
    }
  }

  //Ponemos 0 en las posiciones que coinciden los vertices
  for (int i = 1 ; i <= tam ; i++)
  {
    H[i][i] = 0;
  }

  //Logica del algoritmo
  for (int k = 1 ; k <= tam ; k++)
  {
     for (int i = 1 ; i <= tam ; i++)
     {
       for (int j = 1 ; j <= tam ; j++)
       {
         if (H[i][k] + H[k][j] < H[i][j])
         {
           H[i][j] = H[i][k] + H[k][j];
         }
       }
     }
  }

  return H;

}

int main()
{
  int nvertices = 0;

  float **G;
  float **H;

  G = LeerGrafo(nvertices);

  if (G == NULL)
  {
    cout << "Fallo en la lectura del grafo" << endl;
    return (-1);
  }

  cout << "Vertices: " << nvertices << endl;

  for (int i = 1 ; i <= nvertices ; i++)
  {
    for (int j = 1 ; j <= nvertices ; j++)
    {
      if (G[i][j] == INF)
      {
        cout << "INF " ;
      }
      else
      {
        cout << G[i][j] << " ";
      }
    }
    cout << endl;
  }
  cout << endl;

  //Reserva de memoria matriz H
  H = new float* [nvertices + 1];

  for (int i = 1 ; i <= nvertices ; i++)
  {
    H[i] = new float [nvertices + 1];
  }

  H = CalcularCostesMinimos(nvertices, G, H);

  cout << "Costes caminos mas cortos:" << endl;

  for (int i = 1 ; i <= nvertices ; i++)
  {
    cout << "\t";
    cout << "(" << i << ")";
  }
  cout << endl;

  for (int i = 1 ; i <= nvertices ; i++)
  {
    cout << "(" << i << ")";

    for (int j = 1 ; j <= nvertices ; j++)
    {
      cout << "\t";
      cout << H[i][j];
    }
    cout << endl;
  }

  return 0;
}
