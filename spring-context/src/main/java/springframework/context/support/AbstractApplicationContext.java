package springframework.context.support;

import org.springframework.core.io.DefaultResourceLoader;
import springframework.context.ConfigurableApplicationContext;

public abstract class AbstractApplicationContext extends DefaultResourceLoader
        implements ConfigurableApplicationContext {


    @Override
    public void close() {
    }
}
