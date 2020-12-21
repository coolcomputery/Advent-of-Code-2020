import java.io.*;
import java.util.*;
public class D21 {
    private static final PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        List<Set<String>> A=new ArrayList<>(), B=new ArrayList<>();
        for (String line:lines) {
            String[] info=line.split("contains ");
            String[] a=info[0].substring(0,info[0].length()-2).split(" ");
            Set<String> sa=new HashSet<>();
            for (String s:a) sa.add(s);
            String[] b=info[1].substring(0,info[1].length()-1).split(", ");
            Set<String> sb=new HashSet<>();
            for (String s:b) sb.add(s);
            A.add(sa);
            B.add(sb);
        }
        for (int i=0; i<A.size(); i++)
            P$.println(A.get(i)+" "+B.get(i));
        int N=A.size();
        int cnt=0;
        Set<String> ings=new HashSet<>(), alls=new HashSet<>();
        for (Set<String> a:A) ings.addAll(a);
        for (Set<String> b:B) alls.addAll(b);
        Set<String> inerts=new HashSet<>();
        for (String s:ings) {
            boolean okay=false;
            for (String al:alls) {
                //assume s contains allergen al
                boolean good=true;
                for (int i=0; i<N; i++)
                if (A.get(i).contains(s))
                for (String s1:A.get(i))
                if (!s1.equals(s))
                for (int j=0; j<N; j++)
                if (A.get(j).contains(s1)
                        &&!A.get(j).contains(s)
                        &&B.get(j).contains(al))
                    good=false;
                if (good) okay=true;
            }
            if (!okay) {
                for (int i=0; i<N; i++)
                    if (A.get(i).contains(s))
                        cnt++;
                inerts.add(s);
            }
        }
        P$.println(cnt);
        //part 2
        ings.removeAll(inerts);
        Map<String,Set<String>> possibles=new HashMap<>();
        for (String s:ings) {
            possibles.put(s,new HashSet<>());
            for (String al:alls) {
                //assume s contains allergen al
                boolean good=true;
                for (int i=0; i<N; i++)
                    if (A.get(i).contains(s))
                        for (String s1:A.get(i))
                            if (!s1.equals(s))
                                for (int j=0; j<N; j++)
                                    if (A.get(j).contains(s1)
                                            &&!A.get(j).contains(s)
                                            &&B.get(j).contains(al))
                                        good=false;
                if (good) possibles.get(s).add(al);
            }
        }
        Map<String,String> map=new HashMap<>();
        while (true) {
            boolean added=false;
            for (String s:possibles.keySet())
                if (possibles.get(s).size()==1) {
                    String a=possibles.get(s).iterator().next();
                    map.put(s,a);
                    added=true;
                    for (String s1:possibles.keySet())
                        possibles.get(s1).remove(a);
                }
            if (!added) break;
        }
        String[] strs=new String[map.size()];
        {
            int i=0;
            for (String s:map.keySet())
                strs[i++]=s;
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return map.get(o1).compareTo(map.get(o2));
            }
        });
        for (String s:strs)
            P$.print(","+s);
    }
}
