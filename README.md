#### 多端数据备份恢复框架
支持完全备份, 差异备份, 增量备份

##### usage
1. 实现BackupDataRepository
2. 实现BackupLocker
3. 实现VersionGenerator

###### config
```$xslt
@Configuration
public class BackupConfig {

    @Autowired
    private BackupLocker backupLocker;
    @Autowired
    private BackupDataRepository backupDataRepository;
    @Autowired
    private VersionGenerator versionGenerator;

    @Bean
    public Backup backup() {
        return new StandardBackup(backupDataRepository, versionGenerator, backupLocker);
    }
}

```

###### service
```$xslt
@Service
public class Service {
    
    @Autowired
    private Backup backup;
    
    public void backup() {
        backup.backup(new BackupData("key1", null, null, BackupStrategy.full));
    }
}
```