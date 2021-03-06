/** 
###############################################################################
#                                                                             # 
#    Copyright 2016, AdeptJ (http://adeptj.com)                               #
#                                                                             #
#    Licensed under the Apache License, Version 2.0 (the "License");          #
#    you may not use this file except in compliance with the License.         #
#    You may obtain a copy of the License at                                  #
#                                                                             #
#        http://www.apache.org/licenses/LICENSE-2.0                           #
#                                                                             #
#    Unless required by applicable law or agreed to in writing, software      #
#    distributed under the License is distributed on an "AS IS" BASIS,        #
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. #
#    See the License for the specific language governing permissions and      #
#    limitations under the License.                                           #
#                                                                             #
###############################################################################
*/
package com.adeptj.runtime.osgi;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;

/**
 * BridgeHttpSessionIdListener.
 *
 * @author Rakesh.Kumar, AdeptJ.
 */
public class BridgeHttpSessionIdListener implements HttpSessionIdListener {

	@Override
	public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
		HttpSessionIdListener sessionIdDispatcher = this.getHttpSessionIdListener();
		if (sessionIdDispatcher != null) {
			sessionIdDispatcher.sessionIdChanged(event, oldSessionId);
		}
	}

	private HttpSessionIdListener getHttpSessionIdListener() {
		HttpSessionIdListener listener = null;
		EventDispatcherTracker tracker = EventDispatcherTrackerSupport.INSTANCE.getEventDispatcherTracker();
		if (tracker != null) {
			listener = tracker.getHttpSessionIdListener();
		}
		return listener;
	}
}
