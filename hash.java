import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Node {
    String usn;
    int addr;
    Node next;

    public Node(String usn, int addr, Node next) {
        this.usn = usn;
        this.addr = addr;
        this.next = next;
    }

    public Node(String usn, int addr) {
        this.usn = usn;
        this.addr = addr;
        this.next = null;
    }
}

public class hash {

    static ArrayList<Node> h = new ArrayList<>(3);
    static Scanner s = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        hash obj = new hash();
        int i;
        for (i = 0; i <= 3; i++)
            h.add(i, null);

        while (true) {
            System.out.println("-----------------------------");
            System.out.println("1.Insert\n2.Display\n3.Search\n4.Exit");
            System.out.println("Enter your choice");
            int choice = s.nextInt();
            s.nextLine();
            switch (choice) {
                case 1: {
                    obj.insert();
                }
                break;
                case 2: {
                    System.out.println("Enter the department ");
                    String branch = s.nextLine();
                    int a=key(branch);;
                    Node test = h.get(a);
                    obj.display(test);
                
                }
                break;
                case 3:
                        obj.search();
                    break;
                case 4: {
                    System.out.println("thank you");
                    System.exit(0);
                }
                break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    private void insert() throws IOException {
        System.out.println("Enter usn,name,branch,address,phone");
        String usn = s.nextLine();
        String name = s.nextLine();
        String branch = s.nextLine();
        String address = s.nextLine();
        String phone = s.nextLine();
        String b = usn + "|" + name + "|" + branch + "|" + address + "|" + phone + "$";
        String fname = "students.txt";
        File f = new File(fname);
        if (!f.exists()) {
            boolean newFile = f.createNewFile();
            if (newFile) {
                System.out.println(fname + "created successfully");
            }
        }
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(fname, true));
        RandomAccessFile randomAccessFile = new RandomAccessFile(fname, "rw");
        randomAccessFile.seek(randomAccessFile.length());
        int pos = (int) randomAccessFile.getFilePointer();
        int a = key(branch);
        System.out.println(pos);
        printWriter.println(b);
        printWriter.close();
        randomAccessFile.seek(pos);
        System.out.println(randomAccessFile.readLine());
        randomAccessFile.close();
        Node k = new Node(usn, pos);
        if (a != -1) {
            if (h.get(a) == null)
                //h.add(new Node<String,int,Node>());
                h.set(a, k);
            else {
                //int addr;
                Node test = h.get(a);

                while (test.next != null) {
                    test = test.next;

                }
                test.next = k;
            }

        } else {
            System.out.println("dept it error");
        }
        display(h.get(a));

    }

    public static int key(String b) {
        int key=-1;
        if (b.equalsIgnoreCase("ise"))
            key=0;
        else if (b.equalsIgnoreCase("cse"))
            key=1;
        else if (b.equalsIgnoreCase("ece"))
            key=2;
        else if (b.equalsIgnoreCase("tce"))
            key=3;
        return key;
    }

    public void display(Node head) {

        Node current = head;
        if (head == null) {
            System.out.println("empty");
            return;
        }
        System.out.println("Nodes: ");
        while (current != null) {
            System.out.println(current.usn + "->" + current.addr);
            current = current.next;
        }
        System.out.println();
    }
    public void search() throws FileNotFoundException,IOException{
        int flag=0;
        System.out.println("Enter USN to be searched");
        String USN=s.nextLine();
        String branch=USN.substring(5,7)+"e";
        System.out.println(branch);
        int a = key(branch);
        Node head=h.get(a);
        Node current = head;
        if (head == null) {
            System.out.println("Record not foundDDD");
            return;
        }
        //System.out.println("Nodes: ");
        while (current != null) {
            System.out.println(current.usn + "->" + current.addr);
            if(current.usn.equals(USN)){
                int pos=current.addr;
                String fname = "students.txt";
                RandomAccessFile randomAccessFile = new RandomAccessFile(fname, "rw");
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.seek(pos);
                System.out.println(randomAccessFile.readLine());
                randomAccessFile.close();
                //System.out.println("Record found,Details are");

                flag=1;
                break;
            }
            current = current.next;
        }
        if(flag==0){
            System.out.println("Record Not found");
        }


    }
}