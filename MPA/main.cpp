#include <iostream>

using namespace std;

int Contar (int *V , int valorBuscar, int tamanoVector);
void pintarBucle (int *V, int tamanoVector);


int main()
{
    int valorBuscar;
    int tamanoVector;

    int *vPeor;
    int *vMejor;
    int *vCualquiera;

    cout << "Introduce el valor a buscar: ";
    cin >> valorBuscar;

    cout << "Introduce el tamano del vector: ";
    cin >> tamanoVector;

    //vector <int> vPeor;


    vPeor = new int [tamanoVector + 1];
    if (vPeor == NULL)
    {
      cout << "Error al reservar memoria" << endl;
      return -1;
    }
    vMejor = new int [tamanoVector + 1];
    if (vMejor == NULL)
    {
      cout << "Error al reservar memoria" << endl;
      return -1;
    }
    vCualquiera = new int [tamanoVector + 1];
    if (vCualquiera == NULL)
    {
      cout << "Error al reservar memoria" << endl;
      return -1;
    }

    for (int i = 1 ; i <= tamanoVector ; i++)
    {
      vPeor[i] = valorBuscar;

      vMejor[i] = valorBuscar + 1;

      if (i <= (tamanoVector/2))
      {
        vCualquiera[i] = valorBuscar;
      }
      else
      {
        vCualquiera[i] = valorBuscar + 1;
      }

    }

    cout << "vPeor" << endl;
    pintarBucle(vPeor, tamanoVector);
    cout << endl;
    cout << "Veces encontrado: "<< Contar(vPeor, valorBuscar, tamanoVector) << endl;

    cout << "vMejor" << endl;
    pintarBucle(vMejor, tamanoVector);
    cout << endl;
    cout << "Veces encontrado: "<< Contar(vMejor, valorBuscar, tamanoVector) << endl;

    cout << "vCualquiera" << endl;
    pintarBucle(vCualquiera, tamanoVector);
    cout << endl;
    cout << "Veces encontrado: "<< Contar(vCualquiera, valorBuscar, tamanoVector) << endl;

}

int Contar (int *V , int valorBuscar, int tamanoVector)
{
  int nVeces = 0;

  for (int i = 1; i <= tamanoVector; i++)
  {
    if (V[i] == valorBuscar)
    {
      nVeces = nVeces + 1;
    }

  }

return nVeces;

}


void pintarBucle(int *V, int tamanoVector)
{
  for (int n = 1 ; n <= tamanoVector ; n++)
  {
    cout << V[n] << " ";
  }
}
