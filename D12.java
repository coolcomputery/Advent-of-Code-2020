import java.io.*;
import java.util.*;
public class D12 {
    private static final PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        int N=lines.size();
        char[] com=new char[N];
        int[] num=new int[N];
        for (int i=0; i<N; i++) {
            com[i]=lines.get(i).charAt(0);
            num[i]=Integer.parseInt(lines.get(i).substring(1));
        }
        int part=2;
        P$.println("!!!PART "+part);
        if (part==1) {
            double x=0, y=0;
            int dir=0;
            for (int i=0; i<N; i++) {
                char c=com[i];
                int n=num[i];
                if (c=='N')
                    y+=n;
                else if (c=='S') y-=n;
                else if (c=='E') x+=n;
                else if (c=='W') x-=n;
                else if (c=='L') dir+=n;
                else if (c=='R') dir-=n;
                else {
                    x+=n*Math.cos(dir*Math.PI/180);
                    y+=n*Math.sin(dir*Math.PI/180);
                }
            }
            P$.println(Math.abs(x)+Math.abs(y));
        }
        else {
            //P$.println(Arrays.toString(rot(10,0,90*Math.PI/180)));
            double x=0, y=0;
            double wx=10, wy=1;
            for (int i=0; i<N; i++) {
                char c=com[i];
                int n=num[i];
                if (c=='N')
                    wy+=n;
                else if (c=='S') wy-=n;
                else if (c=='E') wx+=n;
                else if (c=='W') wx-=n;
                else if (c=='L') {
                    double[] p=rot(wx,wy,n*Math.PI/180);
                    wx=p[0];
                    wy=p[1];
                }
                else if (c=='R') {
                    double[] p=rot(wx,wy,-n*Math.PI/180);
                    wx=p[0];
                    wy=p[1];
                }
                else {
                    x+=n*wx;
                    y+=n*wy;
                }
                P$.println(x+" "+y+":"+wx+" "+wy);
            }
            P$.println(Math.abs(x)+Math.abs(y));
        }
    }
    private static double[] rot(double x, double y, double rad) {
        return new double[] {x*Math.cos(rad)-y*Math.sin(rad),x*Math.sin(rad)+y*Math.cos(rad)};
    }
}
