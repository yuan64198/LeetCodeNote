/**
 * 
 *
 */


class FileSystem {
    class Node {
        String name;
        String content = "";
        boolean isFile;
        Map<String, Node> children = new HashMap();
        
        public Node(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
        }
        
    }
    
    Node root;
    
    public FileSystem() {
        root = new Node("root", false);
    }
    
    private Node findNode(String path, boolean isFile) {
        Node curr = root;
        int index = 1;
        while(curr != null && index < path.length()) {
            int nextIndex = currentDirIndex(path, index);
            if(nextIndex == 1) break;
            String prefix = path.substring(index, nextIndex);
            //System.out.println(prefix);
            if(!curr.children.containsKey(prefix)) {
                curr.children.put(prefix, new Node(prefix, isFile));
            }
            //********
            curr = curr.children.get(prefix);
            index = nextIndex + 1;
        }
        return curr;
    }
    
    private int currentDirIndex(String path, int start) {
        int i;
        for(i = start; i < path.length(); ++i) {
            char c = path.charAt(i);
            if(c == '/') {
                break;
            }
        }
        return i;
    }
    
    public List<String> ls(String path) {
        Node node = findNode(path, false);
        List<String> result = new ArrayList();
        if(node.isFile == true) {
            result.add(node.name);
            return result;
        }
        for(String name : node.children.keySet()) {
            result.add(name);
        }
        Collections.sort(result);
        return result;
    }
    
    public void mkdir(String path) {
        Node node = findNode(path, false);
    }
    
    public void addContentToFile(String filePath, String content) {
        Node node = findNode(filePath, true);
        node.content += content;
    }
    
    public String readContentFromFile(String filePath) {
        return findNode(filePath, true).content;
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
