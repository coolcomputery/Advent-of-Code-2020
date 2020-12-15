import java.io.*;
import java.util.*;
public class D15 {
    private static final PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        List<Integer> A=new ArrayList<>();
        String[] info=lines.get(0).split(",");
        for (String s:info) A.add(Integer.parseInt(s));
        int part=2;
        P$.println("!!!PART "+part);
        if (part==1) {
            while (A.size()<2020) {
                int v=A.get(A.size()-1);
                int cnt=-1;
                for (int i=0; i<A.size()-1; i++)
                    if (A.get(i)==v) cnt=i;
                A.add(cnt>-1?A.size()-1-cnt:0);
            }
            P$.println(A.get(2019));
            P$.println(A);
        }
        else {
            int N=30000000;
            /*Map<Integer,Integer> I=new HashMap<>();
            for (int i=0; i<A.size()-1; i++)
                I.put(A.get(i),i);
            while (A.size()<N) {
                int v=A.get(A.size()-1);
                int nv=I.containsKey(v)?A.size()-1-I.get(v):0;
                A.add(nv);
                I.put(v,A.size()-2);
            }*/
            int[] I=new int[N];
            Arrays.fill(I,-1);
            for (int i=0; i<A.size()-1; i++)
                I[A.get(i)]=i;
            int len=A.size();
            int[] B=new int[N];
            for (int i=0; i<A.size(); i++)
                B[i]=A.get(i);
            while (len<N) {
                int v=B[len-1];
                int cnt=I[v];
                int nv=cnt>-1?len-1-cnt:0;
                B[len]=nv;
                len++;
                I[v]=Math.max(I[v],len-2);
            }
            P$.println(B[N-1]);
        }
    }
}
