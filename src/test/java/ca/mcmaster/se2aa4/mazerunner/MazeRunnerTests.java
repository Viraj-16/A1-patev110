package ca.mcmaster.se2aa4.mazerunner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;

public class MazeRunnerTests {

    @Test
    public void testFactorizeSimplePath() {
        String input = "FFFRRLL";
        String expected = "3F 2R 2L";
        assertEquals(expected, Factorize.factorizePath(input).trim());
    }


    @Test
    public void testUnfactorizeSimplePath() {
        String input = "3F 2R 2L";
        String expected = "FFFRRLL";
        String actual = Factorize.unfactorizePath(input).replaceAll("\\s", "");
        assertEquals(expected, actual);
    }

    @Test
    public void testValidatePlainPath() {
        String input = "FLFRLF";
        boolean[] result = PathVerifier.validatePathFormat(input);
        assertTrue(result[0]);
        assertFalse(result[1]);
    }

    @Test
    public void testValidateFactorizedPath() {
        String input = "3F 2R";
        boolean[] result = PathVerifier.validatePathFormat(input);
        assertTrue(result[0]);
        assertTrue(result[1]);
    }

    @Test
    public void testMazeLoadFromFile() throws IOException {
        File tempFile = File.createTempFile("maze", ".txt");
        try (FileWriter fw = new FileWriter(tempFile)) {
            fw.write("##.\n.#.\n.##");
        }
        ArrayList<ArrayList<String>> grid = MazeLoad.loadMaze(tempFile.getAbsolutePath());
        assertEquals(3, grid.size());
        assertEquals("W", grid.get(0).get(0));
        assertEquals("P", grid.get(0).get(2));
    }

    @Test
    public void testUnfactorizeThenFactorizeGivesSame() {
        String input = "3F 2R 2L";
        String result = Factorize.factorizePath(Factorize.unfactorizePath(input));

        // Normalize whitespace for comparison
        String expected = input.trim().replaceAll("\\s+", " ");
        String actual = result.trim().replaceAll("\\s+", " ");
        
        assertEquals(expected, actual);
    }

    @Test
    public void testVerifyPathCorrect() {
        ArrayList<ArrayList<String>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(java.util.List.of("P", "P", "P")));
        Position start = new Position(0, 0);
        Position end = new Position(2, 0);
        String path = "FF";
        String result = PathVerifier.verifyPath(path, grid, start, end);
        assertEquals("correct", result);
    }

    @Test
    public void testVerifyPathIncorrect() {
        ArrayList<ArrayList<String>> grid = new ArrayList<>();
        grid.add(new ArrayList<>(java.util.List.of("P", "W", "P")));
        Position start = new Position(0, 0);
        Position end = new Position(2, 0);
        String path = "FF";
        String result = PathVerifier.verifyPath(path, grid, start, end);
        assertEquals("incorrect", result);
    }

    @Test
    public void testValidatePathFormatRejectsInvalidCharacters() {
        String badPath = "FLRXYZ";
        boolean[] result = PathVerifier.validatePathFormat(badPath);
        assertFalse(result[0]);
    }

    @Test
    public void testUnfactorizeHandlesMalformedInput() {
        String input = "3F R2 2L";
        String result = Factorize.unfactorizePath(input.replaceAll("\\s", ""));
        assertTrue(result.contains("R"));
    }
    

}


