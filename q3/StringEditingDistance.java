public class StringEditingDistance {
    static int ins=1;
    static int del=1;
    static int sub=1;
    static int[][] matrix;
	public static void main(String[] args) {
        ins=Integer.valueOf(args[0]);
        sub=Integer.valueOf(args[1]);
        del=Integer.valueOf(args[2]);
        System.out.println("Insertion Cost: "+args[0]);
        System.out.println("Substitution Cost: "+args[1]);
        System.out.println("Deletion Cost: "+args[2]);
        String s1="iowaisinmidwest";
        String s2="iovaisinwideeast";
        System.out.println("String1: "+s1+", String2: "+s2);
        D(s1, s2);
        backpath(s1,s2);
	}
    public static int D(String s1, String s2) {

        matrix = new int[s1.length()+1][s2.length()+1];
        for (int i=0; i<s1.length()+1; i++) {
            matrix[i][0]=i;
        }
        for (int i=0; i<s2.length()+1; i++) {
            matrix[0][i]=i;
        }
        for (int i=1; i<s1.length()+1; i++) {
            for (int j=1; j<s2.length()+1; j++) {
                if (s1.charAt(i-1)==s2.charAt(j-1)){
                    matrix[i][j]=matrix[i-1][j-1];
                }
                else {
                    matrix[i][j]=min(ins+matrix[i][j-1],del+matrix[i-1][j],sub+matrix[i-1][j-1]);
                }
            }
        }
        System.out.println("Matrix:");
        for (int i=0; i<s1.length()+1; i++) {
            System.out.print(matrix[i][0]);
            for (int j=1; j<s2.length()+1; j++) {
                System.out.print("\t"+matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("String Editing Distance: "+matrix[s1.length()][s2.length()]);
        return matrix[s1.length()][s2.length()];
    }
    public static void backpath(String s1, String s2) {
        int i=s1.length();
        int j=s2.length();
        String path="";
        while(i!=0&&j!=0) {
            if (s1.charAt(i-1)==s2.charAt(j-1)) {
                i--;
                j--;
                path="_, "+path;
            }
            else {
                if (matrix[i][j]==sub+matrix[i-1][j-1]) {
                    path="S("+s1.charAt(i-1)+", "+s2.charAt(j-1)+"), "+path;
                    i--;
                    j--;
                }
               else  if (matrix[i][j]==ins+matrix[i][j-1]) {
                    path="I("+s2.charAt(j-1)+"), "+path;
                    j--;
                }
                else if (matrix[i][j]==del+matrix[i-1][j]) {
                    path="D("+s1.charAt(i-1)+"), "+path;
                    i--;
                }
            }
        }
        System.out.println("String editing path:");
        System.out.println(path.substring(0,path.length()-2));
    }
    public static int min(int a, int b, int c) {
        if (a>b){
            if (b>c)
                return c;
            else
                return b;
        }
        else{
            if (a>c)
                return c;
            else
                return a;
        }
    }
}
