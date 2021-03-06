/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ro.nextreports.server.web.action;

import javax.jcr.ReferentialIntegrityException;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.transaction.TransactionSystemException;

import ro.nextreports.server.domain.Entity;
import ro.nextreports.server.exception.NotFoundException;
import ro.nextreports.server.exception.ReferenceException;
import ro.nextreports.server.service.StorageService;
import ro.nextreports.server.util.StorageUtil;
import ro.nextreports.server.web.NextServerSession;
import ro.nextreports.server.web.common.behavior.AlertBehavior;
import ro.nextreports.server.web.common.event.AjaxUpdateEvent;
import ro.nextreports.server.web.core.EntityBrowserPanel;
import ro.nextreports.server.web.core.action.ActionConfirmAjaxLink;
import ro.nextreports.server.web.core.action.ActionContext;
import ro.nextreports.server.web.core.event.SelectEntityEvent;


import java.util.List;
import java.util.ArrayList;

/**
 * @author Decebal Suiu
 */
public class DeleteActionLink extends ActionConfirmAjaxLink {

    @SpringBean
    private StorageService storageService;

    public DeleteActionLink(ActionContext actionContext, String message) {
        super(actionContext, message);
    }

    public void executeAction(AjaxRequestTarget target) {
        EntityBrowserPanel panel = findParent(EntityBrowserPanel.class);
        if (getActionContext().isMenuAction()) {
            NextServerSession.get().setSearchContext(null);
            panel.restoreWorkspace(target);
        }

        String entityPath = getActionContext().getEntity().getPath();
        List<String> ids  = new ArrayList<String>();
        for (Entity entity : getActionContext().getEntities()) {
            ids.add(entity.getId());
        }        
        try {
            try {
                // IMPORTANT : see EntitiesRemoveAdvice , ReportRemovedAdvice, SchedulerJobRemovedAdvice,
                // UserRemovedAdvice
                storageService.removeEntitiesById(ids);
            } catch (TransactionSystemException e) {
                if (e.getRootCause() instanceof ReferentialIntegrityException) {
                    throw new ReferenceException(e.getRootCause());
                }
            }
            
            if (panel.isForward(target)) {
                new AjaxUpdateEvent(this, target).fire();
            } else {
                Entity parent = storageService.getEntity(StorageUtil.getParentPath(entityPath));
                new SelectEntityEvent(this, target, parent).fire();
            }
        } catch (ReferenceException e) {
            String message = e.getMessage();

            int index = message.indexOf(":");
            int lastIndex = message.lastIndexOf(":");
            if ((index != -1) && (lastIndex != -1) && (lastIndex > index)) {
                String id = message.substring(index + 2, lastIndex);
                Entity entity;
				try {
					entity = storageService.getEntityById(id);
	                message = "References to child entity \"" + entity.getName() + "\" exist.";
				} catch (NotFoundException e1) {
					// TODO
					e1.printStackTrace();
				}
            }
            
            add(new AlertBehavior(message));
            target.add(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
