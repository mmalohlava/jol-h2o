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
    out.println(banner("Current VM details"));
    out.println(VM.current().details());

    out.println(banner(Key.class));
    out.println(ClassLayout.parseClass(water.Key.class).toPrintable());

    out.println(banner("Instance of " + Key.class.getCanonicalName()));
    Key randomKey = water.Key.make();
    out.println("Key internal array len: " + randomKey._kb.length);
    GraphLayout keyGl = GraphLayout.parseInstance(randomKey);
    out.println(keyGl.toPrintable());
    out.println(keyGl.toFootprint());

    out.println(banner(String.class));
    out.println(ClassLayout.parseClass(String.class).toPrintable());

    out.println(banner("Instance of " + String.class.getCanonicalName()));
    out.println(GraphLayout.parseInstance("FooBar").toPrintable());

    out.println(banner(String[].class));
    out.println(ClassLayout.parseClass(String[].class).toPrintable());

    out.println(banner("Instance of " + String[].class.getCanonicalName()));
    final String[] testDomain = { "One", "two", "three"};
    GraphLayout strAryGl = GraphLayout.parseInstance(testDomain);
    out.println(strAryGl.toPrintable());
    out.println(strAryGl.toFootprint());
    out.println("cmethod: " + cmethod(testDomain) + " bytes");
    out.println("difference (actual layout - cmethod): " + (strAryGl.totalSize() - cmethod(testDomain)) + " bytes");

  }

  private static long cmethod(String[] ss) {
    long dsz = (2/*hdr*/ + 1/*len*/ + ss.length) * 8;  // Size of base domain array
    for (String s : ss) {
      if (s != null) {
        dsz += 2 * s.length() + (2/*hdr*/ + 1/*value*/ + 1/*hash*/ + 2/*hdr*/ + 1/*len*/) * 8;
      }
    }
    return dsz;
  }

  private static String banner(Class klazz) {
    return "------------- " + klazz.getCanonicalName() + "------------- ";
  }

  private static String banner(String msg) {
    return "------------- " + msg + "------------- ";
  }
}
