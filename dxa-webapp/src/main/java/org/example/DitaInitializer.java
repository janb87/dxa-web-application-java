package org.example;

import org.example.entity.Topic;
import com.sdl.webapp.common.api.mapping.views.AbstractInitializer;
import com.sdl.webapp.common.api.mapping.views.ModuleInfo;
import com.sdl.webapp.common.api.mapping.views.RegisteredViewModel;
import com.sdl.webapp.common.api.mapping.views.RegisteredViewModels;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@org.springframework.context.annotation.Configuration
//todo dxa2 move to com.sdl.dxa.modules.*
@ComponentScan("org.example")
public class DitaInitializer {

    @RegisteredViewModels({
            @RegisteredViewModel(viewName = "Topic", modelClass = Topic.class),
    })
    @Component
    @ModuleInfo(name = "Dita module", areaName = "Dita", description = "Dita DXA module which contains basic views")
    public static class DitaViewInitializer extends AbstractInitializer {
        @Override
        protected String getAreaName() {
            return "Core";
        }
    }
}