package zipkin2.storage.scouter;

import scouter.util.StringUtil;
import zipkin2.storage.scouter.udp.ScouterConfig;

import java.util.Locale;
import java.util.Optional;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 31/10/2018
 */
public class ScouterConstants {
    public static final String OBJ_PREFIX = "/ZIPKIN/";
    public static final String OBJ_TYPE_PREFIX = "z$";
    public static final String UNKNOWN = "UNKNOWN";

    public static String toScouterObjName(String hostname, String name) {
        return Optional.ofNullable(name)
                .filter(StringUtil::isNotEmpty)
                .map(localServiceName -> StringUtil.isEmpty(hostname) ? OBJ_PREFIX + localServiceName : "/" + hostname + "/" + localServiceName)
                .orElse(ScouterConstants.OBJ_PREFIX + ScouterConstants.UNKNOWN);
    }

    public static String toScouterObjType(String name, ScouterConfig conf) {
        return Optional.ofNullable(name)
                .flatMap(localServiceName -> Optional.ofNullable(conf.getServiceToObjTypeMap().get(localServiceName)).map(serviceToObjType -> OBJ_TYPE_PREFIX + serviceToObjType))
                .orElse("zipkin");
    }
}
