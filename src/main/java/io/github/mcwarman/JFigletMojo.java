package io.github.mcwarman;

import com.github.lalyos.jfiglet.FigletFont;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 * Maven plugin that generates figlet text based on the message set, and either:
 * <ul>
 *   <li>Send output to log</li>
 *   <li>Write output to file</li>
 * </ul>
 *
 *
 * @author mcwarman
 */
@Mojo(name = "generate", requiresProject = false)
public class JFigletMojo extends AbstractMojo
{

  /**
   * Message to convert to figlet text. Required.
   */
  @Parameter(property = "message", required = true)
  protected String message;

  /**
   * File containing figlet font configuration. Optional, Default standard.flf.
   */
  @Parameter(property = "fontFile")
  protected File fontFile;

  /**
   * Stops message being printed to log output. Optional, default false.
   */
  @Parameter(property = "suppressPrint", defaultValue = "false")
  protected boolean suppressPrint;

  /**
   * Appends newline to print output. Optional, default true.
   */
  @Parameter(property = "printAppendNewLine", defaultValue = "true")
  protected boolean printAppendNewLine;

  /**
   * Stops message being written to file. Optional, default true.
   */
  @Parameter(property = "suppressFile", defaultValue = "true")
  protected boolean suppressFile;

  /**
   * File to write message to. Optional, default <code>${project.build.directory/figlet.txt}</code>.<br>
   * NOTE: {@link #suppressFile} needs to be set to true for this to take effect
   */
  @Parameter(property = "outputFile", defaultValue = "${project.build.directory}/figlet.txt")
  protected File outputFile;

  /**
   * If file present it will be overwritten. Optional, default false.<br>
   * NOTE: {@link #suppressFile} needs to be set to true for this to take effect
   */
  @Parameter (property = "overwriteFile", defaultValue = "false")
  protected boolean overwriteFile;

  public void execute() throws MojoExecutionException
  {
    final String asciiArt = convertMessage();
    if (!suppressPrint) {
      getLog().info( (printAppendNewLine ? "\n" : "") + asciiArt );
    }
    if(!suppressFile){
      checkFileAndCreateParentDirectory();
      try(PrintStream out = getPrintStream()) {
        out.print(asciiArt);
      } catch (IOException | NullPointerException e) {
        throw new MojoExecutionException(String.format("Failed to write to output file [%s]", outputFile.getAbsolutePath()));
      }
    }
  }

  private String convertMessage() throws MojoExecutionException {
    try {
      StringBuilder result = new StringBuilder();
      for (String line : message.split("\n")) {
        if (fontFile == null) {
          result.append(FigletFont.convertOneLine(line));
        } else {
          result.append(FigletFont.convertOneLine(fontFile, line));
        }
      }
      return result.toString();
    } catch (IOException e) {
      throw new MojoExecutionException("Failed to generate FIGFont", e);
    }
  }

  private void checkFileAndCreateParentDirectory(){
    if (outputFile.isDirectory() || (outputFile.exists() && !overwriteFile)) {
      throw new IllegalArgumentException(String.format("Output file is invalid argument [%s]: directory [%s], exists [%s], overwrite [%s]", outputFile.getAbsolutePath(), outputFile.isDirectory(), outputFile.exists(), overwriteFile));
    }
    if (outputFile.getParentFile().exists()) {
      if (outputFile.getParentFile().isFile()) {
        throw new IllegalArgumentException(String.format("Output files parent is not a directory [%s]", outputFile.getParentFile().getAbsolutePath()));
      }
    } else {
      outputFile.getParentFile().mkdirs();
    }
  }

  PrintStream getPrintStream() throws IOException {
    return new PrintStream(new FileOutputStream(outputFile, false));
  }
}
