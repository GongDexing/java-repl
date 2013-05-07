package javarepl.console.rest;

import com.googlecode.utterlyidle.ServerConfiguration;
import com.googlecode.utterlyidle.httpserver.RestServer;
import javarepl.console.Console;
import javarepl.console.DelegatingConsole;

import static com.googlecode.utterlyidle.BasePath.basePath;
import static com.googlecode.utterlyidle.ServerConfiguration.defaultConfiguration;

public class RestConsole extends DelegatingConsole {
    private final RestServer server;

    public RestConsole(Console console, Integer port) throws Exception {
        super(console);

        ServerConfiguration configuration = defaultConfiguration().port(port);
        RestConsoleApplication application = new RestConsoleApplication(basePath("/"), this);
        server = new RestServer(application, configuration);
    }

    @Override
    public void shutdown() {
        try {
            server.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.shutdown();
    }
}
