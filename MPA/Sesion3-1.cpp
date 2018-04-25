#include <iostream>

using namespace std;

void binario(int n)
{

  if ((n/2) == 0)
  {
    cout << n%2;
  }
  else
  {
    binario(n/2);
    cout << n%2;
  }

}

void binario_traza(int n, int count)
{

cout << " "<< count << ".-" << "binario_traza(" << n << ")" << endl;

  if ((n/2) == 0)
  {
    count++;
  }
  else
  {
    count++;
    binario_traza(n/2,count);
}
}



int main()
{

  int a;
  cout << "Introduce el numero: ";
  cin >> a;

  cout << "Numero binario: ";
  binario(a);

  cout << endl;

  cout << "Binario traza: " << endl;
  binario_traza(a,0);
  cout << endl;
  binario(a);
  cout << endl;


  return 0;
}
