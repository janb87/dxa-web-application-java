package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdl.webapp.common.api.localization.*;
import com.sdl.webapp.common.api.mapping.semantic.config.SemanticSchema;
import com.sdl.webapp.common.impl.localization.semantics.JsonSchema;
import com.sdl.webapp.common.impl.localization.semantics.JsonVocabulary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static com.sdl.webapp.common.impl.localization.semantics.SemanticsConverter.convertSemantics;

@Component
@Slf4j
public class UnknownLocalizationHandler implements com.sdl.webapp.common.api.localization.UnknownLocalizationHandler {

    public Localization handleUnknown(Exception exception, ServletRequest request) {
        // "magically" resolve localization or return null to fallback to getFallbackException()
        log.debug("Trying to magically resolve the localization");
        UnknownLocalizationHandler self = this;
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
                return self.getSemanticSchemas();
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
                return new ArrayList(Arrays.asList("json"));
            }
        };
    }

    public LocalizationNotResolvedException getFallbackException(Exception exception, ServletRequest request) {
        return new LocalizationNotResolvedException.WithCustomResponse("Exception with JSON", 200,
                "{\"message\":\"Localization problem + JSON!\"}", "application/json");
    }

    private Map<Long, SemanticSchema> getSemanticSchemas() {
        try {
            List<JsonSchema> schemas = getJsonObject("semantic-schemas/schemas.json", new TypeReference<List<JsonSchema>>() {
            });
            List<JsonVocabulary> vocabularies = getJsonObject("semantic-schemas/vocabularies.json", new TypeReference<List<JsonVocabulary>>() {
            });

            Iterable<SemanticSchema> semanticSchemas = convertSemantics(schemas, vocabularies);
            Map<Long, SemanticSchema> schemasMap = new HashMap();
            for (SemanticSchema semanticSchema : semanticSchemas) {
                schemasMap.put(semanticSchema.getId(), semanticSchema);
            }
            return schemasMap;
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } catch (LocalizationFactoryException localizationFactoryEx) {
           localizationFactoryEx.printStackTrace();
        }
        return null;
    }

    private <T> T getJsonObject(String path, TypeReference<T> resultType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream in = read(path);
        return objectMapper.readValue(in, resultType);
    }

    private InputStream read(String path) {
        return getClass().getClassLoader().getResourceAsStream(path);
    }
}
