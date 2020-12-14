import java.io.*;
import java.util.*;
public class D14 {
    private static final PrintStream P$=System.out;
    private static boolean pref(String s, String p) {
        return s.length()>p.length() && s.substring(0,p.length()).equals(p);
    }
    private static long masked(long v, String m) {
        long out=v;
        for (int b=0; b<36; b++) {
            char c=m.charAt(m.length()-1-b);
            if (c!='X') {
                //P$.println(b+" "+c);
                if (c=='0')
                    out&=~(1L<<b);
                else
                    out|=1L<<b;
            }
        }
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
        int part=2;
        P$.println("!!!PART "+part);
        if (part==1) {
            String pmask="mask = ";
            String mask="";
            Map<Long,Long> mem=new HashMap<>();
            for (String l:lines) {
                if (pref(l,pmask))
                    mask=l.substring(pmask.length());
                else {
                    long idx=Long.parseLong(l.substring("mem[".length(),l.indexOf(']')));
                    long v=Long.parseLong(l.substring(l.lastIndexOf(' ')+1));
                    //P$.println(mask+" "+v+" "+masked(v,mask));
                    mem.put(idx,masked(v,mask));
                }
            }
            long out=0;
            for (long v:mem.keySet())
                out+=mem.get(v);
            P$.println(out);
        }
        else {
            String pmask="mask = ";
            String mask="";
            Map<Long,Long> mem=new HashMap<>();
            for (String l:lines) {
                if (pref(l,pmask))
                    mask=l.substring(pmask.length());
                else {
                    String idx=Long.toBinaryString(Long.parseLong(l.substring("mem[".length(),l.indexOf(']'))));
                    while (idx.length()<36) idx="0"+idx;
                    long v=Long.parseLong(l.substring(l.lastIndexOf(' ')+1));
                    String m="";
                    for (int i=0; i<36; i++) {
                        char c=mask.charAt(i);
                        m+=c=='0'?idx.charAt(i):c=='1'?"1":"X";
                    }
                    int xcnt=0;
                    for (int i=0; i<36; i++)
                        if (m.charAt(i)=='X') xcnt++;
                    for (long att=0; att<(1L<<xcnt); att++) {
                        long adr=0;
                        for (int b=0, i=0; b<36; b++)
                            if (m.charAt(35-b)=='X') {
                                if (((att>>i)&1L)!=0) adr|=1L<<b;
                                i++;
                            }
                            else if (m.charAt(35-b)=='1')
                                adr|=1L<<b;
                        mem.put(adr,v);
                    }
                }
            }
            long out=0;
            for (long v:mem.keySet())
                out+=mem.get(v);
            P$.println(out);
        }
    }
}
