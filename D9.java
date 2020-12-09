import java.io.*;
import java.util.*;
public class D9 {
    private static final PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        List<Long> nums=new ArrayList<>();
        for (String s:lines) nums.add(Long.parseLong(s));
        int part=2;
        P$.println("!!!PART "+part);
        if (part==1) {
            int K=25;
            for (int i=K; i<nums.size(); i++) {
                boolean good=false;
                for (int a=i-K; a<i; a++)
                    for (int b=i-K; b<a; b++)
                        if (nums.get(a)!=nums.get(b)
                                && nums.get(a)+nums.get(b)==nums.get(i)) {
                    good=true;
                    break;
                        }
                if (!good) {
                    P$.println(nums.get(i));
                    break;
                }
            }
        }
        else {
            long x=177777905;
            /*long[] P=new long[nums.size()];
            for (int i=0; i<nums.size(); i++)
                P[i]=nums.get(i)+(i>0?P[i-1]:0);
            for (int i=0; i<P.length; i++)
                for (int j=i+1; j<P.length; j++)
                    if ((P[j]-(i>0?P[i-1]:0))==x) {
                        long min=Long.MAX_VALUE, max=Long.MIN_VALUE;
                        for (int k=i; k<=j; k++) {
                            min=Math.min(min,nums.get(i));
                            max=Math.max(max,nums.get(i));
                        }
                        P$.println(min+max);
                        //break;
                    }*/
            for (int i=0; i<nums.size(); i++) {
                long sum=nums.get(i);
                for (int j=i+1; j<nums.size(); j++) {
                    sum+=nums.get(j);
                    if (sum>x) break;
                    if (sum==x) {
                        P$.println(i+" "+j);
                        long min=Long.MAX_VALUE, max=Long.MIN_VALUE;
                        for (int k=i; k<=j; k++) {
                            min=Math.min(min,nums.get(k));
                            max=Math.max(max,nums.get(k));
                        }
                        P$.println(min+max);
                    }
                }
            }
        }
    }
}
