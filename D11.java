import java.io.*;
import java.util.*;
public class D11 {
    private static final PrintStream P$=System.out;
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new FileReader("IN.txt"));
        List<String> lines=new ArrayList<>();
        while (true) {
            String l=in.readLine();
            if (l==null) break;
            lines.add(l);
        }
        int N=lines.size(), M=lines.get(0).length();
        int[][] B=new int[N][M];
        for (int i=0; i<N; i++)
            for (int j=0; j<M; j++) {
                char c=lines.get(i).charAt(j);
                B[i][j]=c=='.'?0:c=='L'?1:2;
            }
        int[] dr={1,1,0,-1,-1,-1,0,1},
                dc={0,1,1,1,0,-1,-1,-1};
        int part=2;
        P$.println("!!!PART "+part);
        if (part==1) {
            while (true) {
                int[][] NB=new int[N][M];
                for (int i=0; i<N; i++)
                    for (int j=0; j<M; j++) {
                        int[] cnt=new int[3];
                        for (int d=0; d<8; d++) {
                            int ni=i+dr[d], nj=j+dc[d];
                            if (ni>-1 && ni<N && nj>-1 && nj<M) {
                                cnt[B[ni][nj]]++;
                            }
                        }
                        if (B[i][j]==1 && cnt[2]==0)
                            NB[i][j]=2;
                        else if (B[i][j]==2 && cnt[2]>=4)
                            NB[i][j]=1;
                        else NB[i][j]=B[i][j];
                    }
                boolean same=true;
                for (int i=0; i<N; i++)
                    for (int j=0; j<M; j++)
                        if (NB[i][j]!=B[i][j])
                            same=false;
                if (same) break;
                B=NB;
            }
            int ans=0;
            for (int i=0; i<N; i++)
                for (int j=0; j<M; j++)
                    if (B[i][j]==2) ans++;
            P$.println(ans);
        }
        else {
            while (true) {
                int[][] NB=new int[N][M];
                for (int i=0; i<N; i++)
                    for (int j=0; j<M; j++) {
                        int[] cnt=new int[3];
                        for (int d=0; d<8; d++) {
                            for (int s=1;; s++) {
                                int ni=i+s*dr[d], nj=j+s*dc[d];
                                if (ni>-1 && ni<N && nj>-1 && nj<M) {
                                    if (B[ni][nj]!=0) {
                                        cnt[B[ni][nj]]++;
                                        break;
                                    }
                                }
                                else break;
                            }
                        }
                        if (B[i][j]==1 && cnt[2]==0)
                            NB[i][j]=2;
                        else if (B[i][j]==2 && cnt[2]>=5)
                            NB[i][j]=1;
                        else NB[i][j]=B[i][j];
                    }
                boolean same=true;
                for (int i=0; i<N; i++)
                    for (int j=0; j<M; j++)
                        if (NB[i][j]!=B[i][j])
                            same=false;
                if (same) break;
                B=NB;
            }
            int ans=0;
            for (int i=0; i<N; i++)
                for (int j=0; j<M; j++)
                    if (B[i][j]==2) ans++;
            P$.println(ans);
        }
    }
}
