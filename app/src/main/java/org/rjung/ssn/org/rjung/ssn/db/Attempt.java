package org.rjung.ssn.org.rjung.ssn.db;

import java.util.Date;

public class Attempt {

    private Date started;
    private Date updated;
    private Date finished;

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

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Long getUpdatedStore() {
        return updated == null ? null : updated.getTime();
    }

    public void setUpdatedStore(Long updated) {
        this.updated = updated == null ? null : new Date(updated);
    }

    public Date getFinished() {
        return finished;
    }

    public Long getFinishedStore() {
        return finished == null ? null : finished.getTime();
    }

    public void setFinishedStore(Long finished) {
        this.finished = finished == null ? null : new Date(finished);
    }

    public boolean isFinished() {
        return finished != null;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

}
