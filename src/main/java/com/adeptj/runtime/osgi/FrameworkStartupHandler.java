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

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.adeptj.runtime.common.StartupOrder;
import com.adeptj.runtime.initializer.StartupHandler;
import com.adeptj.runtime.sci.StartupHandlerInitializer;

/**
 * StartupHandler is a {@link javax.servlet.annotation.HandlesTypes} that handles the OSGi Framework startup.
 *
 * @author Rakesh.Kumar, AdeptJ
 */
@StartupOrder(0)
public class FrameworkStartupHandler implements StartupHandler {
	
    /**
     * This method will be called by the {@link StartupHandlerInitializer} while application startup is in
     * progress.
     *
     * @param context
     * @throws ServletException
     */
    @Override
	public void onStartup(ServletContext context) throws ServletException {
		FrameworkProvisioner.INSTANCE.startFramework(context);
	}
}
