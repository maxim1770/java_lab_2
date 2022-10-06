import java.util.Scanner;

class Node {
    char elem;
    Node left;
    Node right;
}

class TreeFoo {
    static void PrintTree(Node Tree, int l) {
        if (Tree != null) {
            PrintTree(Tree.right, l + 1);
            for (int i = 1; i <= l; i++) System.out.print("    ");
            System.out.println(Tree.elem);
            PrintTree(Tree.left, l + 1);
        }
    }

    static int CalcTree(Node Tree) {
        int num1, num2;
        if (Tree.left == null)
            return Tree.elem - '0';
        num1 = CalcTree(Tree.left);
        num2 = CalcTree(Tree.right);
        switch (Tree.elem) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }
        return -1;
    }


    static int Priority(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return 5;
    }

    static Node MakeTree(String Expr, int first, int last) {
        int MinPrt, i, k = 0, prt;
        Node Tree = new Node();
        if (first > last)
            return null;
        if (first == last) {
            if ((Expr.charAt(first) == '(') || (Expr.charAt(first) == ')'))
                return null;
            Tree.elem = Expr.charAt(first);
            Tree.left = null;
            Tree.right = null;
            return Tree;
        }

        if ((Expr.charAt(first) == '(') && (Expr.charAt(last) == ')')) {
            if (Stack.check_word(Expr.substring(first + 1, last))) {
                first++;
                last--;
            }
        }

        MinPrt = 5;
        int flag = 0;
        for (i = first; i <= last; i++) {
            if (Expr.charAt(i) == '(')
                flag++;
            else if (Expr.charAt(i) == ')')
                flag--;
            else if (flag == 0) {
                prt = Priority(Expr.charAt(i));
                if (prt <= MinPrt) {
                    MinPrt = prt;
                    k = i;
                }
            }
        }

        Tree.elem = Expr.charAt(k);
        Tree.left = MakeTree(Expr, first, k - 1);
        Tree.right = MakeTree(Expr, k + 1, last);
        return Tree;
    }

}

public class TreeTest {
    public static void main(String[] args) {
        String s;

        do {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Введите выражение: ");
            s = keyboard.nextLine();

            if (Stack.check_word(s)) {
                System.out.println("Выpажение пpавильное");

                Node tree = TreeFoo.MakeTree(s, 0, s.length() - 1);

                TreeFoo.PrintTree(tree, 0);
                System.out.println("Результат вычислений: ");
                System.out.println(TreeFoo.CalcTree(tree));

            } else
                System.out.println("Выpажение непpавильное");


        } while (!s.equals("0"));
    }
}
