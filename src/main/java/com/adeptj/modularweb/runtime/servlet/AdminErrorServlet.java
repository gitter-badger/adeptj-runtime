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
package com.adeptj.modularweb.runtime.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.exceptions.TemplateInputException;

import com.adeptj.modularweb.runtime.viewengine.Models;
import com.adeptj.modularweb.runtime.viewengine.ViewEngine;
import com.adeptj.modularweb.runtime.viewengine.ViewEngineContext;

/**
 * OSGi AdminErrorServlet that serves the error page w.r.t status(401, 403, 404, 500 etc.).
 *
 * @author Rakesh.Kumar, AdeptJ
 */
@WebServlet(name = "AdminErrorServlet", urlPatterns = { "/admin/error/*" })
public class AdminErrorServlet extends HttpServlet {

	private static final long serialVersionUID = -3339904764769823449L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestURI = req.getRequestURI();
		Models models = new Models();
		if ("/admin/error".equals(requestURI)) {
			ViewEngine.THYMELEAF
					.processView(new ViewEngineContext("error/generic", models, req, resp, req.getLocale()));
		} else {
			Object exception = req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
			String statusCode = this.getStatusCode(requestURI);
			if (exception != null && "500".equals(statusCode)) {
				models.put("exception", req.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
				ViewEngine.THYMELEAF
						.processView(new ViewEngineContext("error/500", models, req, resp, req.getLocale()));
			} else {
				try {
					ViewEngine.THYMELEAF.processView(new ViewEngineContext(String.format("error/%s", statusCode),
							models, req, resp, req.getLocale()));
				} catch (TemplateInputException ex) {
					ViewEngine.THYMELEAF.processView(new ViewEngineContext(String.format("error/404", statusCode),
							models, req, resp, req.getLocale()));
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

	private String getStatusCode(String requestURI) {
		return requestURI.substring(requestURI.lastIndexOf('/') + 1);
	}
}