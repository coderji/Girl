package com.ji.tree.app.tencent;

import com.ji.tree.app.local.AppData;
import com.ji.tree.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class TopApps {
    @JsonUtils.FieldName("app")
    private List<App> appList;

    @Override
    public String toString() {
        return "appList:" + appList;
    }

    public static class App {
        @JsonUtils.FieldName("iconurl")
        private String iconUrl;
        @JsonUtils.FieldName("name")
        private String name;
        @JsonUtils.FieldName("pName")
        private String packageName;
        @JsonUtils.FieldName("url")
        private String apkUrl;
        @JsonUtils.FieldName("versionCode")
        private long versionCode;

        @Override
        public String toString() {
            return "iconUrl:" + iconUrl
                    + " name:" + name
                    + " packageName:" + packageName
                    + " apkUrl:" + apkUrl
                    + " versionCode:" + versionCode;
        }
    }

    public List<AppData> getApps() {
        List<AppData> list = new ArrayList<>();
        for (App app : appList) {
            AppData appData = new AppData();
            appData.iconUrl = app.iconUrl;
            appData.name = app.name;
            appData.packageName = app.packageName;
            appData.versionCode = app.versionCode;
            list.add(appData);
        }
        return list;
    }
}
