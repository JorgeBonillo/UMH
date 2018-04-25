#include <iostream>
#include <stack>
#include <math.h>

using namespace std;


float potencia (float a, int b)
{
    if (b == 0)
    {
      return 1;
    }
    else
    {
      return a * (potencia(a, b-1));
    }
}

float potencia_iterativo (float a, int b)
{
  float b0;
  float s;

  b0 = b;

  do {
    b0--;
  } while(b0 != 0);

  s = 1;

  do {
    b0++;
    s = s * a;
  } while(b0 != b);

  return s;

}

float potenciaversion2(float a, int b)
{
  if (b == 0)
  {
    return 1;
  }
  else if ((b%2) == 0) //Par
  {
    return (potenciaversion2 (a,b/2) * potenciaversion2 (a,b/2));
  }
  else if ((b%2) == 1) //Impar
  {
    return (a * potenciaversion2 (a,b/2) * potenciaversion2 (a,b/2));
  }
}

float potenciaversion2_iterativo (float a, int b)
{
  stack<int> pila;
  float s;
  int b0;

  b0 = b;

  do {
    pila.push(b0);
    b0 = (b0/2);
  } while(b0 != 0);

  s = 1;

  while(! pila.empty())
  {
    b0 = pila.top();
    pila.pop();
    if ((b0%2) == 0)
    {
      s = s*s;
    }
    else
    {
      s = a * s * s;
    }
  }

  return s;

}


int main()
{
  float a,b,z;
  cout << "Introduce numero 1: ";
  cin >> a;
  cout << "Introduce numero 2: ";
  cin >> b;


  cout << "Actividad 1" << endl;
  cout << endl;

  cout << "Potencia: " << potencia(a,b);
  cout << endl;

  cout << "Potencia iterativo: " << potencia_iterativo(a,b);
  cout << endl;
  cout << endl;

  cout << "Actividad 2" << endl;
  cout << endl;

  cout << "Potencia version 2: " << potenciaversion2(a,b);
  cout << endl;

  cout << "Potencia version 2 iterativo: " << potenciaversion2_iterativo (a,b);
  cout << endl;


  return 0;
}
