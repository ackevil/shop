package com.huituopin.common.utils;

import javax.persistence.Entity;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Entity
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDbType();
    }
}
