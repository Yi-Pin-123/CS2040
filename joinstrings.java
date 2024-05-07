import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

class StringNode {
    StringBuilder content;
    Integer next; //next is for printing order of strings correctly
    Integer tail; 
    //track the tail of the chain so O(n) when update tail of chain

    public StringNode(String str) {
        this.content = new StringBuilder(str);
        this.next = null;
        this.tail = null; // Initialize next and tail to null
    }
}

public class joinstrings {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine());
        StringNode[] nodes = new StringNode[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new StringNode(br.readLine());
        }

        if (N == 1) { // edge case when N = 1
            pw.print(nodes[1].content);
            pw.flush();
            return;
        }
        
        int start = 0; //this will be assigned to the start of the linked nodes
        
        for (int i = 1; i < N; i++) {
            String[] parts = br.readLine().split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            
            if (i == N - 1) {
                start = a; // Set start to the last 'a' value read
    }
            StringNode currentNode = nodes[a];
            if (currentNode.next == null) {
                currentNode.next = b;
                if (nodes[b].tail == null){
                    currentNode.tail = b;}
                else{currentNode.tail = nodes[b].tail;}
            } else if (currentNode.tail == null) {
                StringNode followingNode = nodes[currentNode.next];
                followingNode.next = b;
                if (nodes[b].tail == null){
                    currentNode.tail = b;}
                else{currentNode.tail = nodes[b].tail;}
            } else {
                StringNode tailNode = nodes[currentNode.tail];
                tailNode.next = b;
                if (nodes[b].tail == null){
                    currentNode.tail = b;}
                else{currentNode.tail = nodes[b].tail;}
            }
        }


        // Traverse and print the concatenated strings
        Integer index = start;
        while (index != null) {
            pw.print(nodes[index].content);
            index = nodes[index].next;
        }

        pw.flush();
    }
}
