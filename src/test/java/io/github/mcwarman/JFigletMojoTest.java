package io.github.mcwarman;

import io.github.mcwarman.stubs.StubLog;
import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class JFigletMojoTest {

  private static final String LINE_ENDING = "\n";

  private JFigletMojo mojo;
  private StubLog log;

  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();

  @Before
  public void setUp(){
    log = new StubLog();
    mojo = new JFigletMojo();
    mojo.setLog(log);
    mojo.message = "Hello World";
    mojo.printAppendNewLine = true;
    mojo.suppressPrint = false;
    mojo.suppressFile = true;
    mojo.overwriteFile = false;
  }

  @Test
  public void testExecutePrint() throws Exception {
    mojo.printAppendNewLine = true;
    mojo.execute();
    assertNotNull(log.lastInfoCharSequence);
    assertEqualsAsciiArt(log.lastInfoCharSequence.toString(), true);
  }

  @Test
  public void testExecutePrintNoNewLine() throws Exception {
    mojo.printAppendNewLine = false;
    mojo.execute();
    assertNotNull(log.lastInfoCharSequence);
    assertEqualsAsciiArt(log.lastInfoCharSequence.toString(), false);
  }

  @Test
  public void testExecuteSuppressPrint() throws Exception {
    mojo.suppressPrint = true;
    mojo.execute();
    assertNull(log.lastInfoCharSequence);
  }

  @Test
  public void testExecuteFilePrint() throws Exception {
    mojo.suppressPrint = true;
    mojo.suppressFile = false;
    File folder = tempFolder.getRoot();
    mojo.outputFile = new File(folder, "figlet.txt");
    assertFalse(mojo.outputFile.exists());
    mojo.execute();
    assertNull(log.lastInfoCharSequence);
    assertTrue(mojo.outputFile.exists());
    String asciiArt = new String(Files.readAllBytes(mojo.outputFile.toPath()));
    assertEqualsAsciiArt(asciiArt, false);
  }

  @Test
  public void testExecuteFontFile() throws Exception {
    mojo.fontFile = new File(JFigletMojoTest.class.getClassLoader().getResource("standard.flf").getFile());
    mojo.suppressPrint = true;
    mojo.suppressFile = false;
    File folder = tempFolder.getRoot();
    mojo.outputFile = new File(folder, "figlet.txt");
    assertFalse(mojo.outputFile.exists());
    mojo.execute();
    assertNull(log.lastInfoCharSequence);
    assertTrue(mojo.outputFile.exists());
    String asciiArt = new String(Files.readAllBytes(mojo.outputFile.toPath()));
    assertEqualsAsciiArt(asciiArt, false);
  }

  @Test(expected = MojoExecutionException.class)
  public void testExecuteFontFileFailure() throws Exception {
    mojo.fontFile = new File(tempFolder.getRoot(), "does-not-exist.flf");
    mojo.suppressPrint = true;
    mojo.execute();
  }

  @Test
  public void testExecuteFont() throws Exception {
    mojo.font = new File(JFigletMojoTest.class.getClassLoader().getResource("standard.flf").getFile()).getAbsolutePath();
    mojo.suppressPrint = true;
    mojo.suppressFile = false;
    File folder = tempFolder.getRoot();
    mojo.outputFile = new File(folder, "figlet.txt");
    assertFalse(mojo.outputFile.exists());
    mojo.execute();
    assertNull(log.lastInfoCharSequence);
    assertTrue(mojo.outputFile.exists());
    String asciiArt = new String(Files.readAllBytes(mojo.outputFile.toPath()));
    assertEqualsAsciiArt(asciiArt, false);
  }

  @Test
  public void testExecuteFontClasspth() throws Exception {
    mojo.font = "classpath:/standard.flf";
    mojo.suppressPrint = true;
    mojo.suppressFile = false;
    File folder = tempFolder.getRoot();
    mojo.outputFile = new File(folder, "figlet.txt");
    assertFalse(mojo.outputFile.exists());
    mojo.execute();
    assertNull(log.lastInfoCharSequence);
    assertTrue(mojo.outputFile.exists());
    String asciiArt = new String(Files.readAllBytes(mojo.outputFile.toPath()));
    assertEqualsAsciiArt(asciiArt, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOutputFileIsAFolder() throws Exception {
    mojo.suppressFile = false;
    mojo.outputFile = tempFolder.newFolder();
    mojo.execute();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOutputFileExists() throws Exception {
    mojo.suppressFile = false;
    mojo.outputFile = tempFolder.newFile();
    mojo.execute();
  }

  @Test
  public void testOutputFileExistsOverwrite() throws Exception {
    mojo.suppressPrint = true;
    mojo.suppressFile = false;
    mojo.overwriteFile = true;
    mojo.outputFile = tempFolder.newFile();
    assertTrue(mojo.outputFile.exists());
    mojo.execute();
    assertNull(log.lastInfoCharSequence);
    assertTrue(mojo.outputFile.exists());
    String asciiArt = new String(Files.readAllBytes(mojo.outputFile.toPath()));
    assertEqualsAsciiArt(asciiArt, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOutputFileParentIsFile() throws Exception {
    mojo.suppressFile = false;
    File folder = tempFolder.newFile();
    mojo.outputFile = new File(folder, "file.txt");
    mojo.execute();

  }

  @Test
  public void testOutputFileParentDoesNotExist() throws Exception {
    mojo.suppressFile = false;
    File folder = new File(tempFolder.getRoot(), "target");
    mojo.outputFile = new File(folder, "file.txt");
    mojo.execute();
    assertTrue(mojo.outputFile.exists());
    String asciiArt = new String(Files.readAllBytes(mojo.outputFile.toPath()));
    assertEqualsAsciiArt(asciiArt, false);
  }

  @Test
  public void testPrintStreamException() throws Exception {
    StubLog log = new StubLog();
    JFigletMojo mojo = Mockito.spy(new JFigletMojo());
    mojo.setLog(log);
    mojo.message = "Hello World";
    mojo.printAppendNewLine = true;
    mojo.suppressPrint = false;
    mojo.overwriteFile = false;
    mojo.suppressFile = false;
    File folder = new File(tempFolder.getRoot(), "target");
    mojo.outputFile = new File(folder, "file.txt");
    Mockito.doThrow(new IOException()).when(mojo).getPrintStream();
    try {
      mojo.execute();
      fail("Expected exception not thrown");
    } catch (MojoExecutionException expected){
      String expectedMessage = String.format("Failed to write to output file [%s]", mojo.outputFile.getAbsolutePath());
      assertEquals(expectedMessage, expected.getMessage());
    }
    Mockito.verify(mojo).getPrintStream();
  }

  @Test
  public void testPrintStreamNull() throws Exception {
    StubLog log = new StubLog();
    JFigletMojo mojo = Mockito.spy(new JFigletMojo());
    mojo.setLog(log);
    mojo.message = "Hello World";
    mojo.printAppendNewLine = true;
    mojo.suppressPrint = false;
    mojo.overwriteFile = false;
    mojo.suppressFile = false;
    File folder = new File(tempFolder.getRoot(), "target");
    mojo.outputFile = new File(folder, "file.txt");
    Mockito.doReturn(null).when(mojo).getPrintStream();
    try {
      mojo.execute();
      fail("Expected exception not thrown");
    } catch (MojoExecutionException expected){
      String expectedMessage = String.format("Failed to write to output file [%s]", mojo.outputFile.getAbsolutePath());
      assertEquals(expectedMessage, expected.getMessage());
    }
    Mockito.verify(mojo).getPrintStream();
  }

  @Test
  public void testExecutePrintMultiLine() throws Exception {
    mojo.printAppendNewLine = true;
    mojo.message = "Hello\nWorld";
    mojo.execute();
    assertNotNull(log.lastInfoCharSequence);
    assertEqualsAsciiArtMultiLine(log.lastInfoCharSequence.toString());
  }

  private void assertEqualsAsciiArt(String asciiArt, boolean printAppendNewLine) {
    String expectedAsciiArt = (printAppendNewLine ? LINE_ENDING : "") +
        "  _   _          _   _            __        __                 _       _ " + LINE_ENDING +
        " | | | |   ___  | | | |   ___     \\ \\      / /   ___    _ __  | |   __| |" + LINE_ENDING +
        " | |_| |  / _ \\ | | | |  / _ \\     \\ \\ /\\ / /   / _ \\  | '__| | |  / _` |" + LINE_ENDING +
        " |  _  | |  __/ | | | | | (_) |     \\ V  V /   | (_) | | |    | | | (_| |" + LINE_ENDING +
        " |_| |_|  \\___| |_| |_|  \\___/       \\_/\\_/     \\___/  |_|    |_|  \\__,_|" + LINE_ENDING +
        "                                                                         " + LINE_ENDING;
    assertEquals(expectedAsciiArt, asciiArt);
  }

  private void assertEqualsAsciiArtMultiLine(String asciiArt) {
    String expectedAsciiArt = LINE_ENDING +
        "  _   _          _   _         " + LINE_ENDING +
        " | | | |   ___  | | | |   ___  " + LINE_ENDING +
        " | |_| |  / _ \\ | | | |  / _ \\ " + LINE_ENDING +
        " |  _  | |  __/ | | | | | (_) |" + LINE_ENDING +
        " |_| |_|  \\___| |_| |_|  \\___/ " + LINE_ENDING +
        "                               " + LINE_ENDING +
        " __        __                 _       _ " + LINE_ENDING +
        " \\ \\      / /   ___    _ __  | |   __| |" + LINE_ENDING +
        "  \\ \\ /\\ / /   / _ \\  | '__| | |  / _` |" + LINE_ENDING +
        "   \\ V  V /   | (_) | | |    | | | (_| |" + LINE_ENDING +
        "    \\_/\\_/     \\___/  |_|    |_|  \\__,_|" + LINE_ENDING +
        "                                        " + LINE_ENDING;
    assertEquals(expectedAsciiArt, asciiArt);
  }

}
