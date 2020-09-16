

package helseapp.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import helseapp.core.Dag;
import helseapp.core.Dager;

public class DagerModule extends Module {

    @Override
    public String getModuleName() {
        return "DagerModule";
    }

    @Override
    public Version version() {
        return Version.unknownVersion();
    }

    private final SimpleSerializers serializers = new SimpleSerializers();

    public DagerModule() {
        serializers.addSerializer(Dag.class, new DagSerializer());
        serializers.addSerializer(Dager.class, new DagerSerializer());
    }

    @Override
    public void setupModule(final SetupContext context) {
        context.addSerializers(serializers);
    }
}