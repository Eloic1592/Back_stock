package itusolar.utils;

import bean.CGenUtil;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class HUtils extends CGenUtil {

    public static long timestampToLong(Timestamp timestamp) {
        return timestamp.getTime();
    }
    public static void addMethods(Class<?> clazz, ArrayList<Method> methods) {
        methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        if (clazz.getSuperclass() != null)
            HUtils.addMethods(clazz.getSuperclass(), methods);
    }

    /**
     * Gets third parameter type of given method .
     *
     * @param method the method
     * @return the third parm type
     */
    public static Class<?> getThirdParmType(Method method) {
        return HUtils.getParamType(method, 2);
    }

    public static Class<?> getParamType(Method method,int index) {
        Class[] classes = method.getParameterTypes();
        return (classes.length > index)? classes[index] : null;
    }
}
