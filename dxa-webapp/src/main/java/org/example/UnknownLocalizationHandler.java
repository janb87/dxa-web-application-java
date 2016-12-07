package org.example;

import com.sdl.webapp.common.api.localization.Localization;
import com.sdl.webapp.common.api.localization.LocalizationNotResolvedException;
import com.sdl.webapp.common.api.localization.SiteLocalization;
import com.sdl.webapp.common.api.mapping.semantic.config.SemanticSchema;
import lombok.extern.slf4j.Slf4j;
import org.intellij.lang.annotations.Flow;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.util.*;

@Component
@Slf4j
public class UnknownLocalizationHandler implements com.sdl.webapp.common.api.localization.UnknownLocalizationHandler {

    public Localization handleUnknown(Exception exception, ServletRequest request) {
        // "magically" resolve localization or return null to fallback to getFallbackException()
        log.debug("Trying to magically resolve the localization");
        return new Localization() {
            @Override
            public String getId() {
                return "1176127";
            }

            @Override
            public String getPath() {
                return "/";
            }

            @Override
            public boolean isStaticContent(String url) {
                return false;
            }

            @Override
            public boolean isDefault() {
                return false;
            }

            @Override
            public boolean isStaging() {
                return false;
            }

            @Override
            public String getVersion() {
                return null;
            }

            @Override
            public String getCulture() {
                return null;
            }

            @Override
            public Locale getLocale() {
                return null;
            }

            @Override
            public List<SiteLocalization> getSiteLocalizations() {
                return null;
            }

            @Override
            public String getConfiguration(String key) {
                return "";
            }

            @Override
            public String getResource(String key) {
                return null;
            }

            @Override
            public Map<Long, SemanticSchema> getSemanticSchemas() {
                return null;
            }

            @Override
            public List<String> getIncludes(String pageTypeId) {
                return new ArrayList();
            }

            @Override
            public String localizePath(String url) {
                return null;
            }

            @Override
            public List<String> getDataFormats() {
                return new ArrayList<String>(Arrays.asList("json"));
            }
        };
    }

    public LocalizationNotResolvedException getFallbackException(Exception exception, ServletRequest request) {
        return new LocalizationNotResolvedException.WithCustomResponse("Exception with JSON", 200,
                "{\"message\":\"Localization problem + JSON!\"}", "application/json");
    }
}
