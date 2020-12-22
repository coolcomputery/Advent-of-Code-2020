import java.io.*;
import java.util.*;
public class D22 {
    private static final PrintStream P$=System.out;
    static int scr(List<Integer> a) {
        int out=0;
        for (int i=1; i<=a.size(); i++)
            out+=i*a.get(a.size()-i);
        return out;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        int N=0;
        while (lines.get(N).length()>0)
            N++;
        List<Integer> ca=new ArrayList<>(), cb=new ArrayList<>();
        for (int i=1; i<N; i++)
            ca.add(Integer.parseInt(lines.get(i)));
        for (int i=N+2; i<=2*N; i++)
            cb.add(Integer.parseInt(lines.get(i)));
        P$.println(ca);
        P$.println(cb);
        int part=2;
        if (part==1) {
            while (ca.size()>0 && cb.size()>0) {
                int va=ca.remove(0), vb=cb.remove(0);
                if (va>vb) {
                    ca.add(va);
                    ca.add(vb);
                }
                else {
                    cb.add(vb);
                    cb.add(va);
                }
            }
            P$.println(scr(ca.size()>0?ca:cb));
        }
        else {
            cnt=0;
            depth=0;
            /*winning=new HashSet<>();
            losing=new HashSet<>();*/
            p1wins(ca,cb,true);
            P$.println(scr(ca.size()>0?ca:cb));
        }
    }
    //static Set<String> winning, losing;
    static long cnt, depth;
    static boolean p1wins(List<Integer> ca, List<Integer> cb, boolean init) {
        cnt++;
        if (cnt%1000==0)
            P$.println("cnt="+cnt+"; "+depth);//+" "+winning.size()+" "+losing.size());
        //P$.println("!!! "+ca+" "+cb);
        Set<String> states=new HashSet<>();
        boolean prev1=false;
        boolean out;
        while (true) {
            StringBuilder tmp=new StringBuilder();
            tmp.append(ca).append(cb);
            String str=tmp.toString();
            /*if (losing.contains(str)) {
                out=false;
                if (!init) break;
            }*/
            if (/*winning.contains(str)||*/states.contains(str)) {
                /*out=true;
                if (!init) break;*/
                return true;
            }
            states.add(str);
            //P$.println("--- "+ca+" "+cb);
            int va=ca.remove(0), vb=cb.remove(0);
            if (ca.size()>=va && cb.size()>=vb) {
                List<Integer> nca, ncb;
                nca=new ArrayList<>();
                for (int i=0; i<va; i++) //<-- "(the quantity of cards copied is equal to the number on the card they drew to trigger the sub-game)"
                    nca.add(ca.get(i));
                ncb=new ArrayList<>();
                for (int i=0; i<vb; i++)
                    ncb.add(cb.get(i));
                depth++;
                prev1=p1wins(nca,ncb,false);
                depth--;
            }
            else
                prev1=va>vb;
            if (prev1) {
                ca.add(va);
                ca.add(vb);
            }
            else {
                cb.add(vb);
                cb.add(va);
            }
            if (ca.size()==0||cb.size()==0) {
                out=ca.size()>0;
                break;
            }
        }
        //for (String s:states) (out?winning:losing).add(s);
        return out;
    }
}
