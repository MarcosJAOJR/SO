/*
 * =====================================================================================
 *
 *       Filename:  fib.c
 *
 *    Description:  Calculo simples de intevalos da  sequencia de Fibonacci
 *
 *        Version:  1.0
 *        Created:  22/09/2016 11:22:56
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Marcos Augusto
 *   Organization:  UFC
 *
 * =====================================================================================
 */
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

double fib_elem(double elem) {
	return (1/sqrt(5))*(pow(((1+sqrt(5))/2),elem) - pow(((1-sqrt(5))/2),elem));
}

int main (int argc, char **argv) {

	if(argv[1] == NULL || argv[2] == NULL) {
		printf("ERRO: Você precisa informar o limite inferior e superior do intervalo\n");
		return 1;
	}

	double inf = strtod(argv[1], NULL) - 1.0;
	double sup = strtod(argv[2], NULL) - 1.0;

	if(inf > sup) {
		printf("ERRO: O limite superior não pode ser menor que o limite inferior\n");
		return 1;
	}

	if(inf < 1 || sup < 1) {
		printf("ERRO: Parametros inválidos\n");
		return 1;
	}

	double i;
	for(i = inf; i < sup; i++) {
		printf("%d, ", (int)fib_elem(i));
	}
	printf("%d\n", (int)fib_elem(i));

	return 0;
}
