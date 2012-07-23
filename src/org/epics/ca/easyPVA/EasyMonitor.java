/**
 * 
 */
package org.epics.ca.easyPVA;

import org.epics.pvdata.misc.BitSet;
import org.epics.pvdata.monitor.MonitorRequester;
import org.epics.pvdata.pv.PVStructure;
import org.epics.pvdata.pv.Status;

/**
 * @author mrk
 *
 */
public interface EasyMonitor {
    void destroy();
    boolean connect();
    void issueConnect();
    boolean waitConnect();
    void setRequester(MonitorRequester monitorRequester);
    void start();
    void stop();
    PVStructure getEvent();
    BitSet getChangedBitSet();
    BitSet getOverrunBitSet();
    void releaseEvent();
    /**
     * Set a new status value. The new value will replace the current status. The initial status is statusOK.
     * @param status The new status.
     */
    void setStatus(Status status);
    /**
     * Get the latest status. Calling this resets the latest status to statusOK.
     * @return The status.
     */
    Status getStatus();

}
