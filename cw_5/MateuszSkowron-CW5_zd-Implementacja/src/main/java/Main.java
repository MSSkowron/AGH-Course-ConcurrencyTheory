import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("One argument with input data is required!");
            System.exit(1);
        }

        String[] elementySladu = {};
        List<String> akcje = new ArrayList<>();
        Map<Character, List<Character>> D = new HashMap<>();
        Map<Character, List<Character>> I = new HashMap<>();

        try {
            File inputFile = new File(args[0]);
            Scanner scanner = new Scanner(inputFile);

            // Przeczytaj alfabet
            if (!scanner.hasNextLine()) {
                System.out.println("Wymagany jest alfabet!");
                System.exit(2);
            }

            String data = scanner.nextLine();
            String[] parts = data.split(" ");
            if (parts.length != 3 || !Objects.equals(parts[0], "A") || !Objects.equals(parts[1], "=") || !parts[2].startsWith("{") || !parts[2].endsWith("}")) {
                System.out.println("Niepoprawny format alfabetu");
                System.exit(2);
            }

            // Przeczytaj slad
            if (!scanner.hasNextLine()) {
                System.out.println("Wymagany jest slad!");
                System.exit(2);
            }

            data = scanner.nextLine();
            parts = data.split(" ");
            if (parts.length != 3 || !Objects.equals(parts[0], "w") || !Objects.equals(parts[1], "=")) {
                System.out.println("Niepoprawny format sladu");
                System.exit(2);
            }

            elementySladu = parts[2].split("");

            // Przeczytaj akcje
            if (!scanner.hasNextLine()) {
                System.out.println("Wymagana jest przynajmniej jedna akcja!");
                System.exit(2);
            }

            while (scanner.hasNextLine()) {
                String akcja = scanner.nextLine();
                parts = akcja.split(" ");
                if (parts.length != 4 || !Objects.equals(parts[2], ":=")) {
                    System.out.println("Niepoprawna akcja: " + akcja);
                    System.exit(2);
                }
                akcje.add(akcja);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
            System.exit(2);
        }

        for (String i: akcje) {
            ArrayList<Character> a = new ArrayList<>();
            a.add(i.charAt(0));
            D.put(i.charAt(0), a);
        }

        for (String i: akcje) {
            I.put(i.charAt(0), new ArrayList<>());
        }

        // Zbior relacji
        for (int i = 0; i < akcje.size(); i++) {
            for (int j = i + 1; j < akcje.size(); j++) {
                String a1 = akcje.get(i);
                String a2 = akcje.get(j);
                if (a1.charAt(3) == a2.charAt(3) || a1.substring(8).contains(String.valueOf(a2.charAt(3))) || a2.substring(8).contains(String.valueOf(a1.charAt(3)))) {
                    D.get(a1.charAt(0)).add(a2.charAt(0));
                    D.get(a2.charAt(0)).add(a1.charAt(0));
                } else {
                    I.get(a1.charAt(0)).add(a2.charAt(0));
                    I.get(a2.charAt(0)).add(a1.charAt(0));
                }
            }
        }

        System.out.print("D={");
        for (var entry : D.entrySet()) {
            for(Character c : entry.getValue()){
                System.out.print("("+entry.getKey()+","+c+")");
            }
        }
        System.out.print("}\n");

        System.out.print("I={");
        for (var entry : I.entrySet()) {
            for(Character c : entry.getValue()){
                System.out.print("("+entry.getKey()+","+c+")");
            }
        }
        System.out.print("}\n");

        // Postac Normalna Foaty
        List<List<Character>> FNF = new ArrayList<>();
        for (String s : elementySladu) {
            if (FNF.size() == 0) {
                ArrayList<Character> x = new ArrayList<>();
                x.add(s.charAt(0));
                FNF.add(x);
                continue;
            }

            int classIndex = -1;
            for (int i = FNF.size() - 1; i >= 0 ; i--) {
                boolean isIndependent = true;
                for (Character c : FNF.get(i)) {
                    if (D.get(c).contains(s.charAt(0))) {
                        isIndependent = false;
                    }
                }
                if(isIndependent) {
                    classIndex = i;
                } else {
                    break;
                }
            }

            if (classIndex == -1) {
                ArrayList<Character> x = new ArrayList<>();
                x.add(s.charAt(0));
                FNF.add(x);
            } else {
                FNF.get(classIndex).add(s.charAt(0));
            }

        }

        System.out.print("FNF([w])=");
        for(List<Character> fClass : FNF){
            System.out.print("(");
            for (Character c : fClass) {
                System.out.print(c);
            }
            System.out.print(")");
        }
        System.out.print("\n");


        // GRAF DIEKERTA
        ArrayList<ArrayList<Integer>> grafDiekerta = new ArrayList<>();
        // Najpierw tworzymy graf pelny
        for (int i = 0; i < elementySladu.length; i++) {
            ArrayList<Integer> x = new ArrayList<>();
            for (int j = i + 1; j < elementySladu.length; j++) {
                if (D.get(elementySladu[i].charAt(0)).contains(elementySladu[j].charAt(0))) {
                    x.add(j);
                }
            }
            grafDiekerta.add(x);
        }

        // Usuwamy przechodniosc w grafie
        ArrayList<ArrayList<Integer>> toRemove = new ArrayList<>();
        for (int i = 0; i < elementySladu.length; i++) {
            toRemove.add(new ArrayList<>());
        }

        for (int i = 0; i < elementySladu.length; i++) {
            for (int j = i + 1; j < elementySladu.length; j++) {
                ArrayList<Integer> v1 = grafDiekerta.get(i);
                if (!v1.contains(j)) {
                    continue;
                }

                ArrayList<Integer> v2 = grafDiekerta.get(j);
                for (Integer v: v1) {
                    if (v2.contains(v)){
                        toRemove.get(i).add(v);
                    }
                }
            }
        }

        for (int i = 0; i < toRemove.size(); i++) {
            for(Integer el: toRemove.get(i)) {
                grafDiekerta.get(i).remove(el);
            }
        }

        System.out.println("digraph g{");
        for (int i = 0; i < grafDiekerta.size(); i++) {
            for (Integer j : grafDiekerta.get(i)) {
                System.out.println((i+1) + " -> " + (j+1));
            }
        }
        for (int i = 0; i < grafDiekerta.size(); i++) {
            System.out.println((i+1)+"[label="+elementySladu[i]+"]");
        }
        System.out.println("}");
    }
}
