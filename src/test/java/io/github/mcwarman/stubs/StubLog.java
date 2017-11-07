package io.github.mcwarman.stubs;

import org.apache.maven.plugin.logging.Log;

public class StubLog implements Log {

  public CharSequence lastInfoCharSequence;

  public boolean isDebugEnabled() {
    return false;
  }

  public void debug(CharSequence charSequence) {
    //Stub method deliberately empty
  }

  public void debug(CharSequence charSequence, Throwable throwable) {
    //Stub method deliberately empty
  }

  public void debug(Throwable throwable) {
    //Stub method deliberately empty
  }

  public boolean isInfoEnabled() {
    return true;
  }

  public void info(CharSequence charSequence) {
    this.lastInfoCharSequence = charSequence;
  }

  public void info(CharSequence charSequence, Throwable throwable) {
    //Stub method deliberately empty
  }

  public void info(Throwable throwable) {
    //Stub method deliberately empty
  }

  public boolean isWarnEnabled() {
    return false;
  }

  public void warn(CharSequence charSequence) {
    //Stub method deliberately empty
  }

  public void warn(CharSequence charSequence, Throwable throwable) {
    //Stub method deliberately empty
  }

  public void warn(Throwable throwable) {
    //Stub method deliberately empty
  }

  public boolean isErrorEnabled() {
    return false;
  }

  public void error(CharSequence charSequence) {
    //Stub method deliberately empty
  }

  public void error(CharSequence charSequence, Throwable throwable) {
    //Stub method deliberately empty
  }

  public void error(Throwable throwable) {
    //Stub method deliberately empty
  }
}
