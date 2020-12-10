import java.io.*;
import java.util.*;
public class D10 {
    private static final PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        int[] nums=new int[lines.size()];
        for (int i=0; i<nums.length; i++)
            nums[i]=Integer.parseInt(lines.get(i));
        int part=2;
        int N=lines.size();
        int d=0;
        for (int v:nums) d=Math.max(d,v);
        d+=3;
        Arrays.sort(nums);
        P$.println("!!!PART "+part);
        if (part==1) {
            int d1=0, d3=0;
            for (int i=-1; i<N; i++) {
                int a=(i==-1?0:nums[i]);
                int b=(i==N-1?d:nums[i+1]);
                if (b-a==1) d1++;
                if (b-a==3) d3++;
            }
            P$.println(d1*d3);
        }
        else {
            int[] V=new int[N+2];
            V[0]=0;
            for (int i=0; i<N; i++)
                V[i+1]=nums[i];
            V[N+1]=d;
            P$.println(Arrays.toString(V));
            long[] dp=new long[N+2];
            dp[0]=1;
            for (int i=1; i<=N+1; i++) {
                dp[i]=0;
                for (int j=i-1; j>-1 && V[j]>=V[i]-3; j--)
                    dp[i]+=dp[j];
            }
            P$.println(dp[N+1]);
        }
    }
}
