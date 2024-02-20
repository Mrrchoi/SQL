import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();

        for(int i = 0; i < s.length(); i += 10) {
            bw.write(s.substring(i, Math.min(i + 10, s.length())) + "\n");
        }

        bw.close();
    }
}