/*************************************************************************
	> File Name: q1.c
	> Author: 
	> Mail: 
	> Created Time: Thu Oct 23 17:05:12 2014
 ************************************************************************/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>
int main(int args, char *argv[]) {
    if (args==1){
        printf("Need file name\n");
        return 1;
    }
    float *x = (float *)malloc(8*60000);
    float *y = (float *)malloc(8*60000);
    FILE *f;
    f = fopen(argv[1],"r");
    int i = 0;
    while (EOF != fscanf(f, "%f %f\n", &x[i], &y[i])) {
        i++;
    }
    printf("File finish reading\n");
    int length=i;
    printf("%d\n", length);
    fclose(f);
    int k;
    int j;
    long *count = (long *)malloc(sizeof(long)*21);
    long *count2 = (long *)malloc(sizeof(long)*21);
    printf("start calculating\n");
    for (k = -10; k < 11; k++) {
        for (i = 0; i < length; i++) {
            for (j = i; j < length; j++) {
                double t1 = pow(x[i]-x[j], 2);
                double t2 = pow(y[i]-y[j], 2);
                double distance = sqrt(t1+t2);
                if (i == j) {
                    count[k+10]++;
                    count2[k+10]++;
                    continue;
                }
                if (distance <= pow(10,k)) {
                    count[k+10]=count[k+10]+2;
                }
                distance = fabs(x[i]-x[j]) + fabs(y[i]-y[j]);
                if (distance <= pow(10,k)) {
                    count2[k+10]=count2[k+10]+2;
                }
            }
        }
        printf("One round finish\n");
    }
    FILE *res;
    char *file="data/mystery1";
    if (memcmp(argv[1],file,13))
        res = fopen("./output/mystery2_l1.result","w");
    else
        res = fopen("./output/mystery1_l1.result","w");
    for (i = 0; i < 21; i++) {
        fprintf(res, "%g\t%lu\n",pow(10,i-10),count[i]);
    }
    FILE *res2;
    if (memcmp(argv[1],file,13))
        res2 = fopen("./output/mystery2_l2.result","w");
    else
        res2 = fopen("./output/mystery1_l2.result","w");
    for (i=0; i < 21; i++) {
        fprintf(res2, "%g\t%lu\n",pow(10,i-10),count2[i]);
    }
    fclose(res);
    free(x);
    free(y);
    free(count);
    free(count2);
}
