# 2014/10/24

## Lambda式はどう見えるか

```
$ cat Lambdas.java                                                   [~/src/.../ch1/sec02]
```

```java
import java.util.*;
import javafx.event.*;

public class Lambdas {
   public static void main(String[] args) {
      Comparator<String> comp = 
         (String first, String second)
            -> Integer.compare(first.length(), second.length());

      comp = 
         (String first, String second) -> {
            if (first.length() < second.length()) return -1;
            else if (first.length() > second.length()) return 1;
            else return 0;
         };

      Runnable runner =
         () -> { for (int i = 0; i < 1000; i++) doWork(); };

      comp =
        (first, second) // Same as (String first, String second)
          -> Integer.compare(first.length(), second.length());

      EventHandler<ActionEvent> listener = e -> System.out.println(e.getTarget());
         // Instead of (e) -> or (ActionEvent e) ->
   }

   public static void doWork() {
   }
}
```

javac&javap

```
$ javac Lambdas.java                                                 [~/src/.../ch1/sec02]
$ ls                                                                 [~/src/.../ch1/sec02]
Lambdas.class Lambdas.java
$ javap -p Lambdas                                                   [~/src/.../ch1/sec02]
Compiled from "Lambdas.java"
public class Lambdas {
  public Lambdas();
  public static void main(java.lang.String[]);
  public static void doWork();
  private static void lambda$main$4(javafx.event.ActionEvent);
  private static int lambda$main$3(java.lang.String, java.lang.String);
  private static void lambda$main$2();
  private static int lambda$main$1(java.lang.String, java.lang.String);
  private static int lambda$main$0(java.lang.String, java.lang.String);
}
```

classファイルは一つだけ生成される。無名クラスの振る舞いとは異なる。
