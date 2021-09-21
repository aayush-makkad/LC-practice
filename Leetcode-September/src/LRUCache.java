import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private static class DLinkNode{
        int value;
        int key;
        DLinkNode prev;
        DLinkNode next;

        public DLinkNode(int value, int key) {
            this.value = value;
            this.key = key;
        }

        public DLinkNode() {
        }
    }

    private final DLinkNode head;
    private final DLinkNode tail;
    private int size;
    private final int capacity;
    private final Map<Integer, DLinkNode> nodeMap;

    private void addNode(DLinkNode node) {
        DLinkNode temp = head.next;
        head.next = node;
        node.prev = head;
        node.next = temp;
        node.next.prev = node;
    }

    private DLinkNode popTail() {
        DLinkNode node = tail.prev;
        tail.prev = node.prev;
        node.prev.next = tail;
        return node;
    }

    private void removeNode(DLinkNode node) {
        DLinkNode prev = node.prev;
        DLinkNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(DLinkNode node) {
        removeNode(node);
        addNode(node);
    }



    public LRUCache(int capacity) {
        head = new DLinkNode();
        tail = new DLinkNode();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        this.size = 0;
    }

    public int get(int key) {
        if(nodeMap.containsKey(key)) {
            DLinkNode node = nodeMap.get(key);
            moveToHead(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if(nodeMap.containsKey(key)) {
            DLinkNode node = nodeMap.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            DLinkNode newNode = new DLinkNode(value, key);
            nodeMap.put(key, newNode);
            addNode(newNode);
            size++;
            while(size > capacity) {
                DLinkNode dLinkNode = popTail();
                nodeMap.remove(dLinkNode.key);
                size--;
            }
        }
    }

}
