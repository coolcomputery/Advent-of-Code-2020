import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class D18 {
    private static final PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        int part=2;
        P$.println("!!!PART "+part);
        BigInteger out=BigInteger.ZERO;
        for (String line:lines) {
            StringBuilder s=new StringBuilder();
            for (int i=0; i<line.length(); i++)
                if (line.charAt(i)!=' ')
                    s.append(line.charAt(i));
            //P$.println(s);
            int[] nesting=new int[s.length()];
            for (int i=0, v=0; i<s.length(); i++) {
                char c=s.charAt(i);
                if (c=='(') {
                    v++;
                    nesting[i]=v;
                }
                else if (c==')') {
                    nesting[i]=v;
                    v--;
                }
            }
            //P$.println(Arrays.toString(nesting));
            out=out.add(part==1?val(s.toString(),nesting,0,s.length()-1):
                    val2(s.toString(),nesting,0,s.length()-1));
        }
        P$.println(out);
    }
    private static BigInteger com(BigInteger a, BigInteger b, char op) {
        return op=='+'?(a.add(b)):a.multiply(b);
    }
    private static BigInteger val(String s, int[] nesting, int i, int j) {
        BigInteger out;
        int k;
        if (s.charAt(i)=='(') {
            int en=i+1;
            while (nesting[en]!=nesting[i]) en++;
            out=val(s,nesting,i+1,en-1);
            k=en+1;
        }
        else {
            out=new BigInteger(""+(s.charAt(i)-'0'));
            k=i+1;
        }
        for (; k<=j;) {
            char op=s.charAt(k);
            if (s.charAt(k+1)=='(') {
                int en=k+2;
                while (nesting[en]!=nesting[k+1])
                    en++;
                out=com(out,val(s,nesting,k+2,en-1),op);
                k=en+1;
            }
            else {
                out=com(out,new BigInteger(""+(s.charAt(k+1)-'0')),op);
                k+=2;
            }
        }
        //P$.println(i+" "+j+"-->"+out);
        return out;
    }
    private static BigInteger val2(String s, int[] nesting, int i, int j) {
        List<BigInteger> nums=new ArrayList<>();
        int k;
        if (s.charAt(i)=='(') {
            int en=i+1;
            while (nesting[en]!=nesting[i]) en++;
            nums.add(val2(s,nesting,i+1,en-1));
            k=en+1;
        }
        else {
            nums.add(new BigInteger(""+(s.charAt(i)-'0')));
            k=i+1;
        }
        List<Character> ops=new ArrayList<>();
        ops.add(' ');
        for (; k<=j;) {
            char op=s.charAt(k);
            if (s.charAt(k+1)=='(') {
                int en=k+2;
                while (nesting[en]!=nesting[k+1])
                    en++;
                nums.add(val2(s,nesting,k+2,en-1));
                k=en+1;
            }
            else {
                nums.add(new BigInteger(""+(s.charAt(k+1)-'0')));
                k+=2;
            }
            ops.add(op);
        }
        for (int $=0; $<2; $++)
        for (int x=ops.size()-1; x>0; x--)
        if (ops.get(x)==($==0?'+':'*')) {
            nums.set(x-1,com(nums.get(x-1),nums.get(x),ops.get(x)));
            nums.remove(x);
            ops.remove(x);
        }
        return nums.get(0);
    }
}
