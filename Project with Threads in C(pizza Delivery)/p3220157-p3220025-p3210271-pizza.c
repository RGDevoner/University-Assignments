#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>
#include "p3220157-p3220025-p3210271-pizza.h"

int avlble_tel = Ntel;
int avlble_ovens = Noven;

pthread_mutex_t tel_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t tel_cond = PTHREAD_COND_INITIALIZER;
pthread_mutex_t oven_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t oven_cond = PTHREAD_COND_INITIALIZER;
pthread_mutex_t screen_mutex = PTHREAD_MUTEX_INITIALIZER;

typedef struct {
    int pizzas;
    int order_id;
    int *pizza_types;
    time_t start_time;
    time_t end_time;
    time_t bake_end_time;
} order_t;

int ts_marg = 0;
int ts_pep = 0;
int ts_special = 0;
double total_revenue = 0.0;
int suc_orders = 0;
int f_orders = 0;
time_t max_st= 0;
time_t total_st = 0;
time_t max_coolt = 0;
time_t total_coolt = 0;

void *process_order(void *arg);
void *making_pizza(void *arg);
void *delivery_person(void *arg);

void *process_order(void *arg) {
    int order_id = *(int *)arg;
    unsigned int seedp = order_id;
    int sleep_time, pizzas;

    // Order start time
    time_t order_start_time = time(NULL);

    // Order time
    sleep_time = Torderlow + rand_r(&seedp) % (Torderhigh - Torderlow + 1);
    sleep(sleep_time);

    // Lock telephonist
    pthread_mutex_lock(&tel_mutex);
    while (avlble_tel == 0) {
        pthread_cond_wait(&tel_cond, &tel_mutex);
    }
    avlble_tel--;
    pthread_mutex_unlock(&tel_mutex);

    // Process order
    pizzas = Norderlow + rand_r(&seedp) % (Norderhigh - Norderlow + 1); // Random number of pizzas
    int *pizza_types = malloc(pizzas * sizeof(int));
    double order_revenue = 0.0;
    for (int i = 0; i < pizzas; i++) {
        int rand_num = rand_r(&seedp) % 100;
        if (rand_num < Pm * 100) {
            pizza_types[i] = 0; // Margherita
            order_revenue += Cm;
        } else if (rand_num < (Pm + Pp) * 100) {
            pizza_types[i] = 1; // Pepperoni
            order_revenue += Cp;
        } else {
            pizza_types[i] = 2; // Special
            order_revenue += Cs;
        }
    }

    // Payment processing
    sleep_time = Tpaymentlow + rand_r(&seedp) % (Tpaymenthigh - Tpaymentlow + 1);
    sleep(sleep_time);
    if ((rand_r(&seedp) % 100) < Pfail * 100) { // Pfail chance of payment failure
        pthread_mutex_lock(&screen_mutex);
        printf("Η παραγγελία με αριθμό %d απέτυχε\n", order_id);
        f_orders++;
        pthread_mutex_unlock(&screen_mutex);

        pthread_mutex_lock(&tel_mutex);
        avlble_tel++;
        pthread_cond_signal(&tel_cond);
        pthread_mutex_unlock(&tel_mutex);

        free(arg);
        free(pizza_types);
        pthread_exit(NULL);
    }

    // Order successful
    pthread_mutex_lock(&screen_mutex);
     printf("Η παραγγελία με αριθμό  %d καταχωρήθηκε \n", order_id);
    total_revenue += order_revenue;
    for (int i = 0; i < pizzas; i++) {
        if (pizza_types[i] == 0) {
            ts_marg++;
        } else if (pizza_types[i] == 1) {
            ts_pep++;
        } else {
            ts_special++;
        }
    }
    pthread_mutex_unlock(&screen_mutex);

    // Release telephonist
    pthread_mutex_lock(&tel_mutex);
    avlble_tel++;
    pthread_cond_signal(&tel_cond);
    pthread_mutex_unlock(&tel_mutex);

    // Now its time for the second phase, making and baking the pizza
    order_t *order_data = malloc(sizeof(order_t));
    order_data->pizzas = pizzas;
    order_data->order_id = order_id;
    order_data->pizza_types = pizza_types;
    order_data->start_time = order_start_time;
    pthread_t cook_thread;
    if (pthread_create(&cook_thread, NULL, making_pizza, (void *)order_data) != 0) {
        free(order_data->pizza_types);
        free(order_data);
        free(arg);
        pthread_exit(NULL);
    }

    pthread_detach(cook_thread); // Detaching the cooking thread since we're done with it
    free(arg);
    pthread_exit(NULL);
}

