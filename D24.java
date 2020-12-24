import java.io.*;
import java.util.*;
public class D24 {
    static PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        List<int[]> coords=new ArrayList<>();
        //e=<1,0>, ne=<0,1>, nw=<-1,1>, w=<-1,0>, se=<1,-1>, sw=<0,-1>
        Map<String,int[]> vecs=new HashMap<>();
        vecs.put("e",new int[] {1,0});
        vecs.put("w",new int[] {-1,0});
        vecs.put("ne",new int[] {0,1});
        vecs.put("sw",new int[] {0,-1});
        vecs.put("nw",new int[] {-1,1});
        vecs.put("se",new int[] {1,-1});
        for (String s:lines) {
            int x=0, y=0;
            for (int i=0; i<s.length();) {
                char c=s.charAt(i);
                String d=c=='n'||c=='s'?s.substring(i,i+2):s.substring(i,i+1);
                int[] v=vecs.get(d);
                x+=v[0];
                y+=v[1];
                i+=d.length();
            }
            coords.add(new int[] {x,y});
        }
        //part 1
        Set<String> ans=new HashSet<>();
        for (int[] c:coords) {
            String s=c[0]+","+c[1];
            if (ans.contains(s))
                ans.remove(s);
            else
                ans.add(s);
        }
        P$.println(ans.size());
        //part 2
        List<int[]> disps=new ArrayList<>();
        for (String s:vecs.keySet())
            disps.add(vecs.get(s));
        for (int rep=0; rep<100; rep++) {
            Set<String> salives=new HashSet<>(),
                    nalives=new HashSet<>();
            for (String s:ans) {
                int[] c=new int[2];
                String[] info=s.split(",");
                c[0]=Integer.parseInt(info[0]);
                c[1]=Integer.parseInt(info[1]);
                int cnt=0;
                for (int[] d:disps) {
                    int nx=c[0]+d[0], ny=c[1]+d[1];
                    if (ans.contains(nx+","+ny))
                        cnt++;
                }
                if (cnt!=0&&cnt<=2)
                    salives.add(s);
                for (int[] d:disps) {
                    int nx=c[0]+d[0], ny=c[1]+d[1];
                    if (!ans.contains(nx+","+ny)) {
                        int ncnt=0;
                        for (int[] d1:disps) {
                            int px=nx+d1[0], py=ny+d1[1];
                            if (ans.contains(px+","+py))
                                ncnt++;
                        }
                        if (ncnt==2)
                            nalives.add(nx+","+ny);
                    }
                }
            }
            ans.clear();
            ans.addAll(salives);
            ans.addAll(nalives);
        }
        P$.println(ans.size());
    }
}
