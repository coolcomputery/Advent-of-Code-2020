import java.io.*;
import java.util.*;
public class D25 {
    static PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        int A=Integer.parseInt(lines.get(0));
        int B=Integer.parseInt(lines.get(1));
        int part=1;
        if (part==1) {
            long cl=log(7,A,20201227);
            long dl=log(7,B,20201227);
            P$.println(exp(B,cl,20201227));
        }
        else { //no problem to solve for part 2
        }
    }
    static long exp(long n, long k, long m) {
        if (k==0) return 1;
        if (k==1) return n%m;
        long h=exp(n,k/2,m);
        h=(h*h)%m;
        return k%2==0?h:(h*n)%m;
    }
    static long log(long n, long p, long m) {
        //find i s.t. n^i mod m == p
        long out=0, v=1;
        while (v%m!=p%m) {
            out++;
            v=(v*n)%m;
        }
        return out;
    }
}
