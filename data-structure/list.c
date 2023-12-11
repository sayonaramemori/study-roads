#ifndef VIOLET_LIST
#define VIOLET_LIST
typedef struct{
    void *prev;
    void *next;
    void *val;
}list_node;

typedef struct{
    struct list_node *head;
    struct list_node *rear;
}list;

list *initList();
void insert(int);


#endif
