package com.example.sbomdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

public class App {

  private static final Logger logger = LogManager.getLogger(App.class);

  public static void main(String[] args) {
    demoLog4j();
    demoCommonsLang3();
    demoCommonsText();
    demoCommonsCollections();
    demoGson();
  }

  private static void demoLog4j() {
    logger.info("log4j-core: application starting up");
  }

  private static void demoCommonsLang3() {
    String result = StringUtils.capitalize("commons-lang3 says hello");
    logger.info("commons-lang3: {}", result);
  }

  private static void demoCommonsText() {
    Map<String, String> values = new HashMap<>();
    values.put("name", "sbom-demo");
    StringSubstitutor substitutor = new StringSubstitutor(values);
    String result = substitutor.replace("Project: ${name}");
    logger.info("commons-text: {}", result);
  }

  private static void demoCommonsCollections() {
    List<String> tags = new ArrayList<>();
    tags.add("sbom");
    tags.add("demo");
    boolean empty = CollectionUtils.isEmpty(tags);
    logger.info("commons-collections: tags={} isEmpty={}", tags, empty);
  }

  private static void demoGson() {
    Map<String, Object> payload = new HashMap<>();
    payload.put("project", "maven-sbom-vuln-demo");
    payload.put("dependencyCount", 5);
    String json = new Gson().toJson(payload);
    logger.info("gson: {}", json);
  }
}
