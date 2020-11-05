
package helseapp.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import helseapp.core.Dag;
import helseapp.core.Dager;

public class DagerModule extends SimpleModule {

  // Module name
  private static final String NAME = "DagModule";

  /**
   * Initializes this dagerModule with appropriate serializers and deserializers.
   */
  public DagerModule() {
    super(NAME, Version.unknownVersion());
    addSerializer(Dag.class, new DagSerializer());
    addSerializer(Dager.class, new DagerSerializer());
    addDeserializer(Dag.class, new DagDeserializer());
    addDeserializer(Dager.class, new DagerDeserializer());
  }

}