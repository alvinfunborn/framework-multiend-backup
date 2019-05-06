package com.alvin.framework.multiend.backup.service;

/**
 * datetime 2019/5/6 15:07
 *
 * @author sin5
 */
public interface BackupLocker {

    /**
     * lock key of backup data
     *
     * @param key key of backup data
     */
    void lock(String key);

    /**
     * unlock key of backup data
     *
     * @param key key of backup data
     */
    void unlock(String key);
}
