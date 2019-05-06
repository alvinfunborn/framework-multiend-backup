package com.alvin.framework.multiend.backup.model;

/**
 * datetime 2019/5/6 17:09
 *
 * @author sin5
 */
public class BackupResult {

    /**
     * backup status
     * @see BackupStatus
     */
    private String status;
    /**
     * latest version
     */
    private String version;
    /**
     * error message
     */
    private String errmsg;

    public static BackupResult ofSuccess(String version) {
        BackupResult result = new BackupResult();
        result.setStatus(BackupStatus.ok.name());
        result.setVersion(version);
        return result;
    }

    public static BackupResult ofError(String message) {
        BackupResult result = new BackupResult();
        result.setStatus(BackupStatus.error.name());
        result.setErrmsg(message);
        return result;
    }

    public static BackupResult ofVersionConflict(String version) {
        BackupResult result = new BackupResult();
        result.setStatus(BackupStatus.version_conflict.name());
        result.setVersion(version);
        return result;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
