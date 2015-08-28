package com.sdl.webapp.main.taglib.dxa;

import com.sdl.webapp.common.api.model.EntityModel;
import com.sdl.webapp.common.api.model.PageModel;
import com.sdl.webapp.common.api.model.RegionModel;
import com.sdl.webapp.common.markup.AbstractMarkupTag;
import com.sdl.webapp.common.controller.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import java.io.IOException;

import static com.sdl.webapp.common.controller.RequestAttributeNames.PAGE_MODEL;

public class EntityTag extends AbstractMarkupTag {
    private static final Logger LOG = LoggerFactory.getLogger(EntityTag.class);

    private String regionName;

    private String entityId;

    private RegionModel parentRegion;
    
    private EntityModel entityRef;

    public void setRegion(String regionName) {
        this.regionName = regionName;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
    public void setParentRegion(RegionModel parent)
    {
    	this.parentRegion = parent;
    }
    public void setEntity(EntityModel entity) { this.entityRef = entity; }

    @Override
    public int doStartTag() throws JspException {
        final PageModel page = (PageModel) pageContext.getRequest().getAttribute(PAGE_MODEL);

        if (page == null) {
            LOG.debug("Page not found in request attributes");
            return SKIP_BODY;
        }
        RegionModel region = null;
        final EntityModel entity;
        if ( entityId != null ) {
        	 
             if(parentRegion != null)
             {
             	region = parentRegion;	
             }
             else
             {
             	region = page.getRegions().get(regionName);
             }
            if (region == null) {
                LOG.debug("Region not found on page: {}", regionName);
                return SKIP_BODY;
            }

            //entity = region.getEntities().get(entityId);
            entity = region.getEntity(entityId);
        }
        else {
            entity = entityRef;
        }
        if (entity != null) {
            LOG.debug("Including entity: {}/{}", regionName, entity.getId());
            try {
            	pageContext.getRequest().setAttribute("_region_" + regionName, region);
                //pageContext.include(ControllerUtils.getIncludePath(entity));
                this.decorateInclude(ControllerUtils.getIncludePath(entity), entity);
            } catch (ServletException | IOException e) {
                throw new JspException("Error while processing entity tag", e);
            }
        } else {
            LOG.debug("Entity not found in region: {}/{}", regionName, entity.getId());
        }

        return SKIP_BODY;
    }
}
