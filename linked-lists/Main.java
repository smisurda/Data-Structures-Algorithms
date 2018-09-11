/**
 * @author Samantha L. Misurda
 * smisurda@andrew.cmu.edu
 * Assignment 1
 * Main.java

 * Driver program for assignment 1

 */  

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        part1(args);
        part2();
    }

    public static void part2() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter 1st string: ");
        String string1 = scanner.next();

        System.out.print("Enter 2nd string: ");
        String string2 = scanner.next();

        // Build singly linked lists
        SingleLinkedList list1 = new SingleLinkedList();
        SingleLinkedList list2 = new SingleLinkedList();
        SingleLinkedList list3 = new SingleLinkedList();
        
        for(int i=0; i<string1.length();i++)
        {
            list1.addCharAtEnd(string1.charAt(i));    
        } 

        for(int i=0; i<string2.length();i++)
        {
            list2.addCharAtEnd(string2.charAt(i));    
        } 

        // Insert code to perform concatenation in part 2
        list3 = concatLists(list1, list2);
           

        // Insert code to output combined list
        System.out.println(list3);

    }

    public static void part1(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.addCharAtEnd('H');
        list.addCharAtEnd('e');
        list.addCharAtEnd('l');
        list.addCharAtEnd('l');
        list.addCharAtEnd('o');
        System.out.println(list);
        System.out.println("Deleting l");
        list.deleteChar('l');
        System.out.println(list);
        System.out.println("Deleting H");
        list.deleteChar('H');
        System.out.println(list);
        System.out.println("Deleting o");
        list.deleteChar('o');
        System.out.println(list);
        System.out.println("Deleting e");
        list.deleteChar('e');
        System.out.println(list);
        System.out.println("Deleting l");
        list.deleteChar('l');
        System.out.println(list);
        list.addCharAtFront('o');
        list.addCharAtFront('l');
        list.addCharAtFront('l');
        list.addCharAtFront('e');
        list.addCharAtFront('H');
        System.out.println(list);

        System.out.println(list.countNodes());

        System.out.println("Popping everything");
        while(!list.isEmpty()){
            System.out.println(list.removeCharFromFront());
        }

        list.addCharAtFront('o');
        list.addCharAtFront('l');
        list.addCharAtFront('l');
        list.addCharAtFront('e');
        list.addCharAtFront('H');

        System.out.println("Popping everything from the end");
        while(!list.isEmpty()){
            System.out.println(list.removeCharAtEnd());
        }
        System.out.println(list.countNodes());

        list.addCharAtEnd('o');
        list.addCharAtEnd('l');
        list.addCharAtEnd('l');
        list.addCharAtEnd('e');
        list.addCharAtEnd('H');

        list.reverse();
        System.out.println(list);

        list.reverse();
        System.out.println(list);

        DoublyLinkedList list2 = new DoublyLinkedList();
        list2.addCharAtEnd('O');
        list2.addCharAtEnd('K');
        list2.reverse();
        System.out.println(list2);
        System.out.println(list2.countNodes());
    }

    public static SingleLinkedList concatLists(SingleLinkedList a, SingleLinkedList b)
    {
        SingleLinkedList newList = new SingleLinkedList();
        SingleNode currNode = a.getHead();
        while(currNode.getNext() != null)
        {
            newList.addCharAtEnd(currNode.getC());
            currNode=currNode.getNext();
        }
        newList.addCharAtEnd(currNode.getC());
        currNode = b.getHead();
        while(currNode.getNext() != null)
        {
            newList.addCharAtEnd(currNode.getC());
            currNode=currNode.getNext();
        }
        newList.addCharAtEnd(currNode.getC());
        return newList;
    }
}
