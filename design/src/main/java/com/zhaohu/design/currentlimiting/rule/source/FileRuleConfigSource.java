package com.zhaohu.design.currentlimiting.rule.source;

import com.zhaohu.design.currentlimiting.rule.UniformRuleConfigMap;
import com.zhaohu.design.currentlimiting.rule.parse.JsonRuleConfigParse;
import com.zhaohu.design.currentlimiting.rule.parse.YamlRuleConfigParse;
import com.zhaohu.design.currentlimiting.rule.parse.RuleConfigParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @creator : zhaohu
 * @date : 6/10/2022
 * @description :
 */
public class FileRuleConfigSource implements RuleConfigSource {

    private static Logger logger = LoggerFactory.getLogger(FileRuleConfigSource.class);

    private static final String RULE_CONFIG_FILENAME = "application";

    private static final Map<String, RuleConfigParse> PARSE_MAP = new HashMap<>();

    private static final String YAML_EXTENSION = "yaml";
    private static final String YML_EXTENSION = "yml";
    private static final String JSON_EXTENSION = "json";

    private static final String[] SUFFIX_EXTENSION = new String[]{YAML_EXTENSION, YML_EXTENSION, JSON_EXTENSION};

    static {
        PARSE_MAP.put("yml", new YamlRuleConfigParse());
        PARSE_MAP.put("yaml", new YamlRuleConfigParse());
        PARSE_MAP.put("json", new JsonRuleConfigParse());
    }

    @Override
    public UniformRuleConfigMap load() {
        for (String extension : SUFFIX_EXTENSION) {
            try (InputStream inputStream = FileRuleConfigSource.class.getClassLoader().getResourceAsStream(getFileName(extension))) {
                if (inputStream != null) {
                    return PARSE_MAP.get(extension).parse(inputStream);
                }
            } catch (IOException exception) {
                logger.error("加载配置文件错误", exception);
                return null;
            }
        }

        return null;
    }

    private String getFileName(String extension) {
        return RULE_CONFIG_FILENAME + extension;
    }
}
