import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr1 = new int[n];
        int[] arr2 = new int[m];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        int idx1 = 0, idx2 = 0;

        while (idx1 < n && idx2 < m) {
            if(arr1[idx1] < arr2[idx2]) {
                bw.write(arr1[idx1++] + " ");

                if(idx1 == n) {
                    while(idx2 < m) {
                        bw.write(arr2[idx2++] + " ");
                    }
                }

            }
            else {
                bw.write(arr2[idx2++] + " ");

                if (idx2 == m) {
                    while (idx1 < n) {
                        bw.write(arr1[idx1++] + " ");
                    }
                }
            }
        }

        bw.close();
    }
}