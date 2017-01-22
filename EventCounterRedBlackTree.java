import java.io.*;
import java.util.*;
	
public class EventCounterRedBlackTree
	{
    	static RBTree root; //Red Black tree root
    	private static final boolean RED = true; //true implies color of node is red
    	private static final boolean BLACK = false; //false implies color of node is black
    
   class RBTree 
   {
        int id; // event ID
        int count; // Count associated with each ID.
        RBTree parent, leftChild, rightChild;
        boolean isRed; // To check if color is red

        RBTree(int key, int count) {
                
        this.id = key;
        this.count = count;
            
        this.isRed = BLACK; //color is black by default
        this.leftChild=null;
        this.rightChild=null;//left child, right child and parent node set to null on creation
        this.parent=null;
        }    
    }
    
    public RBTree newbst(int[] id,int[] count,int start, int end,int height){
        if (start > end) {
            return null;
        }
         int mid = (start + end) / 2;
        RBTree node = new RBTree(id[mid],count[mid]);
        /*Constructs the left subtree recursively*/
        node.leftChild = newbst(id,count,start, mid-1,height-1);
        
        if(node.leftChild!=null){
            node.leftChild.parent=node;
          
        }
 
    /*Constructs the left subtree recursively*/    
        node.rightChild = newbst(id,count,mid+1, end,height-1);
        
        //Parent pointers are set
        
         if(node.rightChild!=null){
            node.rightChild.parent=node;
        }
         if(height==0)
             node.isRed=true;
      
         
        return node;//Returns the root of the balanced tree
        
    }
    
    //searches for the node
    public RBTree Search(int id){
         RBTree x=root;
         while(x!=null && id!=x.id){
             if(id<x.id)
                 x=x.leftChild;
             else
                 x=x.rightChild;
             
                 
         }
         return x;
     }
    //if this has no next id
    public RBTree nextKey(int id){
       
         RBTree x=root;
         RBTree parentx;
        
         while(x!=null && id!=x.id){
             if(id<x.id){
                 parentx=x;
                 x=x.leftChild;
                 if(x==null)
                     return parentx;
                 
              }
             else
             {
                 parentx=x;
                 x=x.rightChild;
                 if(x==null){
                     while(parentx!=null && parentx.id<id){
                         parentx=parentx.parent;
                         
                     }
                     if(parentx!=null)
                         return parentx;
                     else 
                         return null;
                 }
             }
         }
         return x;       
     }
    //if this has no previous id
    public RBTree previousKey(int id){
       
         RBTree x=root;
         RBTree parentx;
        
         while(x!=null && id!=x.id){
             if(id<x.id){
                 parentx=x;
                 x=x.leftChild;
                 if(x==null){
                     while(parentx!=null && parentx.id>id){
                         parentx=parentx.parent;
                         
                     }
                     if(parentx!=null)
                         return parentx;
                     else 
                         return null;
                 }
                     
                 
                 
              }
             else
             {
                 parentx=x;
                 x=x.rightChild;
                 if(x==null)
                     return parentx;
             }
         }
         return x;	         
     }
    
    //insert_Nodes the node
    public void insert_Node(int id,int count){
         RBTree z=new RBTree(id,count);
         RBTree y=null;
         RBTree x=root;
         while(x!=null){
             y=x;
             if(z.id<x.id)
                 x=x.leftChild;
             else
                 x=x.rightChild;
            }
         z.parent=y;
         if(y==null)
             root=z;
         else if(z.id<y.id)
             y.leftChild=z;
         else
             y.rightChild=z;
         z.leftChild=null;
         z.rightChild=null;
         z.isRed=true;
         insertfix(z);
         
         
     }
    //fixes the tree after insert_Nodeion     
    public void insertfix(RBTree z){
         RBTree y;
         
         while(z.parent.isRed==true){
             if(z.parent==z.parent.parent.leftChild){
                 y=z.parent.parent.rightChild;
                 if(y!=null && y.isRed==true){
                     z.parent.isRed=false;
                     y.isRed=false;
                     z.parent.parent.isRed=true;
                     z=z.parent.parent;
                     
                     
                 }
                 else if(z==z.parent.rightChild){
                     z=z.parent;
                     rotate_left(z);
                 }
                 else{
                    z.parent.isRed=false;
                    z.parent.parent.isRed=true;
                    rotate_right(z.parent.parent);
                 }
                  
                
                 
                 
             }
             else{
                 y=z.parent.parent.leftChild;
                 if(y!=null && y.isRed==true){
                     z.parent.isRed=false;
                     y.isRed=false;
                     z.parent.parent.isRed=true;
                     z=z.parent.parent;
                     
                     
                 }
                 else if(z==z.parent.leftChild){
                     z=z.parent;
                     rotate_right(z);
                 }
                 else{
                    z.parent.isRed=false;
                    z.parent.parent.isRed=true;
                    rotate_left(z.parent.parent);
                 }
                 
                 
                 
             }
            
                 
             if(z==root)
                 break;
         }
          root.isRed=false;
     }
     //rotate_left the subtree for balancing the tree
     public void rotate_left(RBTree x){
         RBTree y=x.rightChild;
         x.rightChild=y.leftChild;
         if(y.leftChild!=null)
             y.leftChild.parent=x;
         y.parent=x.parent;
         if(x.parent==null)
             root=y;
         else if(x==x.parent.leftChild)
             x.parent.leftChild=y;
         else 
             x.parent.rightChild=y;
         y.leftChild=x;
         x.parent=y;
         
         
     }
     //right rotate the subtree for balancing the tree
     public void rotate_right(RBTree y){
         RBTree x=y.leftChild;
         y.leftChild=x.rightChild;
         if(x.rightChild!=null)
             x.rightChild.parent=y;
         x.parent=y.parent;
         if(y.parent==null)
             root=x;
         else if(y==y.parent.rightChild)
             y.parent.rightChild=x;
         else 
             y.parent.rightChild=x;
         x.rightChild=y;
         y.parent=x;
         
         
     }
     //reduces the count of the id
     private int reduce(int id,int m){
         RBTree curNode=Search(id);
         if(curNode!=null){
             curNode.count=curNode.count-m;
             if(curNode.count>0)
                 return curNode.count;
             else{
                 delete_node(id,m);
                 return 0;
                 
             }
                 
             
             
         }
             
         
         else
             return 0;
     }
     //deletes the node
     
     //fix the tree after deletion
     public void deletefix(RBTree x){
         while(x!=root && x.isRed==false){
             if(x==x.parent.leftChild){
                 RBTree w=x.parent.rightChild;
                 if(w.isRed==true){
                     w.isRed=false;
                     x.parent.isRed=true;
                     rotate_left(x.parent);
                     w=x.parent.rightChild;
                     
                 }
                 else if(w.leftChild.isRed==false && w.rightChild.isRed==false){
                     w.isRed=true;
                     x=x.parent;
                 }
                 else if(w.rightChild.isRed==false){
                     w.leftChild.isRed=false;
                     w.isRed=true;
                     rotate_right(w);
                     w=x.parent.rightChild;
                 }
                 else{
                     w.isRed=x.parent.isRed;
                     x.parent.isRed=false;
                     w.rightChild.isRed=false;
                     rotate_left(x.parent);
                     x=root;
                 }
                    
                 
             }
             else{
                 RBTree w=x.parent.leftChild;
                 if(w.isRed==true){
                     w.isRed=false;
                     x.parent.isRed=true;
                     rotate_right(x.parent);
                     w=x.parent.leftChild;
                     
                 }
                 else if(w.rightChild.isRed==false && w.leftChild.isRed==false){
                     w.isRed=true;
                     x=x.parent;
                 }
                 else if(w.leftChild.isRed==false){
                     w.rightChild.isRed=false;
                     w.isRed=true;
                     rotate_left(w);
                     w=x.parent.leftChild;
                 }
                 else{
                     w.isRed=x.parent.isRed;
                     x.parent.isRed=false;
                     w.leftChild.isRed=false;
                     rotate_right(x.parent);
                     x=root;
                 }
                 
             }
             
         }
         x.isRed=false;
         
     }
     
     public RBTree treeMinimum(RBTree x){
         
             
        
         while(x.leftChild!=null)
             x=x.leftChild;
        
         return x;
      }
     
     public RBTree treeMaximum(RBTree x){
         
             
        
         while(x.rightChild!=null)
             x=x.rightChild;
        
         return x;
      }
     
     public void transplant(RBTree u,RBTree v)
     {
         if(u.parent==null)
             root=v;
         else if(u==u.parent.leftChild)
             u.parent.leftChild=v;
         else
             u.parent.rightChild=v;
         if(v!=null)
             v.parent=u.parent;
             
     }
     public int rangeImp(RBTree rootnode,int id1,int id2){
        
        if(rootnode==null)
            return 0;
        
        // Special Optional case for improving efficiency
        if (rootnode.id == id2 && rootnode.id == id1)
            return rootnode.count;
 
        // If current node is in range, then include it in count and
        // recur for left and right children of it
        if (rootnode.id <= id2 && rootnode.id >= id1)
            return rootnode.count + rangeImp(rootnode.leftChild, id1, id2) + rangeImp(rootnode.rightChild, id1, id2);
 
        // If current node is smaller than low, then recur for right
        // child
        else if (rootnode.id < id1)
            return rangeImp(rootnode.rightChild,id1,id2);
 
        // Else recur for left child
        else 
            return rangeImp(rootnode.leftChild,id1,id2);
         
     }
     //to check the number of ids in the given range
     public int inRange(int id1,int id2)
     {
         int countSum=rangeImp(root,id1,id2);
         return countSum;
         
     }
     
     public void delete_node(int id,int m){
         RBTree z=Search(id);
         
         RBTree y=z;
         RBTree x=null;
         
         boolean yoriginalcolor=y.isRed;
         if(z.leftChild==null){
             x=z.rightChild;
             transplant(z,z.rightChild);
         }
         else if(z.rightChild==null){
             x=z.leftChild;
             transplant(z,z.leftChild);
         }
         else{
                     y=treeMinimum(z.rightChild);
                     yoriginalcolor=y.isRed;
                     x=y.rightChild;
                     if(y.parent==z){
                         if(x!=null){
                            x.parent=y;
                         }
                     }
                        
                     if(y.parent!=z){
                        transplant(y,y.rightChild);
                        y.rightChild=z.rightChild;
                        y.rightChild.parent=y;
                     
                     }
                     transplant(z,y);
                     y.leftChild=z.leftChild;
                     y.leftChild.parent=y;
                     y.isRed=z.isRed;
                     
                     
                     }
         if(yoriginalcolor==false && x!=null)
             deletefix(x);
             
         
         
     }
     //gives the count of the id
    public int count(int id){
        RBTree targetNode=Search(id);
        if(targetNode!=null)
            return targetNode.count;
        else
            return 0;
    }
     //increases the count of the event 
    public int increase(int id,int m)
    {
         RBTree curNode=Search(id);
         if(curNode!=null){
             curNode.count=curNode.count+m;
             return curNode.count;
         }
         else{
             insert_Node(id,m);
             return m;
         }
             
     }
     //to find the next node
     public void next(int id)
     {
         RBTree currentNode=Search(id);
         if(currentNode!=null){
             RBTree nextNode=null;
         RBTree y;
         if(currentNode!=null && currentNode.rightChild!=null)
             nextNode=treeMinimum(currentNode.rightChild);
         else{
             y=currentNode.parent;
             while(y!=null && currentNode==y.rightChild){
                currentNode=y;
                y=y.parent;
             }
             nextNode=y;
         
         }
         if(nextNode!=null)
             System.out.println(nextNode.id + " " + nextNode.count);
         else
             System.out.println("0 0");
         }
         else{
             RBTree currentNoKey=nextKey(id);
             if(currentNoKey!=null)
                 System.out.println(currentNoKey.id + " " + currentNoKey.count);
             else
                 System.out.println("0 0");
                 
                 
                 
             
             
         }
         
     }
     //to find the previous node
     public void previous(int id)
     {
         RBTree currentNode=Search(id);
         if(currentNode!=null){
             RBTree previousNode=null;
         RBTree y;
         if(currentNode!=null && currentNode.leftChild!=null)
             previousNode=treeMaximum(currentNode.leftChild);
         else{
             y=currentNode.parent;
             while(y!=null && currentNode==y.leftChild){
                currentNode=y;
                y=y.parent;
             
         }
             previousNode=y;
         
         }
         if(previousNode!=null)
             System.out.println(previousNode.id + " " + previousNode.count);
         else
             System.out.println("0 0");
             
         }
         else{
              RBTree currentNoKey=previousKey(id);
             if(currentNoKey!=null)
                 System.out.println(currentNoKey.id + " " + currentNoKey.count);
             else
                 System.out.println("0 0");
             
         }
         
         
     }
             
    public static void main(String[] args){
        
        try {
            FileReader fread=new FileReader(args[0]);
            BufferedReader buffer=new BufferedReader(fread);
            int j=0;
            String Line=null;
            int EventNumber = Integer.parseInt(buffer.readLine());
        
            int idarray[]=new int[EventNumber];
            int countarray[]=new int[EventNumber];
            
            int i=0;
            while((Line=buffer.readLine())!=null){
               
                String[] details = Line.split("\\s+");
                String id = details[0];
                String count = details[1];
                int y = Integer.parseInt(id);
                int z = Integer.parseInt(count);
                idarray[i]=y;
                countarray[i]=z;
                i++;
                
             }
                    
             int height=(int)(Math.log(EventNumber)/Math.log(2));
             EventCounterRedBlackTree  tree=new EventCounterRedBlackTree ();//red black tree object
             root=tree.newbst(idarray,countarray,0,EventNumber-1,height);//creating a balanced bst from sorted array of event IDs
             
             BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
             String command;
             while((command=in.readLine())!=null){
                
                 
                 
                 
                 String[] commandarray = command.split("\\s+");
                 
               
                 switch(commandarray[0]){//switch case for the functions
                     case "increase":
                         
                         int inc1=Integer.parseInt(commandarray[1]);
                         int inc2=Integer.parseInt(commandarray[2]);
                         int increasedCount=tree.increase(inc1,inc2);
                         System.out.println(increasedCount);
                         break;
                     case "reduce":
                         int dec1=Integer.parseInt(commandarray[1]);
                         int dec2=Integer.parseInt(commandarray[2]);
                         int reducedCount=tree.reduce(dec1,dec2);
                         System.out.println(reducedCount);
                         break;
                     case "count":
                         int idCount=Integer.parseInt(commandarray[1]);
                         int eventCount=tree.count(idCount);
                         System.out.println(eventCount);
                         break;
                     case "inrange":
                         int id1=Integer.parseInt(commandarray[1]);
                         int id2=Integer.parseInt(commandarray[2]);
                         int inrangeSum=tree.inRange(id1,id2);
                         System.out.println(inrangeSum);
                         break;
                     case "next":
                         int nxtId=Integer.parseInt(commandarray[1]);
                         tree.next(nxtId);
                         break;
                     case "previous":
                         int preId=Integer.parseInt(commandarray[1]);
                         tree.previous(preId);
                         break;
                     case "quit":
                          System.exit(0);
                         
                         
                                 
                 }
                     
                 
             }
                     
             
             
                
   
                }
        catch (Exception e) {         
            e.printStackTrace();
        }
       
    }
    
    
    
}
