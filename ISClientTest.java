import java.util.Locale;

import com.softwareag.net.IPAddressUtil;
import com.wm.app.b2b.client.ServiceException;
import com.wm.app.b2b.client.ns.ContextWrapper;
import com.wm.data.IData;
import com.wm.data.IDataFactory;
import com.wm.driver.comm.b2b.WmConnection;
import com.wm.driver.comm.b2b.WmConnectionFactory;
import com.wm.driver.comm.b2b.WmConnectionParms;
import com.wm.driver.comm.b2b.http.HTTPConnectionParms;
import com.wm.io.comm.CommException;
import com.wm.util.lifecycle.LifecycleManager;


public class ISClientTest {
	public static void main(String[] args) throws Exception, Exception {
		//args[0] = hostname e.g. localhost
		//args[1] = port e.g. 5555
		//args[2] = username e.g. Administrator
		//args[3] = password e.g. manage
		//
		HTTPConnectionParms connParam = new HTTPConnectionParms(IPAddressUtil.concatHostAndPortString(args[0], args[1]));
		connParam.setUserCredentials(args[2], args[3]);
		connParam.setUseSession(true);
		connParam.setSecure(false);
		connParam.setQOS(WmConnectionParms.QOS_VOLATILE);
		connParam.setLocale(Locale.getDefault());

		// add any proxy information to the connection parms
		//	usingProxy = ProxyHelper.getInstance().setProxyInformationForConnection(connParam);

		WmConnection gContext = WmConnectionFactory.create(connParam);
        ContextWrapper contextWrapper = new ContextWrapper(gContext);
        gContext.setLinkReady();
		IData response = contextWrapper.invoke("wm.server.schema:getRegistry", null, IDataFactory.create());

		System.out.println("response::.................." + response);
		gContext.disconnect();
	}
}
