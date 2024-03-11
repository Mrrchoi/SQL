import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
    public static int[][] delta = {{0, -1, 0}, {0, 0, 1}, {0, 1, 0}, {0, 0, -1}, {1, 0, 0}, {-1, 0, 0}};
    public static int min = Integer.MAX_VALUE;
    public static boolean stop = false;
    public static void bfs(int[][][] miro) {
        Queue<int[]> queue = new LinkedList<>();
        int[][][] visit = new int[5][5][5];

        queue.add(new int[]{0, 0, 0});
        visit[0][0][0] = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if(min <= visit[now[0]][now[1]][now[2]]) return;

            for(int i = 0; i < delta.length; i++) {
                int[] next = {now[0] + delta[i][0], now[1] + delta[i][1], now[2] + delta[i][2]};

                if(next[0] < 0 || next[0] >= 5 || next[1] < 0 || next[1] >= 5 || next[2] < 0 || next[2] >= 5 || miro[next[0]][next[1]][next[2]] == 0 || visit[next[0]][next[1]][next[2]] != 0) continue;

                queue.add(next);
                visit[next[0]][next[1]][next[2]] = visit[now[0]][now[1]][now[2]] + 1;

                if(next[0] == 4 && next[1] == 4 && next[2] == 4) {
                    min = Math.min(min, visit[next[0]][next[1]][next[2]] - 1);
                    return;
                }
            }
        }
    }
    public static void permutation(int[][][] miro, int depth) {
        if(depth == miro.length) {
            if(miro[0][0][0] == 1 && miro[4][4][4] == 1) {
                bfs(miro);

                if(min == 12) stop = true;
            }

            return;
        }

        for(int i = depth; i < miro.length; i++) {
            for(int j = 0; j < 4; j++) {
                rotate(miro, i);
                swap(miro, i, depth);
                permutation(miro, depth + 1);
                if(stop) return;
                swap(miro, i, depth);
            }
        }
    }
    public static void swap(int[][][] arr, int idx1, int idx2) {
        int[][] tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
    public static void rotate(int[][][] arr, int idx) {
        int[][] tmp = new int[5][5];

        for(int i = 4; i >= 0; i--) {
            for(int j = 0; j <= 4; j++) {
                tmp[4 - i][j] = arr[idx][4 - j][4 - i];
            }
        }

        arr[idx] = tmp;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int[][][] miro = new int[5][5][5];

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                st = new StringTokenizer(br.readLine());

                for(int k = 0; k < 5; k++) {
                    miro[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        permutation(miro, 0);

        bw.write( (min == Integer.MAX_VALUE ? -1 : min) + "");
        bw.close();
    }
}