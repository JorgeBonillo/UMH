#include <iostream>

using namespace std;


int combinaciones(int n, int k)
{

  int comb = 0;

  if (k == 0 || k == n)
  {
    comb++;
  }
  else
  {
    int a2 = n-1;
    int b2 = k-1;
    comb = combinaciones(a2,b2) + combinaciones(a2,k);
  }

  return comb;

}

void combinaciones_traza(int n, int k, int &count)
{

  cout << " "<< count << ".-" << "combinaciones_traza(" << n << "," << k << ")" << endl;

  if (k == 0 || k == n)
  {
    count++;
  }
  else
  {
    int a2 = n-1;
    int b2 = k-1;
    count++;
    combinaciones_traza(a2,b2,count);
    //count++;
    combinaciones_traza(a2,k,count);
  }



}

int main()
{
  int k;
  int n;
  int combi;
  int a = 1;

  cout << "Personas: ";
  cin >> n;

  cout << "Grupos: ";
  cin >> k;

  combi = combinaciones(n,k);

  cout << "Combinaciones: " << combi << endl;

  combinaciones_traza(n,k,a);




  return 0;
}
