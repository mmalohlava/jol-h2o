package water;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

/*
 * Report size of critical H2O classes.
 *
 * Launch with `-Djol.tryWithSudo=true -XX:+UseCompressedOops`
 */
public class JolH2O {

  public static void main(String[] args) {
    out.println(VM.current().details());
    out.println("-------------");
    out.println(ClassLayout.parseClass(water.Key.class).toPrintable());
    out.println("-------------");
    Key randomKey = water.Key.make();
    out.println("Key internal array len: " + randomKey._kb.length);
    out.println(GraphLayout.parseInstance(randomKey).toPrintable());
    out.println("-------------");
  }
}
