import java.io.*;
import java.util.*;
public class D19 {
    private static final PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        int R=0;
        while (lines.get(R).length()>0) R++;
        rules=new int[R][][];
        type=new char[R];
        for (int i=0; i<R; i++) {
            String s=lines.get(i);
            int t=s.indexOf(": ");
            int r=Integer.parseInt(s.substring(0,t));
            s=s.substring(t+2);
            if (s.charAt(0)=='\"') {
                type[r]=s.charAt(1);
                P$.println(type[r]);
                continue;
            }
            t=s.indexOf('|');
            List<String[]> as=new ArrayList<>();
            if (t==-1) as.add(s.split(" "));
            else {
                as.add(s.substring(0,t-1).split(" "));
                as.add(s.substring(t+2).split(" "));
            }
            rules[r]=new int[as.size()][];
            for (int ri=0; ri<as.size(); ri++) {
                String[] a=as.get(ri);
                rules[r][ri]=new int[a.length];
                for (int j=0; j<a.length; j++)
                    rules[r][ri][j]=Integer.parseInt(a[j]);
            }
            for (int[] a:rules[r])
                P$.print(Arrays.toString(a)+" ");
            P$.println();
        }
        int out=0;
        for (int i=R+1; i<lines.size(); i++) {
            String s=lines.get(i);
            int[][][] dp=new int[s.length()][s.length()][200];
            for (int[][] p:dp)
                for (int[] r:p) Arrays.fill(r,-1);
            if (sat(s,0,s.length()-1,0,dp))
                out++;
        }
        P$.println(out);
    }
    private static int[][][] rules;
    private static char[] type;
    private static boolean sat(String s, int l, int r, int ri, int[][][] dp) {
        if (dp[l][r][ri]!=-1) return dp[l][r][ri]==1;
        if (type[ri]=='a'||type[ri]=='b')
            return l==r&&s.charAt(l)==type[ri];
        boolean out=false;
        for (int[] seq:rules[ri]) {
            if (seq.length==1)
                out=out||sat(s,l,r,seq[0],dp);
            else if (seq.length==2) {
                for (int k=l; k<r; k++)
                    out=out||(sat(s,l,k,seq[0],dp)&&sat(s,k+1,r,seq[1],dp));
            }
            else {
                for (int x=l; x<r; x++)
                    for (int y=x+1; y<r; y++)
                        out=out||(sat(s,l,x,seq[0],dp)&&sat(s,x+1,y,seq[1],dp)&&sat(s,y+1,r,seq[2],dp));
            }
        }
        dp[l][r][ri]=out?1:0;
        return out;
    }
}
