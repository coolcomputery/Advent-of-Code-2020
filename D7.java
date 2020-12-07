import java.io.*;
import java.util.*;
public class D7 {
    //template code
    private static final PrintStream P$=System.out;
    private static int[] ints(String s, String d) {
        String[] t=s.split(d);
        int[] out=new int[t.length];
        for (int i=0; i<t.length; i++)
            out[i]=Integer.parseInt(t[i]);
        return out;
    }
    //actual code
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        P$.println(lines);
        relats=new HashMap<>();
        for (String l:lines) {
            String[] info=l.split(" bags contain ");
            String[] tos=info[1].substring(0,info[1].length()-1).split(", ");
            if (tos[0].equals("no other bags")) {
                relats.put(info[0],new BSet(new String[0],new int[0]));
            }
            else {
                String[] t = new String[tos.length];
                int[] am = new int[tos.length];
                for (int i = 0; i < t.length; i++) {
                    t[i] = tos[i].substring(tos[i].indexOf(" ")+1, tos[i].lastIndexOf(" "));
                    am[i] = Integer.parseInt(tos[i].substring(0, tos[i].indexOf(" ")));
                }
                relats.put(info[0], new BSet(t, am));
            }
        }
        //for (String b:relats.keySet()) P$.println(b+"-->"+Arrays.toString(relats.get(b).amt)+"*"+Arrays.toString(relats.get(b).b));
        graph=new HashMap<>();
        for (String b:relats.keySet())
            for (String c:relats.get(b).b) {
                adde(graph,c,b);
            }
        for (String b:relats.keySet())
            if (!graph.containsKey(b))
                graph.put(b,new HashSet<>());
        int part=2;
        if (part==1) {
            P$.println(graph);
            goods=new HashSet<>();
            dfs("shiny gold");
            P$.println(goods.size()-1);
            P$.println(goods);
        }
        else {
            //topological ordering
            List<String> ord=new ArrayList<>();
            Map<String,Set<String>> rg=new HashMap<>();
            for (String b:relats.keySet())
                rg.put(b,new HashSet<>());
            for (String b:relats.keySet())
                for (String c:relats.get(b).b)
                    adde(rg,b,c);
            for (int rep=0; rep<relats.keySet().size(); rep++) {
                Set<String> topop=new HashSet<>();
                for (String s:rg.keySet())
                    if (rg.get(s).size()==0) {
                        //remove s
                        ord.add(s);
                        for (String n:graph.get(s))
                            rg.get(n).remove(s);
                        topop.add(s);
                        graph.remove(s);
                    }
                for (String s:topop) rg.remove(s);
            }
            Map<String,Long> cost=new HashMap<>();
            for (String s:ord) {
                //P$.println(s);
                long c=1;
                for (int i=0; i<relats.get(s).b.length; i++)
                    c+=cost.get(relats.get(s).b[i])*(long)relats.get(s).amt[i];
                cost.put(s,c);
            }
            P$.println(cost.get("shiny gold")-1);
        }
    }
    private static void adde(Map<String,Set<String>> graph, String a, String b) {
        if (!graph.containsKey(a)) graph.put(a,new HashSet<>());
        graph.get(a).add(b);
    }
    private static Set<String> goods;
    private static Map<String,Set<String>> graph;
    private static Map<String, BSet> relats;
    private static void dfs(String type) {
        if (goods.contains(type)) return;
        goods.add(type);
        if (graph.get(type)!=null)
        for (String s:graph.get(type))
            dfs(s);
    }
    private static class BSet {
        String[] b;
        int[] amt;
        public BSet(String[] b, int[] amt) {
            this.b=b;
            this.amt=amt;
        }
    }
}
