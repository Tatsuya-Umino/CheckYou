import play.*;
import play.api.mvc.EssentialFilter;
import play.filters.csrf.*;

public class Global extends GlobalSettings {

  // CSRFフィルター
  @Override
  public <T extends EssentialFilter> Class<T>[] filters() {
    return new Class[]{CSRFFilter.class};
  }
}