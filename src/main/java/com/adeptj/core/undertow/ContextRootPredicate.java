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
package com.adeptj.core.undertow;

import com.adeptj.core.util.Constants;

import io.undertow.predicate.Predicate;
import io.undertow.server.HttpServerExchange;

/**
 * Predicate checks if the request is for context root "/".
 * 
 * @author Rakesh.Kumar, AdeptJ
 */
public class ContextRootPredicate implements Predicate {

	@Override
	public boolean resolve(HttpServerExchange exchange) {
		return Constants.CONTEXT_PATH.equals(exchange.getRequestURI());
	}

}