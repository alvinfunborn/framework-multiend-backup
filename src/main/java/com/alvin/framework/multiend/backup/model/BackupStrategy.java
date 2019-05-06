package com.alvin.framework.multiend.backup.model;

/**
 * datetime 2019/5/6 11:30
 *
 * @author sin5
 */
public enum BackupStrategy {

    /**
     * full backup
     */
    full,
    /**
     * incremental backup
     */
    incremental,
    /**
     * differential backup
     */
    differential;
}
