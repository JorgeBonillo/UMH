#include <iostream>

using namespace std;

#define INF -10000

float CalcularSuma(float *x, float *v, int k)
{
    float suma=0;
    int i=0;

    while(i<=k)
    {
        if(x[i]==1)
        {
            suma=suma+v[i];
        }
        i++;
    }
    return suma;

}
void Mochila(int n, float m, float *p, float *v, float *x, int k, float *xopt, float &vopt)
    {
        float valor = 0;
        int i=1;
        int j=1;
        float peso = 0;
        int term = 0;


        if(k<=n)
        {
            for(i=0;i<=1;i++)
            {
                x[k]=i;
                peso = CalcularSuma(x,p,k);
                if(peso<=m)
                {
                    if(k==n)
                    {
                        valor = CalcularSuma(x,v,k);
                        if(valor > vopt)
                        {
                            for(j=1;j<=n;j++)
                            {
                                xopt[j]=x[j];
                            }
                            vopt = valor;
                        }
                    }
                    else
                    {
                        Mochila(n,m,p,v,x,k+1,xopt,vopt);
                    }
                }
            }
          }
    }


    int main()
  {

	float *pesos, *valores, *x, *xopt;
	int n;
	int k;
	float M;
	float vopt = INF;
	k = 1;
  float pesoMochila;

	cout << "Introduce el numero de objetos a introducir: " << endl;
	cin >> n;

	//RESERVA DE MEMORIA
	pesos = new float[n + 1];
	valores = new float[n + 1];
	x = new float[n + 1];
	xopt = new float[n + 1];

	if (pesos == NULL || valores == NULL || x == NULL || xopt == NULL) {

		cout << "Error al reservar memoria" << endl;
		return -1;

	}

	cout << "Introduce la capacidad de la mochila: " << endl;
	cin >> M;



	 cout << "Pesos" << endl;
	 for(int i=1; i<=n; i++){

	 cout << "Objeto " << i << ": ";
	 cin >> pesos[i];

	 }

	 cout << "Valores" << endl;
	 for(int i=1; i<=n; i++){

	 cout << "Objeto " << i << ": ";
	 cin >> valores[i];

	 }

	cout << endl;
	for (int i = 1; i <= n; i++)
  {
		cout << i << ". " << "Peso: " << pesos[i] << "\t" << "Valor: " << valores[i] << endl;
  }

	Mochila(n, M, pesos, valores, x, k, xopt, vopt);

	cout << "\nEl valor optimo es: " << vopt << endl << endl;


  cout << "Peso de la mochila: " << CalcularSuma(xopt,pesos,n) << endl;


	cout << "Objetos que quedan fuera: ";

	for (int i = 1; i <= n; i++) {

		if (xopt[i] == 0) {

			cout << i << " ";
		}
	}

  cout << endl << endl;

	cout << "Objetos que quedan dentro: ";
  for (int i = 1; i <= n; i++)
  {

		if (xopt[i] == 1)
    {

			cout << i << " ";
		}
}
  cout << endl;
  cout << "\nValores y peso dentro" << endl;

  for (int i = 1; i <= n; i++)
  {

    if (xopt[i] == 1)
    {

      cout << i << ". " << "Peso: " << pesos[i] << "\t" << "Valor: " << valores[i] << endl;
    }
  }

  cout << "\nValores y peso fuera" << endl;

  for (int i = 1; i <= n; i++)
  {

    if (xopt[i] == 0)
    {

      cout << i << ". " << "Peso: " << pesos[i] << "\t" << "Valor: " << valores[i] << endl;
    }
  }

	cout << endl;
	return 0;
}
