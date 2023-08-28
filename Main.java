import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int i, n;
    String[][] aux;
    String result = "";
    boolean first = false;

    String input = scanner.nextLine();
    Table table = new Table();

    while (!input.equals("F")) {
      for (String value : input.split(",")) {
        if (!Evaluator.operators.contains(input)) {
          table.insert(value);
        }
      }

      aux = table.nextInstance();
      first = Evaluator.eval(input, aux);
      for (i = 1, n = (int) Math.pow(2, table.size()); i < n; i++) {
        aux = table.nextInstance();
        if (first != Evaluator.eval(input, aux)) {
          result = "undefined";
          break;
        }
      }

      if (i == n) {
        result = first ? "tautology" : "contradiction";
      }

      System.out.println(result);

      table.clear();
      input = scanner.nextLine();
    }
    scanner.close();
  }
}

class Evaluator {
  public static List<String> operators = Arrays.asList("=>", "<=>", "~", "v", "^");

  public static boolean eval(String expression, String[][] propositions) {
    ArrayList<Boolean> states = new ArrayList<Boolean>();
    boolean result = false;
    int size = 0;

    String[] input = expression.split(",");
    String value;

    for (int i = 0; i < input.length; i++) {
      if (!operators.contains(input[i])) {
        value = input[i];

        if (!(value.equals("false") || value.equals("true"))) {
          for (int j = 0; j < propositions.length; j++) {
            if (value.equals(propositions[j][0])) {
              value = propositions[j][1];
              break;
            }
          }
        }
        states.add(Boolean.valueOf(value));
        size++;
      }
      else {
        // Not
        if (input[i].equals("~")) {
          states.set(size - 1, !states.get(size - 1));
        }
        else {
          // Conditional, (p => q) == (~p v p)
          if (input[i].equals("=>")) {
            result = !states.get(size - 2) | states.get(size - 1);
          }
          // Biconditional (xnor), (p <=> q) == ~(p xor q)
          else if (input[i].equals("<=>")) {
            result = !(states.get(size - 2) ^ states.get(size - 1));
          }
          // And
          else if (input[i].equals("^")) {
            result = states.get(size - 2) & states.get(size - 1);
          }
          // Or
          else if (input[i].equals("v")) {
            result = states.get(size - 2) | states.get(size - 1);
          }
          states.set(size - 2, result);
          states.remove(size - 1);
          size--;
        }
      }
    }
    return states.get(0);
  }
}

interface Set {
    void insert(String proposition);
    String[][] nextInstance();
}

class Table implements Set {
  private ArrayList<String> propositions;
  private int line = 0;
  private int size = 0;

  Table() {
    this.propositions = new ArrayList<String>();
  }

  public void insert(String proposition) {
    if (!this.propositions.contains(proposition)) {
      this.propositions.add(proposition);
      this.size++;
    }
  }

  public String[][] nextInstance() {
    String[][] aux = new String[this.size][2];
    int i = 0;

    for (String proposition : this.propositions) {
      aux[i][0] = proposition;
      aux[i][1] = ((this.line >> (this.size - 1 - i)) != 0) ? "true" : "false";
      i++;
    }

    this.line++;
    return aux;
  }

  public int size() {
    return this.size;
  }

  public void clear() {
    this.line = 0;
    this.size = 0;
    this.propositions.clear();
  }
}
