/**
 * 
 */
package org.epics.ca.easyPVA;
import org.epics.pvdata.factory.FieldFactory;
import org.epics.pvdata.factory.PVDataFactory;
import org.epics.pvdata.pv.Field;
import org.epics.pvdata.pv.FieldCreate;
import org.epics.pvdata.pv.PVStructure;
import org.epics.pvdata.pv.ScalarType;
import org.epics.pvdata.pv.Structure;

/**
 * @author mrk
 *
 */
public class ExampleEasyRPC {
    static EasyPVA easyPVA = EasyPVAFactory.get();
   
	private final static FieldCreate fieldCreate = FieldFactory.getFieldCreate();
	
	private final static Structure requestStructure =
		fieldCreate.createStructure(
				new String[] { "a", "b" },
				new Field[] { fieldCreate.createScalar(ScalarType.pvString),
							  fieldCreate.createScalar(ScalarType.pvString) }
				);

	public static void main(String[] args) {
		exampleRPC("sum");
		exampleRPCCheck("sum");
        easyPVA.destroy();
        System.out.println("all done");
    }

    static void exampleRPC(String channelName) {
		PVStructure request = PVDataFactory.getPVDataCreate().createPVStructure(requestStructure);
		request.getStringField("a").put("12.3");
		request.getStringField("b").put("45.6");

		PVStructure result = easyPVA.createChannel(channelName).createRPC().request(request);
        System.out.println(request +"\n =\n" + result);
    }
    
    static void exampleRPCCheck(String channelName) {
        EasyChannel channel =  easyPVA.createChannel(channelName);
        boolean result = channel.connect(2.0);
        if(!result) {
            System.out.printf(
                "exampleRPCCheck %s channel connect failed %s%n",
                channelName,
                channel.getStatus());
            return;
        }
        EasyRPC rpc = channel.createRPC();
        result = rpc.connect();
        if(!result) {
            System.out.printf(
                "exampleRPCCheck %s rpc connect failed %s%n",
                channelName,
                rpc.getStatus());
            return;
        }

		PVStructure request = PVDataFactory.getPVDataCreate().createPVStructure(requestStructure);
		request.getStringField("a").put("12.3");
		request.getStringField("b").put("45.6");

		rpc.issueRequest(request);
        PVStructure rpcResult = rpc.waitRequest();
        if(rpcResult==null) {
            System.out.printf(
                "exampleRPCCheck %s rpc failed %s%n",
                channelName,
                rpc.getStatus());
            return;
        }
        System.out.printf("%s %s%n",channelName,rpcResult.toString());
        channel.destroy();
    }

}
