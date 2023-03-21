#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define BUFFER_SIZE 128

int main(int argc, char *argv[]) {
    int pid;
    unsigned int vendor_id;
    unsigned int device_id;
    FILE *file = fopen("/sys/kernel/debug/lab2/structs", "r+");
    if (file == NULL) {
        printf("Нельзя открыть файл!\n");
        return 0;
    }
    if (strcmp(argv[1],"--help")==0) {
        printf("Первый аргумент: pid\n");
        printf("Второй аргумент: vendor_id\n");
        printf("Третий аргумент: device_id\n");
        fclose(file);
        return 0;
    }
    if ((sscanf(argv[2], "%x", &vendor_id)) && (sscanf(argv[3], "%x", &device_id)) && (sscanf(argv[1], "%d", &pid))) {
        char *buffer[BUFFER_SIZE];
        fprintf(file, "pid: %d, vendor_id: %x, device_id: %x", pid, vendor_id, device_id);
        while (true) {
            char *result = fgets(buffer, BUFFER_SIZE, file);
            if (feof(file)) break;
            printf(result);
        }
    } else {
        printf("Аргументов много!!!");
    }
    fclose(file);
    return 0;
}
