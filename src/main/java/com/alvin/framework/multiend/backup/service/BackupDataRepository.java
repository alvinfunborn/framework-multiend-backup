package com.alvin.framework.multiend.backup.service;

import com.alvin.framework.multiend.backup.model.BackupData;
import com.alvin.framework.multiend.backup.model.BackupStrategy;

import java.util.List;

/**
 * datetime 2019/5/6 12:00
 *
 * @author sin5
 */
public interface BackupDataRepository {

    void save(BackupData data);

    void deleteAllByKeyAndStrategy(String key, BackupStrategy strategy);

    BackupData findFirstByKeyOrderByVersionDesc(String key);

    List<BackupData> findAllByKeyAndStrategyOrderByVersionDesc(String key, BackupStrategy strategy);
}
