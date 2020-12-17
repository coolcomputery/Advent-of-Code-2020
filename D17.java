import java.io.*;
import java.util.*;
public class D17 {
    private static final PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        int C=6, N=lines.size();
        int part=2;
        P$.println("!!!PART "+part);
        if (part==1) {
            boolean[][][] board=new boolean[N+2*C][N+2*C][2*C+1];
            for (int i=0; i<N; i++)
                for (int j=0; j<N; j++)
                    board[i+C][j+C][C]=lines.get(i).charAt(j)=='#';
            for (int rep=0; rep<C; rep++) {
                boolean[][][] nb=new boolean[N+2*C][N+2*C][2*C+1];
                for (int i=0; i<N+2*C; i++)
                for (int j=0; j<N+2*C; j++)
                for (int z=0; z<=2*C; z++) {
                    int cnt=0;
                    for (int ni=Math.max(0,i-1); ni<=Math.min(N+2*C-1,i+1); ni++)
                        for (int nj=Math.max(0,j-1); nj<=Math.min(N+2*C-1,j+1); nj++)
                            for (int nz=Math.max(0,z-1); nz<=Math.min(2*C,z+1); nz++)
                                if (ni!=i||nj!=j||nz!=z) {
                                    if (board[ni][nj][nz]) cnt++;
                                }
                    if (board[i][j][z])
                        nb[i][j][z]=cnt==2||cnt==3;
                    else
                        nb[i][j][z]=cnt==3;
                }
                board=nb;
            }
            int out=0;
            for (int i=0; i<N+2*C; i++)
                for (int j=0; j<N+2*C; j++)
                    for (int z=0; z<=2*C; z++)
                        if (board[i][j][z]) out++;
            P$.println(out);
        }
        else {
            boolean[][][][] board=new boolean[N+2*C][N+2*C][2*C+1][2*C+1];
            for (int i=0; i<N; i++)
                for (int j=0; j<N; j++)
                    board[i+C][j+C][C][C]=lines.get(i).charAt(j)=='#';
            for (int rep=0; rep<C; rep++) {
                boolean[][][][] nb=new boolean[N+2*C][N+2*C][2*C+1][2*C+1];
                for (int i=0; i<N+2*C; i++)
                    for (int j=0; j<N+2*C; j++)
                        for (int z=0; z<=2*C; z++)
                            for (int w=0; w<=2*C; w++) {
                            int cnt=0;
                            for (int ni=Math.max(0,i-1); ni<=Math.min(N+2*C-1,i+1); ni++)
                                for (int nj=Math.max(0,j-1); nj<=Math.min(N+2*C-1,j+1); nj++)
                                    for (int nz=Math.max(0,z-1); nz<=Math.min(2*C,z+1); nz++)
                                        for (int nw=Math.max(0,w-1); nw<=Math.min(2*C,w+1); nw++)
                                        if (ni!=i||nj!=j||nz!=z||nw!=w) {
                                            if (board[ni][nj][nz][nw]) cnt++;
                                        }
                            if (board[i][j][z][w])
                                nb[i][j][z][w]=cnt==2||cnt==3;
                            else
                                nb[i][j][z][w]=cnt==3;
                        }
                board=nb;
            }
            int out=0;
            for (int i=0; i<N+2*C; i++)
                for (int j=0; j<N+2*C; j++)
                    for (int z=0; z<=2*C; z++)
                        for (int w=0; w<=2*C; w++)
                        if (board[i][j][z][w]) out++;
            P$.println(out);
        }
    }
}
