/*
 * =====================================================================================
 *
 *       Filename:  FIBONACCI.C
 *
 *    Description:  Calc Fibonacci's serie using pthreads
 *
 *        Version:  1.0
 *        Created:  05/10/2016 09:49:47
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Marcos Augusto
 *   Organization:  UFC
 *
 * =====================================================================================
 */
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

long *Fib; /* this data is shared by the thread and used to store Fibonacci values */
void *fib_calc(void *param); /* the thread */

int main(int argc, char *argv[])
{
	int i, len = atoi(argv[1]);

	pthread_t tid; /* the thread identifier */
	pthread_attr_t attr; /* set of attributes for the thread */
	if (argc != 2) {
		fprintf(stderr,"usage: %s <integer value>\n", argv[0]);
		return -1;
	}

	if (len < 0) {
		fprintf(stderr,"Argument %d must be non-negative\n",len);
		return -1;
	}

	if (len > 92) {
		fprintf(stderr,"Argument %d must be less than or equal to 92\n",len);
		return -1;
	}

	Fib = (long*)malloc(sizeof(long) * len);
	/* get the default attributes */
	pthread_attr_init(&attr);
	/* create the thread */
	pthread_create(&tid,&attr,fib_calc,argv[1]);
	/* now wait for the thread to exit */
	pthread_join(tid,NULL);
	for (i = 0; i < len; i++)
		printf("Fib(%d) = %ld\n",i, Fib[i]);
}
/**
* The thread will begin control in this function
*/
void *fib_calc(void *param)
{
	Fib[0] = 0;
	Fib[1] = 1;
	int i, len = atoi((char *)param);
	if (len > 0) {
		for (i = 2; i < len; i++)
			Fib[i] = Fib[i-1] + Fib[i-2];
	}
	pthread_exit(0);
}
