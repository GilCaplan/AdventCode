import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class day11 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Monkey> input = getinput();
        System.out.println(solve(input, 20, 1));
        System.out.println(solve(input, 10000, 2));
    }

    public static long solve(List<Monkey> ls, int max, int part) {
        long mod = 1;
        for (Monkey m : ls)
            mod *= m.getTest()[0];

        int i = 0;
        long item;
        long[] cnt = new long[ls.size()];
        for (int rnd = 0; rnd < max; rnd++) {
            if (i >= ls.size()) i = 0; // reset counter index when starting a new round
            for (Monkey m : ls) {
                while (m.getItems().size() > 0) {
                    item = calc(m.getItems().get(0), m.getOperation()); // do calculation
                    if (part == 2) item = item % mod; // apply modulus with mod
                    else item /= 3;
                    cnt[i]++; // item inspected, so monkey i adds to count
                    m.getItems().remove(0); // remove item because monkey throws it

                    if (item % m.getTest()[0] == 0)
                        ls.get(Math.toIntExact(m.getTest()[1])).addItem(item);
                    else
                        ls.get(Math.toIntExact(m.getTest()[2])).addItem(item);
                }
                i++; // next monkey
            }
        }
        return calcMax(cnt);
    }

    public static long calcMax(long[] cnt) {
        Arrays.sort(cnt);
        return cnt[cnt.length - 1] * cnt[cnt.length - 2];
    }

    public static long calc(long x, String[] op) {
        if (op[0].equals("*")) {
            if (op[1].equals("old"))
                return x * x;
            return x * Long.parseLong(op[1]);
        }
        if (op[1].equals("old"))
            return x + x;
        return x + Long.parseLong(op[1]);
    }

    public static @NotNull List<Monkey> getinput() throws FileNotFoundException {
        var reader = new Scanner(new File("C:\\Users\\USER\\IdeaProjects\\AdventOfCode_Gil_2022\\src\\input11"));
        var list = new ArrayList<Monkey>();
        while (reader.hasNextLine()) {
            reader.nextLine();
            String items = reader.nextLine();
            String operation = reader.nextLine();
            String[] test = new String[3];
            for (int i = 0; i < 3; i++)
                test[i] = reader.nextLine();

            list.add(new Monkey(items, operation, test));
            if (reader.hasNextLine()) reader.nextLine();
        }
        return list;
    }
}


import java.util.ArrayList;
import java.util.List;

public class Monkey {
    private List<Long> items;
    private String[] operation;
    private int[] test;//o is what it's divisible by, 1 is monkey if true, 2 is monkey if false

    public int[] getTest() {
        return test;
    }
    public void addItem(Long x) {
        this.items.add(x);
    }

    public List<Long> getItems() {
        return items;
    }

    public String[] getOperation() {
        return operation;
    }

    public Monkey(String items, String operation, String[] test) {
        //example of items: Starting items: 72, 64, 51, 57, 93, 97, 68
        items = items.replaceAll("Starting items: ", "").strip();
        this.items = new ArrayList<>();
        for(String item : items.split(", "))
            this.items.add(Long.valueOf(Long.parseLong(item)));

        //example for operation: Operation: new = old * 11
        this.operation = operation.replaceAll("Operation: new = old ", "").strip().split(" ");
        //example: [*, 11]
        this.test = new int[3];
        this.test[0] = Integer.parseInt((test[0].replaceAll("Test: divisible by ", "").strip()));
        this.test[1] = Integer.parseInt((test[1].replaceAll("If true: throw to monkey ", "").strip()));
        this.test[2] = Integer.parseInt((test[2].replaceAll("If false: throw to monkey ", "").strip()));

    }
}
