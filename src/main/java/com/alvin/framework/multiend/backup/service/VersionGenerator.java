package com.alvin.framework.multiend.backup.service;

/**
 * datetime 2019/5/6 14:29
 *
 * @author sin5
 */
public interface VersionGenerator {

    /**
     * generate new version
     *
     * @return new version
     */
    String initVersion();

    /**
     * generate next version after this one
     *
     * @param version version
     * @return next version
     */
    String nextVersion(String version);

    /**
     * compare version1 and version2
     *
     * @param version1 version1
     * @param version2 version2
     * @return negative value for less, 0 for equal, positive value for larger
     */
    int compare(String version1, String version2);
}
