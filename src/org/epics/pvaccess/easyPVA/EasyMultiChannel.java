/**
 * 
 */
package org.epics.pvaccess.easyPVA;

import org.epics.pvdata.pv.PVStructure;
import org.epics.pvdata.pv.Status;

/**
 * @author mrk
 *
 */
public interface EasyMultiChannel{
    /**
     * Clean up.
     */
    void destroy();
    /**
     * Calls issueConnect and then waitConnect.
     * @param timeout timeOut for waitConnect.
     * @return
     */
    boolean connect(double timeout);
    /**
     * Connect to all channels.
     */
    void issueConnect();
    /**
     * Wait until all channels are connected or until timeout.
     * @param timeout The time to wait for connections.
     * When a timeout occurs a new time out will start if any channels connected since the last timeout.
     * @return (false,true) of all channels (did not, did) connect.
     */
    boolean waitConnect(double timeout);
    /**
     * Are all channels connected?
     * @return if all are connected.
     */
    boolean allConnected();
    /**
     * Get the connection state of each channel.
     * @return The state of each channel.
     */
    boolean[] isConnected();
    /**
     * create a multiChannelGet that presents data as a NTMultiChannel.
     * calls the next method with request = "field(value,alarm,timeStamp)"
     * @return The interface.
     */
    EasyMultiGet createGet(); 
    /**
     * create a multiChannelGet.
     * calls the next method after creating a pvRequest structure.
     * @param request A request string valid for creatRequest.
     * @return The interface.
     */
    EasyMultiGet createGet(String request);
    /**
     * create a multiChannelGet.
     * @param pvRequest The pvRequest for each channel.
     * @return The interface.
     */
    EasyMultiGet createGet(PVStructure pvRequest);
    /**
     * create a multiChannelGet.
     * @param doubleOnly true if data presented as a double[].
     * @return The interface.
     */
    EasyMultiGet createGet(boolean doubleOnly); 
    /**
     * create a multiChannelGet.
     * calls the next method with request = "field(value)"
     * @param doubleOnly true if data presented as a double[].
     * @param request  A request string valid for creatRequest.
     * @return
     */
    EasyMultiGet createGet(boolean doubleOnly,String request);
    /**
     * create a multiChannelGet.
     * @param doubleOnly true if data presented as a double[].
     * @param pvRequest The pvRequest for each channel.
     * @return
     */
    EasyMultiGet createGet(boolean doubleOnly,PVStructure pvRequest);
    /**
     * create a multiChannelPut.
     * @return The interface.
     */
    EasyMultiPut createPut();
    /**
     * create a multiChannelPut.
     * @param doubleOnly true if data must be presented as a double[].
     * @return
     */
    EasyMultiPut createPut(boolean doubleOnly);
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
