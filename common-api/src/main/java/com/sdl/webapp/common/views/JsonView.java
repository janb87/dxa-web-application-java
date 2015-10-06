package com.sdl.webapp.common.views;

import com.sdl.webapp.common.api.WebRequestContext;
import com.sdl.webapp.common.api.formatters.DataFormatter;
import com.sdl.webapp.common.api.model.PageModel;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * TODO: TW Document
 */
public class JsonView extends MappingJackson2JsonView {
    private DataFormatter formatter;
    WebRequestContext context;
    public JsonView(WebRequestContext context){
        this.context = context;
        setPrettyPrint(true);

    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // If manipulation of the page is needed it can be performed here
        // PageModel page = (PageModel)model.get("data");
        super.renderMergedOutputModel(model, request, response);
    }
}