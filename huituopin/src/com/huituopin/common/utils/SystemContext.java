package com.huituopin.common.utils;

public class SystemContext {
    private static ThreadLocal offset = new ThreadLocal();

    private static ThreadLocal pagesize = new ThreadLocal();

    public static int getOffset() {
        Integer os = (Integer) offset.get();
        if (os == null) {
            return 0;
        }
        return os;
    }

    public static void setOffset(int offsetvalue) {
        offset.set(offsetvalue);
    }

    public static void removeOffset() {
        offset.remove();
    }

    public static int getPagesize() {
        Integer ps = (Integer) pagesize.get();
        if (ps == null) {
            return Integer.MAX_VALUE;
        }
        return ps;
    }

    public static void setPagesize(int pagesizevalue) {
        pagesize.set(pagesizevalue);
    }

    public static void removePagesize() {
        pagesize.remove();
    }

}