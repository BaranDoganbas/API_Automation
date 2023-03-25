package herokuapp_smoketest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        S01_Post.class,
        S02_Put.class,
        S03_Delete.class,
        S04_Get.class

})

public class Runner {//Oluşturulan classları sırayla çalıştırmak için bir Runner class oluşturuyoruz

}