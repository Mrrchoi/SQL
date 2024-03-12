import java.util.*;
import java.io.*;

public class Main {
    public static class Edge {
        int v;
        int cost;

        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    public static void union(int[] parent, int x, int y) {
        if(x > y) parent[x] = y;
        else parent[y] = x;
    }
    public static int find(int[] parent, int x){
        if(x == parent[x]) return x;
        else return parent[x] = find(parent, parent[x]);
    }
    public static int kruskal(ArrayList<int[]> graph, ArrayList<Edge>[] mst, int N) {
        int[] parent = new int[N + 1];
        int total = 0, count = 0;

        for(int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < graph.size(); i++) {
            int[] now = graph.get(i);
            int px = find(parent, now[0]);
            int py = find(parent, now[1]);

            if(px != py) {
                union(parent, px, py);
                mst[now[0]].add(new Edge(now[1], now[2]));
                mst[now[1]].add(new Edge(now[0], now[2]));
                total += now[2];
                count++;
            }

            if (count == N - 1) {
                break;
            }
        }

        return total;
    }
    public static int dfs(ArrayList<Edge>[] mst, int N, int x, int y) {
        Stack<Edge> stack = new Stack<>();
        boolean[] visit = new boolean[N + 1];
        int max = 0;

        stack.push(new Edge(x, 0));
        visit[x] = true;

        while (!stack.isEmpty()) {
            Edge now = stack.peek();
            boolean hasChildNode = false;

            for(Edge next : mst[now.v]) {
                if(!visit[next.v]) {
                    stack.push(next);
                    visit[next.v] = true;
                    hasChildNode = true;

                    if(next.v == y) {
                        while (!stack.isEmpty()) {
                            Edge edge = stack.pop();

                            max = Math.max(max, edge.cost);
                        }
                    }

                    break;
                }
            }

            if(max != 0) {
                break;
            }

            if(!hasChildNode) {
                stack.pop();
            }
        }

        return max;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<int[]> graph = new ArrayList<>();
        ArrayList<Edge>[] mst = new ArrayList[N + 1];

        for(int i = 1; i < N + 1; i++) {
            mst[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.add(new int[]{a, b, c});
        }

        graph.sort((o1, o2) -> o1[2] - o2[2]);

        int total = kruskal(graph, mst, N);

        int q = Integer.parseInt(br.readLine());

        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            bw.write( (total - dfs(mst, N, x, y)) + "\n");
        }

        bw.close();
    }
}