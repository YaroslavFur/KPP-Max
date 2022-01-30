package MainPackage;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexManager {

    public static final Pattern pattern = Pattern.compile("\\w+[:,]?\\W+\\w+[?!.]");

    public static String getSecondLastWordFromEachWord(String input) {
        if (Objects.equals(input, ""))
            return new String("No input");

        Matcher matcher = pattern.matcher(input);
        String result = "";
        int startSymbol;
        while (matcher.find()) {
            startSymbol = matcher.start();
            result = result.concat(getWordByIndex(startSymbol, input));
            result = result.concat(" ");
        }
        return result;
    }

    private static String getWordByIndex(int index, String input) {
        String result = "";
        while (input.charAt(index) != ' ' && input.charAt(index) != '\n' && input.charAt(index) != '\t') {
            result = result.concat(String.valueOf(input.charAt(index)));
            index++;
        }
        return result;
    }
}
