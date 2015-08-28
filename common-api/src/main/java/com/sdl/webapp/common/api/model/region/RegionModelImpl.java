package com.sdl.webapp.common.api.model.region;

import com.google.common.collect.ImmutableMap;
import com.sdl.webapp.common.api.model.EntityModel;
import com.sdl.webapp.common.api.model.MvcData;
import com.sdl.webapp.common.api.model.RegionModel;
import com.sdl.webapp.common.api.model.RegionModelSet;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implementation of {@code Region}.
 */
public class RegionModelImpl implements RegionModel {

    private String name;
    private Map<String, EntityModel> entities = new LinkedHashMap<>();
    private Map<String, String> xpmMetadata = new HashMap<>();
    private MvcData mvcData;
    private RegionModelSet regions;

    /*
     * The XPM metadata key used for the ID of the (Include) Page from which the Region originates. Avoid using this in implementation code because it may change in a future release.
     */
    public static final String IncludedFromPageIdXpmMetadataKey = "IncludedFromPageID";

    /*
     * The XPM metadata key used for the title of the (Include) Page from which the Region originates. Avoid using this in implementation code because it may change in a future release.
     */
    public static final String IncludedFromPageTitleXpmMetadataKey = "IncludedFromPageTitle";

    /*
     * The XPM metadata key used for the file name of the (Include) Page from which the Region originates. Avoid using this in implementation code because it may change in a future release.
     */
    public static final String IncludedFromPageFileNameXpmMetadataKey = "IncludedFromPageFileName";
    
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Map<String, EntityModel> getEntities() {
        return entities;
    }

    public void setEntities(Map<String, EntityModel> entities) {
        this.entities = entities;
    }

    public void addEntity(EntityModel entity) {
        this.entities.put(entity.getId(), entity);
    }

    @Override
    public EntityModel getEntity(String entityId) {
        return this.entities.get(entityId);
    }

    @Override
    public Map<String, String> getXpmMetadata() {
        return xpmMetadata;
    }

    public void setXpmMetadata(Map<String, String> xpmMetadata) {
        this.xpmMetadata = ImmutableMap.copyOf(xpmMetadata);
    }

    @Override
    public MvcData getMvcData() {
        return mvcData;
    }

    public void setMvcData(MvcData mvcData) {
        this.mvcData = mvcData;
    }

    @Override
    public RegionModelSet getRegions() {
        return regions;
    }

    public void setRegions(RegionModelSet mvcData) {
        this.regions = mvcData;
    }

    
        
    
    @Override
    public String toString() {
        return "RegionImpl{" +
                "name='" + name + '\'' +
                ", entities=" + entities +
                ", mvcData='" + mvcData + '\'' +
                ", regions='" + regions + '\'' +
                '}';
    }
}
