import java.io.*;
import java.util.*;
public class D8 {
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        System.out.println(lines);
        List<String> coms=new ArrayList<>();
        List<Integer> nums=new ArrayList<>();
        for (String s:lines) {
            String[] info=s.split(" ");
            coms.add(info[0]);
            nums.add(Integer.parseInt(info[1]));
        }
        System.out.println(coms);
        System.out.println(nums);
        int[] freq=new int[lines.size()];
        int part=2;
        if (part==1) {
            int acc=0;
            for (int i=0; freq[i]==0;) {
                freq[i]++;
                if (coms.get(i).equals("jmp")) {
                    i+=nums.get(i);
                    continue;
                }
                if (coms.get(i).equals("acc"))
                    acc+=nums.get(i);
                i++;
            }
            System.out.println(acc);
        }
        else {
            int N=lines.size();
            for (int i=0; i<N; i++)
                if (coms.get(i).equals("jmp")) {
                    coms.set(i,"nop");
                    int[] r=ret(coms,nums);
                    if (r[0]==1) System.out.println(r[1]);
                    coms.set(i,"jmp");
                }
                else if (coms.get(i).equals("nop")) {
                    coms.set(i,"jmp");
                    int[] r=ret(coms,nums);
                    if (r[0]==1) System.out.println(r[1]);
                    coms.set(i,"nop");
                }
        }
    }
    private static int[] ret(List<String> coms, List<Integer> nums) {
        int[] freq=new int[coms.size()];
        int acc=0, i=0;
        for (; i<freq.length && freq[i]==0;) {
            freq[i]++;
            if (coms.get(i).equals("jmp")) {
                i+=nums.get(i);
                continue;
            }
            if (coms.get(i).equals("acc"))
                acc+=nums.get(i);
            i++;
        }
        return new int[] {i>=freq.length?1:0,acc};
    }
}
