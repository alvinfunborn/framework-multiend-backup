package com.alvin.framework.multiend.backup.backup;

import com.alvin.framework.multiend.backup.model.BackupData;
import com.alvin.framework.multiend.backup.model.BackupResult;
import com.alvin.framework.multiend.backup.model.BackupStrategy;

import java.util.List;

/**
 * datetime 2019/5/6 11:07
 *
 * @author sin5
 */
public interface Backup {

    /**
     * backup data
     *
     * @param backupData data obj
     * @return result obj
     */
    BackupResult backup(BackupData backupData);

    /**
     * delete backup data
     *
     * @param key key
     */
    void delete(String key);

    /**
     * delete backup data
     *
     * @param key key
     * @param strategy backup strategy
     */
    void delete(String key, BackupStrategy strategy);

    /**
     * restore data
     * @param key key
     * @return data obj
     */
    List<BackupData> restore(String key);
}
