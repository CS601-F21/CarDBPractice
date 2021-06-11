package practice.cardb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;

class CarDBTest {

    @Test
    void test() {

        String[] args = {"-in", "cars.csv", "-out", "actual.txt"};
        Path actual = Paths.get("actual.txt");
        Path expected = Paths.get("expected.txt");
        checkProjectOutput("CarDB Test", args, actual, expected);
    }

    /**
     * Methods adapted from sjengle 212 projects.
     */

    public static int checkFiles(Path path1, Path path2) throws IOException {
        Charset charset = java.nio.charset.StandardCharsets.UTF_8;

        // used to output line mismatch
        int count = 0;


        try (
                BufferedReader reader1 =
                        Files.newBufferedReader(path1, charset);
                BufferedReader reader2 =
                        Files.newBufferedReader(path2, charset)
        ) {
            String line1 = reader1.readLine();
            String line2 = reader2.readLine();

            while (true) {
                count++;

                // compare lines until we hit a null (i.e. end of file)
                if ((line1 != null) && (line2 != null)) {
                    // use consistent path separators
                    line1 = line1.replaceAll(Matcher.quoteReplacement(File.separator), "/");
                    line2 = line2.replaceAll(Matcher.quoteReplacement(File.separator), "/");

                    // remove trailing spaces
                    line1 = line1.trim();
                    line2 = line2.trim();

                    // check if lines are equal
                    if (!line1.equals(line2)) {
                        return -count;
                    }

                    // read next lines if we get this far
                    line1 = reader1.readLine();
                    line2 = reader2.readLine();
                }
                else {
                    // discard extra blank lines at end of reader1
                    while ((line1 != null) && line1.trim().isEmpty()) {
                        line1 = reader1.readLine();
                    }

                    // discard extra blank lines at end of reader2
                    while ((line2 != null) && line2.trim().isEmpty()) {
                        line2 = reader2.readLine();
                    }

                    if (line1 == line2) {
                        // only true if both are null, otherwise one file had
                        // extra non-empty lines
                        return count;
                    }
                    else {
                        // extra blank lines found in one file
                        return -count;
                    }
                }
            }
        }
    }


    public static void checkProjectOutput(String testName, String[] args,
                                          Path actual, Path expected) {

        try {
            // Remove actual result file if it already exists
            Files.deleteIfExists(actual);

            CarDBDriver.main(args);

            int count = checkFiles(actual, expected);

            if (count <= 0) {
                Assertions.fail(String.format("%n" + "Test Case: %s%n" +
                        " Mismatched Line: %d%n", testName, -count));
            }
        }
        catch (Exception e) {
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));

            Assertions.fail(String.format(
                    "%n" + "Test Case: %s%n" + "Exception: %s%n",
                    testName, writer.toString()));
        }
    }

}
