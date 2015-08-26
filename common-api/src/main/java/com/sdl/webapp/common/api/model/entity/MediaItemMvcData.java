package com.sdl.webapp.common.api.model.entity;

import com.sdl.webapp.common.api.model.MvcData;
import com.sdl.webapp.common.exceptions.DxaException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * SimpleRegionMvcData
 *
 * @author hho
 */
public class MediaItemMvcData implements MvcData {

    private String controllerAreaName = "Core";
    private String controllerName = "Entity";
    private String actionName = "Region";
    private String areaName = "Core";
    private String viewName;

    private String regionAreaName;
    private String regionName;

    private Map<String, String> routeValues = new HashMap<>();
    private Map<String,Object> metadata = new HashMap<>();
    
    public MediaItemMvcData(String qualifiedViewName) throws DxaException
    {
        String[] qualifiedViewNameParts = qualifiedViewName.split(":");
        switch (qualifiedViewNameParts.length)
        {
            case 1:
                this.setAreaName("core");
                this.setViewName(qualifiedViewNameParts[0]);
                break;
            case 2:
            	this.setAreaName(qualifiedViewNameParts[0]);
            	this.setViewName(qualifiedViewNameParts[1]);
                break;
            case 3:
            	this.setAreaName(qualifiedViewNameParts[0]);
            	this.setControllerName(qualifiedViewNameParts[1]);
            	this.setViewName(qualifiedViewNameParts[2]);
                break;
            default:
                throw new DxaException(
                    String.format("Invalid format for Qualified View Name: '{0}'. Format must be 'ViewName' or 'AreaName:ViewName' or 'AreaName:ControllerName:Vieweame.'", 
                        qualifiedViewName)
                        );
        }
    }

    @Override
    public String getControllerAreaName() {
        return controllerAreaName;
    }

    public void setControllerAreaName(String controllerAreaName) {
        this.controllerAreaName = controllerAreaName;
    }

    @Override
    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    @Override
    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public String getRegionAreaName() {
        return regionAreaName;
    }

    public void setRegionAreaName(String regionAreaName) {
        this.regionAreaName = regionAreaName;
    }

    @Override
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public Map<String, String> getRouteValues() {
        return routeValues;
    }

    public void setRouteValues(Map<String, String> routeValues) {
        this.routeValues = routeValues;
    }

    @Override
    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "MediaItemMvcData{" +
                "controllerAreaName='" + controllerAreaName + '\'' +
                ", controllerName='" + controllerName + '\'' +
                ", actionName='" + actionName + '\'' +
                ", areaName='" + areaName + '\'' +
                ", viewName='" + viewName + '\'' +
                ", regionAreaName='" + regionAreaName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", routeValues=" + routeValues +
                ", metadata=" + metadata +
                '}';
    }
}