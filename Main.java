package arvore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        final Path filePath = Paths.get("C:/Users/samue/IdeaProjects/untitled/src/arvore/base");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            AvlTree<String> baseTxt = new AvlTree<>();

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                baseTxt.insert(line);
            }
            System.out.println(baseTxt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
