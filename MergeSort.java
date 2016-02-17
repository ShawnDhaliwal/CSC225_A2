/* Shawn Dhaliwal, V00811632
	This program is to implement MergeSort on a List of Nodes.
	No type of looping or iterators are used.
	Instead, recursion is used in their place.
	The program is Theta(n*logn) run time.
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;

public class MergeSort{

	public static ListNode MergeSort(ListNode head){
		
		if (head == null){	
			System.out.println("Please enter a number..");
			System.exit(0);					
		}
		
		//Begin the Splitting of the List into Smaller lists.
		
		int List_Length = listLength(head);
		ListNode ListOne = head;
		ListNode Split = head;
		Split = FindMiddle(Split, ListOne);		//Makes Split point to middle node of list.
		ListNode ListTwo = Split.next; 			//Make ListTwo point to other half of list.
		    
		if(List_Length > 1)						//If list has more than one node in it, split it up.
			Split.next = null;
			else		
				return head;
				
		ListNode FirstHalf = MergeSort(ListOne);
		ListNode SecondHalf = MergeSort(ListTwo);
		
		//When the code gets here, the List has been split into lists of length 1.
		
		head = Sort_and_Merge(FirstHalf, SecondHalf);		
						
		return head;
	}
	
	/* This functions returns a Node that points to the middle of the list passed as 
		the head in MergeSort.
		At the start of the function, two nodes point to the start of the same list.
		One node jumps two nodes at a time and the other node jumps one node at a time.
		Once one of the nodes ends up at the end of the list, the other will point to the middle.
	*/ 
	
	public static ListNode FindMiddle(ListNode Middle, ListNode FindEnd){
		if(FindEnd.next == null){
			return Middle;
		}
		if(FindEnd.next.next == null){
			return Middle;
		}else{
			return FindMiddle(Middle.next, FindEnd.next.next);
			
		}
	}

	
	/* This function is called when the list is broken down into lists of length 1.
		it then recursively sorts the lists and merges them together at the same time.
		FirstHead points to the start of one list, and SecondHead points to start of 
		the other list,  that are then merged and sorted together.
		Returns a sorted and merged list as head.
	*/
	
	public static ListNode Sort_and_Merge(ListNode FirstHead, ListNode SecondHead){
		ListNode head = FirstHead;
		
		if(FirstHead == null && SecondHead == null){
			return head;
		}else
			if(FirstHead != null && SecondHead == null){
				head = FirstHead;
				head.next = Sort_and_Merge(FirstHead.next, SecondHead);
			}else
				if(FirstHead == null && SecondHead != null){
					head = SecondHead;
					head.next = Sort_and_Merge(FirstHead, SecondHead.next);
				}else{												
					if(FirstHead.value < SecondHead.value){
						head = FirstHead;
						head.next = Sort_and_Merge(FirstHead.next, SecondHead);
					}else
						if(FirstHead.value > SecondHead.value){
							head = SecondHead;
							head.next = Sort_and_Merge(FirstHead, SecondHead.next);
						}else{
							head = FirstHead;
							head.next = Sort_and_Merge(FirstHead.next, SecondHead);
						}
				}
		
		return head;		
	}
    /*The code below was given by instructor and not changed by Shawn Dhaliwal*/

	/* ListNode class */
	/* Do not change, add or remove any aspect of the class definition below.
	   If any of the contents of this class are changed, your submission will
	   not be marked.
	   
	   The members of the class should be accessed directly (e.g. node.next, 
	   node.value), since no get/set methods exist.
	   
	   However, you may create a subclass of this class if you want to extend
	   its functionality. Ensure that your subclass is contained in MergeSort.java
	   with the rest of your code (since you may only submit one file).
	*/
	public static class ListNode{
		int value;
		ListNode next;
		public ListNode(int value){
			this.value = value;
			this.next = null;
		}
		public ListNode(int value, ListNode next){
			this.value = value;
			this.next = next;
		}
	}
	/* Testing code */
	
	/* listLength(node)
	   Compute the length of the list starting at the provided node.
	*/
	public static int listLength(ListNode node){
		if (node == null)
			return 0;
		return 1 + listLength(node.next);
	}
	
	/* testSorted(node)
	   Returns true if all elements of the list starting at the provided node
	   are in ascending order.
	*/
	public static boolean testSorted(ListNode node){
		//An empty list is always considered sorted.
		if (node == null)
			return true;
		//A list with one element is always considered sorted.
		if (node.next == null)
			return true;
		//Test whether the first element is greater than the second element
		if (node.value > node.next.value){
			//If so, the list is not sorted.
			return false;
		}else{
			//Otherwise, test whether the remaining elements are sorted and
			//return the result.
			return testSorted(node.next);
		}
	}
	
	/* printList(node)
	   Print all list elements starting at the provided node.
	*/
	public static void printList(ListNode node){
		if (node == null)
			System.out.println();
		else{
			System.out.print(node.value + " ");
			printList(node.next);
		}
	}
	
	/* readInput(inputScanner)
	   Read integer values from the provided scanner into a linked list.
	   Each recursive call reads one value, and recursion ends when the user
	   enters a negative value or the input file ends.
	*/
	public static ListNode readInput(Scanner inputScanner){
		//If no input is left, return an empty list (i.e. null)
		if (!inputScanner.hasNextInt())
			return null;
		int v = inputScanner.nextInt();
		//If the user entered a negative value, return an empty list.
		if (v < 0)
			return null;
		//If the user entered a valid input value, create a list node for it.
		ListNode node = new ListNode(v);
		//Scan for more values recursively, and set the returned list of values
		//to follow the node we just created.
		node.next = readInput(inputScanner);
		//Return the created list.
		return node;
	}

	/* main()
	   Contains code to test the MergeSort function. Nothing in this function 
	   will be marked. You are free to change the provided code to test your 
	   implementation, but only the contents of the MergeSort() function above 
	   will be considered during marking.
	*/
	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
		}
		ListNode inputListHead = readInput(s);
		
		int inputLength = listLength(inputListHead);
		System.out.printf("Read %d values.\n",inputLength);
		
		
		long startTime = System.currentTimeMillis();
		
		ListNode sortedListHead = MergeSort(inputListHead);
		
		long endTime = System.currentTimeMillis();
		
		double totalTimeSeconds = (endTime-startTime)/1000.0;
		
		//Compute the length of the output list
		int sortedListLength = listLength(sortedListHead);
		
		//Don't print out the values if there are more than 100 of them
		if (sortedListLength <= 100){
			System.out.println("Sorted values:");
			printList(sortedListHead);
		}
		
		//Check whether the sort was successful
		boolean isSorted = testSorted(sortedListHead);
		
		System.out.printf("List %s sorted.\n",isSorted? "is":"is not");
		System.out.printf("Total Time (seconds): %.2f\n",totalTimeSeconds);
	}
}