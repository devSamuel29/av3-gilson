package arvore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        final Path filePathBase = Paths.get("C:/Users/samue/IdeaProjects/untitled/src/arvore/base");
        final Path filePathTest = Paths.get("C:/Users/samue/IdeaProjects/untitled/src/arvore/test");

        try {
            BufferedReader documentBase = new BufferedReader(new FileReader(filePathBase.toFile()));
            BufferedReader documentTest = new BufferedReader(new FileReader(filePathTest.toFile()));

            AvlTree<String> documentBaseTree = new AvlTree<>();
            AvlTree<String> documentTestTree = new AvlTree<>();

            for (String line = documentBase.readLine(); line != null; line = documentBase.readLine()) {
                documentBaseTree.insert(line);
            }

            for (String line = documentTest.readLine(); line != null; line = documentTest.readLine()) {
                documentTestTree.insert(line);
            }

            if(!Objects.equals(documentBaseTree.toString(), documentTestTree.toString())) {
                System.out.println("modificado");
            } else {
                System.out.println("sem modificações");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
