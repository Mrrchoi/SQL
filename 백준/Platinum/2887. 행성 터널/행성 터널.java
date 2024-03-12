import java.io.*;
import java.util.*;

public class Main {
    public static class Edge {
        int v;
        int w;
        long cost;

        public Edge(int v, int w, long cost) {
            this.v = v;
            this.w = w;
            this.cost = cost;
        }
    }
    public static void union(int[] parent, int x, int y) {
        if(x > y) parent[x] = y;
        else parent[y] = x;
    }
    public static int find(int[] parent, int x) {
        if(x == parent[x]) return x;
        else return parent[x] = find(parent, parent[x]);
    }
    public static long kruskal(ArrayList<Edge> edges, int n) {
        int[] parent = new int[n];
        long cost = 0;
        int count = 0;

        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < edges.size() && count < n - 1; i++) {
            Edge now = edges.get(i);
            int px = find(parent, now.v);
            int py = find(parent, now.w);

            if(px != py) {
                union(parent, px, py);
                count++;
                cost += now.cost;
            }
        }

        return cost;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] dot = new int[n][4];
        ArrayList<Edge> edges = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 3; j++) {
                dot[i][j] = Integer.parseInt(st.nextToken());
            }

            dot[i][3] = i;
        }

        Arrays.sort(dot, (o1, o2) -> o1[0] - o2[0]);

        for(int i = 0; i < n - 1; i++) {
            edges.add(new Edge(dot[i][3], dot[i + 1][3], dot[i + 1][0] - dot[i][0]));
        }

        Arrays.sort(dot, (o1, o2) -> o1[1] - o2[1]);

        for(int i = 0; i < n - 1; i++) {
            edges.add(new Edge(dot[i][3], dot[i + 1][3], dot[i + 1][1] - dot[i][1]));
        }

        Arrays.sort(dot, (o1, o2) -> o1[2] - o2[2]);

        for(int i = 0; i < n - 1; i++) {
            edges.add(new Edge(dot[i][3], dot[i + 1][3], dot[i + 1][2] - dot[i][2]));
        }

        edges.sort((o1, o2)-> Long.compare(o1.cost, o2.cost));

        bw.write(kruskal(edges, n) + "");
        bw.close();
    }
}