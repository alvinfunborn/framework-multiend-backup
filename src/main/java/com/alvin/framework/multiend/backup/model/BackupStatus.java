package com.alvin.framework.multiend.backup.model;

/**
 * datetime 2019/5/6 17:10
 *
 * @author sin5
 */
public enum BackupStatus {

    /**
     * backup succeeded
     */
    ok,
    /**
     * backup failed
     */
    error,
    /**
     * version conflict
     */
    version_conflict;
}
