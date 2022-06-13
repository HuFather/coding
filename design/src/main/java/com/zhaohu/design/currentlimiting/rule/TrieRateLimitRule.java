package com.zhaohu.design.currentlimiting.rule;


import com.zhaohu.design.currentlimiting.utils.URLUtils;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.commons.lang3.StringUtils;


import java.util.List;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

/**
 * @creator : zhaohu
 * @date : 6/13/2022
 * @description :
 */
public class TrieRateLimitRule {

    private Node root;

    public TrieRateLimitRule() {
        root = new Node("/");
    }

    public void addApiLimit(ApiLimit apiLimit) {
        String url = apiLimit.getApi();
        if (StringUtils.isEmpty(url) || !url.startsWith("/"))
            throw new IllegalArgumentException("error: api formant not correct.");

        if (url.equals("/")) {
            root.setApiLimit(apiLimit);
            return;
        }

        List<String> paths = URLUtils.tokenizeUrlLimit(apiLimit.getApi());
        if (paths == null || paths.size() == 0)
            return;
        Node p = root;
        for (String path : paths) {
            String addPath = path;
            boolean isPattern = false;
            if (isTemplateVariable(path)) {
                addPath = getPatternPath(path);
                isPattern = true;
            }
            ConcurrentMap<String, Node> children = p.edges;
            Node currentNode = new Node(addPath, isPattern);
            Node oldNode = children.putIfAbsent(addPath, currentNode);
            if (oldNode == null)
                p = currentNode;
            else
                p = oldNode;
        }

        p.setApiLimit(apiLimit);

    }

    public ApiLimit getApiLimit(String url) {
        if (StringUtils.isEmpty(url))
            return null;

        List<String> paths = URLUtils.tokenizeUrlLimit(url);
        if (paths == null || paths.size() <= 0)
            return null;
        if (url.equals("/"))
            return root.apiLimit;
        Node p = root;
        ApiLimit apiLimit = null;
        for (int i = 0; i < paths.size(); i++) {
            String path = paths.get(i);

            Node current = p.getEdges().get(path);
            if (current == null) {
                for (Map.Entry<String, Node> node : p.getEdges().entrySet()) {
                    Node currentNode = node.getValue();
                    if (currentNode.isPattern) {
                        boolean matched = Pattern.matches(current.path, path);
                        if (matched)
                            current = currentNode;
                    }
                }
            }

            if (current != null) {
                p = current;
                if (p.getApiLimit() != null) {
                    apiLimit = p.getApiLimit();
                }
            } else {
                break;
            }
        }


        return apiLimit;
    }

    private boolean isTemplateVariable(String url) {
        return url.startsWith("{") && url.endsWith("}");
    }

    private String getPatternPath(String url) {
        int start = url.indexOf(":");
        StringBuilder stringBuilder = new StringBuilder();
        if (start == -1)
            stringBuilder.append("(^[0-9]*$)");
        else {
            stringBuilder.append("(");
            stringBuilder.append(url.substring(start, url.length() - 1));
            stringBuilder.append(")");
        }

        return stringBuilder.toString();
    }

    public static class Node {
        private boolean isPattern = false;
        private String path;
        private ApiLimit apiLimit;
        private ConcurrentMap<String, Node> edges = new ConcurrentHashMap<>();

        public Node(String path) {
            this.path = path;
        }

        public Node(String path, boolean isPattern) {
            this.path = path;
            this.isPattern = isPattern;
        }

        public boolean isPattern() {
            return isPattern;
        }

        public void setPattern(boolean pattern) {
            isPattern = pattern;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public ApiLimit getApiLimit() {
            return apiLimit;
        }

        public void setApiLimit(ApiLimit apiLimit) {
            this.apiLimit = apiLimit;
        }

        public ConcurrentMap<String, Node> getEdges() {
            return edges;
        }

        public void setEdges(ConcurrentMap<String, Node> edges) {
            this.edges = edges;
        }
    }
}
