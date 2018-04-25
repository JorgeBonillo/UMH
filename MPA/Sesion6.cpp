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


	pesos[1] = 0.450;
	pesos[2] = 0.300;
	pesos[3] = 0.200;
	pesos[4] = 0.430;
	pesos[5] = 0.300;
	pesos[6] = 0.145;
	pesos[7] = 0.170;
	pesos[8] = 0.400;
	pesos[9] = 1.100;
	pesos[10] = 0.750;
	pesos[11] = 0.280;
	pesos[12] = 1.000;
	pesos[13] = 0.150;
	pesos[14] = 0.350;
	pesos[15] = 0.350;
	pesos[16] = 0.400;
	pesos[17] = 0.500;
	pesos[18] = 0.200;
	pesos[19] = 0.300;
	pesos[20] = 0.600;
	pesos[21] = 0.600;
	pesos[22] = 0.750;
	pesos[23] = 0.400;
	pesos[24] = 1.200;
	pesos[25] = 1.100;

	valores[1] = 5;
	valores[2] = 7;
	valores[3] = 7;
	valores[4] = 10;
	valores[5] = 10;
	valores[6] = 6;
	valores[7] = 9;
	valores[8] = 9;
	valores[9] = 7;
	valores[10] = 5;
	valores[11] = 7;
	valores[12] = 9;
	valores[13] = 10;
	valores[14] = 8;
	valores[15] = 9.5;
	valores[16] = 6;
	valores[17] = 9;
	valores[18] = 10;
	valores[19] = 10;
	valores[20] = 6.5;
	valores[21] = 7.5;
	valores[22] = 10;
	valores[23] = 8;
	valores[24] = 9;
	valores[25] = 8;

  /*
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
*/

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
