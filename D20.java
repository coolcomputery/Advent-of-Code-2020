import java.io.*;
import java.util.*;
public class D20 {
    private static final PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        Map<Integer,boolean[][]> data=new HashMap<>();
        for (int i=0; i<lines.size();) {
            int id=Integer.parseInt(lines.get(i)
                    .substring(0,lines.get(i).length()-1).split(" ")[1]);
            i++;
            int j=i;
            while (j<lines.size() && lines.get(j).length()>0) j++;
            boolean[][] b=new boolean[j-i][lines.get(i).length()];
            for (int r=0; r<j-i; r++)
                for (int c=0; c<b[r].length; c++)
                    b[r][c]=lines.get(r+i).charAt(c)=='#';
            data.put(id,b);
            /*P$.println(id+"-->");
            for (boolean[] r:b) P$.println(Arrays.toString(r));*/
            i=j+1;
        }
        List<Integer> ids=new ArrayList<>(data.keySet());
        Map<Integer,String> coord=new HashMap<>();
        Map<Integer,boolean[][]> actual=new HashMap<>();
        Map<String,Integer> taken=new HashMap<>();
        List<Integer> front=new ArrayList<>();
        {
            int i=ids.get(0);
            front.add(i);
            coord.put(i,code(0,0));
            taken.put(code(0,0),i);
            actual.put(i,data.get(i));
        }
        for (int i=0; i<front.size(); i++) {
            int id=front.get(i);
            String[] co=coord.get(id).split(",");
            int x=Integer.parseInt(co[0]),
                    y=Integer.parseInt(co[1]);
            for (int d=0; d<4; d++) {
                int nx=x+dx[d], ny=y+dy[d];
                if (!taken.containsKey(code(nx,ny))) {
                    FIND: for (int nid:ids)
                    if (!coord.containsKey(nid))
                    for (int r=0; r<8; r++)
                    if (match(actual.get(id),oriented(data.get(nid),r),d)) {
                        front.add(nid);
                        coord.put(nid,code(nx,ny));
                        taken.put(code(nx,ny),nid);
                        actual.put(nid,oriented(data.get(nid),r));
                        break FIND;
                    }
                }
            }
        }
        P$.println(coord);
        /*for (int id:actual.keySet()) {
            P$.println(id);
            show(actual.get(id));
        }*/
        int minx=Integer.MAX_VALUE, miny=minx, maxx=Integer.MIN_VALUE, maxy=maxx;
        for (String c:taken.keySet()) {
            String[] t=c.split(",");
            int x=Integer.parseInt(t[0]), y=Integer.parseInt(t[1]);
            minx=Math.min(minx,x);
            miny=Math.min(miny,y);
            maxx=Math.max(maxx,x);
            maxy=Math.max(maxy,y);
        }
        P$.println((long)taken.get(code(minx,miny))
                *taken.get(code(minx,maxy))
                *taken.get(code(maxx,miny))
                *taken.get(code(maxx,maxy)));
        int N=8;
        boolean[][] img=new boolean[(maxx-minx+1)*N][(maxy-miny+1)*N];
        for (int x=minx; x<=maxx; x++)
        for (int y=miny; y<=maxy; y++)
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++)
                img[(x-minx)*N+i][(y-miny)*N+j]=actual.get(taken.get(code(x,y)))[i+1][j+1];
        show(img);
        String[] monster=new String[] {
                "                  # ",
                "#    ##    ##    ###",
                " #  #  #  #  #  #   "
        };
        boolean[][] M=new boolean[monster.length][monster[0].length()];
        for (int i=0; i<M.length; i++)
            for (int j=0; j<M[i].length; j++)
                M[i][j]=monster[i].charAt(j)=='#';
        for (int r=0; r<8; r++) {
            boolean[][] b=oriented(img,r);
            boolean[][] pm=new boolean[b.length][b[0].length];
            int cnt=0;
            for (int i=0; i+M.length<=b.length; i++)
            for (int j=0; j+M[0].length<=b[i].length; j++) {
                boolean sm=true;
                CHECK: for (int di=0; di<M.length; di++)
                    for (int dj=0; dj<M[di].length; dj++)
                        if (M[di][dj] && !b[i+di][j+dj]) {
                            sm=false;
                            break CHECK;
                }
                if (sm) {
                    for (int di=0; di<M.length; di++)
                        for (int dj=0; dj<M[di].length; dj++)
                            if (M[di][dj])
                                pm[i+di][j+dj]=true;
                    cnt++;
                }
            }
            if (cnt>0)
            {
                int out=0;
                for (int i=0; i<b.length; i++)
                    for (int j=0; j<b[i].length; j++)
                        if (b[i][j]&&!pm[i][j]) out++;
                P$.println(out);
                break;
            }
        }
    }
    static final int[] dx={1,0,-1,0}, dy={0,1,0,-1};
    static String code(int x, int y) {
        return x+","+y;
    }
    static boolean match(boolean[][] a, boolean[][] b, int d) {
        int R=b.length, C=b[0].length;
        if (d==0) {
            for (int j=0; j<C; j++)
                if (a[R-1][j]!=b[0][j]) return false;
        }
        else if (d==2) {
            for (int j=0; j<C; j++)
                if (b[R-1][j]!=a[0][j]) return false;
        }
        else if (d==1) {
            for (int i=0; i<R; i++)
                if (a[i][C-1]!=b[i][0]) return false;
        }
        else {
            for (int i=0; i<R; i++)
                if (b[i][C-1]!=a[i][0]) return false;
        }
        return true;
    }
    static void show(boolean[][] b) {
        for (boolean[] r:b) {
            for (boolean v:r)
                P$.print(v?"#":".");
            P$.println();
        }
    }
    static boolean[][] oriented(boolean[][] b, int r) {
        if (r/4==0) return rot(b,r%4);
        else return flip(rot(b,r%4));
    }
    static boolean[][] flip(boolean[][] b) {
        int R=b.length, C=b[0].length;
        boolean[][] nb=new boolean[C][R];
        for (int i=0; i<R; i++)
            for (int j=0; j<C; j++)
                nb[j][i]=b[i][j];
        return nb;
    }
    static boolean[][] rot(boolean[][] b, int r) {
        if (r==0) return b;
        int R=b.length, C=b[0].length;
        boolean[][] nb=new boolean[r%2==0?R:C][r%2==0?C:R];
        for (int i=0; i<R; i++)
            for (int j=0; j<C; j++)
                if (r==1)
                    nb[C-1-j][i]=b[i][j];
                else if (r==2)
                    nb[R-1-i][C-1-j]=b[i][j];
                else
                    nb[j][R-1-i]=b[i][j];
        return nb;
    }
}
