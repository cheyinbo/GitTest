package com.example.demo.test;

//单链表
public class MySingleList<T> {
    private Node head;    		//头结点
    private int size;			//链表元素个数
    //构造函数
    public MySingleList(){
        this.head = null;
        this.size = 0;
    }

    private class Node{
        private T t;
        private Node next;
        public Node(T t,Node next){
            this.t = t;
            this.next = next;
        }
        public Node(T t){
            this(t,null);
        }
    }
    public void addFirst(T t){
        Node node = new Node(t);	//节点对象
        node.next = this.head;
        this.head = node;
        //this.head = new Node(e,head);等价上述代码
        this.size++;
    }

    //向链表尾部插入元素
    public void addLast(T t){
        this.add(t, this.size);
    }
    //向链表中间插入元素
    public void add(T t,int index){
        if (index <0 || index >size){
            throw new IllegalArgumentException("index is error");
        }
        if (index == 0){
            this.addFirst(t);
            return;
        }
        Node preNode = this.head;
        //找到要插入节点的前一个节点
        for(int i = 0; i < index-1; i++){
            preNode = preNode.next;
        }
        Node node = new Node(t);
        //要插入的节点的下一个节点指向preNode节点的下一个节点
        node.next = preNode.next;
        //preNode的下一个节点指向要插入节点node
        preNode.next = node;
        this.size++;
    }

    //删除链表元素
    public void remove(T t){
        if(head == null){
            System.out.println("无元素可删除");
            return;
        }
        //要删除的元素与头结点的元素相同
        while(head != null && head.t.equals(t)){
            head = head.next;
            this.size--;
        }
        /**
         * 上面已经对头节点判别是否要进行删除
         * 所以要对头结点的下一个结点进行判别
         */
        Node cur = this.head;
        while(cur != null && cur.next != null){
            if(cur.next.t.equals(t)){
                this.size--;
                cur.next = cur.next.next;
            }
            else cur = cur.next;
        }

    }
}

