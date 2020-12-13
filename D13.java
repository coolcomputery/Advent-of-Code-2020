import java.io.*;
import java.util.*;
public class D13 {
    private static final PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        int X=Integer.parseInt(lines.get(0));
        String[] info=lines.get(1).split(",");
        List<Integer> nums=new ArrayList<>();
        for (String s:info)
            if (!s.equals("x"))
                nums.add(Integer.parseInt(s));
        int N=nums.size();
        int part=2;
        P$.println("!!!PART "+part);
        if (part==1) {
            int id=-1;
            int ans=Integer.MAX_VALUE;
            for (int n:nums) {
                int t=(X+n-1)/n*n;
                if (t<ans) {
                    ans=t;
                    id=n;
                }
            }
            P$.println((ans-X)*id);
        }
        else {
            //find t s.t. t===-i mod A[i] for all i s.t. A[i]!="x"
            long t=0, lcm=nums.get(0);
            long[] A=new long[info.length];
            for (int i=0; i<A.length; i++)
                A[i]=info[i].equals("x")?-1:Integer.parseInt(info[i]);
            for (int i=1; i<info.length; i++)
            if (A[i]!=-1) {
                long a=A[i];
                P$.println(t+" "+a+" "+mod(-i,a)+" "+lcm);
                while (t%a!=mod(-i,a)) t+=lcm;
                lcm=lcm/gcd(lcm,a)*a;
            }
            P$.println(t);
        }
    }
    private static long mod(long n, long k) {
        return (n%k+k)%k;
    }
    private static long gcd(long a, long b) {
        if (a>b) return gcd(b,a);
        if (a==1) return 1;
        if (b%a==0) return a;
        return gcd(a,b%a);
    }
}
