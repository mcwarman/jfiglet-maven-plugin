package io.github.mcwarman.stubs;

import org.apache.maven.plugin.logging.Log;

public class StubLog implements Log {

  public CharSequence lastInfoCharSequence;

  public boolean isDebugEnabled() {
    return false;
  }

  public void debug(CharSequence charSequence) {

  }

  public void debug(CharSequence charSequence, Throwable throwable) {

  }

  public void debug(Throwable throwable) {

  }

  public boolean isInfoEnabled() {
    return true;
  }

  public void info(CharSequence charSequence) {
    this.lastInfoCharSequence = charSequence;
  }

  public void info(CharSequence charSequence, Throwable throwable) {

  }

  public void info(Throwable throwable) {

  }

  public boolean isWarnEnabled() {
    return false;
  }

  public void warn(CharSequence charSequence) {

  }

  public void warn(CharSequence charSequence, Throwable throwable) {

  }

  public void warn(Throwable throwable) {

  }

  public boolean isErrorEnabled() {
    return false;
  }

  public void error(CharSequence charSequence) {

  }

  public void error(CharSequence charSequence, Throwable throwable) {

  }

  public void error(Throwable throwable) {

  }
}
