package com.alvin.framework.multiend.backup.backup;

import com.alvin.framework.multiend.backup.model.BackupData;
import com.alvin.framework.multiend.backup.model.BackupResult;
import com.alvin.framework.multiend.backup.model.BackupStrategy;
import com.alvin.framework.multiend.backup.service.BackupDataRepository;
import com.alvin.framework.multiend.backup.service.BackupLocker;
import com.alvin.framework.multiend.backup.service.VersionGenerator;

import java.util.List;

/**
 * datetime 2019/5/6 12:00
 *
 * @author sin5
 */
public class StandardBackup implements Backup {

    private BackupDataRepository backupDataRepository;
    private VersionGenerator versionGenerator;
    private BackupLocker backupLocker;

    public StandardBackup(BackupDataRepository backupDataRepository,
                          VersionGenerator versionGenerator,
                          BackupLocker backupLocker) {
        this.backupDataRepository = backupDataRepository;
        this.versionGenerator = versionGenerator;
        this.backupLocker = backupLocker;
    }

    @Override
    public BackupResult backup(BackupData backupData) {
        String key = backupData.getKey();
        try {
            backupLocker.lock(key);
            BackupStrategy strategy = backupData.getStrategy();
            String version = backupData.getVersion();
            BackupData existed = backupDataRepository.findFirstByKeyOrderByVersionDesc(key);
            String existedVersion = existed.getVersion();
            if (version != null && versionGenerator.compare(version, existedVersion) < 0) {
                // version conflict
                return BackupResult.ofVersionConflict(existedVersion);
            }
            String nextVersion = version == null ? versionGenerator.initVersion() : versionGenerator.nextVersion(version);
            backupData.setVersion(nextVersion);
            switch (strategy) {
                case full:
                    backupDataRepository.deleteAllByKeyAndStrategy(key, BackupStrategy.full);
                    backupDataRepository.deleteAllByKeyAndStrategy(key, BackupStrategy.differential);
                    backupDataRepository.deleteAllByKeyAndStrategy(key, BackupStrategy.incremental);
                    backupDataRepository.save(backupData);
                case differential:
                    backupDataRepository.deleteAllByKeyAndStrategy(key, BackupStrategy.differential);
                    backupDataRepository.deleteAllByKeyAndStrategy(key, BackupStrategy.incremental);
                    backupDataRepository.save(backupData);
                case incremental:
                    backupDataRepository.save(backupData);
                    default: break;
            }
            return BackupResult.ofSuccess(nextVersion);
        } catch (Exception e) {
            return BackupResult.ofError(e.getMessage());
        } finally {
            backupLocker.unlock(key);
        }
    }

    @Override
    public void delete(String key) {
        try {
            backupLocker.lock(key);
            backupDataRepository.deleteAllByKeyAndStrategy(key, BackupStrategy.full);
            backupDataRepository.deleteAllByKeyAndStrategy(key, BackupStrategy.differential);
            backupDataRepository.deleteAllByKeyAndStrategy(key, BackupStrategy.incremental);
        } finally {
            backupLocker.unlock(key);
        }
    }

    @Override
    public void delete(String key, BackupStrategy strategy) {
        try {
            backupLocker.lock(key);
            backupDataRepository.deleteAllByKeyAndStrategy(key, strategy);
        } finally {
            backupLocker.unlock(key);
        }
    }

    @Override
    public List<BackupData> restore(String key) {
        try {
            backupLocker.lock(key);
            List<BackupData> result = backupDataRepository.findAllByKeyAndStrategyOrderByVersionDesc(key, BackupStrategy.full);
            result.addAll(backupDataRepository.findAllByKeyAndStrategyOrderByVersionDesc(key, BackupStrategy.differential));
            result.addAll(backupDataRepository.findAllByKeyAndStrategyOrderByVersionDesc(key, BackupStrategy.incremental));
            return result;
        } finally {
            backupLocker.unlock(key);
        }
    }
}