void *delivery_person(void *arg) {
    order_t *order_data = (order_t *)arg;
    int pizzas = order_data->pizzas;
    int order_id = order_data->order_id;
    unsigned int seedp = order_id;

    // Random delivery time
    int delivery_time = Tdellow + rand_r(&seedp) % (Tdelhigh - Tdellow + 1);

    // Lock delivery person
    pthread_mutex_lock(&screen_mutex);
    pthread_mutex_unlock(&screen_mutex);
    sleep(delivery_time);

    // Calculate service time and cooling time
    time_t current_time = time(NULL);
    time_t service_time = current_time - order_data->start_time;
    time_t cooling_time = current_time - order_data->bake_end_time;

    pthread_mutex_lock(&screen_mutex);
     printf("Η παραγγελία με αριθμό %d παραδόθηκε σε  %ld seconds.\n", order_id, service_time);
    suc_orders++;
    total_st += service_time;
    if (service_time > max_st) {
        max_st= service_time;
    }
    total_coolt += cooling_time;
    if (cooling_time > max_coolt) {
        max_coolt = cooling_time;
    }
    pthread_mutex_unlock(&screen_mutex);

    free(order_data->pizza_types);
    free(order_data);
    pthread_exit(NULL);
}

void *making_pizza(void *arg) {
    order_t *order_data = (order_t *)arg;
    int pizzas = order_data->pizzas;
    int order_id = order_data->order_id;

    // Cooks are making the pizza
    pthread_mutex_lock(&screen_mutex);
    pthread_mutex_unlock(&screen_mutex);
    sleep(Tprep);

    // Locking the ovens we will bake the pizzas
    pthread_mutex_lock(&oven_mutex);
    while (avlble_ovens < pizzas) {
        pthread_mutex_lock(&screen_mutex);
        pthread_mutex_unlock(&screen_mutex);
        pthread_cond_wait(&oven_cond, &oven_mutex);
    }
    avlble_ovens -= pizzas;
    pthread_mutex_unlock(&oven_mutex);

    // Baking the pizzas, please wait
    pthread_mutex_lock(&screen_mutex);
   
    pthread_mutex_unlock(&screen_mutex);
    sleep(Tbake);

    // Record bake end time
    order_data->bake_end_time = time(NULL);

    // Releasing the ovens
    pthread_mutex_lock(&oven_mutex);
    avlble_ovens += pizzas;
    pthread_cond_signal(&oven_cond);
    pthread_mutex_unlock(&oven_mutex);
    pthread_mutex_lock(&screen_mutex);
    printf("Η παραγγελία με αριθμό: %d ετοιμάστηκε σε  %d δευτερόλεπτα.\n", order_id,Tbake+Tprep);
    pthread_mutex_unlock(&screen_mutex);

    // Delivery
    pthread_t delivery_thread;
    if (pthread_create(&delivery_thread, NULL, delivery_person, (void *)order_data) != 0) { // Pass order_data as argument
        perror("Could not create delivery thread");
        free(order_data->pizza_types);
        free(order_data);
        pthread_exit(NULL);
    }

    pthread_detach(delivery_thread); // Detaching the delivery thread since we're done with it
    pthread_exit(NULL);
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Usage: %s <number_of_customers> <random_seed>\n", argv[0]);
        return 1;
    }

    int Ncust = atoi(argv[1]);
    unsigned int Seed = (unsigned int)atoi(argv[2]);

    srand(Seed);

    pthread_t threads[Ncust];
    for (int i = 0; i < Ncust; i++) {
        int *order_id = malloc(sizeof(int));
        *order_id = i + 1; // Ensure different seed for each thread
        if (pthread_create(&threads[i], NULL, process_order, (void *)order_id) != 0) {
            perror("Failed to create thread");
        }
    }

    for (int i = 0; i < Ncust; i++) {
        pthread_join(threads[i], NULL);
    }

    pthread_mutex_lock(&screen_mutex);
    printf("Total sales of Margherita pizzas: %d\n", ts_marg);
    printf("Total sales of Pepperoni pizzas: %d\n", ts_pep);
    printf("Total sales of Special pizzas: %d\n", ts_special);
    printf("Total revenue: %.2f\n", total_revenue);
    printf("Successful orders: %d\n", suc_orders);
    printf("Failed orders: %d\n", f_orders);
    if (suc_orders > 0) {
        printf("Average service time: %.2f seconds\n", (double)total_st / suc_orders);
        printf("Maximum service time: %ld seconds\n", max_st);
        printf("Average cooling time: %.2f seconds\n", (double)total_coolt / suc_orders);
        printf("Maximum cooling time: %ld seconds\n", max_coolt);
    }
    pthread_mutex_unlock(&screen_mutex);

    return 0;
}
