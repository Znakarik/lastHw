import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import runners.FirstCucumberRunner;
import runners.SecondCucumberRunner;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        FirstCucumberRunner.class,
        SecondCucumberRunner.class
})
public class Test {
}
