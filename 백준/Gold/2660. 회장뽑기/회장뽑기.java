import java.io.*;
import java.util.*;

public class Main {
    public static void floyd(int[][] arr, int n) {
        for(int k = 1; k < n + 1; k++) {
            for(int j = 1; j < n + 1; j++) {
                for(int i = 1; i < n + 1; i++) {
                    if(i == j || i == k || k == i){
                        continue;
                    }

                    if(arr[i][k] + arr[k][j] < arr[i][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] arr  = new int[n + 1][n + 1];

        for(int i = 1; i < n + 1; i++) {
            Arrays.fill(arr[i], 1000000000);
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());

            if(v == -1 && u == -1) {
                break;
            }

            arr[v][u] = 1;
            arr[u][v] = 1;
        }

        floyd(arr, n);

        int[] max = new int[n + 1];

        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                if(arr[i][j] != 1000000000) {
                    max[i] = Math.max(max[i], arr[i][j]);
                }
            }
        }

        int min = Integer.MAX_VALUE, count = 0;

        for(int i = 1; i < n + 1; i++) {
            min = Math.min(min, max[i]);
        }

        for(int i = 1; i < n + 1; i++) {
            if(min == max[i]) {
                bw.write(i + " ");
                count++;
            }
        }

        System.out.println(min + " " + count);

        bw.close();
    }
}