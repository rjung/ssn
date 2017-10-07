package org.rjung.ssn.org.rjung.ssn.db;

import java.util.Date;

public class Attempt {

    private Date started;
    private Date lastUpdated;
    private Date ended;

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Long getStartedStore() {
        return started == null ? null : started.getTime();
    }

    public void setStartedStore(Long started) {
        this.started = started == null ? null : new Date(started);
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Long getLastUpdatedStore() {
        return lastUpdated == null ? null : lastUpdated.getTime();
    }

    public void setLastUpdatedStore(Long lastUpdated) {
        this.lastUpdated = lastUpdated == null ? null : new Date(lastUpdated);
    }

    public Date getEnded() {
        return ended;
    }

    public Long getEndedStore() {
        return ended == null ? null : ended.getTime();
    }

    public void setEndedStore(Long ended) {
        this.ended = ended == null ? null : new Date(ended);
    }

    public boolean isEnded() {
        return ended != null;
    }

    public void setEnded(Date ended) {
        this.ended = ended;
    }

}
