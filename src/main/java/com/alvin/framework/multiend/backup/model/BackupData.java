package com.alvin.framework.multiend.backup.model;

/**
 * datetime 2019/5/6 11:07
 *
 * @author sin5
 */
public class BackupData {

    /**
     * data key
     */
    private String key;
    /**
     * data value
     */
    private byte[] value;
    /**
     * data version
     */
    private String version;
    /**
     * backup strategy
     * 1. full backup
     * 2. differential backup
     * 3. incremental backup
     */
    private BackupStrategy strategy;

    public BackupData() {
    }

    public BackupData(String key, byte[] value, String version, BackupStrategy strategy) {
        this.key = key;
        this.value = value;
        this.version = version;
        this.strategy = strategy;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public BackupStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(BackupStrategy strategy) {
        this.strategy = strategy;
    }
}
