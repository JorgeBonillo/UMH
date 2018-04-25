#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

int SubsecuenciaMaxima1(int *V, int n, int &imejor, int &jmejor)
{
    int esta_suma, suma_max, i, j, k;

    suma_max = 0;
    imejor = 0;
    jmejor = 0;
    for (i=1 ; i<=n ; i++)
    {
        for (j=i ; j<=n ; j++)
        {
            esta_suma = 0;
            for (k=i ; k<=j ; k++)
            {
                esta_suma = esta_suma + V[k];
                if (esta_suma>suma_max)
                {
                    suma_max = esta_suma;
                    imejor = i;
                    jmejor = j;
                }
            }
        }
    }
    return suma_max;
}

int SubsecuenciaMaxima2(int *V, int n, int &imejor, int &jmejor)
{
    int esta_suma, suma_max, i, j;


    suma_max = 0;
    imejor = 0;
    jmejor = 0;
    for (i=1 ; i<=n ; i++)
    {
        esta_suma = 0;
        for (j=i ; j<=n ; j++)
        {
            esta_suma = esta_suma + V[j];
            if (esta_suma>suma_max)
            {
                suma_max = esta_suma;
                imejor = i;
                jmejor = j;
            }
        }
    }
    return suma_max;
}

int SubsecuenciaMaxima3(int *V, int n, int &imejor, int &jmejor)
{
    int esta_suma, suma_max, i, j;

    i = 1;
    esta_suma = 0;
    suma_max = 0;
    imejor = 0;
    jmejor = 0;
    for (j=1 ; j<=n ; j++)
    {
        esta_suma = esta_suma + V[j];
            if (esta_suma>suma_max)
            {
                suma_max = esta_suma;
                imejor = i;
                jmejor = j;
            }
            else
            {
              if (esta_suma < 0)
              {
                i = j+1;
                esta_suma = 0;
              }
            }
    }

    return suma_max;
}


int main()
{
   int *V;
   int n,a,b,i;
   int suma1,suma2,suma3,i1,j1,i2,j2,i3,j3;


// PASO 1.
// Pide por teclado el tama�o del vector y reserva memoria din�mica.
   cout << "Introduce el numero de elementos del vector: ";
   cin >> n;

   V = new int [n+1];
   if (V == NULL)
   {
      cout << "Fallo al reservar memoria" << endl;
      system("pause");
      return (-1);
   }


// PASO 2.
// Inicializa los elementos del vector con n�meros enteros entre a y b (a<=b) de forma aleatoria
// e imprime el vector
   a = -9;
   b = 12;

   srand(time(NULL));
   for (i=1 ; i<=n ; i++)
       V[i] = a + rand() % (b - a + 1);
  /*
   for (i=1 ; i<=n ; i++)
        cout << V[i] << " ";

   cout << endl;
   */

// PASO 3.
// Aplica las funciones SubsecuenciaMaxima1 y SubsecuenciaMaxima2 al vector,
// imprime los valores de la suma y las posiciones inicial y final.

  clock_t tInicio1, tFinal1, tInicio2, tFinal2, tInicio3, tFinal3;

  tInicio1 = clock();
   suma1 = SubsecuenciaMaxima1(V,n,i1,j1);
  tFinal1 = clock();

  tInicio2 = clock();
   suma2 = SubsecuenciaMaxima2(V,n,i2,j2);
  tFinal2 = clock();

  tInicio3 = clock();
    suma3 = SubsecuenciaMaxima3(V,n,i3,j3);
  tFinal3 = clock();

   cout << " Resultado:" << endl;
   cout << "- SubsecuenciaMaxima1: "<< suma1 << "  (i=" << i1 << ", j=" << j1 << ")" << endl;
   cout << "- SubsecuenciaMaxima2: "<< suma2 << "  (i=" << i2 << ", j=" << j2 << ")" << endl;
   cout << "- SubsecuenciaMaxima3: "<< suma3 << "  (i=" << i3 << ", j=" << j3 << ")" << endl;

   cout << "- Tiempo1: " << (tFinal1-tInicio1) << endl;
   cout << "- Tiempo2: " << (tFinal2-tInicio2) << endl;
   cout << "- Tiempo2: " << (tFinal3-tInicio3) << endl;

   cout << endl;

   //system("pause");

   return 0;
}
