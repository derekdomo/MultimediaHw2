import java.util.*;
import java.io.*;
public class ForestFire{
    public static void main(String[] args) throws Exception{
        double[] p = new double[17];
        int optimal = -1;
        int ind=-1;
        int[] BT = new int[17];
        for (int i=0; i<17; i++)
            p[i]=0.1+0.05*i;
        for (int i=0; i<17; i++) {
            int max=-1;
            System.out.println("\nProbility: "+String.format("%.2f",p[i])+" :");
            for (int j=0; j<5; j++){
                int temp=forestFireSimulate(p[i], false, false);
                if (max<temp){
                    max=temp;
                }
                System.out.print(j+" round: "+temp+",\t");
            }
            if (optimal<max) {
                ind=i;
                optimal=max;
            }
            BT[i]=max;
        }
        File io=new File("Q5.2");
        if (!io.exists())
            io.createNewFile();
        FileWriter resultFile=new FileWriter(io);
        PrintWriter myFile=new PrintWriter(resultFile);
        String content="";
        for (int i=0; i<17; i++) {
            content=content+String.format("%.2f",p[i])+","+String.format("%d",BT[i])+"\n";
        }
        myFile.print(content);
        resultFile.close();
        System.out.println("\n\nThe critical density is:"+String.format("%.2f",p[ind]));
        forestFireSimulate(p[ind],true,false);
        System.out.println("\n\nThe density is: 0.10");
        forestFireSimulate(0.10,true,false);
        System.out.println("\n\nThe density is: 0.90");
        forestFireSimulate(0.90,true,false);
        
        forestFireSimulate(p[ind],false,true);
        forestFireSimulate(0.10,false,true);
        forestFireSimulate(0.90,false,true);
    }
	public static int forestFireSimulate(double p, boolean print, boolean connected) throws Exception {
        Random r = new Random();
        ArrayList<Tree> forest = new ArrayList<Tree>();
        ArrayList<Tree> unburn = new ArrayList<Tree>();
        for (int i=0;i<50;i++){
            if (print) {
                System.out.println();
            }
            for (int j=0;j<50;j++) {
                if (r.nextDouble()<p){
                    if (print) {
                        System.out.print("1 ");
                    }
                    Tree t = new Tree(i,j);
                    forest.add(t);
                    unburn.add(t);
                }
                else {
                    if (print) 
                        System.out.print("0 ");
                }
            }
        }
        int count=0;
        if (connected) {
            String filename="cluster";
            if (p==0.10)
                filename=filename+"0.1.dat";
            else if (p==0.90)
                filename=filename+"0.9.dat";
            else
                filename=filename+"critical.dat";
            File io=new File(filename);
            if (!io.exists()) 
                io.createNewFile();
            FileWriter resultFile=new FileWriter(io);
            PrintWriter myFile=new PrintWriter(resultFile);
            String content="";
            Map<Integer,Integer> cluster = new HashMap<Integer,Integer>();
            while (unburn.size()!=0){
                int size=unburn.size();
                int lighting = r.nextInt(unburn.size());
                unburn.get(lighting).fire=1;
                ArrayList<Tree> burning = new ArrayList<Tree>();
                burning.add(unburn.get(lighting));
                unburn.remove(unburn.get(lighting));
                while (burning.size()!=0) {
                     ArrayList<Tree> going = new ArrayList<Tree>();
                     for (int j=0; j<burning.size(); j++){
                         for (int i=0; i<forest.size(); i++) {
                             if (forest.get(i).fire == 0) {
                                 Tree t = forest.get(i);
                                 Tree b = burning.get(j);
                                 if (neighbor(t,b)) {
                                     going.add(t);
                                     t.fire=1;
                                     unburn.remove(t);
                                 }
                             }
                         }
                     }
                 count++;
                 burning=going;
                }
                if (cluster.containsKey(size-unburn.size())) {
                    cluster.put(size-unburn.size(), cluster.get(size-unburn.size())+1);
                }
                else
                    cluster.put(size-unburn.size(),1);
                size=unburn.size();
            }
            Iterator iter = cluster.entrySet().iterator();
            while(iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                content=content+entry.getKey().toString()+","+entry.getValue().toString()+"\n";
            }
            myFile.print(content);
            resultFile.close();
            return 1;
        }
        int lighting = r.nextInt(unburn.size());
        unburn.get(lighting).fire=1;
        ArrayList<Tree> burning = new ArrayList<Tree>();
        burning.add(unburn.get(lighting));
        unburn.remove(unburn.get(lighting));
        while (burning.size()!=0) {
            ArrayList<Tree> going = new ArrayList<Tree>();
            for (int j=0; j<burning.size(); j++){
                for (int i=0; i<forest.size(); i++) {
                    if (forest.get(i).fire == 0) {
                        Tree t = forest.get(i);
                        Tree b = burning.get(j);
                        if (neighbor(t,b)) {
                            going.add(t);
                            t.fire=1;
                            unburn.remove(t);
                        }
                    }
                }
            }
            count++;
            burning=going;
        }
        return count;
	}
    public static boolean neighbor(Tree t, Tree b){
        if (t.x==b.x&&t.y==b.y+1)
            return true;
        if (t.x==b.x&&t.y==b.y-1)
            return true;
        if (t.x==b.x+1&&t.y==b.y)
            return true;
        if (t.x==b.x-1&&t.y==b.y)
            return true;
        return false;
    }
}
class Tree{
    public int x;
    public int y;
    public int fire;
    public Tree(int x, int y) {
        this.x=x;
        this.y=y;
        this.fire=0;
    }
}
