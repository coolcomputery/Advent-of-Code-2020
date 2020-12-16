import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class D16 {
    private static final PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        int K=0;
        while (lines.get(K).length()>0) K++;
        P$.println("K="+K);
        int[][] rngs=new int[K][];
        for (int i=0; i<K; i++) {
            String s=lines.get(i);
            s=s.substring(s.indexOf(": ")+2);
            String[] info=s.split(" or ");
            rngs[i]=new int[4];
            for (int g=0; g<2; g++) {
                String[] sp=info[g].split("-");
                for (int k=0; k<2; k++)
                    rngs[i][g*2+k]=Integer.parseInt(sp[k]);
            }
        }
        int N0=0;
        while (!lines.get(N0).equals("nearby tickets:"))
            N0++;
        N0++;
        int[][] tckts=new int[lines.size()-N0][];
        for (int i=N0; i<lines.size(); i++) {
            String[] info=lines.get(i).split(",");
            tckts[i-N0]=new int[info.length];
            for (int j=0; j<info.length; j++)
                tckts[i-N0][j]=Integer.parseInt(info[j]);
        }
        int part=2;
        P$.println("!!!PART "+part);
        if (part==1) {
            int out=0;
            for (int[] tckt:tckts)
                for (int v:tckt) {
                    boolean good=false;
                    for (int[] r:rngs)
                        if ((r[0]<=v && v<=r[1])
                            ||(r[2]<=v && v<=r[3]))
                            good=true;
                    if (!good) out+=v;
                }
            P$.println(out);
        }
        else {
            List<int[]> gtckts=new ArrayList<>();
            for (int[] tckt:tckts) {
                boolean valid=true;
                for (int v:tckt) {
                    boolean good=false;
                    for (int[] r:rngs)
                        if ((r[0]<=v && v<=r[1])
                                ||(r[2]<=v && v<=r[3]))
                            good=true;
                    if (!good) {
                        valid=false;
                        break;
                    }
                }
                if (valid) gtckts.add(tckt);
            }
            int[] type=new int[K];
            boolean[][] fit=new boolean[K][K];
            for (int k=0; k<K; k++) {
                for (int t=0; t<K; t++) {
                    boolean good=true;
                    for (int ti=0; ti<gtckts.size() && good; ti++)
                        if (!valid(rngs[t],gtckts.get(ti)[k]))
                            good=false;
                    fit[k][t]=good;
                }
            }
            while (true) {
                boolean added=false;
                for (int k=0; k<K; k++) {
                    int bt=-1;
                    for (int t=0; t<K; t++)
                        if (fit[k][t]) {
                            if (bt==-1) bt=t;
                            else bt=-2;
                        }
                    if (bt>-1) {
                        type[k]=bt;
                        added=true;
                        Arrays.fill(fit[k],false);
                        for (int i=0; i<K; i++)
                            fit[i][bt]=false;
                    }
                }
                if (!added) break;
            }
            boolean[] found=new boolean[K];
            for (int k=0; k<K; k++)
                found[type[k]]=true;
            for (int i=0; i<K; i++)
                if (!found[i])
                    throw new RuntimeException();
            int[] yt=new int[K];
            String[] info=lines.get(K+2).split(",");
            for (int i=0; i<K; i++)
                yt[i]=Integer.parseInt(info[i]);
            P$.println(Arrays.toString(yt));
            BigInteger out=BigInteger.ONE;
            String D="departure";
            for (int k=0; k<K; k++) {
                if (lines.get(type[k]).substring(0,D.length()).equals(D)) {
                    P$.println(lines.get(type[k])+" "+yt[k]);
                    out=out.multiply(new BigInteger(""+yt[k]));
                }
            }
            P$.println(out);
            for (int k=0; k<K; k++) {
                boolean good=true;
                for (int ti=0; ti<gtckts.size(); ti++)
                    if (!valid(rngs[type[k]],gtckts.get(ti)[k]))
                        good=false;
                if (!good) throw new RuntimeException();
            }
        }
    }
    private static boolean valid(int[] r, int v) {
        return (r[0]<=v && v<=r[1])
                ||(r[2]<=v && v<=r[3]);
    }
}
