import java.io.*;
import java.util.*;
public class D23 {
    static PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        int part=2;
        if (part==1) {
            List<Integer> A=new ArrayList<>();
            for (int i=0; i<lines.get(0).length(); i++)
                A.add(lines.get(0).charAt(i)-'0');
            for (int rep=0; rep<100; rep++) {
                P$.println(A);
                List<Integer> t=new ArrayList<>();
                for (int i=3; i>0; i--)
                    t.add(A.remove(i));
                int low=Integer.MAX_VALUE, hi=Integer.MIN_VALUE;
                for (int i:A) {
                    low=Math.min(low,i);
                    hi=Math.max(hi,i);
                }
                int dest=A.get(0)-1;
                int s;
                while (true) {
                    s=-1;
                    for (int i=0; i<A.size(); i++)
                        if (A.get(i)==dest) {
                            s=i;
                            break;
                        }
                    if (s!=-1) break;
                    dest--;
                    if (dest<low) dest=hi;
                }
                for (int i:t)
                    A.add(s+1,i);
                P$.println(A);
                A.add(A.remove(0));
            }
            int s=-1;
            for (int i=0; i<A.size(); i++)
                if (A.get(i)==1) {
                    s=i;
                    break;
                }
            for (int i=0; i<A.size(); i++)
                P$.print(A.get((i+s)%A.size()));
        }
        else {
            //871369452,9,10,11,12,13,...
            //.........,9,10,11,12,...
            //...10,11,12,.....,9,13,14,15,...
            int N=1000000;
            int[] A=new int[N];
            for (int i=0; i<N; i++)
                if (i<lines.get(0).length())
                    A[i]=lines.get(0).charAt(i)-'0';
                else A[i]=i+1;
            int[] /*prev=new int[N+1], */next=new int[N+1];
            //map ea. VALUE to the value immediately to the right of it
            for (int i=0; i<N; i++) {
                next[A[i]]=A[(i+1)%N];
                //prev[A[i]]=A[mod(i-1,N)];
            }
            for (int rep=0, cur=A[0]; rep<10000000; rep++) {
                int dest=cur-1;
                if (dest<1) dest=N;
                int a=next[cur], b=next[a], c=next[b];
                while (dest==a||dest==b||dest==c) {
                    dest--;
                    if (dest<1) dest=N;
                }
                int d=next[c];
                next[cur]=d;
                next[c]=next[dest];
                next[dest]=a;
                cur=next[cur];
            }
            /*for (int v=1;;) {
                P$.print(v);
                v=next[v];
                if (v==1) break;
            }
            P$.println();*/
            P$.println((long)next[1]*next[next[1]]);
            /*int[] loc=new int[N+1];
            for (int i=0; i<N; i++) loc[A[i]]=i;
            int[] tmp=new int[N];
            for (int rep=0, cur=0; rep<10000000; rep++) {
                if (rep%10000==0) P$.println("rep="+rep);
                //P$.println(Arrays.toString(A));
                int dest=A[cur]-1;
                if (dest<1) dest=N;
                int l;
                while (true) {
                    if (dest!=A[(cur+1)%N]
                            &&dest!=A[(cur+2)%N]
                            &&dest!=A[(cur+3)%N]) {
                        l=loc[dest];
                        break;
                    }
                    dest--;
                    if (dest<1) dest=N;
                }
                if (mod(l-cur,N)<N/2) {
                    int s=mod(l-cur,N);
                    for (int d=0; d<s; d++)
                        tmp[d]=A[(cur+d+1)%N];
                    for (int d=0; d<s; d++) {
                        int t=(cur+d+1)%N;
                        A[t]=tmp[(d+3)%s];
                        loc[A[t]]=t;
                    }
                    P$.println(s);
                }
                else {
                    int s=mod(cur+3-l,N);
                    for (int d=0; d<s; d++)
                        tmp[d]=A[(l+d+1)%N];
                    for (int d=0; d<s; d++) {
                        int t=(l+d+1)%N;
                        A[t]=tmp[mod(d-3,s)];
                        loc[A[t]]=t;
                    }
                    cur=(cur+3)%N;
                    P$.println(s);
                }
                cur=(cur+1)%N;
            }
            for (int i=0; i<N; i++)
                if (loc[A[i]]!=i)
                    throw new RuntimeException(""+i);
            int s=-1;
            for (int i=0; i<N; i++)
                if (A[i]==1) {
                    s=i;
                    break;
                }
            P$.println(A[(s+1)%N]*A[(s+2)%N]);*/
        }
    }
    static int mod(int n, int k) {
        return (n%k+k)%k;
    }
}
