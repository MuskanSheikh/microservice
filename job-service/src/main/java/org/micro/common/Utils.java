package org.micro.common;

import java.util.List;

public class Utils {
    public static boolean isValidList(List<?> list) {
        return list != null && !list.isEmpty();
    }
}
