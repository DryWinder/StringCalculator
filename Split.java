import javax.management.StringValueExp;
import java.io.StringReader;
import java.util.Objects;

public class Split {

    public static String Metacharacters(String del) {
        for (int i = 0; i < del.length(); i++) {
            if ((Objects.equals(del.charAt(i), '.')) ||
                    (Objects.equals(del.charAt(i), '+')) ||
                    (Objects.equals(del.charAt(i), '?')) ||
                    (Objects.equals(del.charAt(i), '^')) ||
                    (Objects.equals(del.charAt(i), '*')) ||
                    (Objects.equals(del.charAt(i), '$')) ||
                    (Objects.equals(del.charAt(i), '|')) ||
                    (Objects.equals(del.charAt(i), '\\')) ||
                    (Objects.equals(del.charAt(i), ')')) ||
                    (Objects.equals(del.charAt(i), '('))){
                del = new StringBuilder(del).insert(i, "\\").toString();
                i++;
            }
        }
        return del;
    }

    public static int Summary(String[] nums){
        int sum = 0;
        for(String num : nums) {
            if (num.length() == 0) {num = "0";}

            int number_int = Integer.parseInt(num);

            if (number_int < 0) {System.out.println("Negatives are not allowed! Invalid input: " + number_int);}

            else if (number_int <= 1000) {sum += number_int;}
        }
        return sum;
    }
    public static int Add(String numbers){

        int result = 0;
        String delimeter = "";

        if(numbers.length() == 0){return 0;}

        if (numbers.toCharArray()[0] == '/' && numbers.toCharArray()[1] == '/') {
            if(numbers.toCharArray()[2] == '[') {
                String dels[] = numbers.split("//|\\[|\\]");

                int amount = 0;
                for (String del : dels) {
                    dels[amount] += "0";
                    if (dels[amount].equals("0")) {
                        dels[amount] = "";
                    }
                    amount += 1;
                }

                for (int i = 0; i < amount - 1; i++) {
                    delimeter += dels[i];
                }
                delimeter = Metacharacters(delimeter);
                delimeter = delimeter.replace("0", "|");

                String nums[] = numbers.split(delimeter + "\\[|\\]|//|\n|,");

                result = Summary(nums);
            }
            else{
                delimeter += numbers.toCharArray()[2];
                delimeter = Metacharacters(delimeter);
                String nums[] = numbers.split(delimeter + "|//|\n|,");
                result = Summary(nums);
            }
        }
        else{
            String nums[] = numbers.split(",|\n");
            result = Summary(nums);
        }
        return result;
    }

    public static void main(String[] args){

        System.out.print("Sum: " + Add( "//[*][***][**]\n1*2***3**4,5\n6"));
    }
}
//"//[***][**][*****]\n10***20**30*****40,50\n60"